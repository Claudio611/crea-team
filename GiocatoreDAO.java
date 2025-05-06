package it.unirc.nba.dao;

import it.unirc.nba.model.Giocatore;
import it.unirc.nba.utils.DBManager;

import java.sql.*;
import java.util.Vector;

public class GiocatoreDAO {
    private Connection conn;

    // Metodo per trasformare un record del ResultSet in un oggetto Giocatore
    private Giocatore recordToGiocatore(ResultSet rs) throws SQLException {
        Giocatore giocatore = new Giocatore();
        giocatore.setId(rs.getInt("id"));
        giocatore.setNome(rs.getString("nome"));
        giocatore.setRuolo(rs.getString("ruolo"));
        giocatore.setAltezza(rs.getInt("altezza"));
        giocatore.setAnnoEsordio(rs.getInt("annoEsordio"));
        giocatore.setNazionalita(rs.getString("nazionalita"));
        giocatore.setStile(rs.getString("stile"));
        giocatore.setValutazioneGenerale(rs.getInt("valutazioneGenerale"));
        return giocatore;
    }

    // Metodo per salvare un nuovo giocatore
    public boolean salva(Giocatore giocatore) {
        String query = "INSERT INTO giocatore (nome, ruolo, altezza, annoEsordio, nazionalita, stile, valutazioneGenerale) VALUES (?, ?, ?, ?, ?, ?, ?)";
        boolean esito = false;
        conn = DBManager.startConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, giocatore.getNome());
            ps.setString(2, giocatore.getRuolo());
            ps.setInt(3, giocatore.getAltezza());
            ps.setInt(4, giocatore.getAnnoEsordio());
            ps.setString(5, giocatore.getNazionalita());
            ps.setString(6, giocatore.getStile());
            ps.setInt(7, giocatore.getValutazioneGenerale());
            int tmp = ps.executeUpdate();
            if (tmp == 1) {
                esito = true;
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    giocatore.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection();
        }
        return esito;
    }

    // Metodo per eliminare un giocatore
    public boolean elimina(Giocatore giocatore) {
        String query = "DELETE FROM giocatore WHERE id = ?";
        boolean esito = false;
        conn = DBManager.startConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, giocatore.getId());
            int tmp = ps.executeUpdate();
            esito = tmp == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection();
        }
        return esito;
    }

    // Metodo per aggiornare un giocatore
    public boolean modifica(Giocatore giocatore) {
        String query = "UPDATE giocatore SET nome = ?, ruolo = ?, altezza = ?, annoEsordio = ?, nazionalita = ?, stile = ?, valutazioneGenerale = ? WHERE id = ?";
        boolean esito = false;
        conn = DBManager.startConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, giocatore.getNome());
            ps.setString(2, giocatore.getRuolo());
            ps.setInt(3, giocatore.getAltezza());
            ps.setInt(4, giocatore.getAnnoEsordio());
            ps.setString(5, giocatore.getNazionalita());
            ps.setString(6, giocatore.getStile());
            ps.setInt(7, giocatore.getValutazioneGenerale());
            ps.setInt(8, giocatore.getId());
            esito = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection();
        }
        return esito;
    }

    // Metodo per ottenere un giocatore tramite ID
    public Giocatore getGiocatoreById(int id) {
        Giocatore res = null;
        conn = DBManager.startConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM giocatore WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                res = recordToGiocatore(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection();
        }
        return res;
    }

    // Metodo per ottenere tutti i giocatori
    public Vector<Giocatore> getAll() {
        Vector<Giocatore> giocatori = new Vector<>();
        conn = DBManager.startConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM giocatore ORDER BY nome");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                giocatori.add(recordToGiocatore(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection();
        }
        return giocatori;
    }

    // Metodo per ottenere i giocatori di un team
    public Vector<Giocatore> getGiocatoriByTeam(int teamId) {
        Vector<Giocatore> giocatori = new Vector<>();
        String query = "SELECT g.* FROM giocatore g JOIN team_giocatore tg ON g.id = tg.id_giocatore WHERE tg.id_team = ?";
        conn = DBManager.startConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, teamId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                giocatori.add(recordToGiocatore(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection();
        }
        return giocatori;
    }

 // Metodo per ottenere un giocatore in base ai parametri definiti nell'oggetto Giocatore
    public Giocatore get(Giocatore giocatore) {
        Giocatore res = null;
        conn = DBManager.startConnection();
        try {
            // Costruzione della query di base
            String query = "SELECT * FROM giocatore WHERE 1=1";

            // Aggiunta dei criteri di ricerca
            if (giocatore.getId() > 0) {
                query += " AND id = ?";
            } else if (giocatore.getNome() != null && !giocatore.getNome().isEmpty()) {
                query += " AND nome = ?";
            }

            PreparedStatement ps = conn.prepareStatement(query);

            // Assegna i parametri in base ai valori presenti nell'oggetto Giocatore
            if (giocatore.getId() > 0) {
                ps.setInt(1, giocatore.getId());
            } else if (giocatore.getNome() != null && !giocatore.getNome().isEmpty()) {
                ps.setString(1, giocatore.getNome());
            }

            // Esecuzione della query
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                res = recordToGiocatore(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection();
        }
        return res;
    }
}