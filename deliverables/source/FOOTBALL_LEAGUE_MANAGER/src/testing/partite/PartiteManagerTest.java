package testing.partite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import flm.campionati.CampionatiManager;
import flm.campionati.Campionato;
import flm.giocatori.Giocatore;
import flm.giocatori.GiocatoreManager;
import flm.partite.Informazioni;
import flm.partite.Partita;
import flm.partite.PartiteManager;
import flm.squadre.Squadra;
import flm.squadre.SquadreManager;
import flm.storage.DriverManagerConnectionPool;
import flm.utenti.Allenatore;
import junit.framework.TestCase;

public class PartiteManagerTest extends TestCase{
	Partita partita = new Partita();
	Allenatore al1 =  new Allenatore();
	Campionato camp = new Campionato();
	Informazioni info = new Informazioni();
	Squadra s1 =  new Squadra();
	Squadra s2 = new Squadra();
	Giocatore giocatore = new Giocatore();
	CampionatiManager cm =  new CampionatiManager();
	SquadreManager sm = new SquadreManager();
	PartiteManager pm = new PartiteManager();
	GiocatoreManager gm =  new GiocatoreManager();
	protected void setUp() throws Exception {
		super.setUp();
		al1.setID(2);
		camp.setNomeCampionato("testNomeCampionato2");
		camp.setNumSquadre(2);
		s1.setNomeSquadra("squadreTest1");
		s1.setAllenatore(al1);
		s2.setNomeSquadra("squadreTest2");
		s2.setAllenatore(al1);
		cm.creaCampionato(camp);
		giocatore.setNome("testGiocatore1");
		giocatore.setCognome("testCognome1");
		Iterator<Campionato> it = cm.cercaCampionati().iterator();
		while (it.hasNext()){
			camp = it.next();
		}
		sm.creaSquadra(s1);
		Iterator<Squadra> it2 = sm.trovaSquadreAllenatore(2).iterator();
		while (it2.hasNext()){
			s1= it2.next();
		}
		sm.creaSquadra(s2);
		it2 = sm.trovaSquadreAllenatore(2).iterator();
		while (it2.hasNext()){
			s2= it2.next();
		}
		cm.iscriviSquadra(camp, s1);
		cm.iscriviSquadra(camp, s2);
	}
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		cancellaCampionato();
	}
	public void testSalvaPartita(){

		partita.setCampionato(camp);
		partita.setCasa(s1);
		partita.setOspite(s2);
		partita.setGiornata(1);

		try {
			int n = pm.cercaPartite().size();
			pm.salvaPartita(partita);
			assertEquals(n+1, pm.cercaPartite().size());
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}

	public void testsalvaInfo(){
		partita.setCampionato(camp);
		partita.setCasa(s1);
		partita.setOspite(s2);
		partita.setGiornata(1);
		try {
			pm.salvaPartita(partita);
			Iterator<Partita> it = pm.cercaPartite().iterator();
			while (it.hasNext()){
				partita = it.next();
			}
			
			gm.creaGiocatore(giocatore);
			Iterator<Giocatore> it2 = gm.leggiGiocatori().iterator();
			while (it2.hasNext()){
				giocatore = it2.next();
			}
			info.setPartita(partita);
			info.setGiocatore(giocatore);
			info.setAssist(1);
			info.setCartellino(1);
			info.setGoal(1);
			info.setMotivazione("motivazioneTest");
			info.setSqualifica(1);
			int n = cercaInformazioni().size();
			pm.salvaInfo(info);
			assertEquals(n+1, cercaInformazioni().size());
		} catch (SQLException e1) {
			fail(e1.getMessage());
		}
	}
	public Collection<Informazioni> cercaInformazioni() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<Informazioni> info = new LinkedList<Informazioni>();
		String selectSQL = "SELECT * FROM " + PartiteManager.TABLE_INFORMAZIONI;

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				Informazioni informazioni = new Informazioni();
				info.add(informazioni);

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

		return info;
	}
	public void cancellaCampionato() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String deleteSQL= " DELETE FROM " + CampionatiManager.TABLE_CAMPIONATI + " WHERE Nome='testNomeCampionato2'";

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
}
