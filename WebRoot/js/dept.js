function validateDname() {
	return validateEmpty("dept.dname");
}
function validateInsert() {
	return validateDname();
}
function updateData(did) {
	if (validateEmpty("dname-" + did)) { // 验证数据是否正确
		var dname = document.getElementById("dname-" + did).value;
		document.getElementById("dept.dname").value = dname;
		document.getElementById("dept.did").value = did;
		document.getElementById("deptForm").submit(); // 提交表单
	}
}