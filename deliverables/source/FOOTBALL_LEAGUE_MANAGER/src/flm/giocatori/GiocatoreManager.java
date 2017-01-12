package flm.giocatori;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import flm.storage.DriverManagerConnectionPool;

public class GiocatoreManager {
	private static final String TABLE_GIOCATORI = "Giocatore";

	public void modificaGiocatore(Giocatore giocatore,String nome,String cognome) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String updateSQL = "UPDATE "+ GiocatoreManager.TABLE_GIOCATORI + " SET Nome=?, Cognome=? WHERE ID_Giocatore=?";
		try{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1, nome);
			preparedStatement.setString(2, cognome);
			preparedStatement.setInt(3, giocatore.getID());
			preparedStatement.executeUpdate();
			connection.commit();
		}
		finally{
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			}
			finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	public void creaGiocatore(Giocatore giocatore) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO "+ GiocatoreManager.TABLE_GIOCATORI + "(Nome, Cognome) VALUES(?, ?)";
		try{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, giocatore.getNome());    
			preparedStatement.setString(2, giocatore.getCognome());

			preparedStatement.executeUpdate();
			connection.commit();
		}
		finally{
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			}
			finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	public Collection<Giocatore> leggiGiocatori() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM "+ GiocatoreManager.TABLE_GIOCATORI;

		Collection<Giocatore> lista = new LinkedList<Giocatore>();

		try{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				Giocatore giocatore = new Giocatore();

				giocatore.setID(rs.getInt("ID_Giocatore"));
				giocatore.setNome(rs.getString("Nome"));
				giocatore.setCognome(rs.getString("Cognome"));

				lista.add(giocatore);
			}
		}
		finally{
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			}
			finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		return lista;
	}
}
