<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Area Riservata Utente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="/css/styles.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Lora:wght@400;700&family=Playfair+Display&display=swap" rel="stylesheet">
</head>

<body class="user-area-page">

<%
    // Recupera l'oggetto Utente dalla sessione
    it.unirc.nba.model.Utente utente = (it.unirc.nba.model.Utente) session.getAttribute("utente");

    // Recupera l'username dall'oggetto Utente
    String username = null;
    if (utente != null) {
        username = utente.getUsername();
    } else {
        // Gestisci il caso in cui l'utente non sia autenticato
        username = "Ospite";
    }
%>

<!-- Navbar -->
<%@ include file="frame_nav.html" %>

<br><br>

<section class="welcome-section">
    <!-- Messaggio dinamico che mostra l'username -->
    <h1 class="section-header">Ciao <%= username %>!</h1>
    <p>Benvenuto nella tua area riservata! Qui potrai creare il tuo Dream Team NBA e affrontare le sfide settimanali! 🏀</p>

    <p>Ogni settimana nuove sfide ti aspettano. Seleziona il tuo team di 5 giocatori e prova a vincere! 🏆</p>

    <!-- Pulsante per partecipare alle sfide -->
    <form action="/privato/utente/VisualizzaSfideUtente" method="get">
        <button class="btn btn-primary" type="submit">
            <i class="fas fa-users"></i> Partecipa alle sfide
        </button>
    </form>

    <br>

    <!-- Nuova sezione per la gestione del Dream Team -->
    <h2>Gestisci il tuo Dream Team</h2>
    <p>Vuoi aggiornare il tuo Dream Team? Modifica la tua selezione di giocatori e prepara la squadra perfetta per le prossime sfide!</p>
    <form action="/privato/utente/GestisciDreamTeam" method="get">
        <button class="btn btn-primary" type="submit">
            <i class="fas fa-basketball-ball"></i> Gestisci Dream Team
        </button>
    </form>

    <br>

    <h2>Come funziona?</h2>
    <p>Il sito ti permette di partecipare a sfide settimanali, 
    scegliendo un Dream Team di 5 giocatori, uno per ogni ruolo (PG, SG, SF, PF, C). 
    Ogni sfida ha temi specifici, crea il team migliore!
    </p>

    <div class="motivational-quote">
        "Il successo è il risultato della preparazione, del lavoro duro e dell'apprendimento dai fallimenti." – Kobe Bryant
    </div>
</section>

<!-- Footer -->
<%@ include file="frame_footer.html" %>

</body>
</html>