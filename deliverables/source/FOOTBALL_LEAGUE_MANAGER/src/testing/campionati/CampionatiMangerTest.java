package testing.campionati;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import flm.campionati.CampionatiManager;
import flm.campionati.Campionato;
import flm.storage.DriverManagerConnectionPool;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class CampionatiMangerTest extends TestCase {
	CampionatiManager cm = new CampionatiManager();
	Campionato campionato = new Campionato();
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		cancellaCampioantoTest();
	}
	
	public void testCreaCampionato() {
		campionato.setNomeCampionato("campionatoTest");
		campionato.setNumSquadre(2);
		
		try {
			int n = cm.cercaCampionati().size();
			cm.creaCampionato(campionato);
			assertEquals(n+1, cm.cercaCampionati().size());
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}
	
	public void testChiusuraCampionato() {
		campionato.setNomeCampionato("campionatoTest");
		campionato.setNumSquadre(2);
		
		try {
			int n = cm.cercaCampionati().size();
			cm.creaCampionato(campionato);
			
			Collection<Campionato> lista = cm.cercaCampionati();
			Iterator<Campionato> it = lista.iterator();
			while(it.hasNext()) {
				campionato = it.next();
			}
			
			assertEquals(n+1, lista.size());
			
			cm.chiusuraCampionato(campionato);
			assertEquals(n, cm.cercaCampionati().size());
			
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}
	
	public void cancellaCampioantoTest() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String deleteSQL= " DELETE FROM " + CampionatiManager.TABLE_CAMPIONATI + " WHERE Nome='campionatoTest'";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
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
		return new TestSuite(CampionatiMangerTest.class);
	}

	public static void main(String args[]) {
		junit.textui.TestRunner.run(suite());
	}
}
