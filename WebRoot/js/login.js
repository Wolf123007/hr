function validateAid() {
	return validateEmpty("admin.aid");
}
function validatePassword() {
	return validateEmpty("admin.password");
}
function validateCode() {
	return validateEmpty("code");
}
function validateLogin() {
	return validateAid() && validatePassword() && validateCode();
}
function validateOldpass() {
	return validateEmpty("oldpass");
}
function validateNewpass() {
	return validateEmpty("newpass");
}
function validateConf() {
	return validateCompare("conf", "newpass");
}
function validateUpdatePassword() {
	return validateOldpass() && validateNewpass() && validateConf();
}