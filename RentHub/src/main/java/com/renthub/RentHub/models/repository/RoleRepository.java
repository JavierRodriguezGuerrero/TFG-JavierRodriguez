package com.renthub.RentHub.models.repository;

import com.renthub.RentHub.models.entities.Role;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String name);

}
