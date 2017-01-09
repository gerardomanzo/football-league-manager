package flm.giocatori;

public class Giocatore {
	private int id;
	private String nome;
	private String cognome;
	
	public Giocatore() {
		this.id = -1;
		this.nome = null;
		this.cognome = null;
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
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}
	
	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
}
