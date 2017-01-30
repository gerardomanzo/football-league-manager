function insertTeamCheck(){
	var nomeSquadra = document.creaSquadra.nomeSquadra.value;
	
	if(nomeSquadra == "" || nomeSquadra == null){
		document.creaSquadra.nomeSquadra.style.borderColor = "red";
		document.creaSquadra.nomeSquadra.focus();
		return false;
	}
	document.creaSquadra.submit();
};