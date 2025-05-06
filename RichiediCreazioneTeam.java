package it.unirc.nba.servlet.privato.utente;

import it.unirc.nba.model.Sfida;
import it.unirc.nba.dao.SfidaDAO;
import it.unirc.nba.model.Giocatore;
import it.unirc.nba.dao.GiocatoreDAO;
import it.unirc.nba.model.Team;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Vector;

@WebServlet("/privato/utente/richiediCreazioneTeam")
public class RichiediCreazioneTeam extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recupera il parametro idSfida dalla richiesta
        String idSfidaStr = request.getParameter("idSfida");

        // Controlla se il parametro è presente e valido
        if (idSfidaStr == null || idSfidaStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "idSfida mancante o non valido");
            return;
        }

        int idSfida = Integer.parseInt(idSfidaStr);

        // Recupera l'oggetto Sfida dal database
        Sfida sfida = SfidaDAO.getSfidaById(idSfida);
        
        // Se non è trovata la Sfida, restituisci un errore
        if (sfida == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Sfida non trovata");
            return;
        }

        // Recupera l'id utente dalla sessione
        HttpSession session = request.getSession();
        Integer idUtente = (Integer) session.getAttribute("idUtente");

        // Se l'utente non è autenticato, reindirizza a RichiediLogin
        if (idUtente == null) {
            response.sendRedirect("/RichiediLogin");
            return;
        }

        // Crea un nuovo oggetto Team vuoto per passarlo alla JSP
        Team team = new Team();
        team.setIdUtente(idUtente);  // Imposta l'utente per il nuovo team
        team.setIdSfida(idSfida);    // Imposta la sfida per il nuovo team

        // Recupera i giocatori dal database
        GiocatoreDAO giocatoreDAO = new GiocatoreDAO();
        Vector<Giocatore> giocatori = giocatoreDAO.getAll();

        // Controlla se ci sono giocatori disponibili
        if (giocatori != null && !giocatori.isEmpty()) {
            // Imposta gli oggetti come attributi della richiesta
            request.setAttribute("sfida", sfida);
            request.setAttribute("giocatori", giocatori);
            request.setAttribute("team", team);
        } else {
            // Caso in cui non ci sono giocatori
            request.setAttribute("errorMessage", "Nessun giocatore disponibile.");
        }

        // Inoltro alla JSP
        request.getRequestDispatcher("/privato/utente/creaTeam.jsp").forward(request, response);
    }
}