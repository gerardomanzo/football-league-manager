package flm.partite;

import java.sql.Date;

import flm.campionati.Campionato;
import flm.squadre.Squadra;
import flm.utenti.Arbitro;

public class Partita {
	private int id;
	private int giornata;
	private int goalCasa;
	private int goalOspite;
	private Date data;
	private Campionato campionato;
	private Squadra squadraCasa;
	private Squadra squadraOspite;
	private Arbitro arbitro;
	
	public Partita() {
		this.id = -1;
		this.giornata = -1;
		this.data = null;
		this.squadraCasa = null;
		this.squadraOspite = null;
		this.arbitro = null; 
	}

	/**
	 * @return the id
	 */
	public int getID() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setID(int id) {
		this.id = id;
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
	public Date getData() {
		return data;
	}

	/**
	 * @param data2 the data to set
	 */
	public void setData(Date data2) {
		this.data = data2;
	}
	
	/**
	 * @return the campionato
	 */
	public Campionato getCampionato() {
		return campionato;
	}

	/**
	 * @param campionato the campionato to set
	 */
	public void setCampionato(Campionato campionato) {
		this.campionato = campionato;
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

	/**
	 * @param squadraCasa the squadraCasa to set
	 */
	public void setCasa(Squadra squadraCasa) {
		this.squadraCasa = squadraCasa;
	}

	/**
	 * @param squadraOspite the squadraOspite to set
	 */
	public void setOspite(Squadra squadraOspite) {
		this.squadraOspite = squadraOspite;
	}
}
