package flm.campionati;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import flm.partite.Partita;
import flm.partite.PartiteManager;
import flm.squadre.Squadra;
import flm.squadre.SquadreManager;
import flm.utenti.Utente;

public class CampionatoControl extends HttpServlet{
	private static final long serialVersionUID = -7083459725744424731L;
	private static CampionatiManager modelCampionati = new CampionatiManager();
	private static PartiteManager modelPartite = new PartiteManager();
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
				else if(action.equalsIgnoreCase("visualizzaCalendario")) {
					Collection<Campionato> campionati = modelCampionati.cercaCampionati();
					
					request.removeAttribute("campionati");
					request.setAttribute("campionati", campionati);
					
					RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/calendari.jsp");
					dispatcher.forward(request, response);
				}
				else if(action.equalsIgnoreCase("visualizzaClassifica")) {
					Collection<Campionato> campionati = modelCampionati.cercaCampionati();
					
					request.removeAttribute("campionati");
					request.setAttribute("campionati", campionati);
					
					RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/classifiche.jsp");
					dispatcher.forward(request, response);
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
					
						Campionato campionato = new Campionato();
						campionato.setNomeCampionato(nome);
						campionato.setNumSquadre(numeroSquadre);
							
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
						
						Collection<Squadra> squadre = modelCampionati.getSquadreCampionato(id_campionato);
						
						if(campionato.getSquadre().size() == campionato.getNumSquadre())
							generaCalendario(campionato, squadre);
						
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
				else if(action.equalsIgnoreCase("visualizzaCalendario")) {
					int id_campionato = Integer.parseInt(request.getParameter("campionato"));
					
					Campionato campionato = modelCampionati.leggiCalendario(id_campionato);
					
					request.removeAttribute("campionato");
					request.setAttribute("campionato", campionato);
					
					RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/visualizzaCalendario.jsp");
					dispatcher.forward(request, response);
				}
				else if(action.equalsIgnoreCase("visualizzaClassifica")) {
					Collection<Campionato> campionati = modelCampionati.cercaCampionati();
					
					request.removeAttribute("campionati");
					request.setAttribute("campionati", campionati);
					
					RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/classifiche.jsp");
					dispatcher.forward(request, response);
				}
			}
		}
		catch (Exception e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
	
	private void generaCalendario(Campionato campionato, Collection<Squadra> squadre) throws SQLException {
		Iterator<Squadra> iteratorSquadre = squadre.iterator();
		
		int numSquadre = squadre.size();	
		int giornate = numSquadre-1;
		
		
		Squadra[] casa = new Squadra[numSquadre/2];
		Squadra[] ospite = new Squadra[numSquadre/2];

		for(int i = 0; i < numSquadre/2; i++) {
			casa[i] = iteratorSquadre.next();
			ospite[i] = iteratorSquadre.next(); 
		}

		for(int i = 0; i < giornate; i++) {
			for(int j = 0; j < numSquadre/2 ; j++) {
				Partita partita = new Partita();
				partita.setCampionato(campionato);
				partita.setGiornata(i+1);

				if(j % 2 == 0) {
					partita.setCasa(casa[j]);
					partita.setOspite(ospite[j]);
				}
				else {
					partita.setCasa(ospite[j]);
					partita.setOspite(casa[j]);
				}

				modelPartite.salvaPartita(partita);
			}

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
