package org.example.service;

import org.example.pojo.User;
import java.util.List;

/*
查询所有用户信息

 */
public interface UserService {
    public List<User> findAll();
}
