package com.asil.repositories;

import com.asil.models.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IRoleRepository extends CrudRepository<Role,Long> {

    Optional<Role> findByName(String nombre);
}
