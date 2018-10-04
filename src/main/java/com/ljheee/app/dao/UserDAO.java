package com.ljheee.app.dao;

import com.ljheee.app.entity.User;

import java.util.List;

/**
 *
 */
public interface UserDAO {



    Integer addUser(User user);


    List<User> getUserList();

    Integer getUserCount();

    void deleteById(Integer id);
}
