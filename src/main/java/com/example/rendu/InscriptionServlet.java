package com.example.rendu;

import com.example.rendu.DAOServices;
import com.example.rendu.HashUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class InscriptionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String motDePasse = request.getParameter("motDePasse");

        String motDePasseHache = HashUtil.hashPassword(motDePasse);

        boolean success = DAOServices.registerUser(nom, prenom, motDePasseHache);

        if (success) {
            // ✅ Utiliser la session pour transmettre le message à accueil.jsp
            HttpSession session = request.getSession();
            session.setAttribute("message", "✅ Inscription réussie !");
            response.sendRedirect("accueil");
        } else {
            response.getWriter().println("❌ Erreur lors de l'inscription.");
        }
    }
}
