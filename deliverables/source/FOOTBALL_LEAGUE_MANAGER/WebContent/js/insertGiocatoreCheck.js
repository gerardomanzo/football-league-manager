function insertGiocatoreCheck() {
	var nomeGiocatore = document.inserimentoGiocatore.nomeGiocatore.value;
	var cognomeGiocatore = document.inserimentoGiocatore.cognomeGiocatore.value;
	
	if(nomeGiocatore == "" || nomeGiocatore == null){
		document.inserimentoGiocatore.nomeGiocatore.style.borderColor = "red";
		document.inserimentoGiocatore.nomeGiocatore.focus();
		return false;
	}
	
	if(cognomeGiocatore == "" || cognomeGiocatore == null){
		document.inserimentoGiocatore.cognomeGiocatore.style.borderColor = "red";
		document.inserimentoGiocatore.cognomeGiocatore.focus();
		return false;
	}
	document.inserimentoGiocatore.submit();
};