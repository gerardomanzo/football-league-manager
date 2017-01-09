package flm.campionati;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import flm.utenti.Utente;
public class CampionatoControl extends HttpServlet{
	private static final long serialVersionUID = -7083459725744424731L;
	private static CampionatiManager model = new CampionatiManager();

	public CampionatoControl() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
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
							
						model.creaCampionato(campionato);
						
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
