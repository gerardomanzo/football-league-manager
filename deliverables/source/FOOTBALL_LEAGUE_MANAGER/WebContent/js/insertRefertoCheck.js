function insertRefertoCheck(){
	var data = document.inserimentoReferto.data.value;
	
	if(data == "" || data == null){
		alert("Campo data obbligatorio");
		document.inserimentoReferto.data.focus();
		return false;
	}
	
	var i;
	var goal;
	var assist;
	var motivazione;
	var squalifica;
	
	for(i = 0; i < 16; i++) {
		goal = document.getElementsByName("goal"+i)[0];
		assist = document.getElementsByName("assist"+i)[0];
		motivazione = document.getElementsByName("motivazione"+i)[0];
		squalifica = document.getElementsByName("squalifica"+i)[0];
		
		if(goal == null || goal.value == "") {
			alert("Non hai compilato un campo relativo al giocatore #"+(i+1));
			goal.style.borderColor = "red";
			goal.focus;
			return false;
		}else if(isNaN(goal.value) || Number(goal.value)< 0){
			alert("Deve essere un numero positivo");
			goal.style.borderColor = "red";
			goal.focus();
			return false;
		}
		
		if(assist == null || assist.value == "") {
			alert("Non hai compilato un campo relativo al giocatore #"+(i+1));
			assist.style.borderColor = "red";
			assist.focus;
			return false;
		}else if(isNaN(assist.value) || Number(assist.value)<0){
			alert("Deve essere un numero positivo");
			assist.style.borderColor = "red";
			assist.focus();
			return false;
		}
		
		if(motivazione == null || motivazione.value == "") {
			alert("Non hai compilato un campo relativo al giocatore #"+(i+1));
			motivazione.style.borderColor = "red";
			motivazione.focus;
			return false;
		}
		
		if(squalifica == null || squalifica.value == "") {
			alert("Non hai compilato un campo relativo al giocatore #"+(i+1));
			squalifica.style.borderColor = "red";
			squalifica.focus;
			return false;
		}else if(isNaN(squalifica.value)||Number(squalifica.value)<1){
			alert("Deve essere un numero positivo");
			squalifica.style.borderColor = "red";
			squalifica.focus();
			return false;
		}
	}
	document.inserimentoReferto.submit();
}