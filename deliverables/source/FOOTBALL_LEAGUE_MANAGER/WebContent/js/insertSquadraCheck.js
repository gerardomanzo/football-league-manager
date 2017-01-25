function insertTeamCheck(){
	var nomeSquadra = document.creaSquadra.nomeSquadra.value;
	
	if(nomeSquadra == "" || nomeSquadra == null){
		alert ("Campo nome squadra obbligatorio");
		document.creaSquadra.nomeSquadra.focus();
		return false;
	}
	document.creaSquadra.submit();
};