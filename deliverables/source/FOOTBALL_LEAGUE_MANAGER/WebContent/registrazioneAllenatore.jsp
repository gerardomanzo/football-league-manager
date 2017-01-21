<!DOCTYPE html>
<html lang="it">
<head>
	<%@ include file="headData.html"%>
	<title>Registrazione Allenatore</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-4 offset-sm-4">
				<div class="text-xs-center">
					<h2>
						<strong>Registrazione Allenatore</strong>
					</h2>
				</div>
				<form action="utenti" method="post">
					<div class="form-group">
						<input type="hidden" name="action" value="registrazioneAllenatore">
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
	</div>
</body>
</html>