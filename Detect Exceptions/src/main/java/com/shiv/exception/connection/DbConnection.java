package com.shiv.exception.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnection dbConnection;
    private final Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public static DbConnection getInstance() throws SQLException {
        if(dbConnection==null)
            dbConnection=new DbConnection();
        return dbConnection;
    }

    private DbConnection() throws SQLException {
        connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/temp","def_shiv","def_shiv");
    }
}
