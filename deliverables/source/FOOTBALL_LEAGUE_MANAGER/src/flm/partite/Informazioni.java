package flm.partite;

public class Informazioni {
	private int goal;
	private int assist;
	private int cartellino;
	private int squalifica;
	private String motivazione;
	
	static final int NESSUN_CARTELLINO = 0;
	static final int GIALLO = 1;
	static final int DOPPIO_GIALLO = 2;
	static final int ROSSO = 3;
	
	public Informazioni() {
		this.goal = 0;
		this.assist = 0;
		this.cartellino = NESSUN_CARTELLINO;
		this.squalifica = 0;
		this.motivazione = null;
	}

	/**
	 * @return the goal
	 */
	public int getGoal() {
		return goal;
	}

	/**
	 * @param goal the goal to set
	 */
	public void setGoal(int goal) {
		this.goal = goal;
	}

	/**
	 * @return the assist
	 */
	public int getAssist() {
		return assist;
	}

	/**
	 * @param assist the assist to set
	 */
	public void setAssist(int assist) {
		this.assist = assist;
	}

	/**
	 * @return the cartellino
	 */
	public int getCartellino() {
		return cartellino;
	}

	/**
	 * @param cartellino the cartellino to set
	 */
	public void setCartellino(int cartellino) {
		this.cartellino = cartellino;
	}

	/**
	 * @return the squalifica
	 */
	public int getSqualifica() {
		return squalifica;
	}

	/**
	 * @param squalifica the squalifica to set
	 */
	public void setSqualifica(int squalifica) {
		this.squalifica = squalifica;
	}

	/**
	 * @return the motivazione
	 */
	public String getMotivazione() {
		return motivazione;
	}

	/**
	 * @param motivazione the motivazione to set
	 */
	public void setMotivazione(String motivazione) {
		this.motivazione = motivazione;
	}
}
