package cn.mldn.hr.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.mldn.hr.dao.IDetailsDAO;
import cn.mldn.hr.vo.Details;
import cn.mldn.util.dao.AbstractDAOImpl;

public class DetailsDAOImpl extends AbstractDAOImpl implements IDetailsDAO {

	public DetailsDAOImpl(Connection conn) {
		super(conn);
	}

	@Override
	public boolean doCreate(Details vo) throws SQLException {
		String sql = "INSERT INTO details (cid, eid, ename, score) VALUES (?, ?, ?, ?)";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, vo.getCourse().getCid());
		super.pstmt.setInt(2, vo.getEmployee().getEid());
		super.pstmt.setString(3, vo.getEname());
		super.pstmt.setDouble(4, vo.getScore());
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doUpdate(Details vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Details findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Details> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Details> findAllSplit(Integer currentPage, Integer lineSize,
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
	public List<Details> findAllSplitByCourse(Integer cid, Integer currentPage,
			Integer lineSize, String column, String keyWord)
			throws SQLException {
		List<Details> all = new ArrayList<Details>();
		String sql = "SELECT dtid, cid, eid, ename, score FROM details WHERE cid=? AND "
				+ column + " LIKE ? LIMIT ?, ?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, cid);
		super.pstmt.setString(2, "%" + keyWord + "%");
		super.pstmt.setInt(3, (currentPage - 1) * lineSize );
		super.pstmt.setInt(4, lineSize);
		ResultSet rs = super.pstmt.executeQuery();
		while (rs.next()) {
			Details vo = new Details();
			vo.setDtid(rs.getInt(1));
			vo.getCourse().setCid(rs.getInt(2));
			vo.getEmployee().setEid(rs.getInt(3));
			vo.setEname(rs.getString(4));
			vo.setScore(rs.getDouble(5));
			all.add(vo);
		}
		return all;
	}

	@Override
	public Integer getAllCountByCourse(Integer cid, String column,
			String keyWord) throws SQLException {
		String sql = "SELECT COUNT(*) FROM details WHERE cid=? AND "
				+ column + " LIKE ? ";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, cid);
		super.pstmt.setString(2, "%" + keyWord + "%");
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}

	@Override
	public boolean doUpdateScore(Details vo) throws SQLException {
		String sql = "UPDATE details SET score=? WHERE dtid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setDouble(1, vo.getScore());
		super.pstmt.setInt(2, vo.getDtid());
		return super.pstmt.executeUpdate() > 0;
	}

}
