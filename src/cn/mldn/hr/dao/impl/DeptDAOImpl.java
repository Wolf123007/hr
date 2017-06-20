package cn.mldn.hr.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.hr.dao.IDeptDAO;
import cn.mldn.hr.vo.Dept;
import cn.mldn.util.dao.AbstractDAOImpl;

public class DeptDAOImpl extends AbstractDAOImpl implements IDeptDAO {

	public DeptDAOImpl(Connection conn) {
		super(conn);
	}

	@Override
	public boolean doCreate(Dept vo) throws SQLException {
		String sql = "INSERT INTO dept (dname, current) VALUES (?, ?)";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, vo.getDname());
		super.pstmt.setInt(2, vo.getCurrent());
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doUpdate(Dept vo) throws SQLException {
		String sql = "UPDATE dept SET dname=? WHERE did=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, vo.getDname());
		super.pstmt.setInt(2, vo.getDid());
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
		return super.removeHandle("dept", "did", ids);
	}

	@Override
	public Dept findById(Integer id) throws SQLException {
		Dept vo = null;
		String sql = "SELECT did, dname, current FROM dept WHERE did=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, id);
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			vo = new Dept();
			vo.setDid(rs.getInt(1));
			vo.setDname(rs.getString(2));
			vo.setCurrent(rs.getInt(3));
		}
		return vo;
	}

	@Override
	public List<Dept> findAll() throws SQLException {
		List<Dept> all = new ArrayList<Dept>();
		String sql = "SELECT did, dname, current FROM dept";
		super.pstmt = super.conn.prepareStatement(sql);
		ResultSet rs = super.pstmt.executeQuery();
		while (rs.next()) {
			Dept vo = new Dept();
			vo.setDid(rs.getInt(1));
			vo.setDname(rs.getString(2));
			vo.setCurrent(rs.getInt(3));
			all.add(vo);
		}
		return all;
	}

	@Override
	public List<Dept> findAllSplit(Integer currentPage, Integer lineSize,
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
	public Dept findByDname(String dname) throws SQLException {
		Dept vo = null;
		String sql = "SELECT did, dname, current FROM dept WHERE dname=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, dname);
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			vo = new Dept();
			vo.setDid(rs.getInt(1));
			vo.setDname(rs.getString(2));
			vo.setCurrent(rs.getInt(3));
		}
		return vo;
	}

	@Override
	public Dept findByDname(String dname, Integer did) throws SQLException {
		Dept vo = null;
		String sql = "SELECT did, dname, current FROM dept WHERE dname=? AND did!=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, dname);
		super.pstmt.setInt(2, did);
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			vo = new Dept();
			vo.setDid(rs.getInt(1));
			vo.setDname(rs.getString(2));
			vo.setCurrent(rs.getInt(3));
		}
		return vo;
	}

	@Override
	public boolean doUpdateAddCurrent(Integer did, Integer val)
			throws SQLException {
		String sql = " UPDATE dept SET current=current + " + val + " WHERE did=? ";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, did);
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doUpdateSubCurrent(Integer did, Integer val)
			throws SQLException {
		String sql = " UPDATE dept SET current=current - " + val + " WHERE did=? ";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, did);
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public Map<Integer, Integer> findAllByEmployee(Set<Integer> eids)
			throws SQLException {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT did, COUNT(eid) FROM employee WHERE eid IN (");
		Iterator<Integer> iter = eids.iterator();
		while (iter.hasNext()) {
			buf.append(iter.next()).append(",");
		}
		buf.delete(buf.length() - 1, buf.length()).append(")").append(" AND status=1 ");
		buf.append(" GROUP BY did ");
		super.pstmt = super.conn.prepareStatement(buf.toString());
		ResultSet rs = super.pstmt.executeQuery();
		while (rs.next()) {
			map.put(rs.getInt(1), rs.getInt(2));
		}
		return map;
	}

}
