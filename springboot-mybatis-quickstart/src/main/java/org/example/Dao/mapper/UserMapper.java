package org.example.Dao.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;
import org.example.pojo.User;

import java.util.List;

@Mapper//应用程序运行时，会自动创建一个实现类对象（代理对象），并且会自动将该实现类对象存入IOC容器中成为Bean类
public interface UserMapper {
    /**
    *
    * 查询所有用户信息
    * */
    //@Select("select * from user")
    public List<User> findAll();

    /**
     * 根据Id删除用户信息
     * */
    @Delete("delete from user where id = #{id}")
    public void deleteById(Integer id);

    /**
     * 根据id新增用户信息
     */
    @Insert("insert into user(username,password,name,age) values(#{username},#{password},#{name},#{age})")
    public void insert(User user);

    /**
     * 根据id更新用户信息
     */
    @Update("update user set username = #{username},password = #{password},name = #{name},age = #{age} where id = #{id}")
    public void update(User user);

    /**
     * 根据用户名和密码查询用户信息
     */
    @Select("select * from user where username = #{username} and password = #{password}")
    public User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
