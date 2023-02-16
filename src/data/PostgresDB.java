package data;

import data.interfaces.IDB;
import java.sql.*;


public class PostgresDB implements IDB {

    public Connection getConnection() {
        String URL = "jdbc:postgresql://localhost:8888/market";
        try {
            return DriverManager.getConnection(URL, "postgres", "TheMiko05Khan!");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }

    }

}
