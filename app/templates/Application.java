package <%=packageName%>;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<% if (eureka) { %>import org.springframework.cloud.client.discovery.EnableDiscoveryClient;<% } %>
<% if (hystrix) {%>import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;<% } %>
<% if (security) {%>
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
<%}%>
<% if (jpa) {%>import org.springframework.data.jpa.repository.config.EnableJpaRepositories;<%}%>

@SpringBootApplication
<% if (eureka) { %>@EnableDiscoveryClient<% } %>
<% if (hystrix) {%>@EnableCircuitBreaker<%}%>
<% if (security) {%>@EnableResourceServer<%}%>
<% if (jpa) {%>@EnableJpaRepositories<%}%>
public class <%=capModelName%>Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
<% if (security) {%>
  @Autowired
  AuthorizationCodeResourceDetails oAuth2ProtectedResourceDetails;
  @Autowired
  OAuth2ClientContext oAuth2ClientContext;

  @LoadBalanced
  @Bean
  public OAuth2RestOperations securerestTemplate() {
    return new OAuth2RestTemplate(oAuth2ProtectedResourceDetails, oAuth2ClientContext);
  }

  @Configuration
  @EnableWebSecurity
  @EnableGlobalMethodSecurity(prePostEnabled = true)
  protected static class SecurityConfig extends ResourceServerConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http
        .authorizeRequests().anyRequest().authenticated()
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
      ;
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//      web.ignoring().antMatchers("/*");
//    }
  }
<%}%>
}
