<!DOCTYPE html>
<html lang="it">
<head>
	<%@ include file="headData.html"%>
	<%@ page import="java.util.*, flm.campionati.Campionato, flm.squadre.Squadra"%>
	<%
		Collection<?> squadre = (Collection<?>) request.getAttribute("squadre");
	%>
	<title>Visualizza Rosa</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="text-xs-center">
					<h2>
						<strong>Visualizza Rosa</strong>
					</h2>
				</div>
				<%
					if (squadre != null && squadre.size() > 0) {
				%>
				<form action="squadre" method="get">
					<input type="hidden" name="action" value="leggiRosa">
					<select class="form-control" name="squadra">
						<%
							Iterator<?> it = squadre.iterator();
									while (it.hasNext()) {
										Squadra squadra = (Squadra) it.next();
						%>
						<option value="<%=squadra.getID()%>"><%=squadra.getCampionato().getNomeCampionato()%> | <%=squadra.getNomeSquadra()%></option>
						<%
							}
						%>
					</select> <input type="submit" class="btn btn-primary"
						value="Visualizza rosa">
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
	</div>
</body>
</html>
