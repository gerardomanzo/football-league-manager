<!DOCTYPE html>
<html lang="it">
<head>
       <%@ include file="headData.html"%>
       <%@ page
               import="java.util.*, flm.campionati.Campionato, flm.giocatori.Giocatore, flm.squadre.Squadra"%>
       <%
               Squadra squadra = (Squadra) request.getAttribute("squadra");
       %>
       <title>Visualizza Rosa</title>
</head>
<body>
       <div class="container">
               <div class="row">
                       <div class="col-md-10 offset-md-1">
                               <div class="text-xs-center">
                                       <h2>
                                               <strong>Visualizza Rosa</strong>
                                       </h2>
                               </div>
                               <%
                                       if (squadra != null) {
                               %>
                               <div class="text-xs-center">
                                       <h4>
                                               <strong><%=squadra.getNomeSquadra()%></strong>
                                       </h4>
                               </div>
                               <table class="table">
                                       <thead class="bg-success">
                                               <tr>
                                                       <th>Cognome</th>
                                                       <th>Nome</th>
                                               </tr>
                                       </thead>
                                       <tbody>
                                               <%
                                                       Iterator<Giocatore> it = squadra.getRosa().iterator();

                                                               while (it.hasNext()) {
                                                                       Giocatore giocatore = it.next();
                                               %>

                                               <tr>
                                                       <td><%=giocatore.getCognome()%></td>
                                                       <td><%=giocatore.getNome()%></td>
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
