function validateCname() {
	return validateEmpty("course.cname");
}
function validateBegin() {
	return validateEmpty("course.begin");
}
function validateEnd() {
	return validateEmpty("course.end");
}
function validateTotal() {
	return validateEmpty("course.total");
}
function validateNote() {
	return validateEmpty("course.note");
}
function validateDid() {
	return validateCheckbox("did");
}
function validateInsert() {
	return validateCname() && validateBegin() && validateEnd() 
	&& validateTotal && validateNote() && validateDid();
}
function validateUpdate() {
	return validateCname() && validateBegin() && validateEnd() && validateNote();
}