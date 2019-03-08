package com.sample;

import java.sql.Connection;

import org.h2.util.JdbcUtils;

public class App 
{
    public static void main(String[] args) throws Exception {
        Connection connection = JdbcUtils.getConnection("org.h2.Driver", "jdbc:h2:~/test", "sa", "");
        System.out.println(connection.toString());
    }

}
