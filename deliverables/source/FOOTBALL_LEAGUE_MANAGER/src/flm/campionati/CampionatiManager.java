package flm.campionati;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import flm.partite.Partita;
import flm.partite.PartiteManager;
import flm.squadre.Squadra;
import flm.squadre.SquadreManager;
import flm.storage.DriverManagerConnectionPool;

public class CampionatiManager {
	public static final String TABLE_CAMPIONATI = "Campionato";

	public void creaCampionato (Campionato campionato) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "INSERT INTO " + CampionatiManager.TABLE_CAMPIONATI + "(Nome,NumSquadre) VALUES(?,?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, campionato.getNomeCampionato());
			preparedStatement.setInt(2, campionato.getNumSquadre());
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
	
	public Collection<Squadra> getSquadreCampionato(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<Squadra> squadre = new LinkedList<Squadra>();
		String selectSQL = "SELECT * FROM " + SquadreManager.TABLE_SQUADRE + " WHERE ID_Campionato=?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);
			
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				Squadra squadra = new Squadra();
				squadra.setID(rs.getInt("ID_Squadra"));
				squadra.setNomeSquadra(rs.getString("NomeSquadra"));
				squadra.setVittorie(rs.getInt("Vittorie"));
				squadra.setPareggi(rs.getInt("Pareggi"));
				squadra.setSconfitte(rs.getInt("Sconfitte"));
				squadra.setGoalFatti(rs.getInt("GoalFatti"));
				squadra.setGoalSubiti(rs.getInt("GoalSubiti"));
				
				squadre.add(squadra);
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

		return squadre;
	}
	
	public void chiusuraCampionato(Campionato campionato) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String deleteSQL= " DELETE FROM " + CampionatiManager.TABLE_CAMPIONATI + " WHERE ID_Campionato=?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, campionato.getID());
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

	public void iscriviSquadra(Campionato campionato, Squadra squadra) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "UPDATE " + SquadreManager.TABLE_SQUADRE + " SET ID_Campionato=?, StatoIscrizione=? WHERE ID_Squadra=?";


		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, campionato.getID());
			preparedStatement.setInt(2, Squadra.ATTESA_CONFERMA);
			preparedStatement.setInt(3, squadra.getID());
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

	public Campionato leggiCalendario(int id_campionato) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Campionato campionato = new Campionato();
		String selectSQL = "SELECT * FROM " + CampionatiManager.TABLE_CAMPIONATI + " c NATURAL JOIN " + PartiteManager.TABLE_PARTITE + " JOIN " + SquadreManager.TABLE_SQUADRE + " s1 ON ID_Casa=s1.ID_squadra JOIN " + SquadreManager.TABLE_SQUADRE +  " s2 ON ID_Ospite=s2.ID_Squadra WHERE c.ID_Campionato=?";
				
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id_campionato);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				campionato.setNomeCampionato(rs.getString("Nome"));
				
				Squadra squadraCasa = new Squadra();
				squadraCasa.setNomeSquadra(rs.getString("s1.NomeSquadra"));
				
				Squadra squadraOspite = new Squadra();
				squadraOspite.setNomeSquadra(rs.getString("s2.NomeSquadra"));
				
				Partita partita = new Partita();
				partita.setCampionato(campionato);
				partita.setCasa(squadraCasa);
				partita.setOspite(squadraOspite);
				partita.setData(rs.getDate("Data"));
				partita.setGiornata(rs.getInt("Giornata"));
				partita.setGoalCasa(rs.getInt("GoalCasa"));
				partita.setGoalOspite(rs.getInt("GoalOspite"));
				
				campionato.aggiungiPartita(partita);
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

		return campionato;
	}
}
