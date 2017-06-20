package cn.mldn.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AttributeType {
	private Object currentObject; // 当前的操作对象
	private String attribute; // 属性字符串描述
	private Field field; // 属性的成员
	public AttributeType(Object currentObject, String attribute) {
		this.currentObject = currentObject;
		this.attribute = attribute;
		this.handleParameter();
	}
	
	private void handleParameter() { // 针对传入数据进行处理
		String result [] = this.attribute.split("\\.");
		// 对于类中的getter方法上是不存在参数的，所以参数类型为空
		try {
			if (result.length == 2) { // 现在表示的是单级操作
				Method getMet = this.currentObject.getClass().getMethod("get" + StringUtils.initcap(result[0]));
				this.currentObject = getMet.invoke(this.currentObject); // 调用了getter方法
				this.field = this.currentObject.getClass().getDeclaredField(result[1]); // 取得对象成员
			} else { // 现在表示的是多级操作
				for (int i = 0; i < result.length; i ++) {
					//					System.out.println("i = " + i + " = " + this.currentObj);
					// 必须知道当前操作的成员对象，因为只有对象存在才可以找到属性类型，才可以调用setter方法
					this.field = this.currentObject.getClass().getDeclaredField(result[i]);
					//					System.out.println(this.field.getName());
					if (i < result.length - 1) { // 最后一个“.”之前的数据执行以下方法
						Method met = this.currentObject.getClass().getMethod("get" + StringUtils.initcap(result[i]));
						this.currentObject = met.invoke(this.currentObject);
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Field getField() {
		return this.field;
	}
	public String getFieldType() {
		return this.field.getType().getSimpleName();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
