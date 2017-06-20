package cn.mldn.hr.service.front.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.mldn.hr.dbc.DatabaseConnection;
import cn.mldn.hr.factory.DAOFactory;
import cn.mldn.hr.service.front.IAdminServiceFront;
import cn.mldn.hr.vo.Admin;
import cn.mldn.hr.vo.Adminlogs;
import cn.mldn.hr.vo.Groups;

public class AdminServiceFrontImpl implements IAdminServiceFront{
	private DatabaseConnection dbc = new DatabaseConnection();
	@Override
	public Map<String, Object> login(Admin vo) throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			// 1、进行用户的登录验证
			Admin result = DAOFactory.getIAdminDAOInstance(this.dbc.getConnection()).findLogin(vo);
			// 2、保存登录日志信息
			if (result != null) {	
				Date currentDate = new Date(); // 外面定义Date是因为要保存登录日志和管理员最后一次登录日期相符合的操作
				Adminlogs logs = new Adminlogs();
				logs.setAdmin(result);
				logs.setLogindate(currentDate);
				logs.setIp(vo.getIp());
				if (DAOFactory.getIAdminlogsDAOInstance(this.dbc.getConnection()).doCreate(logs)) {
					// 3、要更新管理员的最后一次登录日期
					if (DAOFactory.getIAdminDAOInstance(this.dbc.getConnection()).doUpdateLastDate(vo.getAid(), currentDate)) {
						// 4、要判断当前的管理员类型
						if (result.getType() == 1) { // 后台管理员
							// 5、根据角色查询出所有的权限组
							List<Groups> allGroups = DAOFactory.getIGroupsDAOInstance(this.dbc.getConnection()).findAllByRole(result.getRole().getRid());
							// 6、根据权限组找到所有的权限信息
							Iterator<Groups> iter = allGroups.iterator();
							while (iter.hasNext()) {
								Groups groups = iter.next();
								groups.setActions(DAOFactory.getIActionDAOInstance(this.dbc.getConnection()).findAllByGroups(groups.getGid()));
							}
							// 7、将所有权限组的信息保存在角色中
							result.getRole().setGroups(allGroups);
						}
					}
				}
			}
			map.put("admin", result);
			map.put("flag", result != null);
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}
	@Override
	public boolean updatePassword(String aid, String oldpass, String newpass)
			throws Exception {
		try {
			// 1、首先根据管理员ID查询出管理员的原始信息
			Admin admin = DAOFactory.getIAdminDAOInstance(this.dbc.getConnection()).findById(aid);
			if (admin != null) { // 已经查找到了指定的管理员对象
				if (oldpass.equals(admin.getPassword())) { // 如果原始密码与输入密码匹配
					// 执行密码的更新操作
					return DAOFactory.getIAdminDAOInstance(this.dbc.getConnection()).doUpdatePassword(aid, newpass);
				}
			}
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}
	
	
	
	
	
	
	
}
