package ru.kata.spring.boot_security.demo.services;


import org.springframework.ui.Model;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    void removeUserById(Long id);

    void updateUserById(Long id, User user, Model model);

    User showById(Long id);

    List<User> getAllUsers();

    User findByUsername(String username);

    List<Role> findAllRoles();
}
