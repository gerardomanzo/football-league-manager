<!DOCTYPE html>
<html lang="it">
<head>
		<%@ include file="headData.html"%>
		<%@ page
				import="java.util.*, flm.campionati.Campionato, flm.partite.Partita, flm.squadre.Squadra"%>
		<%
				Campionato campionato = (Campionato) request.getAttribute("campionato");
		%>
		<title>Visualizza Calendario</title>
</head>
<body>
		<div class="container">
				<div class="row">
						<div class="col-md-6 offset-md-3">
								<div class="text-xs-center">
										<h2>
												<strong>Visualizza Calendario</strong>
										</h2>
								</div>
								<%
										if (campionato != null) {
								%>
								<div class="text-xs-center">
										<h4>
												<strong><%=campionato.getNomeCampionato()%></strong>
										</h4>
								</div>
								<table class="table table-responsive">
										<thead class="bg-success">
												<tr>
														<th>Giornata</th>
														<th>Data</th>
														<th>Squadra Casa</th>
														<th>Squadra Ospite</th>
														<th>Risultato</th>
												</tr>
										</thead>
										<tbody>
												<%
														Iterator<Partita> it = campionato.getCalendario().iterator();

																while (it.hasNext()) {
																		Partita partita = it.next();
												%>

												<tr>
														<th scope="row"><%=partita.getGiornata()%></th>
														<td><%=(partita.getData()==null)?"Da giocare o referto mancante":partita.getData()%></td>
														<td><%=partita.getSquadraCasa().getNomeSquadra()%></td>
														<td><%=partita.getSquadraOspite().getNomeSquadra()%></td>
														<td><%=(partita.getData()==null)?"Da giocare o referto mancante":""+partita.getGoalCasa() + "-" + partita.getGoalOspite()%></td>
												</tr>
												<%
														}
												%>
										</tbody>
								</table>
								<%
										} else {
								%>
								<div class="text-xs-center">
										<h5>Nessuna calendario.</h5>
								</div>
								<%
										}
								%>
						</div>
				</div>
		</div>
</body>