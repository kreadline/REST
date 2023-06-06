package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;
import java.util.NoSuchElementException;

public interface RoleDao {

    List<Role> findAll();

    Role findRoleByAuthority(String authority) throws NoSuchElementException;

}
