<%@page import="it.unirc.nba.model.Giocatore"%>
<%@page import="java.util.Vector"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Conferma Team</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="/css/styles.css" rel="stylesheet" />
</head>
<body>
    <h1>Conferma il Tuo Team</h1>
    <p><strong>Nome del Team:</strong> ${team.nome}</p>
    <p><strong>Feedback:</strong> ${team.feedbackGenerato}</p>
    <p><strong>Punteggio:</strong> ${team.punteggio}</p>
    
    <h3>Giocatori Selezionati:</h3>
    <ul>
        <%
            // Ottieni la lista dei giocatori dalla request
            Vector<Giocatore> giocatori = (Vector<Giocatore>) request.getAttribute("giocatori");

            // Itera sulla lista di giocatori usando un ciclo for
            for (Giocatore giocatore : giocatori) {
        %>
            <li><%= giocatore.getNome() %> (<%= giocatore.getRuolo() %>)</li>
        <%
            }
        %>
    </ul>

    <form action="/privato/utente/ConfermaTeamServlet" method="post">
        <input type="hidden" name="idTeam" value="${team.id}">
        <button type="submit">Conferma Team</button>
    </form>
</body>
</html>