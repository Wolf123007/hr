package cn.mldn.hr.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.mldn.hr.dao.ICourseDAO;
import cn.mldn.hr.vo.Course;
import cn.mldn.util.dao.AbstractDAOImpl;

public class CourseDAOImpl extends AbstractDAOImpl implements ICourseDAO {

	public CourseDAOImpl(Connection conn) {
		super(conn);
	}

	@Override
	public boolean doCreate(Course vo) throws SQLException {
		String sql = "INSERT INTO course (aid, cname, total, begin, end, status, photo, note) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, vo.getAdmin().getAid());
		super.pstmt.setString(2, vo.getCname());
		super.pstmt.setInt(3, vo.getTotal());
		super.pstmt.setTimestamp(4, new Timestamp(vo.getBegin().getTime()));
		super.pstmt.setTimestamp(5, new Timestamp(vo.getEnd().getTime()));
		super.pstmt.setInt(6, vo.getStatus());
		super.pstmt.setString(7, vo.getPhoto());
		super.pstmt.setString(8, vo.getNote());
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doUpdate(Course vo) throws SQLException {
		String sql = "UPDATE course SET aid=?, cname=?, begin=?, end=?, status=?, photo=?, note=? WHERE cid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, vo.getAdmin().getAid());
		super.pstmt.setString(2, vo.getCname());
		super.pstmt.setTimestamp(3, new Timestamp(vo.getBegin().getTime()));
		super.pstmt.setTimestamp(4, new Timestamp(vo.getEnd().getTime()));
		super.pstmt.setInt(5, vo.getStatus());
		super.pstmt.setString(6, vo.getPhoto());
		super.pstmt.setString(7, vo.getNote());
		super.pstmt.setInt(8, vo.getCid());
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Course findById(Integer id) throws SQLException {
		Course vo = null;
		String sql = "SELECT cid, aid, cname, total, begin, end, status, photo, note FROM course WHERE cid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, id);
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			vo = new Course();
			vo.setCid(rs.getInt(1));
			vo.getAdmin().setAid(rs.getString(2));
			vo.setCname(rs.getString(3));
			vo.setTotal(rs.getInt(4));
			vo.setBegin(rs.getDate(5));
			vo.setEnd(rs.getDate(6));
			vo.setStatus(rs.getInt(7));
			vo.setPhoto(rs.getString(8));
			vo.setNote(rs.getString(9));
		}
		return vo;
	}

	@Override
	public List<Course> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Course> findAllSplit(Integer currentPage, Integer lineSize,
			String column, String keyWord) throws SQLException {
		List<Course> all = new ArrayList<Course>();
		String sql = "SELECT cid, aid, cname, total, begin, end, status, photo, note FROM course WHERE "
				+ column + " LIKE ? LIMIT ?, ? ";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, "%" + keyWord + "%");
		super.pstmt.setInt(2, (currentPage - 1) * lineSize);
		super.pstmt.setInt(3, lineSize);
		ResultSet rs = super.pstmt.executeQuery();
		while (rs.next()) {
			Course vo = new Course();
			vo.setCid(rs.getInt(1));
			vo.getAdmin().setAid(rs.getString(2));
			vo.setCname(rs.getString(3));
			vo.setTotal(rs.getInt(4));
			vo.setBegin(rs.getDate(5));
			vo.setEnd(rs.getDate(6));
			vo.setStatus(rs.getInt(7));
			vo.setPhoto(rs.getString(8));
			vo.setNote(rs.getString(9));
			all.add(vo);
		}
		return all;
	}

	@Override
	public Integer getAllCount(String column, String keyWord)
			throws SQLException {
		return super.countHandle("course", column, keyWord);
	}

	@Override
	public Integer getId() throws SQLException {
		return super.getIdHandle();
	}


	@Override
	public List<Course> findAllSplitByEmployee(Integer eid,
			Integer currentPage, Integer lineSize, String column, String keyWord)
			throws SQLException {
		List<Course> all = new ArrayList<Course>();
		String sql = "SELECT cid, aid, cname, total, begin, end, status, photo, note FROM course WHERE cid IN (SELECT cid FROM details WHERE eid=?) AND "
				+ column + " LIKE ? LIMIT ?, ? ";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, eid);
		super.pstmt.setString(2, "%" + keyWord + "%");
		super.pstmt.setInt(3, (currentPage - 1) * lineSize);
		super.pstmt.setInt(4, lineSize);
		ResultSet rs = super.pstmt.executeQuery();
		while (rs.next()) {
			Course vo = new Course();
			vo.setCid(rs.getInt(1));
			vo.getAdmin().setAid(rs.getString(2));
			vo.setCname(rs.getString(3));
			vo.setTotal(rs.getInt(4));
			vo.setBegin(rs.getDate(5));
			vo.setEnd(rs.getDate(6));
			vo.setStatus(rs.getInt(7));
			vo.setPhoto(rs.getString(8));
			vo.setNote(rs.getString(9));
			all.add(vo);
		}
		return all;
	}

	@Override
	public Integer getAllCountByEmployee(Integer eid, String column,
			String keyWord) throws SQLException {
		String sql = "SELECT COUNT(*) FROM course WHERE cid IN (SELECT cid FROM details WHERE eid=?) AND "
				+ column + " LIKE ?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, eid);
		super.pstmt.setString(2, "%" + keyWord + "%");
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}

}
