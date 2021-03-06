<!DOCTYPE html>
<html lang="it">
<head>
	<%@ include file="headData.html"%>
	<title>Creazione Campionato</title>
</head>
<body>
	<div class="container">
		<%
			String ruolo = (String) session.getAttribute("ruolo");
			if (session.getAttribute("utente") != null && session.getAttribute("ruolo") != null
					&& ruolo.equals("amministratore")) {
		%>
		<div class="row">
			<div class="col-sm-4 offset-sm-4">
				<div class="text-xs-center">
					<h2>
						<strong>Creazione Campionato</strong>
					</h2>
				</div>
				<form action="campionati" method="post" name="creaCampionato" onsubmit="return(insertCampionatoCheck())">
					<div class="form-group">
						<input type="hidden" name="action" value="creaCampionato">
						<div class="input-group">
							<input type="text" class="form-control" id="nomeCampionato"
								name="nomeCampionato" placeholder="Nome campionato">
						</div>
						<div class="input-group">
							<input type="text" class="form-control" id="numSquadre"
								name="numSquadre" placeholder="Squadre partecipanti">
						</div>
					</div>
					<input type="submit" class="btn btn-primary"
						value="Crea campionato">
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
	<script type="text/javascript" src="js/insertCampionatoCheck.js"></script>
</body>
</html>
