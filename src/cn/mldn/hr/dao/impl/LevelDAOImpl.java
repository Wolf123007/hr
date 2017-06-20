package cn.mldn.hr.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.mldn.hr.dao.ILevelDAO;
import cn.mldn.hr.vo.Level;
import cn.mldn.util.dao.AbstractDAOImpl;

public class LevelDAOImpl extends AbstractDAOImpl implements ILevelDAO {

	public LevelDAOImpl(Connection conn) {
		super(conn);
	}

	@Override
	public boolean doCreate(Level vo) throws SQLException {
		String sql = "INSERT INTO level (title, losal, hisal) VALUES (?, ?, ?)";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, vo.getTitle());
		super.pstmt.setDouble(2, vo.getLosal());
		super.pstmt.setDouble(3, vo.getHisal());
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doUpdate(Level vo) throws SQLException {
		String sql = "UPDATE level SET title=?, losal=?, hisal=? WHERE levid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, vo.getTitle());
		super.pstmt.setDouble(2, vo.getLosal());
		super.pstmt.setDouble(3, vo.getHisal());
		super.pstmt.setInt(4, vo.getLevid());
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
		return super.removeHandle("level", "levid", ids);
	}

	@Override
	public Level findById(Integer id) throws SQLException {
		Level vo = null;
		String sql = "SELECT levid, title, losal, hisal FROM level WHERE levid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, id);
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			vo = new Level();
			vo.setLevid(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setLosal(rs.getDouble(3));
			vo.setHisal(rs.getDouble(4));
		}
		return vo;
	}

	@Override
	public List<Level> findAll() throws SQLException {
		List<Level> all = new ArrayList<Level>();
		String sql = "SELECT levid, title, losal, hisal FROM level";
		super.pstmt = super.conn.prepareStatement(sql);
		ResultSet rs = super.pstmt.executeQuery();
		while (rs.next()) {
			Level vo = new Level();
			vo.setLevid(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setLosal(rs.getDouble(3));
			vo.setHisal(rs.getDouble(4));
			all.add(vo);
		}
		return all;
	}

	@Override
	public List<Level> findAllSplit(Integer currentPage, Integer lineSize,
			String column, String keyWord) throws SQLException {
		return null;
	}

	@Override
	public Integer getAllCount(String column, String keyWord)
			throws SQLException {
		return null;
	}

	@Override
	public Level findByTitle(String title) throws SQLException {
		Level vo = null;
		String sql = "SELECT levid, title, losal, hisal FROM level WHERE title=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, title);;
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			vo = new Level();
			vo.setLevid(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setLosal(rs.getDouble(3));
			vo.setHisal(rs.getDouble(4));
		}
		return vo;
	}

	@Override
	public Level findByTitle(String title, Integer levid) throws SQLException {
		Level vo = null;
		String sql = "SELECT levid, title, losal, hisal FROM level WHERE title=? AND levid!=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, title);;
		super.pstmt.setInt(2, levid);
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			vo = new Level();
			vo.setLevid(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setLosal(rs.getDouble(3));
			vo.setHisal(rs.getDouble(4));
		}
		return vo;
	}

}
