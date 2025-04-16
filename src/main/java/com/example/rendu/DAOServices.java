package com.example.rendu;

import com.example.rendu.model.Message;
import com.example.rendu.model.Personne;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOServices {

    public static List<Message> getPublicMessages() {
        List<Message> messages = new ArrayList<>();
        try (Connection conn = DAOUtil.getConnection()) {
            String sql = "SELECT * FROM Message WHERE idPersonne = 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Message msg = new Message(
                        rs.getInt("idMessage"),
                        rs.getInt("idPersonne"),
                        rs.getString("sujet"),
                        rs.getString("contenu")
                );
                messages.add(msg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    public static boolean registerUser(String nom, String prenom, String motDePasseHache) {
        String sql = "INSERT INTO Personne (nom, prenom, motDePasse) VALUES (?, ?, ?)";

        try (Connection conn = DAOUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setString(3, motDePasseHache);

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static Personne getUser(String nom, String prenom, String motDePasseHache) {
        String sql = "SELECT * FROM Personne WHERE nom = ? AND prenom = ? AND motDePasse = ?";

        try (Connection conn = DAOUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setString(3, motDePasseHache);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Personne(
                        rs.getInt("idPersonne"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("motDePasse")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Message> getUserMessages(int idPersonne) {
        List<Message> messages = new ArrayList<>();

        try (Connection conn = DAOUtil.getConnection()) {
            String sql = "SELECT * FROM Message WHERE idPersonne = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idPersonne);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Message msg = new Message(
                        rs.getInt("idMessage"),
                        rs.getInt("idPersonne"),
                        rs.getString("sujet"),
                        rs.getString("contenu")
                );
                messages.add(msg);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }



}
