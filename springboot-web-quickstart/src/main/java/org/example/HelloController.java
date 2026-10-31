package org.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//请求处理类
public class HelloController {

   @RequestMapping("/hello")//请求路径
   public String hello(String name){
      System.out.println("name:"+name);
      return "hello "+name+"~";
   }
}
