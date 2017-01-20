package flm.squadre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import flm.giocatori.Giocatore;
import flm.storage.DriverManagerConnectionPool;

public class SquadreManager {
	public static final String TABLE_SQUADRE = "Squadra";
	private static final String TABLE_PARTECIPAZIONE = "Partecipazione";

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

	public Collection<Squadra> trovaSquadreAllenatore(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM " + SquadreManager.TABLE_SQUADRE + " WHERE ID_Allenatore=?";

		Collection<Squadra> lista = new LinkedList<Squadra>();

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

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

	public Collection<Squadra> trovaSquadreAllenatore(int id, int stato) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM " + SquadreManager.TABLE_SQUADRE + " WHERE ID_Allenatore=? AND StatoIscrizione=?";

		Collection<Squadra> lista = new LinkedList<Squadra>();

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, stato);

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

	public void inserisciGiocatore(Squadra squadra, Giocatore giocatore) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "INSERT INTO " + SquadreManager.TABLE_PARTECIPAZIONE + " VALUES(?,?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, squadra.getID());
			preparedStatement.setInt(2, giocatore.getID());
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

	public void rimuoviGiocatore(Squadra squadra, Giocatore giocatore, Giocatore giocatoreNuovo) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "UPDATE " + SquadreManager.TABLE_PARTECIPAZIONE + " SET ID_Giocatore=? WHERE ID_Squadra=? AND ID_Giocatore=?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, giocatoreNuovo.getID());
			preparedStatement.setInt(2, squadra.getID());			
			preparedStatement.setInt(3, giocatore.getID());
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
