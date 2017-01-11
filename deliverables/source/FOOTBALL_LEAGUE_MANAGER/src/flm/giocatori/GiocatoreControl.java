package flm.giocatori;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import flm.utenti.Utente;

public class GiocatoreControl {
	private static GiocatoreManager model=new GiocatoreManager();
	public GiocatoreControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			Utente utente = (Utente) session.getAttribute("utente");

			String ruolo = (String) session.getAttribute("ruolo");

			String action = request.getParameter("action");

			if(action != null) {
				if(action.equalsIgnoreCase("modificaGiocatore")) {
				if(utente != null && ruolo.equalsIgnoreCase("allenatore")){
					String nome =  request.getParameter("nomeGiocatore");
					String cognome = request.getParameter("cognomeGiocatore");
					String nomeNuovo =  request.getParameter("nuovoNome");
					String cognomeNuovo = request.getParameter("nuovoCognome");
					Giocatore giocatore = new Giocatore();
					giocatore.setNome(nomeNuovo);
					giocatore.setCognome(cognomeNuovo);
					model.modificaGiocatore(giocatore, nome, cognome);

					RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/areaAmministratore.jsp");
					dispatcher.forward(request, response);

				}
				}
			}

		}

		catch (Exception e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
}
