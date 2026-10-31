package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data//声明一个JavaBean类
@NoArgsConstructor//生成一个无参构造方法
@AllArgsConstructor//生成一个全参构造方法
public class User {

    private Integer id;
    private String username;
    private String password;
    private String name;
    private Integer age;
    private LocalDateTime updateTime;


}
