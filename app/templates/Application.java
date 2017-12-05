package <%=packageName%>;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<% if (eureka) { %>import org.springframework.cloud.client.discovery.EnableDiscoveryClient;<% } %>
<% if (zuul) {%>import org.springframework.cloud.netflix.zuul.EnableZuulProxy;<%}%>
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
<% if (zuul) {%>@EnableZuulProxy<%}%>
public class <%=capModelName%>Application {

    public static void main(String[] args) {
        SpringApplication.run(<%=capModelName%>Application.class, args);
    }

    //  @Autowired
	// @LoadBalanced
	// private OAuth2RestOperations secureRestTemplate;
}
