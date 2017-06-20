package cn.mldn.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

public class BeanOperate {
	private Object currentObj; // 表示当前程序的保存对象
	private String attribute; // 要操作的属性
	private String value; // 要操作的内容
	private String arrayValue []; // 要操作的数组内容
	private Field field; // 表示要操作的成员对象
	/**
	 * 进行操作数据的接收，接收后才可以进行数据的设置操作
	 * @param obj 表示当前要操作此功能的类对象
	 * @param attribute 包含了“对象.属性.属性...”字符串
	 * @param value 表示属性的内容
	 */
	public BeanOperate(Object obj, String attribute, String value) {
		this.currentObj = obj; // 保存当前的操作对象
		this.attribute = attribute;
		this.value = value;
		this.handleParameter();
		this.setValue();
	}

	/**
	 * 进行数组数据的操作
	 * @param obj
	 * @param attribute
	 * @param value
	 */
	public BeanOperate(Object obj, String attribute, String arrayValue[]) {
		this.currentObj = obj; // 保存当前的操作对象
		this.attribute = attribute;
		this.arrayValue = arrayValue;
		this.handleParameter();
		this.setValue();
	}

	private void handleParameter() { // 针对传入数据进行处理
		String result [] = this.attribute.split("\\.");
		// 对于类中的getter方法上是不存在参数的，所以参数类型为空
		try {
			if (result.length == 2) { // 现在表示的是单级操作
				Method getMet = this.currentObj.getClass().getMethod("get" + StringUtils.initcap(result[0]));
				this.currentObj = getMet.invoke(this.currentObj); // 调用了getter方法
				this.field = this.currentObj.getClass().getDeclaredField(result[1]); // 取得对象成员
			} else { // 现在表示的是多级操作
				for (int i = 0; i < result.length; i ++) {
					//					System.out.println("i = " + i + " = " + this.currentObj);
					// 必须知道当前操作的成员对象，因为只有对象存在才可以找到属性类型，才可以调用setter方法
					this.field = this.currentObj.getClass().getDeclaredField(result[i]);
					//					System.out.println(this.field.getName());
					if (i < result.length - 1) { // 最后一个“.”之前的数据执行以下方法
						Method met = this.currentObj.getClass().getMethod("get" + StringUtils.initcap(result[i]));
						this.currentObj = met.invoke(this.currentObj);
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setValue() { // 定义一个专门设置属性内容的方法，调用的setter操作
		try {
			Method setMet = this.currentObj.getClass().getMethod("set" + StringUtils.initcap(this.field.getName()), this.field.getType());
			String type = this.field.getType().getSimpleName();
			if ("int".equalsIgnoreCase(type) || "Integer".equalsIgnoreCase(type)) {
				if (this.value.matches("\\d+")) {
					setMet.invoke(this.currentObj, Integer.parseInt(this.value));
				} 
			}else if ("double".equalsIgnoreCase(type)) {
				if (this.value.matches("\\d+(\\.\\d+)?")) {
					setMet.invoke(this.currentObj, Double.parseDouble(this.value));
				}
			} else if ("String".equalsIgnoreCase(type)) {
				setMet.invoke(this.currentObj, this.value);
			} else if ("date".equalsIgnoreCase(type)) {
				if (this.value.matches("\\d{4}-\\d{2}-\\d{2}")) {
					setMet.invoke(this.currentObj, new SimpleDateFormat("yyyy-MM-dd").parse(this.value));
				}
				if (this.value.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
					setMet.invoke(this.currentObj, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(this.value));
				}
				if (this.value.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{1}")) {
					setMet.invoke(this.currentObj, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(this.value));
				}
			} else if ("string[]".equalsIgnoreCase(type)) {
				setMet.invoke(this.currentObj, new Object[]{this.arrayValue});
			} else if ("Integer[]".equalsIgnoreCase(type)) {
				Integer[] temp = new Integer [this.arrayValue.length];
				for (int i = 0; i < temp.length; i++) {
					if (this.arrayValue[i].matches("\\d+")) {	
						temp[i] = Integer.parseInt(this.arrayValue[i]);
					}
				}
				setMet.invoke(this.currentObj, new Object[]{temp});
			}
			else if ("int[]".equalsIgnoreCase(type)) {
				int[] temp = new int [this.arrayValue.length];
				for (int i = 0; i < temp.length; i++) {
					if (this.arrayValue[i].matches("\\d+")) {	
						temp[i] = Integer.parseInt(this.arrayValue[i]);
					}
				}
				setMet.invoke(this.currentObj, new Object[]{temp});
			} else if ("Double[]".equals(type)) {
				Double[] temp = new Double [this.arrayValue.length];
				for (int i = 0; i < temp.length; i++) {
					if (this.arrayValue[i].matches("\\d+(\\.\\d+)?")) {	
						temp[i] = Double.parseDouble(this.arrayValue[i]);
					}
				}
				setMet.invoke(this.currentObj, new Object[]{temp});
			}
			else if ("double[]".equals(type)) {
				double[] temp = new double [this.arrayValue.length];
				for (int i = 0; i < temp.length; i++) {
					if (this.arrayValue[i].matches("\\d+(\\.\\d+)?")) {	
						temp[i] = Double.parseDouble(this.arrayValue[i]);
					}
				}
				setMet.invoke(this.currentObj, new Object[]{temp});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
