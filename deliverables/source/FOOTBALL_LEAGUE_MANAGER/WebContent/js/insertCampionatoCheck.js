function insertCampionatoCheck(){
	var nomeCampionato = document.creaCampionato.nomeCampionato.value;
	var numSquadre = document.creaCampionato.numSquadre.value;
	
	if(nomeCampionato == "" || nomeCampionato == null){
		document.creaCampionato.nomeCampionato.style.borderColor = "red";
		document.creaCampionato.nomeCampionato.focus();
		return false;
	}
	
	if(numSquadre == "" || numSquadre == null){
		document.creaCampionato.numSquadre.style.borderColor = "red";
		document.creaCampionato.numSquadre.focus();
		return false;
	}
	else if(isNaN(numSquadre)||Number(numSquadre)<=0){
		alert("Deve essere un numero positivo");
		document.creaCampionato.numSquadre.style.borderColor = "red";
		document.creaCampionato.numSquadre.focus();
		return false;
	}
	document.creaCampionato.submit();
};