package com.slc.spi;

import java.sql.*;

/**
 * @author slc
 */
public class SpiTest {

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sql_store?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai",
                "root", "root");
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE id > ?");
        preparedStatement.setInt(1, 4);
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.printf("+----+------+------+\n| id | name | age  |\n+----+------+------+\n");
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            int age = resultSet.getInt(3);
            System.out.printf("|  %d | %s   |   %d |\n", id, name, age);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
