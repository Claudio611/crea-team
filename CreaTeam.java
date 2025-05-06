package it.unirc.nba.servlet.privato.utente;

import it.unirc.nba.dao.TeamDAO;
import it.unirc.nba.dao.TeamGiocatoreDAO;
import it.unirc.nba.model.Team;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/privato/utente/CreaTeam")
public class CreaTeam extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recupero dei dati dal form
        String nomeTeam = request.getParameter("nomeTeam");
        String idSfidaStr = request.getParameter("idSfida");
        String[] giocatoriSelezionati = request.getParameterValues("giocatoriSelezionati");
        String[] ruoliGiocatori = request.getParameterValues("ruoliGiocatori");

        // Controlla se i dati obbligatori sono presenti
        if (nomeTeam == null || idSfidaStr == null || giocatoriSelezionati == null || ruoliGiocatori == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Dati mancanti o incompleti");
            return;
        }

        int idSfida = Integer.parseInt(idSfidaStr);

        // Recupero l'utente dalla sessione
        HttpSession session = request.getSession();
        Integer idUtente = (Integer) session.getAttribute("idUtente");

        if (idUtente == null) {
            response.sendRedirect("/login.jsp");
            return;
        }

        // Crea un oggetto team per verificare se l'utente ha già un team per questa sfida
        TeamDAO teamDAO = new TeamDAO();
        Team teamToCheck = new Team();
        teamToCheck.setIdUtente(idUtente);
        teamToCheck.setIdSfida(idSfida);

        // Verifica se l'utente ha già un team per questa sfida
        Team existingTeam = teamDAO.get(teamToCheck);

        if (existingTeam != null) {
            response.sendRedirect("/privato/utente/teamEsistente.jsp");
            return;
        }

        // Crea il nuovo team
        Team team = new Team();
        team.setIdUtente(idUtente);
        team.setIdSfida(idSfida);
        team.setNome(nomeTeam);
        team.setConfermato(false); // inizialmente non confermato

        // Salva il team nel database
        if (!teamDAO.salva(team)) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante la creazione del team");
            return;
        }

        // Ottieni l'id del team appena creato
        int teamId = team.getId();

        // Aggiungi i giocatori al team
        TeamGiocatoreDAO teamGiocatoreDAO = new TeamGiocatoreDAO();
        for (int i = 0; i < giocatoriSelezionati.length; i++) {
            int idGiocatore = Integer.parseInt(giocatoriSelezionati[i]);
            String ruolo = ruoliGiocatori[i];

            // Aggiungi ogni giocatore al team con il ruolo
            teamGiocatoreDAO.aggiungiGiocatoreAlTeam(teamId, idGiocatore, ruolo);
        }

        // Redirigi alla pagina di conferma del team
        response.sendRedirect("/privato/utente/confermaTeam.jsp");
    }
}
