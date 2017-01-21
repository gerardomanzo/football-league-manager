package flm.partite;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import flm.utenti.Arbitro;
import flm.utenti.Utente;
import flm.utenti.UtentiManager;

public class PartitaControl extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9143690296317818235L;
	private static PartiteManager modelPartite = new PartiteManager();
	private static UtentiManager modelUtenti = new UtentiManager();

	public PartitaControl() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		try {
			if(action != null) {
				if(action.equalsIgnoreCase("assegnaArbitro")) {
					HttpSession session = request.getSession();
					Utente utente = (Utente) session.getAttribute("utente");

					String ruolo = (String) session.getAttribute("ruolo");

					if(utente != null && ruolo.equalsIgnoreCase("amministratore")) {
						Collection<Arbitro> arbitri = modelUtenti.leggiArbitri();
						Collection<Partita> partite = modelPartite.cercaPartite();

						request.removeAttribute("arbitri");
						request.setAttribute("arbitri", arbitri);

						request.removeAttribute("partite");
						request.setAttribute("partite", partite);

						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/assegnazioneArbitro.jsp");
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
				if(action.equalsIgnoreCase("assegnaArbitro")) {
					HttpSession session = request.getSession();
					Utente utente = (Utente) session.getAttribute("utente");

					String ruolo = (String) session.getAttribute("ruolo");

					if(utente != null && ruolo.equalsIgnoreCase("amministratore")) {
						
						int id_arbitro = Integer.parseInt(request.getParameter("arbitro"));
						int id_partita = Integer.parseInt(request.getParameter("partita"));
						
						Arbitro arbitro = new Arbitro();
						arbitro.setID(id_arbitro);
						
						Partita partita = new Partita();
						partita.setID(id_partita);
						partita.setArbitro(arbitro);
						
						modelPartite.assegnaArbitro(arbitro, partita);
						
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
