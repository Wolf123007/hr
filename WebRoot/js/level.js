function validateTitle() {
	return validateEmpty("level.title");
}
function validateLosal() {
	return validateEmpty("level.losal") && validateRegex("level.losal", /^\d+(\.\d+)?$/);
}
function validateHisal() {
	return validateEmpty("level.hisal") && validateRegex("level.hisal", /^\d+(\.\d+)?$/);
}
function validateInsert() {
	return validateTitle() && validateLosal() && validateHisal();
}
function validateUpdate(levid) {
	return validateEmpty("title-" + levid) && validateEmpty("losal-" + levid) && validateEmpty("hisal-" + levid)
		&& validateRegex("losal-" + levid, /^\d+(\.\d+)?$/) && validateRegex("hisal-" + levid, /^\d+(\.\d+)?$/);
}
function updateData(levid) {
	if (validateUpdate(levid)) { // 验证数据是否正确
		var title = document.getElementById("title-" + levid).value;
		var losal = document.getElementById("losal-" + levid).value;
		var hisal = document.getElementById("hisal-" + levid).value;
		document.getElementById("level.title").value = title;
		document.getElementById("level.levid").value = levid;
		document.getElementById("level.losal").value = losal;
		document.getElementById("level.hisal").value = hisal;
		document.getElementById("levelForm").submit(); // 提交表单
	}
}