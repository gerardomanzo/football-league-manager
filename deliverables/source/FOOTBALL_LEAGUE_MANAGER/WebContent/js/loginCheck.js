function loginCheck() {
	var email =  document.login.email.value;
	var password = document.login.email.value;
	
	if(email == "" || email == null){
		alert ("Campo email obbligatorio");
		document.login.email.focus();
		return false;
	}
	
	if(password == "" || password == null){
		alert ("Campo password obbligatorio");
		document.login.password.focus();
		return false;
	}
	document.login.submit();
};