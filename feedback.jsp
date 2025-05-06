<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="it.unirc.nba.model.Team" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Feedback Team</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="/css/styles.css" rel="stylesheet" />
    <style>
        body {
            background-color: #222;
            color: white;
        }
        .feedback-container {
            max-width: 800px;
            margin: 50px auto;
            padding: 30px;
            border: 2px solid #333;
            border-radius: 10px;
            background-color: #333;
            text-align: center;
        }
        .feedback-container h2 {
            color: orange;
        }
        .feedback-container p {
            font-size: 1.2rem;
        }
        .btn-action {
            background-color: orange;
            border: none;
            padding: 10px 20px;
            color: white;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            margin-top: 20px;
        }
        .btn-action:hover {
            background-color: #ff9800;
        }
    </style>
</head>
<body>

<%@ include file="frame_nav.html" %>

<%
    Team team = (Team) request.getAttribute("team");
    if (team == null) {
        response.sendRedirect("/privato/utente/errore.jsp");
        return;
    }
%>

<div class="feedback-container">
    <h2>Feedback Team</h2>

    <p><strong>Nome del Team:</strong> <%= team.getNome() %></p>
    <p><strong>Sfida:</strong> <%= team.getIdSfida() %></p>
    <p><strong>Punteggio:</strong> <%= team.getPunteggio() %> / 100</p>

    <h3>Messaggio Generato:</h3>
    <p><%= team.getFeedbackGenerato() %></p>

    <a href="/privato/utente/areaRiservata.jsp" class="btn btn-action">Torna all'Area Riservata</a>
</div>

</body>
</html>