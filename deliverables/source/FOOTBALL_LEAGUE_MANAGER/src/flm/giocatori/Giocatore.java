package flm.giocatori;

public class Giocatore {
	private String nome;
	private String cognome;
	
	/**
	 * @param nome
	 * @param cognome
	 */
	public Giocatore(String nome, String cognome) {
		this.nome = nome;
		this.cognome = cognome;
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
