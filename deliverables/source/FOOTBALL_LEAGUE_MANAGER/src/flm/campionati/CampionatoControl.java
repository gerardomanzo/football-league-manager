package flm.campionati;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import flm.squadre.Squadra;
import flm.squadre.SquadreManager;
import flm.utenti.Utente;

public class CampionatoControl extends HttpServlet{
	private static final long serialVersionUID = -7083459725744424731L;
	private static CampionatiManager modelCampionati = new CampionatiManager();
	private static SquadreManager modelSquadre = new SquadreManager();

	public CampionatoControl() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		try {
			if(action != null) {
				if(action.equalsIgnoreCase("iscrizioneSquadra")) {
					HttpSession session = request.getSession();
					Utente utente = (Utente) session.getAttribute("utente");
														
					String ruolo = (String) session.getAttribute("ruolo");
					
					if(utente != null && ruolo.equalsIgnoreCase("allenatore")) {
						Collection<Squadra> squadre = modelSquadre.trovaSquadreAllenatore(utente.getID(), Squadra.NESSUNA_ISCRIZIONE);
						Collection<Campionato> campionati = modelCampionati.cercaCampionati();
						
						request.removeAttribute("squadre");
						request.setAttribute("squadre", squadre);
						
						request.removeAttribute("campionati");
						request.setAttribute("campionati", campionati);
						
						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/iscrizioneSquadra.jsp");
						dispatcher.forward(request, response);
					}
				}
				else if (action.equalsIgnoreCase("chiusuraCampionato")){
					HttpSession session = request.getSession();
					Utente utente = (Utente) session.getAttribute("utente");

					String ruolo = (String) session.getAttribute("ruolo");
					if(utente != null && ruolo.equalsIgnoreCase("amministratore")) {
						Collection<Campionato> campionati = modelCampionati.cercaCampionati();

						request.removeAttribute("campionati");
						request.setAttribute("campionati", campionati);

						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/chiusuraCampionato.jsp");
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
				if(action.equalsIgnoreCase("creaCampionato")) {
					HttpSession session = request.getSession();
					Utente utente = (Utente) session.getAttribute("utente");
														
					String ruolo = (String) session.getAttribute("ruolo");
					
					if(utente != null && ruolo.equalsIgnoreCase("amministratore")) { 
						String nome = request.getParameter("nomeCampionato");
						int numeroSquadre = Integer.parseInt(request.getParameter("numSquadre"));
						float quota = Float.parseFloat(request.getParameter("quotaIscrizione"));
					
						Campionato campionato = new Campionato();
						campionato.setNomeCampionato(nome);
						campionato.setNumSquadre(numeroSquadre);
						campionato.setQuota(quota);
							
						modelCampionati.creaCampionato(campionato);
						
						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/areaAmministratore.jsp");
						dispatcher.forward(request, response);
					}
				}
				else if(action.equalsIgnoreCase("iscrizioneSquadra")) {
					HttpSession session = request.getSession();
					Utente utente = (Utente) session.getAttribute("utente");
														
					String ruolo = (String) session.getAttribute("ruolo");
					
					if(utente != null && ruolo.equalsIgnoreCase("allenatore")) {
						int id_squadra = Integer.parseInt(request.getParameter("squadra"));
						int id_campionato = Integer.parseInt(request.getParameter("campionato"));
						
						Squadra squadra = new Squadra();
						squadra.setID(id_squadra);
						
						Campionato campionato = new Campionato();
						campionato.setID(id_campionato);
						
						modelCampionati.iscriviSquadra(campionato, squadra);
						
						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/areaAllenatore.jsp");
						dispatcher.forward(request, response);
					}
				}
				else if (action.equalsIgnoreCase("chiusuraCampionato")){
					HttpSession session = request.getSession();

					Utente utente = (Utente) session.getAttribute("utente");

					String ruolo = (String) session.getAttribute("ruolo");
					if(utente !=  null && ruolo.equalsIgnoreCase("amministratore")) {
						int id_Campionato = Integer.parseInt(request.getParameter("campionato"));

						Campionato campionato = new Campionato();
						campionato.setID(id_Campionato);

						modelCampionati.chiusuraCampionato(campionato);

						RequestDispatcher dispatcher =  request.getServletContext().getRequestDispatcher("/areaAmministratore.jsp");
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
