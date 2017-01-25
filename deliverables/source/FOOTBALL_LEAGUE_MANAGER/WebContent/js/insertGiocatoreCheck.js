function insertGiocatoreCheck() {
	var nomeGiocatore = document.inserimentoGiocatore.nomeGiocatore.value;
	var cognomeGiocatore = document.inserimentoGiocatore.cognomeGiocatore.value;
	
	if(nomeGiocatore == "" || nomeGiocatore == null){
		alert ("Campo nome giocatore obbligatorio");
		document.inserimentoGiocatore.nomeGiocatore.focus();
		return false;
	}
	
	if(cognomeGiocatore == "" || cognomeGiocatore == null){
		alert ("Campo cognome giocatore obbligatorio");
		document.inserimentoGiocatore.cognomeGiocatore.focus();
		return false;
	}
	document.inserimentoGiocatore.submit();
};