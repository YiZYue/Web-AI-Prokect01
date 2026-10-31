package org.example;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class JdbcTest {

    /*
    *
    *
    *JDBC入门程序
    *
    * */
    @Test
    public void testUpdate() throws ClassNotFoundException, SQLException {
        //1.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        //2.获取连接
        String url = "JDBC:mysql://localhost:3306/web01";
        String username = "root";
        String password = "Su290219";
        Connection connection = DriverManager.getConnection(url, username, password);
        
        //3.获取Statement对象
        Statement statement = connection.createStatement();
        //4.执行SQL语句
        int count = statement.executeUpdate("update user set age = 25 where id = 1");
        System.out.println("SQL执行完毕影响所记录的为："+count);
        //5.释放资源
        statement.close();
        connection.close();
    }

    @Test
    public void testSelect(){
        String URL = "jdbc:mysql://localhost:3306/web01";
        String USER = "root";
        String PASSWORD = "Su290219";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null; //封装查询返回的结果

        try {
            // 1. 注册 JDBC 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. 打开链接
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // 3. 执行查询
            String sql = "SELECT id, username, password, name, age FROM user WHERE username = ? AND password = ?"; //预编译SQL
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, "daqiao");
            stmt.setString(2, "123456");

            rs = stmt.executeQuery();

            // 4. 处理结果集
            while (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getInt("age")
                );
                System.out.println(user); // 使用 Lombok 的 @Data 自动生成的 toString 方法
            }
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // 5. 关闭资源
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

}


