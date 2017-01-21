<!DOCTYPE html>
<html lang="it">
<head>
	<%@ include file="headData.html"%>
	<title>Area Arbitro</title>
</head>
<body>
	<div class="container">
		<%
			String ruolo = (String) session.getAttribute("ruolo");
			if (session.getAttribute("utente") != null && session.getAttribute("ruolo") != null
					&& ruolo.equals("arbitro")) {
		%>
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="card-deck-wrapper">
					<div class="card-deck">
						<div class="card card-outline-success">
							<div class="card-block">
								<h4 class="card-title">Registra referto</h4>
								<p class="card-text">Registra il referto di una partita.</p>
								<a href="#" class="btn btn-primary">Registra!</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%
			} else {
		%>
		<div class="text-xs-center">
			<div class="alert alert-warning" role="alert">
				<strong>Devi prima effettuare l'accesso a Football League Manager.</strong> Ritorna alla <a href="index.jsp">Home</a>.
			</div>
		</div>
		<%
			}
		%>
	</div>
</body>
</html>