<!DOCTYPE html>
<html lang="it">
<head>
	<%@ include file="headData.html"%>
	<%@ page import="java.util.*, flm.giocatori.Giocatore, flm.squadre.Squadra"%>
	<%
		Collection<?> squadre = (Collection<?>) request.getAttribute("squadre");
		Collection<?> giocatori = (Collection<?>) request.getAttribute("giocatori");
	%>
	<title>Sostituzione Giocatore</title>
</head>
<body>
	<div class="container">
		<%
			String ruolo = (String) session.getAttribute("ruolo");
			if (session.getAttribute("utente") != null && session.getAttribute("ruolo") != null
					&& ruolo.equals("allenatore")) {
		%>
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="text-xs-center">
					<h2>
						<strong>Sostituzione Giocatore</strong>
					</h2>
				</div>
				<form action="squadre" method="post">
					<%
						if (squadre != null && squadre.size() > 0) {
					%>
					<input type="hidden" name="action" value="sostituisciGiocatore">
					<div class="form-group">
						<select class="form-control" name="squadra">
							<%
								Iterator<?> it = squadre.iterator();
										while (it.hasNext()) {
											Squadra squadra = (Squadra) it.next();
							%>
							<option value="<%=squadra.getID()%>"><%=squadra.getNomeSquadra()%></option>
							<%
								}
							%>
						</select>
					</div>
					<%
						} else {
					%>
					<div class="text-xs-center">
						<h5>Nessuna squadra.</h5>
					</div>
					<%
						}
							if (giocatori != null && giocatori.size() > 0) {
					%>
					<div class="form-group">
						<label>Giocatore da rimuovere</label> <select class="form-control"
							name="giocatore">
							<%
								Iterator<?> it2 = giocatori.iterator();
										while (it2.hasNext()) {
											Giocatore giocatore = (Giocatore) it2.next();
							%>
							<option value="<%=giocatore.getID()%>"><%=giocatore.getCognome()%>
								<%=giocatore.getNome()%></option>
							<%
								}
							%>
						</select>
					</div>
					<div class="form-group">
						<label>Giocatore da inserire</label> <select class="form-control"
							name="giocatoreNuovo">
							<%
								Iterator<?> it3 = giocatori.iterator();
										while (it3.hasNext()) {
											Giocatore giocatore = (Giocatore) it3.next();
							%>
							<option value="<%=giocatore.getID()%>"><%=giocatore.getCognome()%>
								<%=giocatore.getNome()%></option>
							<%
								}
							%>
						</select>
					</div>
					<input type="submit" class="btn btn-primary"
						value="Conferma scelta">
					<%
						} else {
					%>
					<div class="text-xs-center">
						<h5>Nessuna squadra.</h5>
					</div>
					<%
						}
					%>
				</form>
			</div>
		</div>
		<%
			} else {
		%>
		<div class="text-xs-center">
			<div class="alert alert-warning" role="alert">
				<strong>Devi prima effettuare l'accesso a Football League
					Manager.</strong> Ritorna alla <a href="index.jsp">Home</a>.
			</div>
		</div>
		<%
			}
		%>
	</div>
</body>
</html>