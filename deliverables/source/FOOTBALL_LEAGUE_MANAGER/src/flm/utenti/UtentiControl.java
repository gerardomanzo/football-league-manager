package flm.utenti;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import flm.campionati.CampionatiManager;

public class UtentiControl extends HttpServlet {
	private static final long serialVersionUID = -1710635939916957861L;
	private static UtentiManager managerUtenti = new UtentiManager();
	private static CampionatiManager managerCampionati = new CampionatiManager();
	
	public UtentiControl() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		try {
			if(action.equalsIgnoreCase("registrazioneAllenatore")) {
				
				System.out.println("registrazioneAllenatore");
				
				String nome = request.getParameter("nome");
				String cognome = request.getParameter("cognome");
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				
				Allenatore allenatore = new Allenatore(nome, cognome, email, password);
				
				managerUtenti.salvaAllenatore(allenatore);
			}
			else if(action.equalsIgnoreCase("registrazioneArbitro")) {
				System.out.println("registrazioneArbitro");
			}
			else if(action.equalsIgnoreCase("loginUtente")) {
				System.out.println("loginUtente");
			}
			else {
				System.out.println("loginOspite");
				request.removeAttribute("campionati");
				request.setAttribute("campionati", managerCampionati.doRetrieveAll());
								
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/areaOspite.jsp");
				dispatcher.forward(request, response);
			}
		}
		catch(Exception e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
}
