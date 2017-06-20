package cn.mldn.util.servlet;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.mldn.util.AttributeType;
import cn.mldn.util.BeanOperate;
import cn.mldn.util.ValidateParameter;
import cn.mldn.util.ValidateUtil;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

@SuppressWarnings("serial")
public abstract class DispatcherServlet extends HttpServlet {

	private SmartUpload smart = null;

	private static final String PAGES_BASENAME = "Pages";
	private static final String MESSAGES_BASENAME = "Messages";

	private ResourceBundle pagesResource;
	private ResourceBundle messagesResource;
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	private Integer currentPage = 1;
	private Integer lineSize = 5;
	private String keyWord;
	private String column;


	@Override
	public void init() throws ServletException {
		this.pagesResource = ResourceBundle.getBundle(PAGES_BASENAME, Locale.getDefault());
		this.messagesResource = ResourceBundle.getBundle(MESSAGES_BASENAME, Locale.getDefault());
	}

	/**
	 * 取得Pages.properties文件里面定义的访问路径
	 * @param key 访问路径的key
	 * @return 配置文件中的路径内容，如果没有返回null
	 */
	public String getPath(String key) {
		return this.pagesResource.getString(key);
	}

	/**
	 * 取得Messages.properties文件中的配置文件信息
	 * @param key 访问文字信息的key
	 * @param args 所有点位符的内容
	 * @return 配置文件中的内容，并且是组合后的结果，如果没有返回null
	 */
	public String getMsg(String key, String ... args) {
		String note = this.messagesResource.getString(key);
		if (args.length > 0 || this.getTitle() == null) {
			return MessageFormat.format(note, args);
		} else {
			return MessageFormat.format(note, this.getTitle());
		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		this.request = request;
		this.response = response;
		String path = this.getPath("errors.page");
		String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);

		if (request.getContentType() != null) {
			if (request.getContentType().contains("multipart/form-data")) {
				this.initSmart();
			}
		}
		// 在进行参数处理之前，需要对提交数据进行验证
		if (status != null && status.length() > 0) {
			if (this.validateRequest(status)) {
				this.handleRequest(); // 处理参数与简单Java类之间的自动赋值
				// 现在可以找到当前类对象this，以及要调用的方法名称status，那么可以利用反射进行调用
				try { // 只有将对应的数据都准备完毕了，才可以执行以下方法
					Method method = this.getClass().getMethod(status);
					path = this.getPath((String) method.invoke(this)); // 反射调用方法
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}


	private void handleRequest() {
		if (request.getContentType() != null) {
			// 说明当前使用了表单封装，意味着有文件上传，应该使用SmartUpload接收数据
			if (request.getContentType().contains("multipart/form-data")) {
				// 取得全部的请求参数名称，之后所以需要名称，主要是确定自动赋值的操作
				Enumeration<String> enu = this.smart.getRequest().getParameterNames();
				while (enu.hasMoreElements()) { // 循环所有的参数名称
					String paramName = enu.nextElement();
					if (paramName.contains(".")) { // 按照简单Java为处理
						AttributeType at = new AttributeType(this, paramName);
						if (at.getFieldType().contains("[]")) { // 按照数组的方式进行处理
							BeanOperate bo = new BeanOperate(this, paramName, this.smart.getRequest().getParameterValues(paramName));
						} else { // 按照单个字符串的方式进行处理
							BeanOperate bo = new BeanOperate(this, paramName, this.smart.getRequest().getParameter(paramName));
						}
					}
				}
			} else { // 应该使用普通的request对象进行数据的接收操作
				// 取得全部的请求参数名称，之后所以需要名称，主要是确定自动赋值的操作
				Enumeration<String> enu = request.getParameterNames();
				while (enu.hasMoreElements()) { // 循环所有的参数名称
					String paramName = enu.nextElement();
					if (paramName.contains(".")) { // 按照简单Java为处理
						AttributeType at = new AttributeType(this, paramName);
						if (at.getFieldType().contains("[]")) { // 按照数组的方式进行处理
							BeanOperate bo = new BeanOperate(this, paramName, request.getParameterValues(paramName));
						} else { // 按照单个字符串的方式进行处理
							BeanOperate bo = new BeanOperate(this, paramName, request.getParameter(paramName));
						}
					}
				}
			}
		} else { // 应该使用普通的request对象进行数据的接收操作
			// 取得全部的请求参数名称，之后所以需要名称，主要是确定自动赋值的操作
			Enumeration<String> enu = request.getParameterNames();
			while (enu.hasMoreElements()) { // 循环所有的参数名称
				String paramName = enu.nextElement();
				if (paramName.contains(".")) { // 按照简单Java为处理
					AttributeType at = new AttributeType(this, paramName);
					if (at.getFieldType().contains("[]")) { // 按照数组的方式进行处理
						BeanOperate bo = new BeanOperate(this, paramName, request.getParameterValues(paramName));
					} else { // 按照单个字符串的方式进行处理
						BeanOperate bo = new BeanOperate(this, paramName, request.getParameter(paramName));
					}
				}
			}
		}
	}

	/**
	 * 此方法主要是判断是否有文件上传
	 * @return 没有文件上传返回false
	 */
	public boolean isUpload() {
		try {
			return this.smart.getFiles().getSize() > 0;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 取得上传文件的个数
	 * @return
	 */
	public int getUploadCount() {
		return this.smart.getFiles().getCount();
	}

	public SmartUpload getSmart() {
		return this.smart;
	}

	/**
	 * 创建一个新的文件名称
	 * @return
	 */
	public String createSingleFileName() {
		String fileName = null;
		if (this.isUpload()) {
			if (this.smart.getFiles().getFile(0).getContentType().contains("image")) {
				fileName = UUID.randomUUID() + "." + this.smart.getFiles().getFile(0).getFileExt();
			}
		}
		return fileName;
	}

	public Map<Integer, String> createMultiFileName() {
		Map<Integer, String> all = new HashMap<Integer, String>();
		if (this.isUpload()) {
			for (int i = 0; i < this.getUploadCount(); i ++) {
				if (this.smart.getFiles().getFile(i).getContentType().contains("image")) {
					String fileName = UUID.randomUUID() + "." + this.smart.getFiles().getFile(i).getFileExt();
					all.put(i, fileName);
				}
			}
		}
		return all;
	}

	/**
	 * 专门负责文件的保存操作
	 * @param index SmartUpload操作索引
	 * @param fileName 文件名称
	 * @throws SmartUploadException 
	 * @throws IOException 
	 */
	private void saveFile(int index, String fileName){
		String filePath = super.getServletContext().getRealPath(this.getUploadDirectory()) + fileName;
		File file = new File(filePath);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		if (this.smart.getFiles().getFile(index).getContentType().contains("image")) {
			try {
				this.smart.getFiles().getFile(index).saveAs(filePath);
			} catch (IOException | SmartUploadException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 存放一个文件信息
	 * @param fileName
	 * @throws SmartUploadException 
	 * @throws IOException 
	 */
	public void upload(String fileName) {
		this.saveFile(0, fileName);
	}

	/**
	 * 
	 * @param fileName
	 */
	public void upload(Map<Integer, String> fileName) {
		Iterator<Map.Entry<Integer, String>> iter = fileName.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<Integer, String> me = iter.next();
			this.saveFile(me.getKey(), me.getValue());
		}
	}

	/**
	 * 删除文件
	 * @param fileName
	 */
	public void deleteFile(String fileName) {
		String filePath = super.getServletContext().getRealPath(this.getUploadDirectory()) + fileName;
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
		}
	}

	/**
	 * 批量删除上传文件
	 * @param all
	 */
	public void deleteFile(Set<String> all) {
		Iterator<String> iter = all.iterator();
		while (iter.hasNext()) {
			this.deleteFile(iter.next());
		}
	}

	/**
	 * 对接收到的数据进行验证
	 * @param status 操作状态
	 * @return
	 */
	public boolean validateRequest(String status) {
		boolean flag = false;
		String rule = null;
		try {
			Field field = this.getClass().getDeclaredField(status + "Validate");
			field.setAccessible(true); // 取消封装
			rule = (String) field.get(this); // 取出验证规则的数据
		} catch (Exception e) { // 表示数据现在不存在
			flag = true;
		}
		// 需要针对给定的内容进行指定格式的验证
		if (rule == null) { // 现在没有规则
			flag = true;
		} else { // 现在有规则
			ValidateParameter vp = new ValidateParameter(this, request, smart, rule, this.messagesResource);
			flag = vp.validate(); // 进行验证操作
			this.request.setAttribute("errors", vp.getErrors());
		}
		return flag;
	}

	public void initSmart() {
		this.smart = new SmartUpload();
		try {
			this.smart.initialize(super.getServletConfig(), request, response);
			this.smart.upload();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handleSplit() {
		try {
			this.currentPage = Integer.parseInt(this.request.getParameter("cp"));
		} catch (Exception e) {}
		try {
			this.lineSize = Integer.parseInt(this.request.getParameter("ls"));
		} catch (Exception e) {}
		this.column = this.request.getParameter("col");
		this.keyWord = this.request.getParameter("kw");
		if (column == null) {
			this.column = this.getDefaultColumn();
		}
		if (keyWord == null) {
			this.keyWord = "";
		}
		this.request.setAttribute("currentPage", this.currentPage);
		this.request.setAttribute("lineSize", this.lineSize);
		this.request.setAttribute("column", this.column);
		this.request.setAttribute("keyWord", this.keyWord);
		this.request.setAttribute("columnData", this.getColumnData());
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public Integer getLineSize() {
		return lineSize;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public String getColumn() {
		return column;
	}
	
	public void setMsgAndUrl(String msgKey, String urlKey) {
		this.request.setAttribute("msg", this.getMsg(msgKey, this.getTitle()));
		this.request.setAttribute("url", this.getPath(urlKey));
	}
	
	public String getParameter(String paramName) {
		String str = this.request.getParameter(paramName);
		if (str == null) { // 没有接收到
			if (this.smart != null) { // 准备好了SmartUpload对象
				str = this.smart.getRequest().getParameter(paramName);
			}
		}
		
		return str;
	}
	
	public String [] getParameterValues(String paramName) {
		String str [] = this.request.getParameterValues(paramName);
		if (str == null) { // 没有接收到
			if (this.smart != null) { // 准备好了SmartUpload对象
				str = this.smart.getRequest().getParameterValues(paramName);
			}
		}
		
		return str;
	}
	
	
	
	/**
	 * 进行验证码的验证
	 * @return
	 */
	public boolean checkCode() {
		String code = this.request.getParameter("code");
		String rand = (String) this.request.getSession().getAttribute("rand");
		return ValidateUtil.validateSame(code, rand);
	}
	
	

	/**
	 * 交由不同的子类来实现，可以由子类设置统一的占位符提示信息名称标记
	 * @return 返回不同子类的描述信息
	 */
	public abstract String getTitle();

	/**
	 * 取得文件上传的目的地目录
	 * @return
	 */
	public abstract String getUploadDirectory();

	/**
	 * 得到分页搜索的数据列
	 * @return
	 */
	public abstract String getColumnData();

	/**
	 * 得到默认查询的数据列
	 * @return
	 */
	public abstract String getDefaultColumn();



}
