package <%=packageName%>;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<% if (eureka) { %>import org.springframework.cloud.client.discovery.EnableDiscoveryClient;<% } %>
<% if (hystrix) {%>import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;<% } %>
@SpringBootApplication
<% if (eureka) { %>@EnableDiscoveryClient<% } %>
<% if (hystrix) {%>@EnableCircuitBreaker<%}%>
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
