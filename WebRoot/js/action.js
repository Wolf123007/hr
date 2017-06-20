function validateTitle() {
	return validateEmpty("action.title");
}
function validateUrl() {
	return validateEmpty("action.url");
}
function validateInsert() {
	return validateTitle() && validateUrl();
}
function updateData(actid) {
	if (validateEmpty("title-" + actid) && validateEmpty("url-" + actid)) { // 验证数据是否正确
		var title = document.getElementById("title-" + actid).value;
		var url = document.getElementById("url-" + actid).value;
		var gid = document.getElementById("gid-" + actid).value;
		document.getElementById("action.title").value = title;
		document.getElementById("action.actid").value = actid;
		document.getElementById("action.url").value = url;
		document.getElementById("action.groups.gid").value = gid;
		document.getElementById("actionForm").submit(); // 提交表单
	}
}