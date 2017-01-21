package flm.squadre;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import flm.giocatori.Giocatore;
import flm.giocatori.GiocatoreManager;
import flm.utenti.Allenatore;
import flm.utenti.Utente;

public class SquadraControl extends HttpServlet{
	private static final long serialVersionUID = -2530651357994741963L;
	private static SquadreManager modelSquadre = new SquadreManager();
	private static GiocatoreManager modelGiocatori = new GiocatoreManager();

	public SquadraControl() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String action = request.getParameter("action");

			if(action != null) {
				if(action.equalsIgnoreCase("getSquadreConferma")) {
					HttpSession session = request.getSession();
					Utente utente = (Utente) session.getAttribute("utente");

					String ruolo = (String) session.getAttribute("ruolo");

					if(utente != null && ruolo.equalsIgnoreCase("amministratore")) { 
						Collection<Squadra> squadre = modelSquadre.leggiSquadreConferma();
						
						request.removeAttribute("squadreConferma");
						request.setAttribute("squadreConferma", squadre);
						
						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/confermaSquadra.jsp");
						dispatcher.forward(request, response);
					}
				}
				else if(action.equalsIgnoreCase("iscriviGiocatore")) {
					HttpSession session = request.getSession();
					Utente utente = (Utente) session.getAttribute("utente");

					String ruolo = (String) session.getAttribute("ruolo");

					if(utente != null && ruolo.equalsIgnoreCase("allenatore")) { 
						Collection<Squadra> squadre = modelSquadre.trovaSquadreAllenatore(utente.getID());
						Collection<Giocatore> giocatori = modelGiocatori.leggiGiocatori();
						
						request.removeAttribute("squadre");
						request.setAttribute("squadre", squadre);
						request.removeAttribute("giocatori");
						request.setAttribute("giocatori", giocatori);
						
						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/inserimentoGiocatore.jsp");
						dispatcher.forward(request, response);
					}
				}
				else if(action.equalsIgnoreCase("sostituisciGiocatore")) {
					HttpSession session = request.getSession();
					Utente utente = (Utente) session.getAttribute("utente");

					String ruolo = (String) session.getAttribute("ruolo");

					if(utente != null && ruolo.equalsIgnoreCase("allenatore")) { 
						Collection<Squadra> squadre = modelSquadre.trovaSquadreAllenatore(utente.getID());
						Collection<Giocatore> giocatori = modelGiocatori.leggiGiocatori();
						
						request.removeAttribute("squadre");
						request.setAttribute("squadre", squadre);
						request.removeAttribute("giocatori");
						request.setAttribute("giocatori", giocatori);
						
						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/sostituzioneGiocatore.jsp");
						dispatcher.forward(request, response);
					}
				}
			}
		}
		catch(Exception e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
				
		try {
			if(action != null) {
				if(action.equalsIgnoreCase("creaSquadra")) {
					HttpSession session = request.getSession();
					Utente utente = (Utente) session.getAttribute("utente");

					String ruolo = (String) session.getAttribute("ruolo");

					if(utente != null && ruolo.equalsIgnoreCase("allenatore")) {
						String nomeSquadra = request.getParameter("nomeSquadra");

						Squadra squadra = new Squadra();
						squadra.setNomeSquadra(nomeSquadra);
						squadra.setAllenatore((Allenatore) utente);

						modelSquadre.creaSquadra(squadra);

						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/areaAllenatore.jsp");
						dispatcher.forward(request, response);
					}
				}
				else if(action.equalsIgnoreCase("confermaSquadra")) {
					HttpSession session = request.getSession();
					Utente utente = (Utente) session.getAttribute("utente");

					String ruolo = (String) session.getAttribute("ruolo");
					
					if(utente != null && ruolo.equalsIgnoreCase("amministratore")) {
						int id = Integer.parseInt(request.getParameter("squadra"));
											
						Squadra squadra = new Squadra();
						squadra.setID(id);
						
						modelSquadre.confermaSquadra(squadra);
						
						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/areaAmministratore.jsp");
						dispatcher.forward(request, response);
					}
				}
				else if(action.equalsIgnoreCase("inserisciGiocatore")) {
					HttpSession session = request.getSession();
					Utente utente = (Utente) session.getAttribute("utente");

					String ruolo = (String) session.getAttribute("ruolo");

					if(utente != null && ruolo.equalsIgnoreCase("allenatore")) {
						int IDSquadra = Integer.parseInt(request.getParameter("squadra"));
						int IDGiocatore = Integer.parseInt(request.getParameter("giocatore"));

						Squadra squadra = new Squadra();
						squadra.setID(IDSquadra);

						Giocatore giocatore = new Giocatore();
						giocatore.setID(IDGiocatore);

						modelSquadre.inserisciGiocatore(squadra, giocatore);

						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/areaAllenatore.jsp");
						dispatcher.forward(request, response);
					}
				}
				else if(action.equalsIgnoreCase("sostituisciGiocatore")) {
					HttpSession session = request.getSession();
					Utente utente = (Utente) session.getAttribute("utente");

					String ruolo = (String) session.getAttribute("ruolo");

					if(utente != null && ruolo.equalsIgnoreCase("allenatore")) {
						int IDSquadra = Integer.parseInt(request.getParameter("squadra"));
						int IDGiocatore = Integer.parseInt(request.getParameter("giocatore"));
						int IDGiocatoreNuovo = Integer.parseInt(request.getParameter("giocatoreNuovo"));
						
						Squadra squadra = new Squadra();
						squadra.setID(IDSquadra);

						Giocatore giocatore = new Giocatore();
						giocatore.setID(IDGiocatore);
						
						Giocatore giocatoreNuovo = new Giocatore();
						giocatore.setID(IDGiocatoreNuovo);

						modelSquadre.rimuoviGiocatore(squadra, giocatore, giocatoreNuovo);

						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/areaAllenatore.jsp");
						dispatcher.forward(request, response);
					}
				}
			}
		}
		catch(Exception e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

}
