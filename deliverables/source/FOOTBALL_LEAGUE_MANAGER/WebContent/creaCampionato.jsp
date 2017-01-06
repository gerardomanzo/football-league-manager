<!DOCTYPE html>
<html lang="it">
<head>
	<%@ include file="headData.html"%>
	<title>Creazione Campionato</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-4 offset-sm-4">
				<form action="campionati" method="post">
					<div class="text-xs-center">
						<h5>Creazione campionato</h5>
					</div>
					<div class="form-group">
						<input type="hidden" name="action" value="creaCampionato">
						<div class="input-group">
							<input type="text" class="form-control" id="nomeCampionato" name="nomeCampionato" placeholder="Nome campionato">
						</div>
						<div class="input-group">
							<input type="text" class="form-control" id="numSquadre" name="numSquadre" placeholder="Squadre partecipanti">
						</div>
						<div class="input-group">
							<input type="text" class="form-control" id="quotaIscrizione" name="quotaIscrizione" placeholder="Quota iscrizione">
						</div>
					</div>
					<input type="submit" class="btn btn-primary" value="Crea campionato">
				</form>
			</div>
		</div>
	</div>
</body>
</html>