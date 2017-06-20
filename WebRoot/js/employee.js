function validateEid() {
	return validateEmpty("employee.eid");
}
function validateEname() {
	return validateEmpty("employee.ename");
}
function validateIdcard() {
	return validateEmpty("employee.idcard");
}
function validateBirthday() {
	return validateEmpty("employee.birthday");
}
function validateSchool() {
	return validateEmpty("employee.school");
}
function validateProfession() {
	return validateEmpty("employee.profession");
}
function validateGrad() {
	return validateEmpty("employee.grad");
}
function validateSal() {
	return validateEmpty("employee.sal") && validateRegex("employee.sal", /^\d+(\.\d+)?$/);
}
function validateNote() {
	return validateEmpty("employee.note");
}
function validateReason() {
	return validateEmpty("salary.reason");
}
function validateNote() {
	return validateEmpty("salary.note");
}
function validateReason2() {
	return validateEmpty("work.reason");
}
function validateNote2() {
	return validateEmpty("work.note");
}


function validateInsert() {
	return validateEid() && validateEname() && validateIdcard() && validateBirthday() 
		&& validateSchool() && validateProfession() && validateGrad() && validateSal() 
		&& validateNote();
}
function validateInput() {
	return validateEid();
}
function validateUpdateSal() {
	return validateReason() && validateNote() && validateSal();
}
function validateUpdateDept() {
	return validateReason() && validateNote();
}

