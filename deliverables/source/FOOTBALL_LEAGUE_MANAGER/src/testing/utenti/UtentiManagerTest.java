package testing.utenti;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import flm.storage.DriverManagerConnectionPool;
import flm.utenti.Allenatore;
import flm.utenti.Arbitro;
import flm.utenti.Utente;
import flm.utenti.UtentiManager;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class UtentiManagerTest extends TestCase{
	UtentiManager um;

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		um = new UtentiManager();
	}
	
	public void testAutenticaAmministratore() {
		Utente u = new Utente();
		u.setEmail("c.tramontano@asd.it");
		u.setPassword("prova");
		
		try {
			Utente y = um.autentica(u);
			
			assertTrue(!(y instanceof Allenatore) && !(y instanceof Arbitro));
		}
		catch (SQLException e) {
			fail(e.getMessage());
		}
	}
	
	public void testAutenticaUtenteNonEsistente() {
		Utente u = new Utente();
		u.setEmail("c.rossi@asd.it");
		u.setPassword("prova");
		
		try {
			Utente y = um.autentica(u);
			
			assertNull(y);
		}
		catch (SQLException e) {
			fail(e.getMessage());
		}
	}

	public void testSalvaAllenatore() {
		Allenatore a = new Allenatore();
		a.setNome("Pippo");
		a.setCognome("Pluto");
		a.setEmail("a@b.c");
		a.setPassword("prova");
		
		try {
			um.salvaAllenatore(a);
			
			Utente u = um.autentica(a);
			
			assertTrue(u instanceof Allenatore);
			
			cancellaPippo();
		}
		catch (SQLException e) {
			fail(e.getMessage());
		}
	}

	public void testSalvaArbitro() {
		Arbitro a = new Arbitro();
		a.setNome("Pippo");
		a.setCognome("Pluto");
		a.setEmail("a@b.c");
		a.setPassword("prova");
		
		try {
			um.salvaArbitro(a);
			
			Utente u = um.autentica(a);
			
			assertTrue(u instanceof Arbitro);
			
			cancellaPippo();
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	public void cancellaPippo() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "DELETE FROM " + UtentiManager.TABLE_UTENTI + " WHERE Email = 'a@b.c'";

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
		return new TestSuite(UtentiManagerTest.class);
	}

	public static void main(String args[]) {
		junit.textui.TestRunner.run(suite());
	}
}
