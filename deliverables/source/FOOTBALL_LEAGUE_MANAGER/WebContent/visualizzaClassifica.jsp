<!DOCTYPE html>
<html lang="it">
<head>
	<%@ include file="headData.html"%>
	<%@ page import="java.util.*, flm.squadre.Squadra"%>
	<%
		Collection<?> squadre = (Collection<?>) request.getAttribute("classifica");
	%>
	<title>Visualizza Classifica</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-10 offset-md-1">
				<div class="text-xs-center">
					<h2>
						<strong>Visualizza Classifica</strong>
					</h2>
				</div>
				<%
					if (squadre != null && squadre.size() > 0) {
				%>
				<div class="text-xs-center">
					<h4>
						<strong>Classifica</strong>
					</h4>
				</div>
				<table class="table table-responsive">
					<thead class="bg-success">
						<tr>
							<th>Posizione</th>
							<th>Nome Squadra</th>
							<th>Partite giocate</th>
							<th>Punti</th>
							<th>Vittorie</th>
							<th>Pareggi</th>
							<th>Sconfitte</th>
							<th>Goal fatti</th>
							<th>Goal subiti</th>
						</tr>
					</thead>
					<tbody>
						<%
							Iterator<?> it = squadre.iterator();
							int i = 1;

							while (it.hasNext()) {
								Squadra squadra = (Squadra) it.next();
						%>

						<tr>
							<th><%=i%></th>
							<th><%=squadra.getNomeSquadra()%></th>
							<th><%=squadra.getVittorie() + squadra.getPareggi() + squadra.getSconfitte()%></th>
							<th><%=squadra.getVittorie() * 3 + squadra.getPareggi()%></th>
							<th><%=squadra.getVittorie()%></th>
							<th><%=squadra.getPareggi()%></th>
							<th><%=squadra.getSconfitte()%></th>
							<th><%=squadra.getGoalFatti()%></th>
							<th><%=squadra.getGoalSubiti()%></th>
						</tr>
						<%
								i++;
							}
						%>
					</tbody>
				</table>
				<%
					} else {
				%>
				<div class="text-xs-center">
					<h5>Nessuna classifica.</h5>
				</div>
				<%
					}
				%>
			</div>
		</div>
	</div>
</body>
</html>