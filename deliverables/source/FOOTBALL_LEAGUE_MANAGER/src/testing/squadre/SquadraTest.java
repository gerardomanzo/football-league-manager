package testing.squadre;

import flm.giocatori.Giocatore;
import flm.squadre.Squadra;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class SquadraTest extends TestCase {
	Squadra s;
	Giocatore g1;
	Giocatore g2;
	Giocatore g3;
	Giocatore g4;
	Giocatore g5;
	Giocatore g6;
	Giocatore g7;
	Giocatore g8;
	Giocatore g9;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		s = new Squadra();
		
		g1 = new Giocatore();
		g2 = new Giocatore();
		g3 = new Giocatore();
		g4 = new Giocatore();
		g5 = new Giocatore();
		g6 = new Giocatore();
		g7 = new Giocatore();
		g8 = new Giocatore();
		g9 = new Giocatore();
		
		s.aggiungiGiocatore(g1);
		s.aggiungiGiocatore(g2);
		s.aggiungiGiocatore(g3);
		s.aggiungiGiocatore(g4);
		s.aggiungiGiocatore(g5);
		s.aggiungiGiocatore(g6);
		s.aggiungiGiocatore(g7);
	}
	
	public void testIscriviGiocatore1() {
		int n = s.getRosa().size();
		s.aggiungiGiocatore(g8);
		assertEquals(n+1, s.getRosa().size());
	}
	
	public void testIscriviGiocatore2() {
		int n = s.getRosa().size();
		s.aggiungiGiocatore(g8);
		s.aggiungiGiocatore(g9);
		assertEquals(n+1, s.getRosa().size());
	}
	
	public void testIscriviGiocatore3() {
		int n = s.getRosa().size();
		s.aggiungiGiocatore(g4);
		assertEquals(n, s.getRosa().size());
	}
	
	public void testRimuoviGiocatore1() {
		int n = s.getRosa().size();
		s.rimuoviGiocatore(g7);
		assertEquals(n-1, s.getRosa().size());
	}
	
	public void testRimuoviGiocatore2() {
		int n = s.getRosa().size();
		s.rimuoviGiocatore(g8);
		assertEquals(n, s.getRosa().size());
	}
	
	public static Test suite() {
		return new TestSuite(SquadraTest.class);
	}
	
	public static void main(String args[]) {
		junit.textui.TestRunner.run(suite());
	} 
}
