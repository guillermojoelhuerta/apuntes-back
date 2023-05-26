package com.apuntes.apuntes.repository;

import com.apuntes.apuntes.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByNombre(String nombre);
}
