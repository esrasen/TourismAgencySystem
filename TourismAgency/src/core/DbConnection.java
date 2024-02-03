package core;

import java.sql.Connection;
import java.sql.SQLException;
public class DbConnection {
    private static DbConnection instance = null;
    private Connection connection = null;
    private final String DB_URL = "jdbc:postgresql://localhost:5432/tourismagency";
    private final String DB_USERNAME = "postgres";
    private final String DB_PASS = "postgres";


    //Değerlendirme formu 6
    private DbConnection() {
        try {
            connection = java.sql.DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASS);
        } catch (SQLException e) {
            System.out.println("Veritabanına bağlanırken bir hata oluştu: " + e.getMessage());
        }
    }
    public Connection getConnection() {
        return connection;
    }
    public static Connection getInstance() {
        try {
            if (instance == null || instance.getConnection().isClosed()) {
                instance = new DbConnection();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return instance.getConnection();
    }
}
