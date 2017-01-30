<!DOCTYPE html>
<html lang="it">
<head>
<%@ include file="headData.html"%>
<%@ page import="java.util.*, flm.giocatori.Giocatore"%>
<%
	Collection<?> rosaCasa = (Collection<?>) request.getAttribute("rosaCasa");
	Collection<?> rosaOspite = (Collection<?>) request.getAttribute("rosaOspite");
	int id_partita = (int) request.getAttribute("partita");
%>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<title>Registrazione Referto</title>
</head>
<body>
	<div class="container">
		<%
			String ruolo = (String) session.getAttribute("ruolo");
			if (session.getAttribute("utente") != null && session.getAttribute("ruolo") != null
					&& ruolo.equals("arbitro")) {
		%>
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="text-xs-center">
					<h2>
						<strong>Registrazione Referto</strong>
					</h2>
				</div>
				<%
					if (rosaCasa != null && rosaCasa.size() > 0 && rosaOspite != null && rosaOspite.size() > 0) {
				%>
				<form action="partite" method="post" name="inserimentoReferto" onsubmit="return(insertRefertoCheck())">
					<input type="hidden" name="action" value="inserimentoReferto">
					<input type="hidden" name="partita" value="<%=id_partita%>">
					<div class="input-group">
						<input type="text" class="form-control" name="data" placeholder="Data partita">
					</div>
					<div id="accordion" role="tablist" aria-multiselectable="true">
						<%
						int i = 0;
							Iterator<?> it = rosaCasa.iterator();
								while (it.hasNext()) {
									Giocatore giocatore = (Giocatore) it.next();
						%>
						<div class="card">
							<div class="card-header" role="tab" id="heading<%=i%>">
								<h5 class="mb-0">
									<a data-toggle="collapse" data-parent="#accordion" href="#collapse<%=i%>" aria-expanded="true" aria-controls="collapse<%=i%>"> <%=giocatore.getCognome()%> <%=giocatore.getNome()%></a>
								</h5>
							</div>

							<div id="collapse<%=i%>" class="collapse show"
								role="tabpanel" aria-labelledby="heading<%=i%>">
								<div class="card-block">
									<input type="hidden" name="giocatore<%=i%>" value="<%=giocatore.getID()%>">
									<div class="input-group">
										<input type="text" class="form-control" name="goal<%=i%>" placeholder="Num. goal">
									</div>
									<div class="input-group">
										<input type="text" class="form-control" name="assist<%=i%>" placeholder="Num. assist">
									</div>
									<label>Cartellino</label>
									<select class="form-control" name="cartellino<%=i%>">
										<option value="0">Nessuno</option>
										<option value="1">Giallo</option>
										<option value="2">Doppio giallo</option>
										<option value="3">Rosso</option>
									</select>
									<div class="input-group">
										<input type="text" class="form-control" name="motivazione<%=i%>" placeholder="Motivazione cartellino">
									</div>
									<div class="input-group">
										<input type="text" class="form-control" name="squalifica<%=i%>" placeholder="Durata squalifica">
									</div>
								</div>
							</div>
						</div>
						<%
							i++;
							}
						%>
						<%
							Iterator<?> it2 = rosaOspite.iterator();
								while (it2.hasNext()) {
									Giocatore giocatore = (Giocatore) it2.next();
						%>
						<div class="card">
							<div class="card-header" role="tab" id="heading<%=i%>">
								<h5 class="mb-0">
									<a data-toggle="collapse" data-parent="#accordion" href="#collapse<%=i%>" aria-expanded="true" aria-controls="collapse<%=i%>"> <%=giocatore.getCognome()%> <%=giocatore.getNome()%></a>
								</h5>
							</div>

							<div id="collapse<%=i%>" class="collapse show"
								role="tabpanel" aria-labelledby="heading<%=i%>">
								<div class="card-block">
									<input type="hidden" name="giocatore<%=i%>" value="<%=giocatore.getID()%>">
									<div class="input-group">
										<input type="text" class="form-control" name="goal<%=i%>" placeholder="Num. goal">
									</div>
									<div class="input-group">
										<input type="text" class="form-control" name="assist<%=i%>" placeholder="Num. assist">
									</div>
									<label>Cartellino</label>
									<select class="form-control" name="cartellino<%=i%>">
										<option value="0">Nessuno</option>
										<option value="1">Giallo</option>
										<option value="2">Doppio giallo</option>
										<option value="3">Rosso</option>
									</select>
									<div class="input-group">
										<input type="text" class="form-control" name="motivazione<%=i%>" placeholder="Motivazione cartellino">
									</div>
									<div class="input-group">
										<input type="text" class="form-control" name="squalifica<%=i%>" placeholder="Durata squalifica">
									</div>
								</div>
							</div>
						</div>
						<%
							i++;
							}
						%>
					</div>
					<input type="submit" class="btn btn-primary"
						value="Registra Referto">
				</form>
				<%
					} else {
				%>
				<div class="text-xs-center">
					<h5>Nessuna partita.</h5>
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
	<script type="text/javascript" src="js/insertRefertoCheck.js"></script>
</body>
</html>
