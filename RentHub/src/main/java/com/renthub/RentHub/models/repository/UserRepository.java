package com.renthub.RentHub.models.repository;

import com.renthub.RentHub.models.entities.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

   

    public boolean existsByUsername(String username);
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    


    public Optional<User> findByUsername(String username);

}
