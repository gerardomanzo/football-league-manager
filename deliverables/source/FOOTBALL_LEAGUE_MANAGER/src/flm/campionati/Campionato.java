package flm.campionati;

public class Campionato {
	private String nomeCampionato;
	private int numSquadre;
	private float quota;
	
	/**
	 * @param nomeCampionato
	 * @param numSquadre
	 * @param quota
	 */
	public Campionato(String nomeCampionato, int numSquadre, float quota) {
		this.nomeCampionato = nomeCampionato;
		this.numSquadre = numSquadre;
		this.quota = quota;
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
}
