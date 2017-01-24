package flm.squadre;

import java.util.HashSet;
import java.util.Set;

import flm.campionati.Campionato;
import flm.giocatori.Giocatore;
import flm.utenti.Allenatore;

public class Squadra {
	private int id;
	private String nomeSquadra;
	private int vittorie;
	private int pareggi;
	private int sconfitte;
	private int goalFatti;
	private int goalSubiti;
	private int statoIscrizione;
	private Allenatore allenatore;
	private Set<Giocatore> rosa;
	private Campionato campionato;
	
	public static final int NESSUNA_ISCRIZIONE = 0;
	public static final int ATTESA_CONFERMA = 1;
	public static final int SQUADRA_ISCRITTA = 2;
	
	public Squadra() {
		this.id = -1;
		this.nomeSquadra = null;
		this.vittorie = 0;
		this.pareggi = 0;
		this.sconfitte = 0;
		this.goalFatti = 0;
		this.goalSubiti = 0;
		this.statoIscrizione = NESSUNA_ISCRIZIONE;
		this.allenatore = null;
		this.rosa = new HashSet<Giocatore>();
		this.campionato = null;
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
	 * @return the nomeSquadra
	 */
	public String getNomeSquadra() {
		return nomeSquadra;
	}

	/**
	 * @param nomeSquadra the nomeSquadra to set
	 */
	public void setNomeSquadra(String nomeSquadra) {
		this.nomeSquadra = nomeSquadra;
	}

	/**
	 * @return the vittorie
	 */
	public int getVittorie() {
		return vittorie;
	}

	/**
	 * @param vittorie the vittorie to set
	 */
	public void setVittorie(int vittorie) {
		this.vittorie = vittorie;
	}

	/**
	 * @return the pareggi
	 */
	public int getPareggi() {
		return pareggi;
	}

	/**
	 * @param pareggi the pareggi to set
	 */
	public void setPareggi(int pareggi) {
		this.pareggi = pareggi;
	}

	/**
	 * @return the sconfitte
	 */
	public int getSconfitte() {
		return sconfitte;
	}

	/**
	 * @param sconfitte the sconfitte to set
	 */
	public void setSconfitte(int sconfitte) {
		this.sconfitte = sconfitte;
	}

	/**
	 * @return the goalFatti
	 */
	public int getGoalFatti() {
		return goalFatti;
	}

	/**
	 * @param goalFatti the goalFatti to set
	 */
	public void setGoalFatti(int goalFatti) {
		this.goalFatti = goalFatti;
	}

	/**
	 * @return the goalSubiti
	 */
	public int getGoalSubiti() {
		return goalSubiti;
	}

	/**
	 * @param goalSubiti the goalSubiti to set
	 */
	public void setGoalSubiti(int goalSubiti) {
		this.goalSubiti = goalSubiti;
	}

	/**
	 * @return the statoIscrizione
	 */
	public int getStatoIscrizione() {
		return statoIscrizione;
	}
	
	/**
	 * set statoIscrizione to ATTESA_CONFERMA
	 */
	public void iscriviSquadra() {
		this.statoIscrizione = ATTESA_CONFERMA;
	}
	
	/**
	 * set statoIscrizione to SQUADRA_ISCRITTA
	 */
	public void confermaSquadra() {
		this.statoIscrizione = SQUADRA_ISCRITTA;
	}
		
	/**
	 * @param allenatore the allenatore to set
	 */
	public void setAllenatore(Allenatore allenatore) {
		this.allenatore = allenatore;
	}

	/**
	 * @return the allenatore
	 */
	public Allenatore getAllenatore() {
		return allenatore;
	}
	
	/**
	 * @return the rosa
	 */
	public Set<Giocatore> getRosa() {
		return rosa;
	}
	
	/** 
	 * @param giocatore il giocatore da aggiungere alla rosa
	 */
	public void aggiungiGiocatore(Giocatore giocatore) {
		if(rosa.size() < 8 && !rosa.contains(giocatore))
			rosa.add(giocatore);
	}
	
	/**
	 * @param giocatore il giocatore da rimuovere dalla rosa
	 */
	public void rimuoviGiocatore(Giocatore giocatore) {
		if(rosa.contains(giocatore))
			rosa.remove(giocatore);
	}
	
	public Campionato getCampionato() {
		return campionato;
	}

	public void setCampionato(Campionato campionato) {
		this.campionato = campionato;
	}

}
