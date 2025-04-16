package com.example.rendu;


import com.example.rendu.DAOServices;
import com.example.rendu.model.Message;
import com.example.rendu.model.Personne;
import com.example.rendu.HashUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ConnexionServlet", value = "/connexion")
public class ConnexionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String motDePasse = request.getParameter("motDePasse");

        String motDePasseHache = HashUtil.hashPassword(motDePasse);

        // Recherche de l'utilisateur dans la base
        Personne user = DAOServices.getUser(nom, prenom, motDePasseHache);

        if (user != null) {
            // ✅ Connexion réussie → enregistrer utilisateur en session
            HttpSession session = request.getSession();
            session.setAttribute("utilisateur", user);

            List<Message> messages = DAOServices.getUserMessages(user.getIdPersonne());
            request.setAttribute("messages", messages);
            request.getRequestDispatcher("/compte.jsp").forward(request, response);

        } else {
            // ❌ Connexion échouée → renvoyer vers la page avec un message
            request.setAttribute("erreur", "Nom, prénom ou mot de passe incorrect.");
            request.getRequestDispatcher("/connexion.jsp").forward(request, response);
        }
    }
}
