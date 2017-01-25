function registerCheck() {
	var nome = document.registrazione.nome.value;
	var cognome = document.registrazione.cognome.value;
	var email = document.registrazione.email.value;
	var password = document.registrazione.password.value;
	var passwordrip = document.registrazione.passwordRip.value;
	
	if(nome == "" || nome == null){
		alert ("Campo nome obbligatorio");
		document.registrazione.nome.focus();
		return false;
	}
	
	if (cognome == "" || cognome == null){
		alert ("Campo cognome obbligatorio");
		document.registrazione.cognome.focus();
		return false;
	}
	
	if(email == "" || email == null){
		alert ("Campo email obbligatorio");
		document.registrazione.email.focus();
		return false;
	}
	
	if(password == "" || password == null){
		alert ("Campo password obbligatorio");
		document.registrazione.password.focus()
		return false;
	}
	
	if(passwordrip == "" || passwordrip == null){
		alert ("Campo ripeti password obbligatorio");
		document.registrazione.passwordRip.value;
		return false;
	}
	document.registrazione.submit();
};