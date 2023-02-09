package MEA;

import java.sql.*;

public class DBConnection {

    public Connection getConnection() throws SQLException {
        String URL = "jdbc:postgresql://localhost:5432/market";

        return DriverManager.getConnection(URL, "postgres", "0000");
    }

}
