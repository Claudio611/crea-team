package it.unirc.nba.dao;

import it.unirc.nba.model.Sfida;
import it.unirc.nba.utils.DBManager;

import java.sql.*;
import java.util.Vector;

public class SfidaDAO {
	private static Connection conn = null;
	
		public Sfida get(Sfida sfida) {
			String query = "SELECT * FROM SFIDA WHERE id =?";
			Sfida res = null;
			PreparedStatement ps;
			conn = DBManager.startConnection();
			try {
				ps = conn.prepareStatement(query);
				ps.setInt(1, sfida.getId());
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
                res = recordToSfida(rs);
				}
				} catch (SQLException e) {
				e.printStackTrace();
				}
				DBManager.closeConnection();
			return res;
		}
	
	
	
    private Sfida recordToSfida(ResultSet rs) throws SQLException {
        Sfida sfida = new Sfida();
        sfida.setId(rs.getInt("id"));
        sfida.setTitolo(rs.getString("titolo"));
        sfida.setDescrizione(rs.getString("descrizione"));
        return sfida;
    }

    

    public Vector<Sfida> getAll() {
    	String query = "SELECT * FROM SFIDA order by id";
    	Vector<Sfida> res = new Vector<Sfida>();	
    		
    		PreparedStatement ps;
    		conn = DBManager.startConnection();
    		try {
    			ps = conn.prepareStatement(query);
    			ResultSet rs = ps.executeQuery();
    			while (rs.next()) {
    				Sfida sfida = recordToSfida(rs);
    				res.add(sfida);
    			}
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		DBManager.closeConnection();
    		return res;
    	}

    	public boolean salva(Sfida sfida) {
        String query = "INSERT INTO sfida (titolo, descrizione) VALUES (?, ?)";
        boolean esito = false;
        conn = DBManager.startConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, sfida.getTitolo());
            ps.setString(2, sfida.getDescrizione());
            int tmp = ps.executeUpdate();
            if (tmp == 1) {
                esito = true;
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    sfida.setId(rs.getInt(1));
                    System.out.println("Sfida inserita con ID: " + rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection();
        }
        return esito;
    }

    public boolean modifica(Sfida sfida) {
        String query = "UPDATE sfida SET titolo = ?, descrizione = ? WHERE id = ?";
        boolean esito = false;
        conn = DBManager.startConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, sfida.getTitolo());
            ps.setString(2, sfida.getDescrizione());
            ps.setInt(3, sfida.getId());
            esito = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection();
        }
        return esito;
    }

    public boolean elimina(Sfida sfida) {
        String query = "DELETE FROM sfida WHERE id = ?";
        boolean esito = false;
        conn = DBManager.startConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, sfida.getId());
            esito = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection();
        }
        return esito;
    }



	public Sfida get(int id) {
	    String query = "SELECT * FROM sfida WHERE id = ?";
	    Sfida sfida = null;
	    conn = DBManager.startConnection();
	    try {
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setInt(1, id);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            sfida = new Sfida();
	            sfida.setId(rs.getInt("id"));
	            sfida.setTitolo(rs.getString("titolo"));
	            sfida.setDescrizione(rs.getString("descrizione"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBManager.closeConnection();
	    }
	    return sfida;
	}



	public static Sfida getSfidaById(int idSfida) {
	    String query = "SELECT * FROM sfida WHERE id = ?";
	    Sfida sfida = null;
	    Connection conn = DBManager.startConnection(); // Assicurati che DBManager.startConnection() restituisca una connessione
	    try {
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setInt(1, idSfida);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            sfida = new Sfida();
	            sfida.setId(rs.getInt("id"));
	            sfida.setTitolo(rs.getString("titolo"));
	            sfida.setDescrizione(rs.getString("descrizione"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBManager.closeConnection();
	    }
	    return sfida;
	}

	
}