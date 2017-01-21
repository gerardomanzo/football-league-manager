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
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<form action="campionati" method="post">
					<div class="text-xs-center">
						<h5>Iscrivi Squadra</h5>
					</div>
					<input type="hidden" name="action" value="iscrizioneSquadra">
					<div class="form-group">
						<select class="form-control" name="squadra">
							<%
								Iterator<?> it = squadre.iterator();
								while (it.hasNext()) {
									Squadra squadra = (Squadra) it.next();%>

									<option value="<%=squadra.getID()%>"><%=squadra.getNomeSquadra()%></option>
									<%
								}
							%>
						</select>
					</div>
					<div class="form-group">
						<select class="form-control" name="campionato">
							<%
								Iterator<?> it2 = campionati.iterator();
								while (it2.hasNext()) {
									Campionato campionato = (Campionato) it2.next();%>

									<option value="<%=campionato.getID()%>"><%=campionato.getNomeCampionato()%></option>
									<%
								}
							%>
						</select>
					</div>
					<input type="submit" class="btn btn-primary" value="Iscrizione squadra">
				</form>
			</div>
		</div>
	</div>
</body>
</html>