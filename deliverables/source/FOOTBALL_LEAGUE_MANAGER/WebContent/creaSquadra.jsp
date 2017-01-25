<!DOCTYPE html>
<html lang="it">
<head>
	<%@ include file="headData.html"%>
	<title>Creazione Squadra</title>
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
						<strong>Creazione Squadra</strong>
					</h2>
				</div>
				<form action="squadre" method="post" name="creaSquadra" onsubmit="return(insertSquadraCheck())">
					<input type="hidden" name="action" value="creaSquadra">
					<div class="form-group">
						<div class="input-group">
							<input type="text" class="form-control" id="nome"
								name="nomeSquadra" placeholder="Nome squadra">
						</div>
					</div>
					<input type="submit" class="btn btn-primary" value="Crea squadra">
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