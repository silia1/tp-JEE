package com.example.rendu;

import com.example.rendu.DAOUtil;
import com.example.rendu.DAOServices;
import com.example.rendu.model.Message;
import com.example.rendu.model.Personne;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

@WebServlet(name = "AjoutMessageServlet", value = "/ajout-message")
public class AjoutMessageServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Personne utilisateur = session != null ? (Personne) session.getAttribute("utilisateur") : null;

        if (utilisateur == null) {
            response.sendRedirect("connexion.jsp");
            return;
        }

        String sujet = request.getParameter("sujet");
        String contenu = request.getParameter("contenu");

        try (Connection conn = DAOUtil.getConnection()) {
            String sql = "INSERT INTO Message (idPersonne, sujet, contenu) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, utilisateur.getIdPersonne());
            ps.setString(2, sujet);
            ps.setString(3, contenu);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("❌ Erreur lors de l'ajout du message.");
            return;
        }
        List<Message> messages = DAOServices.getUserMessages(utilisateur.getIdPersonne());
        request.setAttribute("messages", messages);
        request.getRequestDispatcher("/compte.jsp").forward(request, response);
    }
}

