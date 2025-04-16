package com.example.rendu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/Messagerie?useSSL=false&serverTimezone=UTC";
    private static final String USER = "silia"; // Remplace par ton user MySQL
    private static final String PASSWORD = "1234"; // Remplace par ton mot de passe

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Erreur chargement du driver JDBC", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            System.out.println("Connexion réussie !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

