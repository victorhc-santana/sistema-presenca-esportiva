package br.edu.fateczl.presencaesportiva.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
    private static DBUtil instance;
    private Connection connection;

    private DBUtil() throws Exception {
        connection = DriverManager.getConnection(
            "jdbc:mariadb://localhost:3306/presenca_esportiva?allowPublicKeyRetrieval=true&useSSL=false&createDatabaseIfNotExist=true",
            "root", "123456"
        );
    }

    public static DBUtil getInstance() throws Exception {
        if (instance == null) {
            instance = new DBUtil();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
