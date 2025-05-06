package it.unirc.nba.servlet.privato.utente;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Vector;

import it.unirc.nba.dao.SfidaDAO;
import it.unirc.nba.model.Sfida;

@WebServlet("/privato/utente/VisualizzaSfideUtente")
public class VisualizzaSfideUtente extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public VisualizzaSfideUtente() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Controllo sessione
        if (request.getSession().getAttribute("idUtente") == null) {
            response.sendRedirect("/login.jsp");
            return;
        }

        // Recupera tutte le sfide
        SfidaDAO sfidaDAO = new SfidaDAO();
        Vector<Sfida> sfide = sfidaDAO.getAll();

        // Imposta le sfide come attributo della richiesta
        request.setAttribute("sfide", sfide);

        // Inoltro alla view
        request.getRequestDispatcher("sfideUtente.jsp").forward(request, response);
    }
}