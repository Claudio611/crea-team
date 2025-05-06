package it.unirc.nba.dao;

import it.unirc.nba.model.Giocatore;
import it.unirc.nba.model.Team;
import it.unirc.nba.utils.DBManager;

import java.sql.*;
import java.util.Vector;

public class TeamDAO {
    private Connection conn;

    // Metodo per trasformare un record del ResultSet in un oggetto Team
    private Team recordToTeam(ResultSet rs) throws SQLException {
        Team team = new Team();
        team.setId(rs.getInt("id"));
        team.setIdUtente(rs.getInt("id_utente"));
        team.setIdSfida(rs.getInt("id_sfida"));
        team.setNome(rs.getString("nome"));
        team.setFeedbackGenerato(rs.getString("feedback_generato"));
        team.setVincoliRispettati(rs.getBoolean("vincoli_rispettati"));
        team.setConfermato(rs.getBoolean("confermato"));
        team.setPunteggio(rs.getInt("punteggio"));
        return team;
    }

    // Metodo per salvare un nuovo team
    public boolean salva(Team team) {
        String query = "INSERT INTO team (id_utente, id_sfida, nome, feedback_generato, vincoli_rispettati, confermato, punteggio) VALUES (?, ?, ?, ?, ?, ?, ?)";
        boolean esito = false;
        conn = DBManager.startConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, team.getIdUtente());
            ps.setInt(2, team.getIdSfida());
            ps.setString(3, team.getNome());
            ps.setString(4, team.getFeedbackGenerato());
            ps.setBoolean(5, team.isVincoliRispettati());
            ps.setBoolean(6, team.isConfermato());
            ps.setInt(7, team.getPunteggio());
            int tmp = ps.executeUpdate();
            if (tmp == 1) {
                esito = true;
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    team.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection();
        }
        return esito;
    }

    // Metodo per aggiornare un team
    public boolean modifica(Team team) {
        String query = "UPDATE team SET nome = ?, feedback_generato = ?, vincoli_rispettati = ?, confermato = ?, punteggio = ? WHERE id = ?";
        boolean esito = false;
        conn = DBManager.startConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, team.getNome());
            ps.setString(2, team.getFeedbackGenerato());
            ps.setBoolean(3, team.isVincoliRispettati());
            ps.setBoolean(4, team.isConfermato());
            ps.setInt(5, team.getPunteggio());
            ps.setInt(6, team.getId());
            esito = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection();
        }
        return esito;
    }

    // Metodo per recuperare un team specifico
    public Team get(int id) {
        Team res = null;
        conn = DBManager.startConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM team WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                res = recordToTeam(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection();
        }
        return res;
    }

    // Metodo per recuperare tutti i team
    public Vector<Team> getAll() {
        Vector<Team> res = new Vector<>();
        conn = DBManager.startConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM team ORDER BY id");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(recordToTeam(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection();
        }
        return res;
    }

    // Metodo per recuperare i giocatori di un team
    public Vector<Giocatore> getGiocatoriDelTeam(int teamId) {
        Vector<Giocatore> giocatori = new Vector<>();
        String query = "SELECT g.* FROM giocatore g JOIN team_giocatore tg ON g.id = tg.id_giocatore WHERE tg.id_team = ?";
        conn = DBManager.startConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, teamId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Giocatore giocatore = new Giocatore();
                giocatore.setId(rs.getInt("id"));
                giocatore.setNome(rs.getString("nome"));
                giocatore.setRuolo(rs.getString("ruolo"));
                giocatore.setAltezza(rs.getInt("altezza"));
                giocatore.setAnnoEsordio(rs.getInt("annoEsordio"));
                giocatore.setNazionalita(rs.getString("nazionalita"));
                giocatore.setStile(rs.getString("stile"));
                giocatore.setValutazioneGenerale(rs.getInt("valutazioneGenerale"));
                giocatori.add(giocatore);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection();
        }
        return giocatori;
    }

 // Metodo per recuperare un team basandosi sui parametri forniti
    public Team get(Team team) {
        Team res = null;
        conn = DBManager.startConnection();
        try {
            // Costruzione della query di base
            String query = "SELECT * FROM team WHERE 1=1";

            // Aggiunta dei criteri di ricerca
            if (team.getId() > 0) {
                query += " AND id = ?";
            } else if (team.getNome() != null && !team.getNome().isEmpty()) {
                query += " AND nome = ?";
            } else if (team.getIdUtente() > 0) {
                query += " AND id_utente = ?";
            }

            PreparedStatement ps = conn.prepareStatement(query);

            // Assegna i parametri in base ai valori presenti nell'oggetto Team
            if (team.getId() > 0) {
                ps.setInt(1, team.getId());
            } else if (team.getNome() != null && !team.getNome().isEmpty()) {
                ps.setString(1, team.getNome());
            } else if (team.getIdUtente() > 0) {
                ps.setInt(1, team.getIdUtente());
            }

            // Esecuzione della query
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                res = recordToTeam(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection();
        }
        return res;
    }

    public void generaFeedback(Team team) {
        // Recupera i giocatori associati al team
        Vector<Giocatore> giocatori = getGiocatoriDelTeam(team.getId());

        // Controlla se ci sono giocatori
        if (giocatori == null || giocatori.isEmpty()) {
            team.setFeedbackGenerato("Errore: nessun giocatore selezionato. Per favore, seleziona almeno un giocatore.");
            team.setPunteggio(0);
            modifica(team); // Aggiorna il team con il feedback e il punteggio
            return;
        }

        // Calcola il punteggio totale
        int punteggioTotale = 0;
        for (Giocatore giocatore : giocatori) {
            punteggioTotale += giocatore.getValutazioneGenerale();
        }

        // Normalizza il punteggio su base 100
        int punteggioFinale = Math.min(100, punteggioTotale / giocatori.size());

        // Genera un feedback semplice basato sul punteggio
        String feedback;
        if (punteggioFinale >= 80) {
            feedback = "Il tuo team è molto forte! Ottima selezione.";
        } else if (punteggioFinale >= 50) {
            feedback = "Il tuo team è buono, ma può essere migliorato.";
        } else {
            feedback = "Il tuo team ha bisogno di miglioramenti significativi.";
        }

        // Aggiorna il team con il feedback e il punteggio
        team.setFeedbackGenerato(feedback);
        team.setPunteggio(punteggioFinale);
        modifica(team); // Salva i dati aggiornati nel database
    }
    
}