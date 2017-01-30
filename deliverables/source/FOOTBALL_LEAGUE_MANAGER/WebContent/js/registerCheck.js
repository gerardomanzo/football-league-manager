function registerCheck() {
	var nome = document.registrazione.nome.value;
	var cognome = document.registrazione.cognome.value;
	var email = document.registrazione.email.value;
	var password = document.registrazione.password.value;
	var passwordrip = document.registrazione.passwordRip.value;
	
	if(nome == "" || nome == null){
		document.registrazione.nome.style.borderColor = "red";
		document.registrazione.nome.focus();
		return false;
	}
	
	if (cognome == "" || cognome == null){
		document.registrazione.cognome.style.borderColor = "red";
		document.registrazione.cognome.focus();
		return false;
	}
	
	if(email == "" || email == null){
		document.registrazione.email.style.borderColor = "red";
		document.registrazione.email.focus();
		return false;
	}
	
	if(password == "" || password == null){
		document.registrazione.password.style.borderColor = "red";
		document.registrazione.password.focus();
		return false;
	}
	
	if(passwordrip == "" || passwordrip == null){
			document.registrazione.passwordRip.style.borderColor = "red";
			document.registrazione.passwordRip.focus();
			return false;
	}
	else if (passwordrip != "" || passwordrip != null ) {
		if((passwordrip !== password) == true){
			alert("Le passoword devono essere uguali");
			document.registrazione.passwordRip.style.borderColor = "red";
			document.registrazione.passwordRip.focus();
			return false;
		}
	}
	document.registrazione.submit();
};

