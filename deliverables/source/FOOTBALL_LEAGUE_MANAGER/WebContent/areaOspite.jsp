<!DOCTYPE html>
<html lang="it">
<head>
	<%@ include file="headData.html"%>
	<title>Area Ospite</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="card-deck-wrapper">
					<div class="card-deck">
						<div class="card card-outline-success">
							<div class="card-block">
								<h4 class="card-title">Visualizza rosa</h4>
								<p class="card-text">Visualizza la rosa di una squadra.</p>
								<a href="squadre?action=visualizzaRosa" class="btn btn-primary">Visualizza!</a>
							</div>
						</div>
						<div class="card card-outline-success">
							<div class="card-block">
								<h4 class="card-title">Visualizza classifica</h4>
								<p class="card-text">Visualizza la classifica di un campionato.</p>
								<a href="campionati?action=visualizzaClassifica" class="btn btn-primary">Visualizza!</a>
							</div>
						</div>
						<div class="card card-outline-success">
							<div class="card-block">
								<h4 class="card-title">Visualizza calendario</h4>
								<p class="card-text">Visualizza il calendario delle partite.</p>
								<a href="campionati?action=visualizzaCalendario" class="btn btn-primary">Visualizza!</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
