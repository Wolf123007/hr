function validateTitle() {
	return validateEmpty("jobs.title");
}
function validateInsert() {
	return validateTitle();
}
function updateData(jid) {
	if (validateEmpty("title-" + jid)) { // 验证数据是否正确
		var title = document.getElementById("title-" + jid).value;
		var note = document.getElementById("note-" + jid).value;
		document.getElementById("jobs.title").value = title;
		document.getElementById("jobs.jid").value = jid;
		document.getElementById("jobs.note").value = note;
		document.getElementById("jobsForm").submit(); // 提交表单
	}
}