package flm.campionati;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

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
	
	public Collection<Campionato> cercaCampionati() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<Campionato> campionati = new LinkedList<Campionato>();
		String selectSQL = "SELECT * FROM " + CampionatiManager.TABLE_CAMPIONATI;

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				Campionato campionato = new Campionato();
				campionato.setID(rs.getInt("ID_Campionato"));
				campionato.setNomeCampionato(rs.getString("Nome"));
				campionato.setNumSquadre(rs.getInt("NumSquadre"));
				campionato.setQuota(rs.getFloat("Quota"));

				campionati.add(campionato);
			}
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

		return campionati;
	}
	public void chiusuraCampionato(Campionato campionato) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String deleteSQL= " DELETE FROM " + CampionatiManager.TABLE_CAMPIONATI + "WHERE ID_Campionato=? ";
						try {
							connection = DriverManagerConnectionPool.getConnection();
							preparedStatement = connection.prepareStatement(deleteSQL);
							preparedStatement.setInt(1, campionato.getID());
							preparedStatement.executeUpdate();
							connection.commit();
						} finally {
							try {
								if(preparedStatement != null)
									preparedStatement.close();
							} finally {
								DriverManagerConnectionPool.releaseConnection(connection);
							}
						}

	}
}
