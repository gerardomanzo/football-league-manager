package flm.campionati;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import flm.partite.Partita;
import flm.squadre.Squadra;

public class Campionato {
	private String nomeCampionato;
	private int numSquadre;
	private float quota;
	private Map<String, Squadra> squadre;
	private List<Partita> calendario;

	/**
	 * @param nomeCampionato
	 * @param numSquadre
	 * @param quota
	 */
	public Campionato(String nomeCampionato, int numSquadre, float quota) {
		this.nomeCampionato = nomeCampionato;
		this.numSquadre = numSquadre;
		this.quota = quota;
		this.squadre = new HashMap<String, Squadra>();
		this.calendario = new ArrayList<Partita>();
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
	 * @return the quota
	 */
	public float getQuota() {
		return quota;
	}

	/**
	 * @param quota the quota to set
	 */
	public void setQuota(float quota) {
		this.quota = quota;
	}

	/**
	 * @param squadra la squadra da iscrivere
	 * @throws MaxNumSquadreException se è stato raggiunto il max numero di squadre
	 * @throws SquadraGiaIscrittaException se la squadra risulta già iscritta
	 */
	public void iscriviSquadra(Squadra squadra) throws MaxNumSquadreException, SquadraGiaIscrittaException {

		if(squadre.size() == numSquadre)
			throw new MaxNumSquadreException();

		if(squadre.containsKey(squadra.getNomeSquadra()))
			throw new SquadraGiaIscrittaException();

		squadre.put(squadra.getNomeSquadra(), squadra);
		squadra.iscriviSquadra();

		if(squadre.size() == numSquadre)
			generaCalendario();
	}

	private void generaCalendario() {
		int giornate = numSquadre-1;
		Iterator<Squadra> iteratorSquadre = squadre.values().iterator();
		Squadra[] casa = new Squadra[numSquadre/2];
		Squadra[] ospite = new Squadra[numSquadre/2];

		for(int i = 0; i < numSquadre/2; i++) {
			casa[i] = iteratorSquadre.next();
			ospite[i] = iteratorSquadre.next(); 
		}

		for(int i = 0; i < giornate; i++) {
			
			// alterna le partite in casa e fuori
			if(i % 2 == 0)
				for(int j = 0; j < numSquadre /2 ; j++)
					calendario.add(new Partita(i+1, casa[j], ospite[j]));
			else
				for(int j = 0; j < numSquadre /2 ; j++)
					calendario.add(new Partita(i+1, ospite[j], casa[j]));

			// salva l'elemento fisso
			Squadra pivot = casa[0];

			// sposta in avanti gli elementi di "ospite" inserendo 
			// all'inizio l'elemento casa[1] e salva l'elemento uscente in "riporto"
			Squadra riporto = ospite[ospite.length - 1];
			ospite = shiftRight(ospite, casa[1]);

			// sposta a sinistra gli elementi di "casa" inserendo all'ultimo 
			// posto l'elemento "riporto" */
			casa = shiftLeft(casa, riporto);

			// ripristina l'elemento fisso
			casa[0] = pivot ;
		}
	}

	private Squadra[] shiftLeft(Squadra[] data, Squadra add) {
		Squadra[] temp = new Squadra[data.length];
		for(int i = 0; i < data.length-1; i++)
			temp[i] = data[i + 1];
		temp[data.length - 1] = add;
		return temp;
	}

	private Squadra[] shiftRight(Squadra[] data, Squadra add) {
		Squadra[] temp = new Squadra[data.length];
		for (int i = 1; i < data.length; i++)
			temp[i] = data[i - 1];
		temp[0] = add;
		return temp;
	}
}
