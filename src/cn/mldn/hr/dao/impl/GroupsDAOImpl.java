package cn.mldn.hr.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.mldn.hr.dao.IGroupsDAO;
import cn.mldn.hr.vo.Groups;
import cn.mldn.util.dao.AbstractDAOImpl;

public class GroupsDAOImpl extends AbstractDAOImpl implements IGroupsDAO {

	public GroupsDAOImpl(Connection conn) {
		super(conn);
	}

	@Override
	public boolean doCreate(Groups vo) throws SQLException {
		String sql = "INSERT INTO groups (title, note) VALUES (?, ?)";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, vo.getTitle());
		super.pstmt.setString(2, vo.getNote());
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doUpdate(Groups vo) throws SQLException {
		String sql = "UPDATE groups SET title=?, note=? WHERE gid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, vo.getTitle());
		super.pstmt.setString(2, vo.getNote());
		super.pstmt.setInt(3, vo.getGid());
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
		return super.removeHandle("groups", "gid", ids);
	}

	@Override
	public Groups findById(Integer id) throws SQLException {
		Groups vo = new Groups();
		String sql = "SELECT gid, title, note FROM groups WHERE gid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, id);
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			vo.setGid(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setNote(rs.getString(3));
		}
		return vo;
	}

	@Override
	public List<Groups> findAll() throws SQLException {
		List<Groups> all = new ArrayList<Groups>();
		Groups vo = null;
		String sql = "SELECT gid, title, note FROM groups";
		super.pstmt = super.conn.prepareStatement(sql);
		ResultSet rs = super.pstmt.executeQuery();
		while (rs.next()) {
			vo = new Groups();
			vo.setGid(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setNote(rs.getString(3));
			all.add(vo);
		}
		return all;
	}

	@Override
	public List<Groups> findAllSplit(Integer currentPage, Integer lineSize,
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
	public List<Groups> findAllByRole(Integer rid) throws SQLException {
		List<Groups> all = new ArrayList<Groups>();
		String sql = "SELECT gid, title, note FROM groups WHERE gid IN (SELECT gid FROM role_groups WHERE rid=?)";
		super.pstmt =  super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, rid);
		ResultSet rs = super.pstmt.executeQuery();
		while (rs.next()) {
			Groups vo = new Groups();
			vo.setGid(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setNote(rs.getString(3));
			all.add(vo);
		}
		return all;
	}

	
	
	
	
	
	
	
}
