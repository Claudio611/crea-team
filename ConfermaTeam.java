package it.unirc.nba.servlet.privato.utente;

import it.unirc.nba.dao.TeamDAO;
import it.unirc.nba.model.Giocatore;
import it.unirc.nba.model.Team;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Vector;

@WebServlet("/privato/utente/ConfermaTeamServlet")
public class ConfermaTeam extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recupero i dati dalla sessione
        HttpSession session = request.getSession();
        Integer idUtente = (Integer) session.getAttribute("idUtente");
        int idTeam = Integer.parseInt(request.getParameter("idTeam"));
        
        // Se non esiste una sessione utente valida, reindirizzo al login
        if (idUtente == null) {
            response.sendRedirect("/login.jsp");
            return;
        }
        
        // Ottieni il team dal DB
        TeamDAO teamDAO = new TeamDAO();
        Team team = teamDAO.get(idTeam);
        
        // Verifica che il team appartenga all'utente
        if (team == null || team.getIdUtente() != idUtente) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Non puoi confermare un team che non ti appartiene");
            return;
        }

        // Recupera i giocatori del team
        Vector<Giocatore> giocatori = teamDAO.getGiocatoriDelTeam(team.getId());

        // Imposta il team e i giocatori come attributi della request
        request.setAttribute("team", team);
        request.setAttribute("giocatori", giocatori);

        // Invia alla JSP di conferma
        request.getRequestDispatcher("/privato/utente/confermaTeam.jsp").forward(request, response);
    }
}
