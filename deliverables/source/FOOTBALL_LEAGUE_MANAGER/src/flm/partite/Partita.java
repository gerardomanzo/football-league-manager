package flm.partite;

import java.util.GregorianCalendar;

import flm.squadre.Squadra;
import flm.utenti.Arbitro;

public class Partita {
	private int giornata;
	private int goalCasa;
	private int goalOspite;
	private GregorianCalendar data;
	private Squadra squadraCasa;
	private Squadra squadraOspite;
	private Arbitro arbitro;
	
	/**
	 * @param giornata
	 * @param data
	 */
	public Partita(int giornata, Squadra squadraCasa, Squadra squadraOspite) {
		this.giornata = giornata;
		this.squadraCasa = squadraCasa;
		this.squadraOspite = squadraOspite;
		this.arbitro = null; 
	}

	/**
	 * @return the giornata
	 */
	public int getGiornata() {
		return giornata;
	}

	/**
	 * @param giornata the giornata to set
	 */
	public void setGiornata(int giornata) {
		this.giornata = giornata;
	}

	/**
	 * @return the goalCasa
	 */
	public int getGoalCasa() {
		return goalCasa;
	}

	/**
	 * @param goalCasa the goalCasa to set
	 */
	public void setGoalCasa(int goalCasa) {
		this.goalCasa = goalCasa;
	}

	/**
	 * @return the goalOspite
	 */
	public int getGoalOspite() {
		return goalOspite;
	}

	/**
	 * @param goalOspite the goalOspite to set
	 */
	public void setGoalOspite(int goalOspite) {
		this.goalOspite = goalOspite;
	}

	/**
	 * @return the data
	 */
	public GregorianCalendar getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(GregorianCalendar data) {
		this.data = data;
	}
	
	/**
	 * @return the squadraCasa
	 */
	public Squadra getSquadraCasa() {
		return squadraCasa;
	}

	/**
	 * @return the squadraOspite
	 */
	public Squadra getSquadraOspite() {
		return squadraOspite;
	}
	
	/**
	 * @return the arbitro
	 */
	public Arbitro getArbitro() {
		return arbitro;
	}

	/**
	 * @param arbitro the arbitro to set
	 */
	public void setArbitro(Arbitro arbitro) {
		this.arbitro = arbitro;
	}
}