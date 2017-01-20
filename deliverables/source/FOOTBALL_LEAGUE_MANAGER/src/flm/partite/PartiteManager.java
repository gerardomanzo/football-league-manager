package flm.partite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import flm.partite.PartiteManager;
import flm.partite.Partita;
import flm.squadre.Squadra;
import flm.squadre.SquadreManager;
import flm.storage.DriverManagerConnectionPool;

public class PartiteManager {
	private static final String TABLE_PARTITE = "Partita";

	public void creaPartita (Partita partita) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "SELECT * FROM " +  PartiteManager.TABLE_PARTITE + " JOIN " + SquadreManager.TABLE_SQUADRE + " s1 ON " + " ID_Casa=s1.ID_Squadra JOIN " + SquadreManager.TABLE_SQUADRE + " s2 ON " + " ID_Ospite=s2.ID_Squadra";
	
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(query);
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

		public Collection<Partita> cercaPartite() throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			Collection<Partita> partite = new LinkedList<Partita>();
			String selectSQL = "SELECT * FROM " + PartiteManager.TABLE_PARTITE;

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				
				Squadra casa = new Squadra ();
				casa.setNomeSquadra(rs.getString("s1.NomeSquadraCasa"));
				
				Squadra ospite = new Squadra ();
				ospite.setNomeSquadra(rs.getString("s2.NomeSquadraOspite"));
				
				Partita partita = new Partita();
				partita.setID(rs.getInt("ID_Partita"));
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
}
