package flm.utenti;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import flm.storage.DriverManagerConnectionPool;

public class UtentiManager {
	private static final String TABLE_UTENTI = "Utente";
	private static final String RUOLO_ADMIN = "A";
	private static final String RUOLO_ALLENATORE = "T";
	private static final String RUOLO_ARBITRO = "R";

	public void salvaAllenatore(Allenatore allenatore) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM " + UtentiManager.TABLE_UTENTI + " WHERE Email = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();			
			preparedStatement = connection.prepareStatement(selectSQL);

			preparedStatement.setString(1, allenatore.getEmail());

			ResultSet rs = preparedStatement.executeQuery();

			if(!rs.next()) {
				if(preparedStatement != null)
					preparedStatement.close();

				String insertSQL = "INSERT INTO " + UtentiManager.TABLE_UTENTI + "(Nome, Cognome, Email, Password, Ruolo) VALUES(?, ?, ?, ?, ?)";
				preparedStatement = connection.prepareStatement(insertSQL);

				preparedStatement.setString(1, allenatore.getNome());
				preparedStatement.setString(2, allenatore.getCognome());
				preparedStatement.setString(3, allenatore.getEmail());
				preparedStatement.setString(4, allenatore.getPassword());
				preparedStatement.setString(5, UtentiManager.RUOLO_ALLENATORE);
				preparedStatement.executeUpdate();

				connection.commit();
			}
		}
		finally {
			if(preparedStatement != null)
				preparedStatement.close();

			DriverManagerConnectionPool.releaseConnection(connection);
		}
	}
	public void salvaArbitro(Arbitro arbitro) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM " + UtentiManager.TABLE_UTENTI + " WHERE Email = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();			
			preparedStatement = connection.prepareStatement(selectSQL);

			preparedStatement.setString(1, arbitro.getEmail());

			ResultSet rs = preparedStatement.executeQuery();
			if(!rs.next()) {
				if(preparedStatement != null)
					preparedStatement.close();

				String insertSQL = "INSERT INTO " + UtentiManager.TABLE_UTENTI + "(Nome, Cognome, Email, Password, Ruolo) VALUES(?, ?, ?, ?, ?)";
				preparedStatement = connection.prepareStatement(insertSQL);

				preparedStatement.setString(1, arbitro.getNome());
				preparedStatement.setString(2, arbitro.getCognome());
				preparedStatement.setString(3, arbitro.getEmail());
				preparedStatement.setString(4, arbitro.getPassword());
				preparedStatement.setString(5, UtentiManager.RUOLO_ARBITRO);
				preparedStatement.executeUpdate();

				connection.commit();
			}
		}
		finally {
			if(preparedStatement != null)
				preparedStatement.close();

			DriverManagerConnectionPool.releaseConnection(connection);
		}

	}

	public Utente autentica(Utente utente) throws SQLException {		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM " + UtentiManager.TABLE_UTENTI + " WHERE Email = ? AND Password = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();			
			preparedStatement = connection.prepareStatement(selectSQL);

			preparedStatement.setString(1, utente.getEmail());
			preparedStatement.setString(2, utente.getPassword());

			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next())
			{
				Utente utenteReg;

				if(rs.getString("Ruolo").equalsIgnoreCase(UtentiManager.RUOLO_ALLENATORE))
					utenteReg = new Allenatore();
				else if(rs.getString("Ruolo").equalsIgnoreCase(UtentiManager.RUOLO_ARBITRO))
					utenteReg = new Arbitro();
				else if(rs.getString("Ruolo").equalsIgnoreCase(UtentiManager.RUOLO_ADMIN))
					utenteReg = new Utente();
				else
					return null;

				utenteReg.setID(rs.getInt("ID_Utente"));
				utenteReg.setNome(rs.getString("Nome"));
				utenteReg.setCognome(rs.getString("Cognome"));
				utenteReg.setEmail(rs.getString("Email"));
				utenteReg.setPassword(null);

				return utenteReg;
			}
			return null;
		}
		finally {
			if(preparedStatement != null)
				preparedStatement.close();

			DriverManagerConnectionPool.releaseConnection(connection);
		}
	}
	
	public Collection<Arbitro> leggiArbitri() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<Arbitro> lista = new LinkedList<Arbitro>();
		
		String selectSQL = "SELECT * FROM " + UtentiManager.TABLE_UTENTI + " WHERE Ruolo = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();			
			preparedStatement = connection.prepareStatement(selectSQL);

			preparedStatement.setString(1, UtentiManager.RUOLO_ARBITRO);

			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				Arbitro arbitro = new Arbitro();
				arbitro.setID(rs.getInt("ID_Utente"));
				arbitro.setNome(rs.getString("Nome"));
				arbitro.setCognome(rs.getString("Cognome"));
				
				lista.add(arbitro);
			}
		}
		finally {
			if(preparedStatement != null)
				preparedStatement.close();

			DriverManagerConnectionPool.releaseConnection(connection);
		}
		
		return lista;
	}
}