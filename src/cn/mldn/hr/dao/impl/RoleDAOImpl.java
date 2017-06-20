package cn.mldn.hr.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.mldn.hr.dao.IRoleDAO;
import cn.mldn.hr.vo.Groups;
import cn.mldn.hr.vo.Role;
import cn.mldn.util.dao.AbstractDAOImpl;

public class RoleDAOImpl extends AbstractDAOImpl implements IRoleDAO {

	public RoleDAOImpl(Connection conn) {
		super(conn);
	}

	@Override
	public boolean doCreate(Role vo) throws SQLException {
		String sql = "INSERT INTO role (title, note) VALUES (?, ?)";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, vo.getTitle());
		super.pstmt.setString(2, vo.getNote());
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doUpdate(Role vo) throws SQLException {
		String sql = "UPDATE role SET title=?, note=? WHERE rid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, vo.getTitle());
		super.pstmt.setString(2, vo.getNote());
		super.pstmt.setInt(3, vo.getRid());
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
		return super.removeHandle("role", "rid", ids);
	}

	@Override
	public Role findById(Integer id) throws SQLException {
		Role vo = null;
		String sql = "SELECT rid, title, note FROM role WHERE rid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, id);
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			vo = new Role();
			vo.setRid(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setNote(rs.getString(3));
		}
		return vo;
	}

	@Override
	public List<Role> findAll() throws SQLException {
		List<Role> all = new ArrayList<Role>();
		String sql = "SELECT rid, title, note FROM role";
		super.pstmt = super.conn.prepareStatement(sql);
		ResultSet rs = super.pstmt.executeQuery();
		while (rs.next()) {
			Role vo = new Role();
			vo.setRid(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setNote(rs.getString(3));
			all.add(vo);
		}
		return all;
	}

	@Override
	public List<Role> findAllSplit(Integer currentPage, Integer lineSize,
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
	public Integer getId() throws SQLException {
		return super.getIdHandle();
	}

	@Override
	public boolean doCreateRoleGroups(Integer rid, List<Groups> groups)
			throws SQLException {
		if (groups.size() == 0) {
			return false;
		}
		String sql = "INSERT INTO role_groups (rid, gid) VALUES (?, ?)";
		super.pstmt = super.conn.prepareStatement(sql);
		Iterator<Groups> iter = groups.iterator();
		while (iter.hasNext()) {
			Groups gro = iter.next();
			super.pstmt.setInt(1, rid);
			super.pstmt.setInt(2, gro.getGid());
			super.pstmt.addBatch();
		}
		boolean flag = true;
		int[] result = super.pstmt.executeBatch();
		for (int i = 0; i < result.length; i++) {
			if (result[i] == 0) {
				flag = false;
			}
		}
		return flag;
	}

	@Override
	public boolean doRemoveGroups(Integer rid) throws SQLException {
		String sql = "DELETE FROM role_groups WHERE rid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, rid);
		return super.pstmt.executeUpdate() > 0;
	}

}
