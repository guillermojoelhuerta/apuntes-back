package com.apuntes.apuntes.service;

import com.apuntes.apuntes.model.User;
import com.apuntes.apuntes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceBean implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public Collection<User> findAll() {
        Collection<User> usuarios = (Collection<User>) userRepository.findAll();
        return usuarios;
    }

    @Override
    public User findOne(Long id) {
        return null;
    }

    @Override
    public User createx(User usuario) {
        /*if(usuario.getUsuario_id() != null){
            return null;
        }*/
        User saveUsuario = userRepository.save(usuario);
        return saveUsuario;
    }

    @Override
    public User update(User usuario) {
        return null;
    }

    @Override
    public void delete(User usuario) {
        userRepository.delete(usuario);
    }
}
