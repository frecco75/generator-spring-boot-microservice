package <%=packageName%>.service;

import com.techolution.model.<%=capModelName%>;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by tdelesio on 11/21/17.
 */
@RunWith(SpringRunner.class)
public class <%=capModelName%>ServiceTest {

    @TestConfiguration
    static class <%=capModelName%>TestContextConfiguration {

        @Bean
        public <%=capModelName%>Service <%=modelName%>Service() {
            return new <%=capModelName%>Service();
        }
    }

    @Autowired
    private <%=capModelName%>Service employeeService;

//    @MockBean
//    private <%=capModelName%>Repository <%=modelName%>Repository;

    @Before
    public void setUp() {
        
    }

    
}
