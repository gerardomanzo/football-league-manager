package flm.utenti;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UtentiControl extends HttpServlet {
	private static final long serialVersionUID = -1710635939916957861L;
	private static UtentiManager managerUtenti = new UtentiManager();
	
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
				String nome = request.getParameter("nome");
				String cognome = request.getParameter("cognome");
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				
				Allenatore allenatore = new Allenatore();
				allenatore.setNome(nome);
				allenatore.setCognome(cognome);
				allenatore.setEmail(email);
				allenatore.setPassword(password);
				
				managerUtenti.salvaAllenatore(allenatore);

				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
			}
			else if(action.equalsIgnoreCase("registrazioneArbitro")) {

			}
			else if(action.equalsIgnoreCase("loginUtente")) {
				String email = request.getParameter("email");
				String password = request.getParameter("password");

				Utente utente = new Utente();
				utente.setEmail(email);
				utente.setPassword(password);

				utente = managerUtenti.autentica(utente);

				if(utente == null) {

				}
				else if(utente instanceof Allenatore) {
					HttpSession session = request.getSession();

					session.removeAttribute("utente");
					session.setAttribute("utente", utente);
					session.setAttribute("ruolo", "allenatore");

					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/areaAllenatore.jsp");
					dispatcher.forward(request, response);
				}
				else if(utente instanceof Arbitro) {
					HttpSession session = request.getSession();

					session.removeAttribute("utente");
					session.setAttribute("utente", utente);
					session.setAttribute("ruolo", "arbitro");

					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/areaArbitro.jsp");
					dispatcher.forward(request, response);
				}
				else {
					HttpSession session = request.getSession();

					session.removeAttribute("utente");
					session.setAttribute("utente", utente);
					session.setAttribute("ruolo", "amministratore");

					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/areaAmministratore.jsp");
					dispatcher.forward(request, response);
				}
			}
			else {								
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/areaOspite.jsp");
				dispatcher.forward(request, response);
			}
		}
		catch(Exception e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
}
