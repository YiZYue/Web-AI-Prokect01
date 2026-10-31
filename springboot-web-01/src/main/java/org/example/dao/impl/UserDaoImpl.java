package org.example.dao.impl;

import cn.hutool.core.io.IoUtil;
import org.example.dao.UserDao;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component//将当前类交给IOC容器管理
public class UserDaoImpl implements UserDao {

    @Override
    public List<String> findAll() {
        //1.加载并读取User.txt来获取用户列表
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("user.txt");//利用反射获取文件
        ArrayList<String> lines = IoUtil.readLines(in, StandardCharsets.UTF_8, new ArrayList<>());
        return lines;
    }
}
