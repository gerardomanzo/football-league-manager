package flm.partite;

import flm.giocatori.Giocatore;

public class Informazioni {
	private int id;
	private int goal;
	private int assist;
	private int cartellino;
	private int squalifica;
	private String motivazione;
	
	private Partita partita;
	private Giocatore giocatore;
	
	static final int NESSUN_CARTELLINO = 0;
	static final int GIALLO = 1;
	static final int DOPPIO_GIALLO = 2;
	static final int ROSSO = 3;
	
	public Informazioni() {
		this.id = -1;
		this.goal = 0;
		this.assist = 0;
		this.cartellino = NESSUN_CARTELLINO;
		this.squalifica = 0;
		this.motivazione = null;
	}

	/**
	 * @return l'id dell'informazione 
	 */
	public int getID() {
		return id;
	}

	/**
	 * @param id l'id da assegnare all'informazione
	 */
	public void setID(int id) {
		this.id = id;
	}

	/**
	 * @return i goal del giocatore
	 */
	public int getGoal() {
		return goal;
	}

	/**
	 * @param goal i goal da assegnare al giocatore
	 */
	public void setGoal(int goal) {
		this.goal = goal;
	}

	/**
	 * @return gli assist del giocatore
	 */
	public int getAssist() {
		return assist;
	}

	/**
	 * @param assist gli assist da assegnare al giocatore
	 */
	public void setAssist(int assist) {
		this.assist = assist;
	}

	/**
	 * @return il cartellino ricevuto dal giocatore
	 */
	public int getCartellino() {
		return cartellino;
	}

	/**
	 * @param cartellino il cartellino assegnato al giocatore
	 */
	public void setCartellino(int cartellino) {
		this.cartellino = cartellino;
	}

	/**
	 * @return il numero di giornate di squalifica
	 */
	public int getSqualifica() {
		return squalifica;
	}

	/**
	 * @param squalifica il numero di giornate di squalifica
	 */
	public void setSqualifica(int squalifica) {
		this.squalifica = squalifica;
	}

	/**
	 * @return la motivazione del cartellino
	 */
	public String getMotivazione() {
		return motivazione;
	}

	/**
	 * @param motivazione la motivazione del cartellino
	 */
	public void setMotivazione(String motivazione) {
		this.motivazione = motivazione;
	}

	/**
	 * @return il giocatore associato all'informazione
	 */
	public Giocatore getGiocatore() {
		return giocatore;
	}

	/**
	 * @param giocatore il giocatore da associare all'informazione
	 */
	public void setGiocatore(Giocatore giocatore) {
		this.giocatore = giocatore;
	}

	/**
	 * @return la partita associata all'informazione
	 */
	public Partita getPartita() {
		return partita;
	}
	
	/**
	 * @param partita la partita da associare all'informazione
	 */
	public void setPartita(Partita partita) {
		this.partita = partita;
	}
}
