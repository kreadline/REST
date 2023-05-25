package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    RoleRepository roleRepository;


    @Autowired
    public void setUserAndRoleRepository(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User showUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(new User());
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        if (user.getRoleName().equals("ROLE_ADMIN")) {
            user.addRole(roleRepository.findByName(user.getRoleName()));
            user.addRole(roleRepository.findByName("ROLE_USER"));
        } else {
            user.addRole(roleRepository.findByName(user.getRoleName()));
        }
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateUserById(Long id, User user) {
        User updatedUser = showUserById(id);
        updatedUser.setName(user.getName());
        updatedUser.setLastName(user.getLastName());
        userRepository.save(updatedUser);
    }

}
