package testing.campionati;

import flm.campionati.Campionato;
import flm.partite.Partita;
import flm.squadre.Squadra;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class CampionatoTest extends TestCase {
	Campionato c;
	Squadra s1;
	Squadra s2;
	Squadra s3;
	Squadra s4;
	Squadra s5;

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		s1 = new Squadra();
		s1.setNomeSquadra("Milan");

		s2 = new Squadra();
		s2.setNomeSquadra("Inter");

		s3 = new Squadra();
		s3.setNomeSquadra("Juve");

		s4 = new Squadra();
		s4.setNomeSquadra("Napoli");

		s5 = new Squadra();
		s5.setNomeSquadra("Roma");

		c = new Campionato();
		c.setNumSquadre(4);
		c.iscriviSquadra(s1);
		c.iscriviSquadra(s2);
	}

	public void testIscriviSquadra1() {
		int n = c.getSquadre().size();
		c.iscriviSquadra(s3);
		assertEquals(n+1, c.getSquadre().size());
	}

	public void testIscriviSquadra2() {
		int n = c.getSquadre().size();
		c.iscriviSquadra(s2);
		assertEquals(n, c.getSquadre().size());
	}

	public void testIscriviSquadra3() {
		c.iscriviSquadra(s3);
		c.iscriviSquadra(s4);
		c.iscriviSquadra(s5);
		assertEquals(c.getNumSquadre(), c.getSquadre().size());
	}

	public void testInserisciPartita() {
		Partita p = new Partita();
		int n = c.getCalendario().size();
		c.aggiungiPartita(p);
		assertEquals(n+1, c.getCalendario().size());
	}

	public static Test suite() {
		return new TestSuite(CampionatoTest.class);
	}

	public static void main(String args[]) {
		junit.textui.TestRunner.run(suite());
	}
}