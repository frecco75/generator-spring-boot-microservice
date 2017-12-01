package <%=packageName%>;

<%if (jdbc) { %>
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
<%}%>
import com.techolution.auth.domain.User;
import com.techolution.auth.domain.UserBuilder;
import com.techolution.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
//@EnableDiscoveryClient
<%if (jdbc) { %>}
@EnableJpaRepositories
<%}%>
public class AuthServerApplication extends WebMvcConfigurerAdapter implements CommandLineRunner {

	public enum Role {
		ROLE_USER,
		ROLE_ADMIN,
		ROLE_REGISTER,
		ROLE_TRUSTED_CLIENT,
		ROLE_CLIENT
	}

	@Autowired
	private UserService userServices;

	public static void main(String[] args) {
		SpringApplication.run(AuthServerApplication.class, args);
	}

	

	@Override
	public void run(String... arg0) throws Exception {

		//create default users here
		//User user = new UserBuilder("admin", "admin@techoltuion.com", "password").adAdmin().build();
		//userService.createuser(user);

	}
}