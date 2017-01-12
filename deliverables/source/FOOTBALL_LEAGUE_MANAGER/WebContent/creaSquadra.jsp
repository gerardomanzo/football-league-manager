<!DOCTYPE html>
<html lang="it">
<head>
	<%@ include file="headData.html"%>
	<title>Creazione squadra</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-6 offset-md-3">
				<form action="squadre" method="post">
					<div class="text-xs-center">
						<h5>Creazione squadra</h5>
					</div>
					<input type="hidden" name="action" value="creaSquadra">
					<div class="form-group">
						<div class="input-group">
							<input type="text" class="form-control" id="nome" name="nomeSquadra" placeholder="Nome squadra">
						</div>
					</div>
					<input type="submit" class="btn btn-primary" value="Crea squadra">
				</form>
			</div>
		</div>
	</div>
</body>
</html>