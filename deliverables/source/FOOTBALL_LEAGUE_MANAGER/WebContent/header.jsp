<div class="row">
	<div class="col-md-3">
		<img alt="FLM" src="imgs/logo.png" class="col-md-5"
			style="margin-bottom: 16px">
	</div>
	<div class="col-md-6">
		<h1>Football League Manager</h1>
	</div>
	<div class="col-md-3">
		<%
			if (session.getAttribute("utente") != null) {
		%>
		<a href="utenti?action=logout"><i class="fa fa-power-off fa-4x float-xs-right"></i></a>
		<%
			}
		%>
	</div>
</div>