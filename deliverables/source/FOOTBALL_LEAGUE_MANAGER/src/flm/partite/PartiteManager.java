package flm.partite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import flm.campionati.CampionatiManager;
import flm.campionati.Campionato;
import flm.squadre.Squadra;
import flm.squadre.SquadreManager;
import flm.storage.DriverManagerConnectionPool;
import flm.utenti.Arbitro;

public class PartiteManager {
	private static final String TABLE_PARTITE = "Partita";

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
}
