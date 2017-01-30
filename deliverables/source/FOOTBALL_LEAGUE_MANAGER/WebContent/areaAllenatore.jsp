<!DOCTYPE html>
<html lang="it">
<head>
	<%@ include file="headData.html"%>
	<title>Area Allenatore</title>
</head>
<body>
	<div class="container">
		<%
			String ruolo = (String) session.getAttribute("ruolo");
			if (session.getAttribute("utente") != null && session.getAttribute("ruolo") != null
					&& ruolo.equals("allenatore")) {
		%>
		<%@ include file="header.jsp"%>
		<div class="row">
			<div class="col-md-12">
				<div class="card-deck-wrapper">
					<div class="card-deck">
						<div class="card card-outline-success">
							<div class="card-block">
								<h4 class="card-title">Crea squadra</h4>
								<p class="card-text">Crea una nuova squadra e iscrive i
									giocatori.</p>
								<a href="creaSquadra.jsp" class="btn btn-primary">Crea!</a>
							</div>
						</div>
						<div class="card card-outline-success">
							<div class="card-block">
								<h4 class="card-title">Iscrivi squadra</h4>
								<p class="card-text">Iscrive una tua squadra ad un
									campionato.</p>
								<a href="campionati?action=iscrizioneSquadra"
									class="btn btn-primary">Iscrivi!</a>
							</div>
						</div>
						<div class="card card-outline-success">
							<div class="card-block">
								<h4 class="card-title">Inserisci giocatore</h4>
								<p class="card-text">Inserisci un giocatore alla rosa
									attuale di una tua squadra.</p>
								<a href="squadre?action=iscriviGiocatore"
									class="btn btn-primary">Inserisci!</a>
							</div>
						</div>
						<div class="card card-outline-success">
							<div class="card-block">
								<h4 class="card-title">Sostituisci giocatore</h4>
								<p class="card-text">Sostutuisci un giocatore dalla rosa di
									una tua squadra.</p>
								<a href="squadre?action=sostituisciGiocatore"
									class="btn btn-primary">Sostituisci!</a>
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
								<h4 class="card-title">Visualizza rosa</h4>
								<p class="card-text">Visualizza la rosa di una squadra.</p>
								<a href="squadre?action=visualizzaRosa" class="btn btn-primary">Visualizza!</a>
							</div>
						</div>
						<div class="card card-outline-success">
							<div class="card-block">
								<h4 class="card-title">Visualizza classifica</h4>
								<p class="card-text">Visualizza la classifica di un
									campionato.</p>
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
		<%
			} else {
		%>
		<div class="text-xs-center">
			<div class="alert alert-warning" role="alert">
				<strong>Non sei loggatoDevi prima effettuare l'accesso a Football League Manager.</strong> Ritorna alla <a href="index.jsp">Home</a>.
			</div>
		</div>
		<%
			}
		%>
	</div>
</body>
</html>