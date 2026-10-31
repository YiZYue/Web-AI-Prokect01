package com.surui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


public class UserServiceTest {

//    @BeforeEach // 每个测试方法执行前执行
//    public void testBefore(){
//        System.out.println("before...");
//    }
//
//    @AfterEach // 每个测试方法执行后执行
//    public void testAfter(){
//        System.out.println("after...");
//    }
//
//    @BeforeAll //该方法必须被static修饰, 只执行一次
//    public static void testBeforeAll(){
//        System.out.println("before all ...");
//    }
//
//    @AfterAll //该方法必须被static修饰, 只执行一次
//    public static void testAfterAll(){
//        System.out.println("after all...");
//    }

 //   @Test
//    public void testGetAge(){
//        Integer age = new UserService().getAge("110002200505091218");
//        System.out.println(age);
//    }
//
//    @Test
//    public void testGetGender(){
//        String gender = new UserService().getGender("612429198904201611");
//        System.out.println(gender);
//    }


    @Test
    public void testUserService() {
        Integer age = new UserService().getAge("110002200505091218");
        System.out.println(age);
    }

    @Test
    public void testGender() {
        String gender = new UserService().getGender("110002200505091203");
        System.out.println(gender);
    }

    @Test
    public void testGetAge2() {
        Integer age = new UserService().getAge("110002200505091218");
        Assertions.assertNotEquals(18, age, "两个值相等");
//        String s1 = new String("Hello");
//        String s2 = "Hello";
//        Assertions.assertSame(s1, s2, "不是同一个对象引用");
    }

    @Test
    public void testGetGender2() {
        String gender = new UserService().getGender("612429198904201611");
        Assertions.assertEquals("男", gender);
    }

    @Test
    public void testGetGender3() {
        UserService us = new UserService();
        String gender = us.getGender("110002200505091218");
        Assertions.assertThrows(IllegalArgumentException.class, () -> us.getGender(null));
    }

    @DisplayName("测试getGender方法")
    @ParameterizedTest
    @ValueSource(strings = {"110002200505091218", "612429198904201611", "123456789012345678", "110002200505091218"})
    public void testGetGender4(String idCard) {
        UserService us = new UserService();
        Assertions.assertEquals("男", us.getGender(idCard));
    }
}
