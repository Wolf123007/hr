function validateTitle() {
	return validateEmpty("groups.title");
}
function validateInsert() {
	return validateTitle();
}
function updateData(gid) {
	if (validateEmpty("title-" + gid)) { // 验证数据是否正确
		var title = document.getElementById("title-" + gid).value;
		var note = document.getElementById("note-" + gid).value;
		document.getElementById("groups.title").value = title;
		document.getElementById("groups.gid").value = gid;
		document.getElementById("groups.note").value = note;
		document.getElementById("groupsForm").submit(); // 提交表单
	}
}