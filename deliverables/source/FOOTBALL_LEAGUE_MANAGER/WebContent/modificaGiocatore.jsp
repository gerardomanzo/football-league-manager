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
		<%
			String ruolo = (String) session.getAttribute("ruolo");
			if (session.getAttribute("utente") != null && session.getAttribute("ruolo") != null
					&& ruolo.equals("amministratore")) {
		%>
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="text-xs-center">
					<h2>
						<strong>Modifica Giocatore</strong>
					</h2>
				</div>
				<%
					if (giocatori != null && giocatori.size() > 0) {
				%>
				<form action="giocatori" method="post" name="modificaGiocatore" onsubmit="return(updateGiocatoreCheck())">
					<input type="hidden" name="action" value="modificaGiocatore">
					<div class="form-group">
						<select class="form-control" name="giocatore">
							<%
								Iterator<?> it = giocatori.iterator();
										while (it.hasNext()) {
											Giocatore giocatore = (Giocatore) it.next();
							%>

							<option value="<%=giocatore.getID()%>"><%=giocatore.getCognome()%>
								<%=giocatore.getNome()%></option>
							<%
								}
							%>
						</select>
					</div>
					<div class="form-group">
						<input type="text" class="form-control" name="nuovoNome"
							placeholder="Nome nuovo">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" name="nuovoCognome"
							placeholder="Cognome nuovo">
					</div>
					<input type="submit" class="btn btn-primary"
						value="Modifica informazioni">
					<%
						} else {
					%>
					<div class="text-xs-center">
						<h5>Nessun giocatore.</h5>
					</div>

					<%
						}
					%>
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