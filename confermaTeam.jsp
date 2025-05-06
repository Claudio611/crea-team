<%@page import="it.unirc.nba.model.Giocatore"%>
<%@page import="java.util.Vector"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Conferma Team</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h1>Conferma il Tuo Team</h1>
    <form action="ConfermaTeamServlet" method="post">
        <p><strong>Nome del Team:</strong> ${team.nome}</p>
        <p><strong>Feedback:</strong> ${team.feedbackGenerato}</p>
        <p><strong>Punteggio:</strong> ${team.punteggio}</p>
        
        <h3>Giocatori Selezionati:</h3>
        <ul>
            <%
                // Ottieni la lista dei giocatori dalla richiesta
                Vector<Giocatore> giocatori = (Vector<Giocatore>) request.getAttribute("giocatori");

                // Itera sulla lista di giocatori usando un ciclo for
                for (int i = 0; i < giocatori.size(); i++) {
                    Giocatore giocatore = giocatori.get(i);
            %>
                <li><%= giocatore.getNome() %> (<%= giocatore.getRuolo() %>)</li>
            <%
                }
            %>
        </ul>

        <input type="submit" value="Conferma Team">
    </form>
</body>
</html>
