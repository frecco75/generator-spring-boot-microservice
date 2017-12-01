package <%=packageName%>.auth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.techolution.domain.user.User;

@Repository
public interface UserRespository extends CrudRepository<User, String> {

    User findByUsername(String userName);
    
    User findById(Long id);
    
   
}
