package flm.squadre;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import flm.utenti.Allenatore;
import flm.utenti.Utente;
public class SquadraControl extends HttpServlet{
	private static final long serialVersionUID = -2530651357994741963L;
	private static SquadreManager model = new SquadreManager();

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
						Collection<Squadra> squadre = model.leggiSquadreConferma();
						
						request.removeAttribute("squadreConferma");
						request.setAttribute("squadreConferma", squadre);
						
						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/confermaSquadra.jsp");
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

						model.creaSquadra(squadra);

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
						
						model.confermaSquadra(squadra);
						
						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/areaAmministratore.jsp");
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
