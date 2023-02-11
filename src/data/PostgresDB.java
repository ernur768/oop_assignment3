package data;

import data.interfaces.IDB;
import java.sql.*;


public class PostgresDB implements IDB {

    public Connection getConnection() {
        String URL = "jdbc:postgresql://localhost:5432/market";
        try {
            return DriverManager.getConnection(URL, "postgres", "0000");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }

    }

}
