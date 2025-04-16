<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    com.example.rendu.model.Personne utilisateur = (com.example.rendu.model.Personne) session.getAttribute("utilisateur");
%>

<html>
<head>
    <title>Mon compte</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f5f7fa;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }

        .main-content {
            flex: 1;
            display: flex;
            max-width: 1200px;
            margin: 40px auto;
            gap: 20px;
        }

        .form-container, .messages-box {
            flex: 1;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        h1, h2 {
            color: #2c3e50;
        }

        label {
            font-weight: bold;
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"],
        textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 8px;
            font-size: 1em;
        }

        input[type="submit"] {
            background-color: #3498db;
            color: white;
            padding: 10px 25px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-weight: bold;
        }

        input[type="submit"]:hover {
            background-color: #2980b9;
        }

        .messages-box ul {
            list-style: none;
            padding: 0;
        }

        .messages-box li {
            background: #f0f4fa;
            border-radius: 8px;
            padding: 12px;
            margin-bottom: 10px;
            box-shadow: 0 0 5px rgba(0,0,0,0.05);
        }

        .footer {
            text-align: center;
            padding: 20px;
        }
    </style>
</head>
<body>

<h1 style="text-align: center; margin-top: 30px;">Bienvenue, <%= utilisateur.getPrenom() %> 👋</h1>

<div class="main-content">

    <div class="form-container">
        <h2>📝 Ajouter un nouveau message</h2>

        <!-- Formulaire pour envoyer un message -->
        <form action="ajout-message" method="post">
            <label for="sujet">Sujet :</label>
            <input type="text" id="sujet" name="sujet" required>

            <label for="contenu">Contenu :</label>
            <textarea id="contenu" name="contenu" rows="5" required></textarea>

            <input type="submit" value="Envoyer le message">
        </form>

        <!-- Formulaire pour déconnexion (séparé mais dans la même boîte) -->
        <form action="deconnexion" method="post" style="margin-top: 20px;">
            <input type="submit" value="Déconnexion">
        </form>
    </div>


    <!-- 📬 Messages à droite -->
    <div class="messages-box">
        <h2>📬 Vos messages personnels</h2>
        <ul>
            <c:forEach var="msg" items="${messages}">
                <li>
                    <strong>${msg.sujet}</strong><br>
                        ${msg.contenu}
                </li>
            </c:forEach>
        </ul>
    </div>

</div>

</body>
</html>
