<!DOCTYPE html>
<html lang="it">
<head>
	<%@ include file="headData.html"%>
	<%@ page import="java.util.*, flm.campionati.Campionato"%>
	<%
		Collection<?> campionati = (Collection<?>) request.getAttribute("campionati");
	%>
	<title>Area Ospite</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-4 offset-sm-4">
				<%if(campionati != null && campionati.size() != 0)
				{%>
					<form action="utenti" method="post">
						<div class="text-xs-center">
							<h5>Seleziona un campionato</h5>
						</div>
						<div class="form-group">
							<div class="input-group">
								<select class="form-control" id="exampleSelect1">
									<%
									Iterator<?> it = campionati.iterator();
																
									while (it.hasNext())
									{
										Campionato c = (Campionato) it.next();
										%>
										<option><%=c.getNomeCampionato()%></option>
									<%} %>
								</select>
							</div>
						</div>
						<input type="submit" class="btn btn-primary" value="Invia">
					</form>
					<%
				}
				else
				{%>
					<p>Nessun campionato aperto!</p>
					<%
				}%>
			</div>
		</div>
	</div>
</body>
</html>