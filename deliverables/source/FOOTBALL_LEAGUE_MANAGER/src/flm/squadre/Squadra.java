package flm.squadre;

import java.util.HashSet;
import java.util.Set;

import flm.giocatori.Giocatore;
import flm.utenti.Allenatore;

public class Squadra {
	private String nomeSquadra;
	private int vittorie;
	private int pareggi;
	private int sconfitte;
	private int goalFatti;
	private int goalSubiti;
	private int statoIscrizione;
	private Allenatore allenatore;
	private Set<Giocatore> rosa;
	
	static final int NESSUNA_ISCRIZIONE = 0;
	static final int ATTESA_CONFERMA = 1;
	static final int ATTESA_PAGAMENTO = 2;
	static final int SQUADRA_ISCRITTA = 3;
	
	public Squadra() {
		this.nomeSquadra = null;
		this.vittorie = 0;
		this.pareggi = 0;
		this.sconfitte = 0;
		this.goalFatti = 0;
		this.goalSubiti = 0;
		this.statoIscrizione = NESSUNA_ISCRIZIONE;
		this.allenatore = null;
		this.rosa = new HashSet<Giocatore>();
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
	 * set statoIscrizione to ATTESA_PAGAMENTO
	 */
	public void confermaSquadra() {
		this.statoIscrizione = ATTESA_PAGAMENTO;
	}
	
	/**
	 * set statoIscrizione to SQUADRA_ISCRITTA
	 */
	public void confermaPagamento() {
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
	 * @throws MaxNumGiocatoriException se la rosa ha raggiunto 8 giocatori
	 * @throws GiocatoreGiaPresenteException se il giocatore è già presente in rosa
	 */
	public void aggiungiGiocatore(Giocatore giocatore) throws GiocatoreGiaPresenteException, MaxNumGiocatoriException {
		
		if(rosa.size() == 8)
			throw new MaxNumGiocatoriException();
		
		if(rosa.contains(giocatore))
			throw new GiocatoreGiaPresenteException();
		
		rosa.add(giocatore);
	}
	
	/**
	 * @param giocatore il giocatore da rimuovere dalla rosa
	 * @throws GiocatoreNonPresenteException se il giocatore non è presente inn rosa
	 */
	public void rimuoviGiocatore(Giocatore giocatore) throws GiocatoreNonPresenteException {
	
		if(!rosa.contains(giocatore))
			throw new GiocatoreNonPresenteException();
		
		rosa.remove(giocatore);
	}
}
