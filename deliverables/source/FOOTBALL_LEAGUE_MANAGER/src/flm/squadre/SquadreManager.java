package flm.squadre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import flm.campionati.CampionatiManager;
import flm.campionati.Campionato;
import flm.giocatori.Giocatore;
import flm.giocatori.GiocatoreManager;
import flm.partite.PartiteManager;
import flm.storage.DriverManagerConnectionPool;

public class SquadreManager {
	public static final String TABLE_SQUADRE = "Squadra";
	public static final String TABLE_PARTECIPAZIONE = "Partecipazione";

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

	public void salvaPartita(int id_partita, int goalCasa, int goalOspite) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;
		String queryCasa = "";
		String queryOspite = "";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			
			if(goalCasa > goalOspite) {
				queryCasa = "UPDATE " + SquadreManager.TABLE_SQUADRE + " SET Vittorie=Vittorie+1, GoalFatti=GoalFatti+?, GoalSubiti=GoalSubiti+? WHERE ID_Squadra=(SELECT ID_Casa FROM " + PartiteManager.TABLE_PARTITE + " WHERE ID_Partita=?)";
				queryOspite = "UPDATE " + SquadreManager.TABLE_SQUADRE + " SET Sconfitte=Sconfitte+1, GoalFatti=GoalFatti+?, GoalSubiti=GoalSubiti+? WHERE ID_Squadra=(SELECT ID_Ospite FROM " + PartiteManager.TABLE_PARTITE + " WHERE ID_Partita=?)";
			}
			else if(goalCasa < goalOspite) {
				queryCasa = "UPDATE " + SquadreManager.TABLE_SQUADRE + " SET Sconfitte=Sconfitte+1, GoalFatti=GoalFatti+?, GoalSubiti=GoalSubiti+? WHERE ID_Squadra=(SELECT ID_Casa FROM " + PartiteManager.TABLE_PARTITE + " WHERE ID_Partita=?)";
				queryOspite = "UPDATE " + SquadreManager.TABLE_SQUADRE + " SET Vittorie=Vittorie+1, GoalFatti=GoalFatti+?, GoalSubiti=GoalSubiti+? WHERE ID_Squadra=(SELECT ID_Ospite FROM " + PartiteManager.TABLE_PARTITE + " WHERE ID_Partita=?)";
			}
			else {
				queryCasa = "UPDATE " + SquadreManager.TABLE_SQUADRE + " SET Pareggi=Pareggi+1, GoalFatti=GoalFatti+?, GoalSubiti=GoalSubiti+? WHERE ID_Squadra=(SELECT ID_Casa FROM " + PartiteManager.TABLE_PARTITE + " WHERE ID_Partita=?)";
				queryOspite = "UPDATE " + SquadreManager.TABLE_SQUADRE + " SET Pareggi=Pareggi+1, GoalFatti=GoalFatti+?, GoalSubiti=GoalSubiti+? WHERE ID_Squadra=(SELECT ID_Ospite FROM " + PartiteManager.TABLE_PARTITE + " WHERE ID_Partita=?)";
			}
			
			preparedStatement1 = connection.prepareStatement(queryCasa);
			preparedStatement2 = connection.prepareStatement(queryOspite);
			preparedStatement1.setInt(1, goalCasa);
			preparedStatement1.setInt(2, goalOspite);
			preparedStatement2.setInt(1, goalOspite);
			preparedStatement2.setInt(2, goalCasa);
			preparedStatement1.setInt(3, id_partita);
			preparedStatement2.setInt(3, id_partita);
			preparedStatement1.executeUpdate();
			preparedStatement2.executeUpdate();
			connection.commit();
		}
		finally {
			try {
				if(preparedStatement1 != null)
					preparedStatement1.close();
				if(preparedStatement2 != null)
					preparedStatement2.close();
			}
			finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	public Collection<Squadra> trovaSquadre() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM " + SquadreManager.TABLE_SQUADRE + " NATURAL JOIN " + CampionatiManager.TABLE_CAMPIONATI;
		Collection<Squadra> lista = new LinkedList<Squadra>();
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				Campionato campionato = new Campionato();
				Squadra squadra = new Squadra();
				campionato.setNomeCampionato(rs.getString("Nome"));
				squadra.setCampionato(campionato);
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

	public Squadra leggiRosa(int id) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM" + SquadreManager.TABLE_SQUADRE+ " NATURAL JOIN " + CampionatiManager.TABLE_CAMPIONATI + " NATURAL JOIN "  + SquadreManager.TABLE_PARTECIPAZIONE + " NATURAL JOIN" + GiocatoreManager.TABLE_GIOCATORI+" WHERE ID_Squadra = ? ";
		Squadra squadra = new Squadra();
		try{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			Campionato campionato = new Campionato();
			squadra.setCampionato(campionato);
			while (rs.next()){
				campionato.setNomeCampionato(rs.getString("Nome"));
				squadra.setNomeSquadra(rs.getString("NomeSquadra"));
				Giocatore giocatore = new Giocatore();
				giocatore.setNome(rs.getString("Nome"));
				giocatore.setCognome(rs.getString("Cognome"));
				squadra.aggiungiGiocatore(giocatore);
			}
		}finally {
			try{
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return squadra;
	}
}
