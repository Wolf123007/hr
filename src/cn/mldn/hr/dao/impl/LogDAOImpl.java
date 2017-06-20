package cn.mldn.hr.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.mldn.hr.dao.ILogDAO;
import cn.mldn.hr.vo.Log;
import cn.mldn.util.dao.AbstractDAOImpl;

public class LogDAOImpl extends AbstractDAOImpl implements ILogDAO {

	public LogDAOImpl(Connection conn) {
		super(conn);
	}

	@Override
	public boolean doCreate(Log vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Log vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Log findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Log> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Log> findAllSplit(Integer currentPage, Integer lineSize,
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
	public boolean doCreateBatch(Set<Log> vos) throws SQLException {
		if (vos.size() == 0) {
			return false;
		}
		String sql = "INSERT INTO log (dtid, recdate, num, status) VALUES (?, ?, ?, ?)";
		super.pstmt = super.conn.prepareStatement(sql);
		Iterator<Log> iter = vos.iterator();
		while (iter.hasNext()) {
			Log vo = iter.next();
			super.pstmt.setInt(1, vo.getDetails().getDtid());
			super.pstmt.setTimestamp(2, new Timestamp(vo.getRecdate().getTime()));
			super.pstmt.setInt(3, vo.getNum());
			super.pstmt.setInt(4, vo.getStatus());
			super.pstmt.addBatch();
		}
		int[] result = super.pstmt.executeBatch();
		boolean flag = true;
		for (int i = 0; i < result.length; i++) {
			if (result[i] == 0) {
				flag = false;
			}
		}
		return flag;
	}

	@Override
	public List<Log> findAllByDetails(Integer dtid) throws SQLException {
		List<Log> all = new ArrayList<Log>();
		String sql = "SELECT lid, dtid, recdate, num, status FROM log WHERE dtid=? ORDER BY num";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, dtid);
		ResultSet rs = super.pstmt.executeQuery();
		while (rs.next()) {
			Log vo = new Log();
			vo.setLid(rs.getInt(1));
			vo.getDetails().setDtid(rs.getInt(2));
			vo.setRecdate(rs.getTimestamp(3));
			vo.setNum(rs.getInt(4));
			vo.setStatus(rs.getInt(5));
			all.add(vo);
		}
		return all;
	}

	@Override
	public boolean doUpdateStatus(Log vo) throws SQLException {
		String sql = "UPDATE log SET status=?, recdate=? WHERE lid=? AND recdate IS NULL";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, vo.getStatus());
		super.pstmt.setTimestamp(2, new Timestamp(vo.getRecdate().getTime()));
		super.pstmt.setInt(3, vo.getLid());
		return super.pstmt.executeUpdate() > 0;
	}

}
