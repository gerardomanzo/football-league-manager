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
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<form action="squadre" method="post">
					<div class="text-xs-center">
						<h5>Sostituzione Giocatore</h5>
					</div>
					<input type="hidden" name="action" value="sostituisciGiocatore">
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
						<label>Giocatore da rimuovere</label>
						<select class="form-control" name="giocatore">
							<%
								Iterator<?> it2 = giocatori.iterator();
								while (it2.hasNext()) {
									Giocatore giocatore = (Giocatore) it2.next();%>

									<option value="<%=giocatore.getID()%>"><%=giocatore.getCognome()%> <%=giocatore.getNome()%></option>
									<%
								}
							%>
						</select>
					</div>
					<div class="form-group">
						<label>Giocatore da inserire</label>
						<select class="form-control" name="giocatoreNuovo">
							<%
								Iterator<?> it3 = giocatori.iterator();
								while (it3.hasNext()) {
									Giocatore giocatore = (Giocatore) it3.next();%>

									<option value="<%=giocatore.getID()%>"><%=giocatore.getCognome()%> <%=giocatore.getNome()%></option>
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