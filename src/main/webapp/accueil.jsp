<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Accueil</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f5f7fa;
            margin: 0;
            padding: 40px;
        }

        .container {
            max-width: 900px;
            margin: auto;
            background-color: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        .top-buttons {
            text-align: right;
            margin-bottom: 20px;
        }

        .top-buttons form {
            display: inline-block;
            margin-left: 10px;
        }

        input[type="submit"] {
            background-color: #3498db;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 8px;
            font-size: 14px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #2980b9;
        }

        h1 {
            color: #2c3e50;
            margin-bottom: 20px;
        }

        ul {
            list-style-type: none;
            padding-left: 0;
        }

        li {
            background-color: #ecf0f1;
            padding: 15px;
            margin-bottom: 12px;
            border-left: 5px solid #2ecc71;
            border-radius: 8px;
            box-shadow: 0 0 3px rgba(0,0,0,0.05);
        }

        .success-message {
            color: #27ae60;
            font-weight: bold;
            margin-bottom: 20px;
        }
    </style>
</head>

<body>

<div class="container">
    <div class="top-buttons">
        <form action="connexion.jsp" method="get">
            <input type="submit" value="Connexion" />
        </form>

        <form action="inscription.jsp" method="get">
            <input type="submit" value="S'inscrire" />
        </form>
    </div>

    <c:if test="${not empty message}">
        <p class="success-message">${message}</p>
    </c:if>

    <h1>📢 Messages publics</h1>
    <ul>
        <c:forEach var="msg" items="${messages}">
            <li><strong>${msg.sujet}</strong><br>${msg.contenu}</li>
        </c:forEach>
    </ul>
</div>

</body>
</html>
