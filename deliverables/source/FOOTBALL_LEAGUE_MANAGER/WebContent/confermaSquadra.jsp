<!DOCTYPE html>
<html lang="it">
<head>
	<%@ include file="headData.html"%>
	<%@ page import="java.util.*, flm.squadre.Squadra"%>
	<%
		Collection<?> squadre = (Collection<?>) request.getAttribute("squadreConferma");
	%>
	<title>Conferma Squadra</title>
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
						<strong>Conferma Squadra</strong>
					</h2>
				</div>
				<%
					if (squadre != null && squadre.size() > 0) {
				%>
				<form action="squadre" method="post">
					<input type="hidden" name="action" value="confermaSquadra">
					<div class="form-group">
						<select class="form-control" name="squadra">
							<%
								Iterator<?> it = squadre.iterator();
										while (it.hasNext()) {
											Squadra squadra = (Squadra) it.next();
							%>
							<option value="<%=squadra.getID()%>"><%=squadra.getNomeSquadra()%></option>
							<%
								}
							%>
						</select>
					</div>
					<input type="submit" class="btn btn-primary"
						value="Conferma squadra">
				</form>
				<%
					} else {
				%>
				<div class="text-xs-center">
					<h5>Nessuna squadra.</h5>
				</div>
				<%
					}
				%>
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