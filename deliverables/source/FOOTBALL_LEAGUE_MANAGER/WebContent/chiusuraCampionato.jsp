<!DOCTYPE html>
<html lang="it">
<head>
	<%@ include file="headData.html"%>
	<%@ page import="java.util.*, flm.campionati.Campionato"%>
	<%
		Collection<?> campionati = (Collection<?>) request.getAttribute("campionati");
	%>
	<title>Chiusura Campionato</title>
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
						<strong>Chiusura Campionato</strong>
					</h2>
				</div>
				<%
					if (campionati != null && campionati.size() > 0) {
				%>
				<form action="campionati" method="post">
					<input type="hidden" name="action" value="chiusuraCampionato">
					<select class="form-control" name="campionato">
						<%
							Iterator<?> it = campionati.iterator();
									while (it.hasNext()) {
										Campionato campionato = (Campionato) it.next();
						%>
						<option value="<%=campionato.getID()%>"><%=campionato.getNomeCampionato()%></option>
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
					<h5>Nessun campionato.</h5>
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