<!DOCTYPE html>
<html lang="it">
<head>
	<%@ include file="headData.html"%>
	<%@ page import="java.util.*, flm.campionati.Campionato, flm.squadre.Squadra"%>
	<%
		Collection<?> squadre = (Collection<?>) request.getAttribute("squadre");
		Collection<?> campionati = (Collection<?>) request.getAttribute("campionati");
	%>
	<title>Iscrizione Squadra</title>
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
						<strong>Iscrizione Squadra</strong>
					</h2>
				</div>
				<form action="campionati" method="post">
					<%
						if (squadre != null && squadre.size() > 0) {
					%>
					<input type="hidden" name="action" value="iscrizioneSquadra">
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
							if (campionati != null && campionati.size() > 0) {
					%>
					<div class="form-group">
						<select class="form-control" name="campionato">
							<%
								Iterator<?> it2 = campionati.iterator();
										while (it2.hasNext()) {
											Campionato campionato = (Campionato) it2.next();
							%>

							<option value="<%=campionato.getID()%>"><%=campionato.getNomeCampionato()%></option>
							<%
								}
							%>
						</select>
					</div>
					<input type="submit" class="btn btn-primary"
						value="Iscrizione squadra">
					<%
						} else {
					%>
					<div class="text-xs-center">
						<h5>Nessun campionato.</h5>
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