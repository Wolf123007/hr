package cn.mldn.hr.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.mldn.hr.dao.ISalaryDAO;
import cn.mldn.hr.vo.Salary;
import cn.mldn.util.dao.AbstractDAOImpl;

public class SalaryDAOImpl extends AbstractDAOImpl implements ISalaryDAO {

	public SalaryDAOImpl(Connection conn) {
		super(conn);
	}

	@Override
	public boolean doCreate(Salary vo) throws SQLException {
		String sql = "INSERT INTO salary (oldlevid, newlevid, eid, aid, cdate, oldsal, newsal, reason, note) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, vo.getOldlevel().getLevid());
		super.pstmt.setInt(2, vo.getNewlevel().getLevid());
		super.pstmt.setInt(3, vo.getEmployee().getEid());
		super.pstmt.setString(4, vo.getAdmin().getAid());
		super.pstmt.setTimestamp(5, new Timestamp(vo.getCdate().getTime()));
		super.pstmt.setDouble(6, vo.getOldsal());
		super.pstmt.setDouble(7, vo.getNewsal());
		super.pstmt.setString(8, vo.getReason());
		super.pstmt.setString(9, vo.getNote());
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doUpdate(Salary vo) throws SQLException {
		return false;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Salary findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Salary> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Salary> findAllSplit(Integer currentPage, Integer lineSize,
			String column, String keyWord) throws SQLException {
		List<Salary> all = new ArrayList<Salary>();
		String sql = "SELECT sid, oldlevid, newlevid, eid, aid, cdate, oldsal, newsal, reason, note FROM salary WHERE " 
			+ column + " LIKE ? LIMIT ?, ?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, "%" + keyWord + "%");
		super.pstmt.setInt(2, (currentPage - 1) * lineSize);
		super.pstmt.setInt(3, lineSize);
		ResultSet rs = super.pstmt.executeQuery();
		while (rs.next()) {
			Salary vo = new Salary();
			vo.setSid(rs.getInt(1));
			vo.getOldlevel().setLevid(rs.getInt(2));
			vo.getNewlevel().setLevid(rs.getInt(3));
			vo.getEmployee().setEid(rs.getInt(4));
			vo.getAdmin().setAid(rs.getString(5));
			vo.setCdate(rs.getTimestamp(6));
			vo.setOldsal(rs.getDouble(7));
			vo.setNewsal(rs.getDouble(8));
			vo.setReason(rs.getString(9));
			vo.setNote(rs.getString(10));
			all.add(vo);
		}
		return all;
	}

	@Override
	public Integer getAllCount(String column, String keyWord)
			throws SQLException {
		return super.countHandle("salary", column, keyWord);
	}

}
