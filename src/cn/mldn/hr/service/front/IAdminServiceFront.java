package cn.mldn.hr.service.front;

import java.util.Map;

import cn.mldn.hr.vo.Admin;

public interface IAdminServiceFront {
	/**
	 * 实现登录操作，此操作要执行如下功能：<br>
	 * 	<li>调用IAdminDAO.findLogin()方法确定用户名和密码是否正确</li>
	 * @param vo 只包含了管理员id和密码的VO对象
	 * @return 返回以下的几种数据：<br>
	 * 	<li>key = flag; value = 判断IAdminDAO.findLogin()是否为null</li>
	 * 	<li>key = admin; value = 保存IAdminDAO.findLogin()返回结果</li>
	 * @throws Exception
	 */
	public Map<String, Object> login(Admin vo) throws Exception;
	
	/**
	 * 进行密码的更新操作，在密码更新之前应该首先进行密码的检查，执行如下操作：<br>
	 * 	<li>在IAdminDAO接口里面提供有一个findById()方法，判断是否有指定的管理员</li>
	 * 	<li>判断在findById()方法里面返回的password是否是输入的password</li>
	 * 	<li>如果密码匹配，则执行更新操作（IAdminDAO.doUpdatePassword()）</li>
	 * @param aid 要修改密码的管理员ID，从session里面取出
	 * @param oldpass 旧密码
	 * @param newpass 新密码
	 * @return 更新成功返回true，否则返回false
	 * @throws Exception
	 */
	public boolean updatePassword(String aid, String oldpass, String newpass) throws Exception;
}
