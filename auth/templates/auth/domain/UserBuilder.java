package <%=packageName%>.auth.domain;

import com.techolution.auth.domain.User;
import com.techolution.auth.domain.UserType;

public class UserBuilder {

	private String username;
	private String email;
	private String password;
	private String name;
	private String level;
	
//	private List<Authority> authorities;
	
	public UserBuilder(String u, String e, String p)
	{
		this.username = u;
		this.email = e;
		this.password = p;
		this.level = UserType.TRAINEE.toString();
	}
	
	public UserBuilder(String u, String e, String p, UserType userType)
	{
		this.username = u;
		this.email = e;
		this.password = p;
		this.level = UserType.TRAINER.toString();
	}
	

	public User build()
	{
		User player = new User();
		player.setUsername(username);
		player.setEmail(email);
		player.setFirstName(name);
		player.setPassword(password);
		player.setUserType(level);
		
		return player;
	}
	
	public UserBuilder adAdmin()
	{
		this.level = UserType.ADMIN.toString();
		return this;
	}
}
