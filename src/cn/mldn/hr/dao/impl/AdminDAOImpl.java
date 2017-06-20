package cn.mldn.hr.dao.impl;

import java.awt.Window.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import cn.mldn.hr.dao.IAdminDAO;
import cn.mldn.hr.vo.Admin;
import cn.mldn.hr.vo.Role;
import cn.mldn.util.dao.AbstractDAOImpl;

public class AdminDAOImpl extends AbstractDAOImpl implements IAdminDAO {

	public AdminDAOImpl(Connection conn) {
		super(conn);
	}

	@Override
	public boolean doCreate(Admin vo) throws SQLException {
		String  sql = "INSERT INTO admin (aid, rid, password, type, flag) VALUES (?, ?, ?, ?, ?)";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, vo.getAid());
		if (vo.getRole() != null) {
			super.pstmt.setInt(2, vo.getRole().getRid());
		} else {
			super.pstmt.setNull(2, Types.NULL);
		}
		super.pstmt.setString(3, vo.getPassword());
		super.pstmt.setInt(4, vo.getType());
		super.pstmt.setInt(5, vo.getFlag());
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doUpdate(Admin vo) throws SQLException {
		String sql = null;
		if (vo.getPassword() != null && !"".equals(vo.getPassword())) {
			sql = "UPDATE admin SET rid=?, password=?, type=? WHERE aid=? AND flag=0"; // 只有普通管理员可以更新
			super.pstmt = super.conn.prepareStatement(sql);
			if (vo.getRole() != null) {
				super.pstmt.setInt(1, vo.getRole().getRid());
			} else {
				super.pstmt.setNull(1, Types.NULL);
			}
			super.pstmt.setString(2, vo.getPassword());
			super.pstmt.setInt(3, vo.getType());
			super.pstmt.setString(4, vo.getAid());
		} else {
			sql = "UPDATE admin SET rid=?, type=? WHERE aid=?";
			super.pstmt = super.conn.prepareStatement(sql);
			if (vo.getRole() != null) {
				super.pstmt.setInt(1, vo.getRole().getRid());
			} else {
				super.pstmt.setNull(1, Types.NULL);
			}
			super.pstmt.setInt(2, vo.getType());
			super.pstmt.setString(3, vo.getAid());
		}
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doRemoveBatch(Set<String> ids) throws SQLException {
		return super.removeHandleString("admin", "aid", ids);
	}

	@Override
	public Admin findById(String id) throws SQLException {
		Admin admin = null;
		String sql = "SELECT aid, rid, type, lastdate, flag, password FROM admin WHERE aid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, id);
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			admin = new Admin();
			admin.setAid(rs.getString(1));
			admin.getRole().setRid(rs.getInt(2));
			admin.setType(rs.getInt(3));
			admin.setLastdate(rs.getTimestamp(4));
			admin.setFlag(rs.getInt(5));
			admin.setPassword(rs.getString(6));
		}
		return admin;
	}

	@Override
	public List<Admin> findAll() throws SQLException {
		List<Admin> all = new ArrayList<Admin>();
		String sql = "SELECT aid, rid, type, lastdate, flag, password FROM admin";
		super.pstmt = super.conn.prepareStatement(sql);
		ResultSet rs = super.pstmt.executeQuery();
		while (rs.next()) {
			Admin admin = new Admin();
			admin.setAid(rs.getString(1));
			admin.getRole().setRid(rs.getInt(2));
			admin.setType(rs.getInt(3));
			admin.setLastdate(rs.getTimestamp(4));
			admin.setFlag(rs.getInt(5));
			admin.setPassword(rs.getString(6));
			all.add(admin);
		}
		return all;
	}

	@Override
	public List<Admin> findAllSplit(Integer currentPage, Integer lineSize,
			String column, String keyWord) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAllCount(String column, String keyWord)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin findLogin(Admin vo) throws SQLException {
		Admin admin = null;
		String sql = "SELECT aid, rid, type, lastdate, flag FROM admin WHERE aid=? AND password=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, vo.getAid());
		super.pstmt.setString(2, vo.getPassword());
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			admin = new Admin();
			admin.setAid(rs.getString(1));
			admin.getRole().setRid(rs.getInt(2));
			admin.setType(rs.getInt(3));
			admin.setLastdate(rs.getTimestamp(4));
			admin.setFlag(rs.getInt(5));
		}
		return admin;
	}

	@Override
	public boolean doUpdateLastDate(String aid, Date date) throws SQLException {
		String sql = "UPDATE admin SET lastdate=? WHERE aid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setTimestamp(1, new Timestamp(date.getTime()));
		super.pstmt.setString(2, aid);
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doUpdatePassword(String aid, String password)
			throws SQLException {
		String sql = "UPDATE admin SET password=? WHERE aid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, password);
		super.pstmt.setString(2, aid);
		return super.pstmt.executeUpdate() > 0;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
