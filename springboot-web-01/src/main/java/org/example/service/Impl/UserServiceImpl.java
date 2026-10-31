package org.example.service.Impl;

import org.example.dao.UserDao;
import org.example.dao.impl.UserDaoImpl;
import org.example.pojo.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Component//将当前类交给IOC容器管理
public class UserServiceImpl implements UserService {
    @Autowired//应用程序运行时会自动查询该类型的Bean对象，并赋值给该成员变量
    private UserDao userDao;


    @Override
    public List<User> findAll() {
        //1.调用Dao获取数据
        List<String> lines = userDao.findAll();

        //2.解析用户列表，将用户信息封装为User对象 ->list对象
        List<User> userList = lines.stream().map(line -> {
            String[] part = line.split(",");
            Integer id = Integer.parseInt(part[0]);
            String username = part[1];
            String password = part[2];
            String name = part[3];
            Integer age = Integer.parseInt(part[4]);
            LocalDateTime updateTime = LocalDateTime.parse(part[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return new User(id, username, password, name, age, updateTime);
        }).toList();
        return userList;
    }
}
