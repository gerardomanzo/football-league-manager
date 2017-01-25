<!DOCTYPE html>
<html lang="it">
<head>
	<%@ include file="headData.html"%>
	<title>Registrazione Arbitro</title>
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
						<strong>Registrazione Arbitro</strong>
					</h2>
				</div>
				<form action="utenti" method="post" name="registrazione" onsubmit="return(registerCheck())">
					<div class="form-group">
						<input type="hidden" name="action" value="registrazioneArbitro">
						<div class="input-group">
							<input type="text" class="form-control" id="nome" name="nome"
								placeholder="Nome">
						</div>
						<div class="input-group">
							<input type="text" class="form-control" id="cognome"
								name="cognome" placeholder="Cognome">
						</div>
						<div class="input-group">
							<div class="input-group-addon">
								<span class="fa fa-at"></span>
							</div>
							<input type="text" class="form-control" id="email" name="email"
								placeholder="E-mail">
						</div>
						<div class="input-group">
							<div class="input-group-addon">
								<span class="fa fa-key"></span>
							</div>
							<input type="password" class="form-control" id="password"
								name="password" placeholder="Password">
						</div>
						<div class="input-group">
							<input type="password" class="form-control" id="passwordRip"
								name="passwordRip" placeholder="Ripeti password">
						</div>
					</div>
					<input type="submit" class="btn btn-primary" value="Registrati">
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