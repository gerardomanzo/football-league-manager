<!DOCTYPE html>
<html lang="it">
<head>
	<%@ include file="headData.html"%>
	<title>Iscrizione Giocatore</title>
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
						<strong>Iscrizione Giocatore</strong>
					</h2>
				</div>
				<form action="giocatori" method="post" name="inserimentoGiocatore" onsubmit="return(insertGiocatoreCheck())">
					<div class="form-group">
						<input type="hidden" name="action" value="iscrizioneGiocatore">
						<div class="input-group">
							<input type="text" class="form-control" id="nomeGiocatore"
								name="nomeGiocatore" placeholder="Nome giocatore">
						</div>
						<div class="input-group">
							<input type="text" class="form-control" id="cognomeGiocatore"
								name="cognomeGiocatore" placeholder="Cognome giocatore">
						</div>
					</div>
					<input type="submit" class="btn btn-primary" value="Iscrivi">
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