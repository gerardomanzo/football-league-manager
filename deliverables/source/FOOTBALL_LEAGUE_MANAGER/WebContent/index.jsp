<!DOCTYPE html>
<html lang="it">
<head>
	<%@ include file="headData.html"%>
	<title>Home Page</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-5 offset-md-1">
				<div class="jumbotron jumbotron-fluid">
					<div class="container">
						<h3 class="text-xs-center">Football League Manager</h3>
					</div>
				</div>
			</div>
			<div class="col-md-4 offset-md-1">
				<form action="utenti" method="post" class="col-md-12" name="login" onsubmit="return(loginCheck())">
					<div class="text-xs-center">
						<h5>Accedi a FLM</h5>
					</div>
					<div class="form-group">
						<input type="hidden" name="action" value="loginUtente">
						<div class="input-group">
							<div class="input-group-addon">
								<span class="fa fa-at"></span>
							</div>
							<input type="text" class="form-control" id="email" name="email" placeholder="E-mail">
						</div>
						<div class="input-group">
							<div class="input-group-addon">
								<span class="fa fa-key"></span>
							</div>
							<input type="password" class="form-control" id="password" name="password" placeholder="Password">
						</div>
					</div>
					<div class="row">
						<div class="col-md-5">
							<input type="submit" class="btn btn-primary" value="Accedi">
						</div>
						<div class="col-md-7">
							<a href="areaOspite.jsp" class="btn btn-outline-primary">Accedi come ospite</a>
						</div>
					</div>
					<a href="registrazioneAllenatore.jsp">Registra allenatore</a>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="js/loginCheck.js"></script>
</body>
</html>
