package flm.campionati;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import flm.partite.Partita;
import flm.squadre.Squadra;

public class Campionato {
	private int id;
	private String nomeCampionato;
	private int numSquadre;
	private Map<String, Squadra> squadre;
	private Collection<Partita> calendario;

	public Campionato() {
		this.id = -1;
		this.nomeCampionato = null;
		this.numSquadre = 0;
		this.squadre = new HashMap<String, Squadra>();
		this.calendario = new ArrayList<Partita>();
	}

	/**
	 * @return the ID
	 */
	public int getID() {
		return id;
	}

	/**
	 * @param id the ID to set
	 */
	public void setID(int id) {
		this.id = id;
	}

	/**
	 * @return the nomeCampionato
	 */
	public String getNomeCampionato() {
		return nomeCampionato;
	}

	/**
	 * @param nomeCampionato the nomeCampionato to set
	 */
	public void setNomeCampionato(String nomeCampionato) {
		this.nomeCampionato = nomeCampionato;
	}

	/**
	 * @return the numSquadre
	 */
	public int getNumSquadre() {
		return numSquadre;
	}

	/**
	 * @param numSquadre the numSquadre to set
	 */
	public void setNumSquadre(int numSquadre) {
		this.numSquadre = numSquadre;
	}

	/**
	 * @param squadra la squadra da iscrivere
	 */
	public void iscriviSquadra(Squadra squadra) {
		if(squadre.size() < numSquadre && !squadre.containsKey(squadra.getNomeSquadra())) {
			squadre.put(squadra.getNomeSquadra(), squadra);
			squadra.iscriviSquadra();
		}
	}

	public Collection<Squadra> getSquadre()
	{
		return squadre.values();
	}

	public void aggiungiPartita (Partita partita)
	{
		calendario.add(partita);
	}
	
	public Collection<Partita> getCalendario() {
		return calendario;
	}
}