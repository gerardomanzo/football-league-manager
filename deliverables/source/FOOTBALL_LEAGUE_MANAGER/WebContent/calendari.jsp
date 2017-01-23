<!DOCTYPE html>
<html lang="it">
<head>
	<%@ include file="headData.html"%>
	<%@ page import="java.util.*, flm.campionati.Campionato"%>
	<%
		Collection<?> campionati = (Collection<?>) request.getAttribute("campionati");
	%>
	<title>Visualizza Calendario</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="text-xs-center">
					<h2>
						<strong>Visualizza Calendario</strong>
					</h2>
				</div>
				<%
					if (campionati != null && campionati.size() > 0) {
				%>
				<form action="campionati" method="post">
					<input type="hidden" name="action" value="visualizzaCalendario">
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
						value="Visualizza calendario">
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