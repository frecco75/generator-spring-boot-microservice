package <%=packageName%>;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
<%if (jdbc) { %>}
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
<%}%>
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.techolution.auth.service.UserService;

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

	private static final Logger log = LoggerFactory.getLogger(AuthServerApplication.class);

	@Autowired
	private UserService userServices;

	public static void main(String[] args) {
		SpringApplication.run(AuthServerApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

	@Configuration
	@EnableWebSecurity
	@EnableGlobalMethodSecurity(prePostEnabled = true)
	protected static class SecurityConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		private UserService userServices;

		@Autowired
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth
					.userDetailsService(userServices)
					.passwordEncoder(passwordEncoder());

		}

		@Bean
		public BCryptPasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}


        /*@Override
        @Autowired // <-- This is crucial otherwise Spring Boot creates its own
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            log.info("Defining inMemoryAuthentication (3 users)");
            auth
                    .inMemoryAuthentication()
                    .withUser("TRAINEE").password("password")
                    .roles("TRAINEE")
                    .and()
                    .withUser("TRAINER").password("password")
                    .roles("TRAINER")
		            .and()
		            .withUser("ADMIN").password("password")
		            .roles("TRAINER", "ADMIN");
        }*/


		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
					.formLogin()

					.and()

					.httpBasic().disable()
					.anonymous().disable()
					.authorizeRequests().anyRequest().authenticated();
		}

		@Override
		public void configure(WebSecurity web) throws Exception {
			//web.ignoring().antMatchers(new String[] {"/**"});
		}

		@Override
		@Bean
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}


	}


	@Configuration
	@EnableAuthorizationServer
	protected static class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

		@Value("${config.oauth2.privateKey}")
		private String privateKey;

		@Value("${config.oauth2.publicKey}")
		private String publicKey;

		@Autowired
		private AuthenticationManager authenticationManager;

		@Value("${config.oauth2.ui-uri}")
		private String ui;


		@Bean
		public JwtAccessTokenConverter tokenEnhancer() {
			log.info("Initializing JWT with public key:\n" + publicKey);
			JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
			converter.setSigningKey(privateKey);
			converter.setVerifierKey(publicKey);
			return converter;
		}

		@Bean
		public JwtTokenStore tokenStore() {
			return new JwtTokenStore(tokenEnhancer());
		}

		/**
		 * Defines the security constraints on the token endpoints /oauth/token_key and /oauth/check_token
		 * Client credentials are required to access the endpoints
		 *
		 * @param oauthServer
		 * @throws Exception
		 */
		@Override
		public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
			oauthServer
					.tokenKeyAccess("hasAuthority('ROLE_TRUSTED_CLIENT')")
					.checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");
		}

		/**
		 * Defines the authorization and token endpoints and the token services
		 *
		 * @param endpoints
		 * @throws Exception
		 */
		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints

					// Which authenticationManager should be used for the password grant
					// If not provided, ResourceOwnerPasswordTokenGranter is not configured
					.authenticationManager(authenticationManager)
					// Use JwtTokenStore and our jwtAccessTokenConverter
					.tokenStore(tokenStore())
					.accessTokenConverter(tokenEnhancer())
			;
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients.inMemory()

					.withClient("ui")
					.authorizedGrantTypes("client_credentials", "password", "refresh_token","authorization_code", "implicit")
					.authorities(Role.ROLE_TRUSTED_CLIENT.toString())
					.scopes("read", "write")
					.accessTokenValiditySeconds(10)
					.refreshTokenValiditySeconds(30000)
					.secret("secret")
					.autoApprove(true);

		}

	} 

	@Override
	public void run(String... arg0) throws Exception {

		//create default users here

	}
}