package flm.squadre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

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

	public Collection<Squadra> leggiSquadreConferma() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM " + SquadreManager.TABLE_SQUADRE + " WHERE StatoIscrizione=?";
		
		Collection<Squadra> lista = new LinkedList<Squadra>();

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, Squadra.ATTESA_CONFERMA);

			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				Squadra squadra = new Squadra();

				squadra.setID(rs.getInt("ID_Squadra"));
				squadra.setNomeSquadra(rs.getString("NomeSquadra"));

				lista.add(squadra);
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
		
		return lista;
	}

	public void confermaSquadra(Squadra squadra) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String updateSQL = "UPDATE "+ SquadreManager.TABLE_SQUADRE+ " SET StatoIscrizione=? WHERE ID_Squadra=?";
		
		try{
			connection = DriverManagerConnectionPool.getConnection();
			
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setInt(1, Squadra.SQUADRA_ISCRITTA);
			preparedStatement.setInt(2, squadra.getID());
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
}
