function insertCampionatoCheck(){
	var nomeCampionato = document.creaCampionato.nomeCampionato.value;
	var numSquadre = document.creaCampionato.numSquadre.value;
	
	if(nomeCampionato == "" || nomeCampionato == null){
		alert("Campo nome campionato obbligatorio");
		document.creaCampionato.nomeCampionato.focus();
		return false;
	}
	
	if(numSquadre == "" || numSquadre == null){
		alert("Campo numero squadre obbligatorio");
		document.creaCampionato.numSquadre.focus();
		return false;
	}
	document.creaCampionato.submit();
};