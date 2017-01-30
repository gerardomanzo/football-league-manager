function loginCheck() {
	var email =  document.login.email.value;
	var password = document.login.email.value;
	
	if(email == "" || email == null){
		document.login.email.style.borderColor = "red";
		document.login.email.focus();
		return false;
	}
	
	if(password == "" || password == null){
		document.login.password.style.borderColor = "red";
		document.login.password.focus();
		return false;
	}
	document.login.submit();
};