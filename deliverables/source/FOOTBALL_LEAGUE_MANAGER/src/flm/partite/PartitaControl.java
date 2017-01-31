package flm.partite;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import flm.giocatori.Giocatore;
import flm.squadre.SquadreManager;
import flm.utenti.Arbitro;
import flm.utenti.Utente;
import flm.utenti.UtentiManager;

public class PartitaControl extends HttpServlet{
	private static final long serialVersionUID = 9143690296317818235L;
	private static PartiteManager modelPartite = new PartiteManager();
	private static UtentiManager modelUtenti = new UtentiManager();
	private static SquadreManager modelSquadre = new SquadreManager();

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
				else if(action.equalsIgnoreCase("registrazioneReferto")) {
					HttpSession session = request.getSession();
					Utente utente = (Utente) session.getAttribute("utente");

					String ruolo = (String) session.getAttribute("ruolo");

					if(utente != null && ruolo.equalsIgnoreCase("arbitro")) {
						Collection<Partita> partite = modelPartite.cercaPartiteArbitro(utente.getID());
						
						request.removeAttribute("partite");
						request.setAttribute("partite", partite);
						
						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/registrazioneReferto.jsp");
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
				else if(action.equalsIgnoreCase("registrazioneReferto")) {
					HttpSession session = request.getSession();
					Utente utente = (Utente) session.getAttribute("utente");

					String ruolo = (String) session.getAttribute("ruolo");

					if(utente != null && ruolo.equalsIgnoreCase("arbitro")) {
						int id_partita = Integer.parseInt(request.getParameter("partita"));

						Collection<Giocatore> rosaCasa = modelPartite.rosaCasa(id_partita);
						Collection<Giocatore> rosaOspite = modelPartite.rosaOspite(id_partita);
						
						request.removeAttribute("rosaCasa");
						request.setAttribute("rosaCasa", rosaCasa);
						
						request.removeAttribute("rosaOspite");
						request.setAttribute("rosaOspite", rosaOspite);
						
						request.removeAttribute("partita");
						request.setAttribute("partita", id_partita);
						
						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/inserimentoReferto.jsp");
						dispatcher.forward(request, response);
					}
				}
				else if(action.equalsIgnoreCase("inserimentoReferto")) {
					HttpSession session = request.getSession();
					Utente utente = (Utente) session.getAttribute("utente");

					String ruolo = (String) session.getAttribute("ruolo");

					if(utente != null && ruolo.equalsIgnoreCase("arbitro")) {
						int id_partita = Integer.parseInt(request.getParameter("partita"));
						
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						Date data = new Date(sdf.parse(request.getParameter("data")).getTime());
						
						int id_giocatore = -1;
						int goal = 0;
						int assist = 0;
						int cartellino = 0;
						int squalifica = 0;
						String motivazione = "";
						
						int goalCasa = 0;
						int goalOspite = 0;
						
						for(int i = 0; i < 16; i++) {
							id_giocatore = Integer.parseInt(request.getParameter("giocatore"+i));
							goal = Integer.parseInt(request.getParameter("goal"+i));
							assist = Integer.parseInt(request.getParameter("assist"+i));
							cartellino = Integer.parseInt(request.getParameter("cartellino"+i));
							motivazione = request.getParameter("motivazione"+i);
							squalifica = Integer.parseInt(request.getParameter("squalifica"+i));
							
							Partita partita = new Partita();
							partita.setID(id_partita);
							
							Giocatore giocatore = new Giocatore();
							giocatore.setID(id_giocatore);
							
							Informazioni info = new Informazioni();
							info.setPartita(partita);
							info.setGiocatore(giocatore);
							info.setGoal(goal);
							info.setAssist(assist);
							info.setCartellino(cartellino);
							info.setSqualifica(squalifica);
							info.setMotivazione(motivazione);
							
							modelPartite.salvaInfo(info);
							
							if(i < 8)
								goalCasa+=goal;
							else
								goalOspite+=goal;
						}
						modelPartite.salvaReferto(id_partita, data, goalCasa, goalOspite);
						modelSquadre.salvaPartita(id_partita, goalCasa, goalOspite);
						
						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/areaArbitro.jsp");
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
