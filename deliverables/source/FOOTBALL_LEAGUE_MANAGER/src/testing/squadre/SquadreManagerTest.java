package testing.squadre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import flm.giocatori.Giocatore;
import flm.giocatori.GiocatoreManager;
import flm.squadre.Squadra;
import flm.squadre.SquadreManager;
import flm.storage.DriverManagerConnectionPool;
import flm.utenti.Allenatore;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class SquadreManagerTest extends TestCase {
	
	Squadra s1;
	SquadreManager sm = new SquadreManager();
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		s1 = new Squadra();
		s1.setNomeSquadra("SquadraTest");
		
		Allenatore a = new Allenatore();
		a.setID(2);
	
		s1.setAllenatore(a);
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		
		cancellaSquadraTest();
	}
	
	public void testCreaSquadra() {
		try {
			int n = sm.trovaSquadre().size();
			sm.creaSquadra(s1);
			assertEquals(n+1, trovaSquadre().size());
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}
	
	public void testInserisciGiocatore() {
		GiocatoreManager gm = new GiocatoreManager();
		
		try {
			sm.creaSquadra(s1);
			
			Collection<Squadra> lista = trovaSquadre();
			Iterator<Squadra> it = lista.iterator();
			
			while(it.hasNext())
				s1 = it.next();
			
			Giocatore g = null;
			Collection<Giocatore> lista2 = gm.leggiGiocatori();
			Iterator<Giocatore> it2 = lista2.iterator();
			
			while(it2.hasNext())
				g = it2.next();
						
			int n = leggiRosa(s1.getID()).getRosa().size();
			sm.inserisciGiocatore(s1, g);
			assertEquals(n+1, leggiRosa(s1.getID()).getRosa().size());
			
		} catch (SQLException e) {
			fail(e.getMessage());
		}		
	}
	
	public Collection<Squadra> trovaSquadre() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM " + SquadreManager.TABLE_SQUADRE;
		Collection<Squadra> lista = new LinkedList<Squadra>();
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				Squadra squadra = new Squadra();
				squadra.setID(rs.getInt("ID_Squadra"));
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
		String selectSQL = "SELECT * FROM " + SquadreManager.TABLE_SQUADRE+ " NATURAL JOIN "  + SquadreManager.TABLE_PARTECIPAZIONE + " NATURAL JOIN " + GiocatoreManager.TABLE_GIOCATORI+" WHERE ID_Squadra = ? ";
		Squadra squadra = new Squadra();
		try{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()){
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
	
	public void cancellaSquadraTest() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "DELETE FROM " + SquadreManager.TABLE_SQUADRE + " WHERE NomeSquadra='SquadraTest'";
				
		try {
			connection = DriverManagerConnectionPool.getConnection();			
			preparedStatement = connection.prepareStatement(selectSQL);

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
	
	public static Test suite() {
		return new TestSuite(SquadreManagerTest.class);
	}
	
	public static void main(String args[]) {
		junit.textui.TestRunner.run(suite());
	} 
}
