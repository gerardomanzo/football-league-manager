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
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<form action="campionati" method="post">
					<div class="text-xs-center">
						<h5>Chiusura Campionato</h5>
					</div>
					<input type="hidden" name="action" value="chiusuraCampionato">
					<div class="form-group">
						<select class="form-control" name="campionato">
							<%
								Iterator<?> it = campionati.iterator();
								while (it.hasNext()) {
									Campionato campionato = (Campionato) it.next();%>

									<option value="<%=campionato.getID()%>"><%=campionato.getNomeCampionato()%></option>
									<%
								}
							%>
						</select>
					</div>
					<input type="submit" class="btn btn-primary" value="Conferma scelta">
				</form>
			</div>
		</div>
	</div>
</body>
</html>