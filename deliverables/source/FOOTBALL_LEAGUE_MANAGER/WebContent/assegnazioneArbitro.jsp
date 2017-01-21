<!DOCTYPE html>
<html lang="it">
<head>
	<%@ include file="headData.html"%>
	<%@ page import="java.util.*, flm.partite.Partita, flm.utenti.Arbitro"%>
	<%
		Collection<?> arbitri = (Collection<?>) request.getAttribute("arbitri");
		Collection<?> partite = (Collection<?>) request.getAttribute("partite");
	%>
	<title>Assegnazione Arbitro</title>
</head>
<body>
	<div class="container">
		<%
			String ruolo = (String) session.getAttribute("ruolo");
			if (session.getAttribute("utente") != null && session.getAttribute("ruolo") != null
					&& ruolo.equals("amministratore")) {
		%>
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="text-xs-center">
					<h2>
						<strong>Assegnazione Arbitro</strong>
					</h2>
				</div>
				<form action="partite" method="post">
					<%
						if (arbitri != null && arbitri.size() != 0) {
					%>
					<input type="hidden" name="action" value="assegnaArbitro">
					<div class="form-group">
						<select class="form-control" name="arbitro">
							<%
								Iterator<?> it = arbitri.iterator();
										while (it.hasNext()) {
											Arbitro arbitro = (Arbitro) it.next();
							%>
							<option value="<%=arbitro.getID()%>"><%=arbitro.getCognome()%>
								<%=arbitro.getNome()%></option>
							<%
								}
							%>
						</select>
					</div>
					<%
						} else {
					%>
					<div class="text-xs-center">
						<h5>Nessun arbitro.</h5>
					</div>
					<%
						}
							if (partite != null && partite.size() > 0) {
					%>
					<div class="form-group">
						<select class="form-control" name="partita">
							<%
								Iterator<?> it2 = partite.iterator();
										while (it2.hasNext()) {
											Partita partita = (Partita) it2.next();
							%>
							<option value="<%=partita.getID()%>"><%=partita.getCampionato().getNomeCampionato()%>
								|
								<%=partita.getSquadraCasa().getNomeSquadra()%> -
								<%=partita.getSquadraOspite().getNomeSquadra()%></option>
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
						<h5>Nessuna partita.</h5>
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