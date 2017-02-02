package testing.giocatori;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import flm.giocatori.Giocatore;
import flm.giocatori.GiocatoreManager;
import flm.storage.DriverManagerConnectionPool;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class GiocatoreManagerTest extends TestCase {
	GiocatoreManager gm;

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		gm = new GiocatoreManager();
	}
	
	public  void testCreaGiocatore() {
		Giocatore g = new Giocatore();
		g.setNome("Pippo");
		g.setCognome("Pluto");
		
		try {
			int n = gm.leggiGiocatori().size();
			gm.creaGiocatore(g);
			assertEquals(n+1, gm.leggiGiocatori().size());
			
			cancellaPippoPluto();
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}
	
	public  void testModificaGiocatore() {
		Giocatore g = new Giocatore();
		g.setNome("Pippo");
		g.setCognome("Pluto");
		
		try {
			gm.creaGiocatore(g);
			Collection<Giocatore> lista = gm.leggiGiocatori();
			
			Iterator<Giocatore> it = lista.iterator();
			
			Giocatore g1 = null;
			
			while(it.hasNext())
				g1 = it.next();
			
			gm.modificaGiocatore(g1, "Pluto", "Pippo");
			lista = gm.leggiGiocatori();
			it = lista.iterator();
			
			while(it.hasNext())
				g1 = it.next();
						
			assertEquals("Pluto", g1.getNome());
			assertEquals("Pippo", g1.getCognome());
			
			cancellaPlutoPippo();
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}
	
	public void cancellaPippoPluto() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "DELETE FROM " + GiocatoreManager.TABLE_GIOCATORI + " WHERE Nome='Pippo' AND Cognome='Pluto'";

		try {
			connection = DriverManagerConnectionPool.getConnection();			
			preparedStatement = connection.prepareStatement(selectSQL);

			preparedStatement.executeUpdate();

			connection.commit();
		}
		finally {
			if(preparedStatement != null)
				preparedStatement.close();

			DriverManagerConnectionPool.releaseConnection(connection);
		}
	}
	
	public void cancellaPlutoPippo() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "DELETE FROM " + GiocatoreManager.TABLE_GIOCATORI + " WHERE Nome='Pluto' AND Cognome='Pippo'";

		try {
			connection = DriverManagerConnectionPool.getConnection();			
			preparedStatement = connection.prepareStatement(selectSQL);

			preparedStatement.executeUpdate();

			connection.commit();
		}
		finally {
			if(preparedStatement != null)
				preparedStatement.close();

			DriverManagerConnectionPool.releaseConnection(connection);
		}
	}
	
	public static Test suite() {
		return new TestSuite(GiocatoreManagerTest.class);
	}

	public static void main(String args[]) {
		junit.textui.TestRunner.run(suite());
	}
}
