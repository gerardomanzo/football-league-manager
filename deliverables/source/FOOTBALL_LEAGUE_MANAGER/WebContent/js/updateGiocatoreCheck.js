function updateGiocatoreCheck(){
	var nuovoNome = document.modificaGiocatore.nuovoNome.value;
	var nuovoCognome = document.modificaGiocatore.nuovoCognome.value;
	
	if(nuovoNome == "" || nuovoNome == null){
		document.modificaGiocatore.nuovoNome.style.borderColor = "red";
		document.modificaGiocatore.nuovoNome.focus();
		return false;
	}
	
	if(nuovoCognome == "" || nuovoCognome ==  null){
		document.modificaGiocatore.nuovoCognome.style.borderColor = "red";
		document.modificaGiocatore.nuovoCognome.focus();
		return false;
	}
	document.modificaGiocatore.submit();
};





