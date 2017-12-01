package <%=packageName%>.auth.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techolution.auth.exception.UserErrorMessages;
import com.techolution.auth.exception.UserException;
import com.techolution.auth.repository.UserRespository;
import com.techolution.auth.domain.User;
import com.techolution.auth.domain.UserType;

@Service
public class UserService implements UserDetailsService {
	
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRespository userRespository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		/*User user = userRespository.findByUsername(username);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);*/
		User user = userRespository.findByUsername(username);
		if (user == null) 
			return null;
		List<GrantedAuthority> auth = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_TRAINEE");
		if (user.getUserType().equals(UserType.ADMIN.toString())) {
			auth = AuthorityUtils.createAuthorityList("ROLE_TRAINER", "ROLE_ADMIN");
		}
		if (user.getUserType().equals(UserType.TRAINER.toString())) {
			auth = AuthorityUtils.createAuthorityList("ROLE_TRAINER");
		}	
		user.setAuthorities(auth);
		return user;
	}
	
	
	public User createuser(User user)
	{
		//validatePlayer(player);
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		userRespository.save(user);
		
		return user;
	}
	
	public User createUser(User user) {
        if (userRespository.findByUsername(user.getUsername()) == null) {
        	    return userRespository.save(user);
        } else
        //	logger.error("Unable to create. A User with name {} already exist", user.getName());
        //return new ResponseEntity(new CustomErrorType("Unable to create. A User with name " + 
        //user.getName() + " already exist."),HttpStatus.CONFLICT);
        throw new UserException(HttpStatus.BAD_REQUEST.value(), UserErrorMessages.DUPLICATE_USER.name());
    }
	
	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}


	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}


	public User getUserByUserId(long id) {		
		return userRespository.findById(id);
	}


	public void updateUser(User currentUser) {
		createUser(currentUser);
	}
}
