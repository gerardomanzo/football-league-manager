<!DOCTYPE html>
<html lang="it">
<head>
	<%@ include file="headData.html"%>
	<%@ page import="java.util.*, flm.campionati.Campionato, flm.partite.Partita, flm.squadre.Squadra"%>
	<%
		Collection<?> partite = (Collection<?>) request.getAttribute("partite");
	%>
	<title>Registrazione Referto</title>
</head>
<body>
	<div class="container">
		<%
			String ruolo = (String) session.getAttribute("ruolo");
			if (session.getAttribute("utente") != null && session.getAttribute("ruolo") != null
					&& ruolo.equals("arbitro")) {
		%>
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="text-xs-center">
					<h2>
						<strong>Registrazione Referto</strong>
					</h2>
				</div>
				<%
					if (partite != null && partite.size() > 0) {
				%>
				<form action="partite" method="post">
					<input type="hidden" name="action" value="registrazioneReferto">
					<select class="form-control" name="partita">
						<%
							Iterator<?> it = partite.iterator();
									while (it.hasNext()) {
										Partita partita = (Partita) it.next();
						%>
						<option value="<%=partita.getID()%>"><%=partita.getCampionato().getNomeCampionato()%> | <%=partita.getSquadraCasa().getNomeSquadra()%> - <%=partita.getSquadraOspite().getNomeSquadra()%></option>
						<%
							}
						%>
					</select> <input type="submit" class="btn btn-primary"
						value="Conferma scelta">
				</form>
				<%
					} else {
				%>
				<div class="text-xs-center">
					<h5>Nessuna partita.</h5>
				</div>
				<%
					}
				%>
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