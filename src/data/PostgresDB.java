package data;

import data.interfaces.IDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDB implements IDB {

    @Override
    public Connection getConnection() {
        String URL = "jdbc:postgresql://localhost:5432/market";
        try {

            return DriverManager.getConnection(URL, "postgres", "0000");
        }
        catch (SQLException e){
            System.out.println("SQLException");
            System.out.println(e.getMessage());
            return null;
        }

    }

}
