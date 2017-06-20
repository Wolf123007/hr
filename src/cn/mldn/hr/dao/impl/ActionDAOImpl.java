package cn.mldn.hr.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.mldn.hr.dao.IActionDAO;
import cn.mldn.hr.vo.Action;
import cn.mldn.hr.vo.Groups;
import cn.mldn.util.dao.AbstractDAOImpl;

public class ActionDAOImpl extends AbstractDAOImpl implements IActionDAO {

	public ActionDAOImpl(Connection conn) {
		super(conn);
	}

	@Override
	public boolean doCreate(Action vo) throws SQLException {
		String sql = "INSERT INTO action (gid, title, url) VALUES (?, ?, ?)";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, vo.getGroups().getGid());
		super.pstmt.setString(2, vo.getTitle());
		super.pstmt.setString(3, vo.getUrl());
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doUpdate(Action vo) throws SQLException {
		String sql = "UPDATE action SET gid=?, title=?, url=? WHERE actid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, vo.getGroups().getGid());
		super.pstmt.setString(2, vo.getTitle());
		super.pstmt.setString(3, vo.getUrl());
		super.pstmt.setInt(4, vo.getActid());
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
		return super.removeHandle("action", "actid", ids);
	}

	@Override
	public Action findById(Integer id) throws SQLException {
		Action vo = null;
		String sql = "SELECT actid, gid, title, url FROM action WHERE actid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, id);
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			vo = new Action();
			vo.setActid(rs.getInt(1));
			vo.getGroups().setGid(rs.getInt(2));
			vo.setTitle(rs.getString(3));
			vo.setUrl(rs.getString(4));
		}
		return vo;
	}

	@Override
	public List<Action> findAll() throws SQLException {
		List<Action> all = new ArrayList<Action>();
		String sql = "SELECT actid, gid, title, url FROM action";
		super.pstmt = super.conn.prepareStatement(sql);
		ResultSet rs = super.pstmt.executeQuery();
		while (rs.next()) {
			Action vo = new Action();
			vo.setActid(rs.getInt(1));
			vo.getGroups().setGid(rs.getInt(2));
			vo.setTitle(rs.getString(3));
			vo.setUrl(rs.getString(4));
			all.add(vo);
		}
		return all;
	}

	@Override
	public List<Action> findAllSplit(Integer currentPage, Integer lineSize,
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
	public List<Action> findAllByGroups(Integer gid) throws SQLException {
		List<Action> all = new ArrayList<Action>();
		String sql = "SELECT actid, gid, title, url FROM action WHERE gid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, gid);
		ResultSet rs = super.pstmt.executeQuery();
		while (rs.next()) {
			Action vo = new Action();
			vo.setActid(rs.getInt(1));
			Groups groups = new Groups();
			groups.setGid(rs.getInt(2));
			vo.setGroups(groups);
			vo.setTitle(rs.getString(3));
			vo.setUrl(rs.getString(4));
			all.add(vo);
		}
		return all;
	}

	
	
	
	
	
}
