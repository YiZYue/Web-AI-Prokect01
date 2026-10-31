package org.example.controller;
import org.example.pojo.User;
import org.example.service.Impl.UserServiceImpl;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController//已经封装好了@ResponseBody ->作用 ：将controller返回值作为响应体数据直接响应;返回值是对象/集合->json格式->响应
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public List<User> list()  {
        //1.调用Service，获取数据
        List<User> userList =userService.findAll();



        //2.返回数据（json格式）
        return userList;
    }
}
