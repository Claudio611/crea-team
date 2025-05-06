package it.unirc.nba.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import it.unirc.nba.dao.UtenteDAO;
import it.unirc.nba.model.Utente;

@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ottieni username e password dalla richiesta
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Crea l'oggetto utente
        UtenteDAO utenteDAO = new UtenteDAO();
        Utente utente = utenteDAO.getByUsernamePassword(username, password); // Verifica che l'utente esista

        if (utente != null) {
            // Crea una sessione
            HttpSession session = request.getSession();
            session.setAttribute("utente", utente);  // Salva l'utente nella sessione
            session.setAttribute("idUtente", utente.getId()); // Salva l'ID utente nella sessione

            // Verifica lo status dell'utente e reindirizza
            if (utente.isAdmin()) {
                // Se l'utente è admin, reindirizza all'area admin
                response.sendRedirect("/privato/admin/areaAdmin.jsp");
            } else {
                // Se l'utente non è admin, reindirizza all'area riservata dell'utente
                response.sendRedirect("/privato/utente/areaRiservata.jsp");
            }
        } else {
            // Se l'utente non esiste, reindirizza alla pagina di accesso negato
            response.sendRedirect("/privato/accessoNegato.jsp");
        }
    }
}