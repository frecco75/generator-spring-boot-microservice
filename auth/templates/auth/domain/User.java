package <%=packageName%>.auth.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User implements UserDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @JsonProperty("username")
    @Column(unique = true)
    private String username;
    
    @JsonProperty("firstName")
	private String firstName;
    
    @JsonProperty("lastName")
	private String lastName;
    
   	@JsonProperty("email")
    @Column(unique = true)
	private String email;
   	
   	private String password;
   	
   	@Transient
    private String passwordConfirm;
    
    @JsonProperty("userType")
	private String userType;
    
    @Column(name = "created_on")
	private Date createdOn;

	@Column(name = "last_login")
	private Date lastLogin;

	@Column(name = "reset_token")
	private String resetToken;
   	
    //private byte[] image;
	private String image;
    
    private boolean enabled = true;
	
	private boolean accountNonLocked = true;
    
	public Long getId() {
        return id;
    }

	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    //@OneToMany(cascade=CascadeType.ALL, mappedBy="authority")
    @Transient
	private List<GrantedAuthority> authorities;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public void addAuthority(Authority authority)
	{
		if (authorities == null)
			authorities = new ArrayList<>();
		
		authorities.add(authority);
	}
	
	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}


    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

}
