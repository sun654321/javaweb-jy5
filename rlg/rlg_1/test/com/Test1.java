package com;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Test1 {

   @Test
 public  void test2()throws Exception {
       ComboPooledDataSource co=new ComboPooledDataSource();
       Connection cn= co.getConnection();
       String sql="select * from users";
       PreparedStatement preparedStatement=cn.prepareStatement(sql);
      ResultSet resultSet =preparedStatement.executeQuery();
       while(resultSet.next()){
         System.out.println(resultSet.getString(2));
     }


       }


   }


