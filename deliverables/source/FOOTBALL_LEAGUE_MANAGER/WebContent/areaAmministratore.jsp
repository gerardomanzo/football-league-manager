<!DOCTYPE html>
<html lang="it">
<head>
	<%@ include file="headData.html"%>
	<title>Area Amministratore</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<div class="card-deck-wrapper">
					<div class="card-deck">
						<div class="card card-outline-success">
							<div class="card-block">
								<h4 class="card-title">Crea campionato</h4>
								<p class="card-text">Crea un nuovo campionato e apre le
									iscrizioni.</p>
								<a href="creaCampionato.jsp" class="btn btn-primary">Crea!</a>
							</div>
						</div>
						<div class="card card-outline-success">
							<div class="card-block">
								<h4 class="card-title">Chiudi campionato</h4>
								<p class="card-text">Chiudi un campionato e assegna il
									premio al vincitore.</p>
								<a href="#" class="btn btn-primary">Chiudi!</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="card-deck-wrapper">
					<div class="card-deck">
						<div class="card card-outline-success">
							<div class="card-block">
								<h4 class="card-title">Registra arbitro</h4>
								<p class="card-text">Registra un nuovo arbitro al sistema.</p>
								<a href="registrazioneArbitro.jsp" class="btn btn-primary">Registra!</a>
							</div>
						</div>
						<div class="card card-outline-success">
							<div class="card-block">
								<h4 class="card-title">Assegna arbitro</h4>
								<p class="card-text">Assegna un arbitro ad una partita.</p>
								<a href="#" class="btn btn-primary">Assegna!</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="card-deck-wrapper">
					<div class="card-deck">
						<div class="card card-outline-success">
							<div class="card-block">
								<h4 class="card-title">Conferma squadra</h4>
								<p class="card-text">Conferma l'iscrizione di una squdra.</p>
								<a href="#" class="btn btn-primary">Conferma!</a>
							</div>
						</div>
						<div class="card card-outline-success">
							<div class="card-block">
								<h4 class="card-title">Iscrivi giocatore</h4>
								<p class="card-text">Iscrive un giocatore al sistema</p>
								<a href="iscrizioneGiocatore.jsp" class="btn btn-primary">Iscrivi!</a>
							</div>
						</div>
						<div class="card card-outline-success">
							<div class="card-block">
								<h4 class="card-title">Modifica giocatore</h4>
								<p class="card-text">Aggiorna le informazioni di un
									giocatore.</p>
								<a href="giocatori?action=getGiocatori" class="btn btn-primary">Modifica!</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>