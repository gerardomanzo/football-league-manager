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
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<form action="partite" method="post">
					<div class="text-xs-center">
						<h5>Assegnazione Arbitro</h5>
					</div>
					<input type="hidden" name="action" value="assegnaArbitro">
					<div class="form-group">
						<select class="form-control" name="arbitro">
							<%
								Iterator<?> it = arbitri.iterator();
								while (it.hasNext()) {
									Arbitro arbitro = (Arbitro) it.next();%>

									<option value="<%=arbitro.getID()%>"><%=arbitro.getCognome()%> <%=arbitro.getNome()%></option>
									<%
								}
							%>
						</select>
					</div>
					<div class="form-group">
						<select class="form-control" name="partita">
							<%
								Iterator<?> it2 = partite.iterator();
								while (it2.hasNext()) {
									Partita partita = (Partita) it2.next();%>

									<option value="<%=partita.getID()%>"><%=partita.getCampionato().getNomeCampionato()%> | <%=partita.getSquadraCasa().getNomeSquadra()%> - <%=partita.getSquadraOspite().getNomeSquadra()%></option>
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