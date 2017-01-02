package flm.utenti;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import flm.storage.DriverManagerConnectionPool;

public class UtentiManager {
	private static final String TABLE_UTENTI = "Utenti";
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
}