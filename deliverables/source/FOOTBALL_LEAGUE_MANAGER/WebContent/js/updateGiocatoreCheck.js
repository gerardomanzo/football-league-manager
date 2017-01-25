function updateGiocatoreCheck(){
	var nuovoNome = document.modificaGiocatore.nuovoNome.value;
	var nuovoCognome = document.modificaGiocatore.nuovoCognome.value;
	
	if(nuovoNome == "" || nuovoNome == null){
		alert ("Campo nuovo nome obbligatorio");
		document.modificaGiocatore.nuovoNome.focus();
		return false;
	}
	
	if(nuovoCognome == "" || nuovoCognome ==  null){
		alert ("Campo nuovo cognome obbligatorio");
		document.modificaGiocatore.nuovoCognome.focus();
		return false;
	}
	document.modificaGiocatore.submit();
};





