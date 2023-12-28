package com.shiv.exception.connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class RandomDBOperations {
    public static void main(String[] args) throws Exception {
        DbConnection.getInstance().getConnection().setAutoCommit(false);
        long startTime=System.currentTimeMillis();
        for(int i=1;i<5000;i++){
            PreparedStatement preparedStatement1=DbConnection.getInstance().getConnection().prepareStatement("insert into course(name) values(?);",Statement.RETURN_GENERATED_KEYS);
            preparedStatement1.setString(1,"ECE"+i);
            preparedStatement1.executeUpdate();
            ResultSet resultSet1 = preparedStatement1.getGeneratedKeys();
            if(!resultSet1.next())
                throw new Exception("Error auto gen id for course");

            PreparedStatement preparedStatement= DbConnection.getInstance()
                    .getConnection().prepareStatement("insert into students(name,email,course_id) values (?,?,?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,"Mohan "+i);
            preparedStatement.setString(2,"sh1@"+i);
            preparedStatement.setInt(3,resultSet1.getInt(1));
            preparedStatement.executeUpdate();
            ResultSet resultSet=preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                System.out.println("Id - "+resultSet.getInt(1));
            }
            resultSet1.close();
            resultSet.close();
        }
        DbConnection.getInstance().getConnection().commit();
        long endTime=System.currentTimeMillis();
        System.out.println("Done in "+(endTime-startTime)+" ms");
    }
}
