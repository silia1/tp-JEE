package com.example.rendu;
import com.example.rendu.DAOServices;
import com.example.rendu.model.Message;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AccueilServlet", urlPatterns = {"/accueil"})
public class AccueilServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ✅ Récupère les messages publics
        List<Message> messages = DAOServices.getPublicMessages();
        request.setAttribute("messages", messages);

        // ✅ Récupère un message temporaire de session si présent
        HttpSession session = request.getSession(false);
        if (session != null) {
            String msg = (String) session.getAttribute("message");
            if (msg != null) {
                request.setAttribute("message", msg);
                session.removeAttribute("message");
            }
        }

        // ✅ Forward vers la JSP
        request.getRequestDispatcher("/accueil.jsp").forward(request, response);
    }
}
