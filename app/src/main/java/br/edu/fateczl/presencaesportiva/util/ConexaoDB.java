package br.edu.fateczl.presencaesportiva.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {

    private static final String DB_URI = "jdbc:mariadb://localhost:3306/presenca_esportiva?allowPublicKeyRetrieval=true&useSSL=false&createDatabaseIfNotExist=true";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "123456";

    private static Connection instancia = null;

    private ConexaoDB() {}

    public static Connection getConexao() throws SQLException {
        if (instancia == null || instancia.isClosed()) {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                instancia = DriverManager.getConnection(DB_URI, DB_USER, DB_PASS);
                System.out.println("Banco conectado.");
            } catch (ClassNotFoundException e) {
                throw new SQLException("Driver MariaDB não encontrado.", e);
            }
        }
        return instancia;
    }
}
