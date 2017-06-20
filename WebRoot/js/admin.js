function validateAid() {
	return validateEmpty("admin.aid");
}
function validatePassword() {
	return validateEmpty("admin.password");
}
function setRole(type) {
	if (type == 0) { // 前台人事
		document.getElementById("admin.role.rid").disabled = true;
	} else { // 后台管理员
		document.getElementById("admin.role.rid").disabled = false;
	}
}
function setRole2(type, aid) {
	if (type == 0) { // 前台人事
		document.getElementById("rid-" + aid).disabled = true;
	} else { // 后台管理员
		document.getElementById("rid-" + aid).disabled = false;
	}
}
function validateInsert() {
	return validateAid() && validatePassword();
}
function updateData(aid) {
	if (validateEmpty("rid-" + aid) && validateEmpty("password-" + aid)
			&& validateEmpty("type-" + aid)) { // 验证数据是否正确
		var rid = document.getElementById("rid-" + aid).value;
		var password = document.getElementById("password-" + aid).value;
		var type = document.getElementById("type-" + aid).value;
		
		document.getElementById("admin.aid").value = aid;
		document.getElementById("admin.role.rid").value = rid;
		document.getElementById("admin.password").value = password;
		document.getElementById("admin.type").value = type;
		document.getElementById("adminForm").submit(); // 提交表单
	}
}