package flm.campionati;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import flm.storage.DriverManagerConnectionPool;

public class CampionatiManager {
	private static final String TABLE_CAMPIONATI = "Campionato";

	public void creaCampionato (Campionato campionato) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "INSERT INTO " + CampionatiManager.TABLE_CAMPIONATI + "(Nome,NumSquadre,Quota) VALUES(?,?,?)";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, campionato.getNomeCampionato());
			preparedStatement.setInt(2, campionato.getNumSquadre());
			preparedStatement.setFloat(3, campionato.getQuota());
			preparedStatement.executeUpdate();
			connection.commit();
		}
		finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			}
			finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
}
