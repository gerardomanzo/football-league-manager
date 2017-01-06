package flm.utenti;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
				if(rs.getString("Ruolo").equalsIgnoreCase(UtentiManager.RUOLO_ALLENATORE)) {
					Allenatore allenatore = new Allenatore();
					allenatore.setNome(rs.getString("Nome"));
					allenatore.setCognome(rs.getString("Cognome"));
					allenatore.setEmail(utente.getEmail());

					return allenatore;
				}
				else if(rs.getString("Ruolo").equalsIgnoreCase(UtentiManager.RUOLO_ARBITRO)) {
					Arbitro arbitro = new Arbitro();
					arbitro.setNome(rs.getString("Nome"));
					arbitro.setCognome(rs.getString("Cognome"));
					arbitro.setEmail(utente.getEmail());

					return arbitro;
				}
				else if(rs.getString("Ruolo").equalsIgnoreCase(UtentiManager.RUOLO_ADMIN)) {
					utente.setNome(rs.getString("Nome"));
					utente.setCognome(rs.getString("Cognome"));
					utente.setPassword(null);

					return utente;
				}

			return null;
		}
		finally {
			if(preparedStatement != null)
				preparedStatement.close();

			DriverManagerConnectionPool.releaseConnection(connection);
		}
	}
}