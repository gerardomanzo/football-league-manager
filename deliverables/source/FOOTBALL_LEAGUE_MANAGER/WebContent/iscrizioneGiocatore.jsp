<!DOCTYPE html>
<html lang="it">
<head>
	<%@ include file="headData.html"%>
	<title>Iscrizione Giocatore</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-4 offset-sm-4">
				<form action="giocatori" method="post">
					<div class="text-xs-center">
						<h5>Iscrizione giocatore</h5>
					</div>
					<div class="form-group">
						<input type="hidden" name="action" value="iscrizioneGiocatore">
						<div class="input-group">
							<input type="text" class="form-control" id="nomeGiocatore" name="nomeGiocatore" placeholder="Nome giocatore">
						</div>
						<div class="input-group">
							<input type="text" class="form-control" id="cognomeGiocatore" name="cognomeGiocatore" placeholder="Cognome giocatore">
						</div>
					</div>
					<input type="submit" class="btn btn-primary" value="Iscrivi">
				</form>
			</div>
		</div>
	</div>
</body>
</html>