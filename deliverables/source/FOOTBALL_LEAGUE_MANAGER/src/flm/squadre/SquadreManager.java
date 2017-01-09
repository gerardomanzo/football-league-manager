package flm.squadre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import flm.storage.DriverManagerConnectionPool;

public class SquadreManager {
	private static final String TABLE_SQUADRE = "Squadra";

	public void creaSquadra(Squadra squadra) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "INSERT INTO " + SquadreManager.TABLE_SQUADRE + "(ID_Allenatore, NomeSquadra) VALUES(?,?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, squadra.getAllenatore().getID());
			preparedStatement.setString(2, squadra.getNomeSquadra());
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
