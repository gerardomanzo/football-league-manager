package flm.partite;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import flm.campionati.CampionatiManager;
import flm.campionati.Campionato;
import flm.giocatori.Giocatore;
import flm.giocatori.GiocatoreManager;
import flm.squadre.Squadra;
import flm.squadre.SquadreManager;
import flm.storage.DriverManagerConnectionPool;
import flm.utenti.Arbitro;

public class PartiteManager {
	public static final String TABLE_PARTITE = "Partita";
	public static final String TABLE_INFORMAZIONI = "Informazioni";

	public Collection<Partita> cercaPartite() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<Partita> partite = new LinkedList<Partita>();
		String selectSQL = "SELECT * FROM " +  PartiteManager.TABLE_PARTITE + " NATURAL JOIN " + CampionatiManager.TABLE_CAMPIONATI + " JOIN " + SquadreManager.TABLE_SQUADRE + " s1 ON ID_Casa=s1.ID_Squadra JOIN " + SquadreManager.TABLE_SQUADRE + " s2 ON ID_Ospite=s2.ID_Squadra";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {

				Squadra casa = new Squadra ();
				casa.setNomeSquadra(rs.getString("s1.NomeSquadra"));

				Squadra ospite = new Squadra ();
				ospite.setNomeSquadra(rs.getString("s2.NomeSquadra"));

				Campionato campionato = new Campionato();
				campionato.setNomeCampionato(rs.getString("Nome"));

				Partita partita = new Partita();
				partita.setID(rs.getInt("ID_Partita"));
				partita.setCampionato(campionato);
				partita.setCasa(casa);
				partita.setOspite(ospite);

				partite.add(partita);
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

		return partite;
	}

	public void assegnaArbitro(Arbitro arbitro, Partita partita) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "UPDATE " + PartiteManager.TABLE_PARTITE + " SET ID_Arbitro=? WHERE ID_Partita=?";


		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, arbitro.getID());
			preparedStatement.setInt(2, partita.getID());
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

	public void salvaPartita(Partita partita) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "INSERT INTO " + PartiteManager.TABLE_PARTITE + "(ID_Campionato, ID_Casa, ID_Ospite, Giornata) VALUES(?,?,?,?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, partita.getCampionato().getID());
			preparedStatement.setInt(2, partita.getSquadraCasa().getID());
			preparedStatement.setInt(3, partita.getSquadraOspite().getID());
			preparedStatement.setInt(4, partita.getGiornata());
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

	public Collection<Partita> cercaPartiteArbitro(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<Partita> partite = new LinkedList<Partita>();
		
		String selectSQL = "SELECT * FROM " + PartiteManager.TABLE_PARTITE + " NATURAL JOIN " + CampionatiManager.TABLE_CAMPIONATI + " JOIN " + SquadreManager.TABLE_SQUADRE + " s1 ON ID_Casa=s1.ID_Squadra JOIN " + SquadreManager.TABLE_SQUADRE +" s2 ON ID_Ospite=s2.ID_Squadra WHERE ID_Arbitro=? AND ID_Partita NOT IN(SELECT ID_Partita FROM "+ PartiteManager.TABLE_INFORMAZIONI + " GROUP BY ID_Partita)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				Campionato campionato = new Campionato();
				campionato.setNomeCampionato(rs.getString("Nome"));
				
				Squadra casa = new Squadra();
				casa.setNomeSquadra(rs.getString("s1.NomeSquadra"));
				
				Squadra ospite = new Squadra();
				ospite.setNomeSquadra(rs.getString("s2.NomeSquadra"));
				
				Partita partita = new Partita();
				partita.setID(rs.getInt("ID_Partita"));
				partita.setCampionato(campionato);
				partita.setCasa(casa);
				partita.setOspite(ospite);
				
				partite.add(partita);
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

		return partite;
	}

	public Collection<Giocatore> rosaCasa(int id_partita) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<Giocatore> rosaCasa = new LinkedList<Giocatore>();
		
		String selectSQL = "SELECT * FROM " + PartiteManager.TABLE_PARTITE + " JOIN " + SquadreManager.TABLE_SQUADRE + " ON ID_Casa=ID_Squadra NATURAL JOIN " + SquadreManager.TABLE_PARTECIPAZIONE + " NATURAL JOIN " + GiocatoreManager.TABLE_GIOCATORI + " WHERE ID_Partita=?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id_partita);
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				Giocatore giocatore = new Giocatore();
				giocatore.setID(rs.getInt("ID_Giocatore"));
				giocatore.setCognome(rs.getString("Cognome"));
				giocatore.setNome(rs.getString("Nome"));
				
				rosaCasa.add(giocatore);
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

		return rosaCasa;
	}
	
	public Collection<Giocatore> rosaOspite(int id_partita) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<Giocatore> rosaOspite = new LinkedList<Giocatore>();
		
		String selectSQL = "SELECT * FROM " + PartiteManager.TABLE_PARTITE + " JOIN " + SquadreManager.TABLE_SQUADRE + " ON ID_Ospite=ID_Squadra NATURAL JOIN " + SquadreManager.TABLE_PARTECIPAZIONE + " NATURAL JOIN " + GiocatoreManager.TABLE_GIOCATORI + " WHERE ID_Partita=?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id_partita);
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				Giocatore giocatore = new Giocatore();
				giocatore.setID(rs.getInt("ID_Giocatore"));
				giocatore.setCognome(rs.getString("Cognome"));
				giocatore.setNome(rs.getString("Nome"));
				
				rosaOspite.add(giocatore);
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

		return rosaOspite;
	}

	public void salvaInfo(Informazioni info) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "INSERT INTO " + PartiteManager.TABLE_INFORMAZIONI + " VALUES(?,?,?,?,?,?,?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, info.getPartita().getID());
			preparedStatement.setInt(2, info.getGiocatore().getID());
			preparedStatement.setInt(3, info.getGoal());
			preparedStatement.setInt(4, info.getAssist());
			preparedStatement.setInt(5, info.getCartellino());
			preparedStatement.setString(6, info.getMotivazione());
			preparedStatement.setInt(7, info.getSqualifica());
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

	public void salvaReferto(int id_partita, Date data, int goalCasa, int goalOspite) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "UPDATE " + PartiteManager.TABLE_PARTITE + " SET Data=?, GoalCasa=?, GoalOspite=? WHERE ID_Partita=?";


		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setDate(1, data);
			preparedStatement.setInt(2, goalCasa);
			preparedStatement.setInt(3, goalOspite);
			preparedStatement.setInt(4, id_partita);
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
