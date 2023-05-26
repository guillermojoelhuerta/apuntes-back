
package com.apuntes.apuntes.service;

import com.apuntes.apuntes.model.User;

import java.util.Collection;

public interface UserService {
    Collection<User> findAll();
    User findOne(Long id);
    User createx(User usuario);
    User update(User usuario);
    void delete(User usuario);
}