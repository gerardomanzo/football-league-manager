function insertRefertoCheck(){
	var data = document.inserimentoReferto.data.value;
	var goal = document.inserimentoReferto.goal.value;
	var assist = document.inserimentoReferto.assist.value;
	var motivazione = document.inserimentoReferto.motivazione.value;
	var squalifica = document.inserimentoReferto.squalifica.value;
	
	if(data == "" || data == null){
		alert("Campo data obbligatorio");
		document.inserimentoReferto.data.focus();
		return false;
	}
	if(goal == "" || goal == null){
		alert("Campo goal obbligatorio");
		document.inserimentoReferto.goal.focus();
		return false;
	}
	if(assist == "" || assist == null){
		alert("Campo assist obbligatorio");
		document.inserimentoReferto.assist.focus();
		return false;
	}
	if(motivazione == "" || motivazione == null){
		alert("Campo motivazione obbligatorio");
		document.inserimentoReferto.motivazione.focus()
		return false;
	}
	if(squalifica == "" || squalifica == null){
		alert("Campo squalifica obbligatorio");
		document.inserimentoReferto.squalifica.focus();
		return false;
	}
	document.inserimentoReferto.submit();
};