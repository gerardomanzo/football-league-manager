package flm.squadre;

import java.util.ArrayList;
import java.util.Collection;

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
	private Collection<Giocatore> rosa;
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
		this.rosa = new ArrayList<Giocatore>();
		this.campionato = null;
	}

	/**
	 * @return l'id
	 */
	public int getID() {
		return id;
	}

	/**
	 * @param id l'id della squadra
	 */
	public void setID(int id) {
		this.id = id;
	}

	/**
	 * @return il nome della squadra
	 */
	public String getNomeSquadra() {
		return nomeSquadra;
	}

	/**
	 * @param nomeSquadra il nome della squadra 
	 */
	public void setNomeSquadra(String nomeSquadra) {
		this.nomeSquadra = nomeSquadra;
	}

	/**
	 * @return le vittorie della squadra
	 */
	public int getVittorie() {
		return vittorie;
	}

	/**
	 * @param vittorie le vittorie da assegnare alla squadra
	 */
	public void setVittorie(int vittorie) {
		this.vittorie = vittorie;
	}

	/**
	 * @return i pareggi della squadra
	 */
	public int getPareggi() {
		return pareggi;
	}

	/**
	 * @param pareggi i pareggi da assegnare alla squadra
	 */
	public void setPareggi(int pareggi) {
		this.pareggi = pareggi;
	}

	/**
	 * @return le sconfitte della squadra
	 */
	public int getSconfitte() {
		return sconfitte;
	}

	/**
	 * @param sconfitte le sconfitte da assegnare alla squadra
	 */
	public void setSconfitte(int sconfitte) {
		this.sconfitte = sconfitte;
	}

	/**
	 * @return i goal fatti dalla squadra
	 */
	public int getGoalFatti() {
		return goalFatti;
	}

	/**
	 * @param goalFatti i goal fatti da assegnare alla squadra
	 */
	public void setGoalFatti(int goalFatti) {
		this.goalFatti = goalFatti;
	}

	/**
	 * @return i goal subiti dalla squadra
	 */
	public int getGoalSubiti() {
		return goalSubiti;
	}

	/**
	 * @param goalSubiti i goal subiti da assegnare alla squadra
	 */
	public void setGoalSubiti(int goalSubiti) {
		this.goalSubiti = goalSubiti;
	}

	/**
	 * @return lo stato d'iscrizione della squadra
	 */
	public int getStatoIscrizione() {
		return statoIscrizione;
	}
	
	/**
	 * imposta lo stato d'iIscrizione della squadra a ATTESA_CONFERMA
	 */
	public void iscriviSquadra() {
		this.statoIscrizione = ATTESA_CONFERMA;
	}
	
	/**
	 * imposta lo stato d'iIscrizione della squadra a SQUADRA_ISCRITTA
	 */
	public void confermaSquadra() {
		this.statoIscrizione = SQUADRA_ISCRITTA;
	}
		
	/**
	 * @param allenatore l'allenatore della squadra
	 */
	public void setAllenatore(Allenatore allenatore) {
		this.allenatore = allenatore;
	}

	/**
	 * @return l'allenatore della squadra
	 */
	public Allenatore getAllenatore() {
		return allenatore;
	}
	
	/**
	 * @return la rosa della squadra
	 */
	public Collection<Giocatore> getRosa() {
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
	
	/**
	 * 
	 * @return il campionato a cui partecipa la squadra
	 */
	public Campionato getCampionato() {
		return campionato;
	}
	
	/**
	 * 
	 * @param campionato il campionato a cui partecipa la squadra
	 */
	public void setCampionato(Campionato campionato) {
		this.campionato = campionato;
	}

}
