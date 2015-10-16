package kz.hts.ce.service;

import kz.hts.ce.entity.User;

import java.util.List;

public interface userService {

    void insert(User user);

    List<User> findAll();

    User findByUsername(String username);

}
