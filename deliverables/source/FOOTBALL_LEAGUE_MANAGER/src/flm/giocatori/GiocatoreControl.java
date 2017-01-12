package flm.giocatori;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import flm.utenti.Utente;

public class GiocatoreControl extends HttpServlet{
	private static final long serialVersionUID = -8462132438315054976L;
	private static GiocatoreManager model = new GiocatoreManager();

	public GiocatoreControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			Utente utente = (Utente) session.getAttribute("utente");

			String ruolo = (String) session.getAttribute("ruolo");

			String action = request.getParameter("action");

			if(action != null) {
				if(action.equalsIgnoreCase("getGiocatori")) {
					if(utente != null && ruolo.equalsIgnoreCase("amministratore")) {
						Collection<Giocatore> giocatori = model.leggiGiocatori();

						request.removeAttribute("giocatori");
						request.setAttribute("giocatori", giocatori);

						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/modificaGiocatore.jsp");
						dispatcher.forward(request, response);
					}
				}
			}
		}
		catch (Exception e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			Utente utente = (Utente) session.getAttribute("utente");

			String ruolo = (String) session.getAttribute("ruolo");

			String action = request.getParameter("action");

			if(action != null) {
				if(action.equalsIgnoreCase("modificaGiocatore")) {
					if(utente != null && ruolo.equalsIgnoreCase("amministratore")){
						int id =  Integer.parseInt(request.getParameter("giocatore"));
						String nomeNuovo =  request.getParameter("nuovoNome");
						String cognomeNuovo = request.getParameter("nuovoCognome");
						Giocatore giocatore = new Giocatore();
						giocatore.setID(id);
						model.modificaGiocatore(giocatore, nomeNuovo, cognomeNuovo);

						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/areaAmministratore.jsp");
						dispatcher.forward(request, response);

					}
				}
				else if(action.equalsIgnoreCase("iscrizioneGiocatore")) {
					if(utente != null && ruolo.equalsIgnoreCase("amministratore")){
						String nome =  request.getParameter("nomeGiocatore");
						String cognome = request.getParameter("cognomeGiocatore");
						Giocatore giocatore = new Giocatore();
						giocatore.setNome(nome);
						giocatore.setCognome(cognome);
						model.creaGiocatore(giocatore);

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
