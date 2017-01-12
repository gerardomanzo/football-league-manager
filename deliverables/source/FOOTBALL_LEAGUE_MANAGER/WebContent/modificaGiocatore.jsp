<!DOCTYPE html>
<html lang="it">
<head>
	<%@ include file="headData.html"%>
	<%@ page import="java.util.*, flm.giocatori.Giocatore"%>
	<%
		Collection<?> giocatori = (Collection<?>) request.getAttribute("giocatori");
	%>
	<title>Modifica Giocatore</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<form action="giocatori" method="post">
					<div class="text-xs-center">
						<h5>Modifica informazioni giocatore</h5>
					</div>
					<input type="hidden" name="action" value="modificaGiocatore">
					<div class="form-group">
						<select class="form-control" name="giocatore">
							<%
								Iterator<?> it = giocatori.iterator();
								while (it.hasNext()) {
									Giocatore giocatore = (Giocatore) it.next();%>

									<option value="<%=giocatore.getID()%>"><%=giocatore.getCognome()%> <%=giocatore.getNome()%></option>
									<%
								}
							%>
						</select>
					</div>
					<div class="form-group">
						<input type="text" class="form-control" name="nuovoNome" placeholder="Nome nuovo">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" name="nuovoCognome" placeholder="Cognome nuovo">
					</div>
					<input type="submit" class="btn btn-primary" value="Modifica informazioni">
				</form>
			</div>
		</div>
	</div>
</body>
</html>