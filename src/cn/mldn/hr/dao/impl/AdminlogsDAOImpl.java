package cn.mldn.hr.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import cn.mldn.hr.dao.IAdminlogsDAO;
import cn.mldn.hr.vo.Adminlogs;
import cn.mldn.util.dao.AbstractDAOImpl;

public class AdminlogsDAOImpl extends AbstractDAOImpl implements IAdminlogsDAO {

	public AdminlogsDAOImpl(Connection conn) {
		super(conn);
	}

	@Override
	public boolean doCreate(Adminlogs vo) throws SQLException {
		String sql = "INSERT INTO adminlogs(aid, logindate, ip) VALUES (?, ?, ?)";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, vo.getAdmin().getAid());
		super.pstmt.setTimestamp(2, new java.sql.Timestamp(vo.getLogindate().getTime()));
		super.pstmt.setString(3, vo.getIp());
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doUpdate(Adminlogs vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Adminlogs findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Adminlogs> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Adminlogs> findAllSplit(Integer currentPage, Integer lineSize,
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

}
