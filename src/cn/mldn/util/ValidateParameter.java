package cn.mldn.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import com.jspsmart.upload.SmartUpload;

public class ValidateParameter {
	private HttpServletRequest request;
	private SmartUpload smart;
	private String rule;
	private Object currentObject;
	// 保存所有的错误信息，其中key保存的是参数名称，value保存内容
	private Map<String, String> map = new HashMap<String, String>();
	private ResourceBundle msg = null;

	/**
	 * 进行数据验证的设置操作
	 * @param request 需要接收所有的请求参数，所以需要request对象
	 * @param rule 验证规则
	 */
	public ValidateParameter(Object currentObject, HttpServletRequest request, SmartUpload smart, 
			String rule, ResourceBundle msg) {
		this.request = request;
		this.smart = smart;
		this.rule = rule;
		this.currentObject = currentObject;
		this.msg = msg;
	}

	public boolean validate() {
		boolean flag = true;
		String result [] = this.rule.split("\\|");
		for (int i = 0; i < result.length; i++) {
			AttributeType at = new AttributeType(this.currentObject, result[i]);
			String type = at.getFieldType().toLowerCase();

			if (type.contains("[]")) {
				String value [] = null;
				if (request.getContentType().contains("multipart/form-data")) {	
					value = smart.getRequest().getParameterValues(result[i]);
				} else {
					value = request.getParameterValues(result[i]);
				}
				if (value == null) { // 现在没有数据提交
					this.map.put(result[i], this.msg.getString("validate.string"));
				} else {
					if ("int[]".equals(type) || "integer[]".equals(type)) {
						for (int j = 0; j < value.length; j++) {
							if (!ValidateUtil.validateRegex(value[j], "\\d+")) {
								this.map.put(result[i], this.msg.getString("validate.int"));
							}
						}
					} else if ("double[]".equals(type)) {
						for (int j = 0; j < value.length; j++) {
							if (!ValidateUtil.validateRegex(value[j], "\\d+(\\.\\d+)?")) {
								this.map.put(result[i], this.msg.getString("validate.double"));
							}
						}
					}
				}
			} else {
				String value = null;
				if (this.request.getContentType() != null) {
					if (request.getContentType().contains("multipart/form-data")) {
						value = this.smart.getRequest().getParameter(result[i]);
					} else {
						value = this.request.getParameter(result[i]);
					}
				} else {
					value = this.request.getParameter(result[i]);
				}
				if ("string".equals(type)) {
					if (!ValidateUtil.validateEmpty(value)) { // 数据现在为空
						this.map.put(result[i], this.msg.getString("validate.string"));
					}
				} else if ("int".equals(type) || "integer".equals(type)) {
					if (!ValidateUtil.validateRegex(value, "\\d+")) {
						this.map.put(result[i], this.msg.getString("validate.int"));
					}
				} else if ("double".equals(type)) {
					if (!ValidateUtil.validateRegex(value, "\\d+(\\.\\d+)?")) {
						this.map.put(result[i], this.msg.getString("validate.double"));
					}
				} else if ("date".equals(type)) {
					if (!ValidateUtil.validateRegex(value, "\\d{4}-\\d{2}-\\d{2}")) {
						if (!ValidateUtil.validateRegex(value, "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
							if (!ValidateUtil.validateRegex(value, "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{1}")) {
								this.map.put(result[i], this.msg.getString("validate.date"));
							}
						}
					}
				} else if ("string[]".equals(type)) {

				}
			}
		}
		if (this.map.size() > 0) {
			flag = false;
		}
		return flag;
	}

	public Map<String, String> getErrors() {
		return this.map;
	}



}
