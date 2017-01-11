package flm.giocatori;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import flm.storage.DriverManagerConnectionPool;

public class GiocatoreManager {
	private static final String TABLE_GIOCATORI = "Giocatore";	
	public void modificaGiocatore(Giocatore giocatore,String nome,String cognome) throws SQLException{
		Connection connection=null;
		PreparedStatement preparedStatement = null;
		String updateSQL = "UPDATE "+ GiocatoreManager.TABLE_GIOCATORI + " SET Nome=?, Cognome=? WHERE Nome=? AND Cognome=?";
		try{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
		    preparedStatement.setString(1, nome);
		    preparedStatement.setString(2, cognome);
		    preparedStatement.setString(3, giocatore.getNome());    
		    preparedStatement.setString(4, giocatore.getCognome());
		    preparedStatement.executeUpdate();
		    connection.commit();
		}finally{
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} finally {
			 DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	
	public void creaGiocatore(Giocatore giocatore) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO "+ GiocatoreManager.TABLE_GIOCATORI + "(Nome, Cognome) VALUES(?, ?)";
		try{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
		    preparedStatement.setString(1, giocatore.getNome());    
		    preparedStatement.setString(2, giocatore.getCognome());
		    
		    preparedStatement.executeUpdate();
		    connection.commit();
		}finally{
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} finally {
			 DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
}
