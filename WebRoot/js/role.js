function validateTitle() {
	return validateEmpty("role.title");
}
function validateNote() {
	return validateEmpty("role.note");
}
function validateGid() {
	return validateCheckbox("gid");
}
function validateInsert() {
	return validateGid() && validateTitle() && validateNote();
}
