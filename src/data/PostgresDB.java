package data;

import data.interfaces.IDB;
import java.sql.*;


public class PostgresDB implements IDB {

    public static final Connection getConnection() throws SQLException, ClassNotFoundException {
        String URL = "jdbc:postgresql://localhost:5432/market";
        try {
            // Here we load the driver’s class file into memory at the runtime
            Class.forName("org.postgresql.Driver");

            return DriverManager.getConnection(URL, "postgres", "TheMiko05Khan!");
        }
        catch (SQLException e){
            System.out.println(e);
            return null;
        }

    }

}
