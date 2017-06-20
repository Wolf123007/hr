package cn.mldn.hr.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.mldn.hr.dao.IWorkDAO;
import cn.mldn.hr.vo.Work;
import cn.mldn.util.dao.AbstractDAOImpl;

public class WorkDAOImpl extends AbstractDAOImpl implements IWorkDAO {

	public WorkDAOImpl(Connection conn) {
		super(conn);
	}

	@Override
	public boolean doCreate(Work vo) throws SQLException {
		String sql = "INSERT INTO work (eid, aid, olddid, newdid, oldjid, newjid, cdate, olddname, oldjob, newdname, newjob, reason, note) "
				+ " VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, vo.getEmployee().getEid());
		super.pstmt.setString(2, vo.getAdmin().getAid());
		super.pstmt.setInt(3, vo.getOlddept().getDid());
		super.pstmt.setInt(4, vo.getNewdept().getDid());
		super.pstmt.setInt(5, vo.getOldjobs().getJid());
		super.pstmt.setInt(6, vo.getNewJobs().getJid());
		super.pstmt.setTimestamp(7, new Timestamp(vo.getCdate().getTime()));
		super.pstmt.setString(8, vo.getOlddname());
		super.pstmt.setString(9, vo.getOldjob());
		super.pstmt.setString(10, vo.getNewdname());
		super.pstmt.setString(11, vo.getNewjob());
		super.pstmt.setString(12, vo.getReason());
		super.pstmt.setString(13, vo.getNote());
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doUpdate(Work vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Work findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Work> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Work> findAllSplit(Integer currentPage, Integer lineSize,
			String column, String keyWord) throws SQLException {
		List<Work> all = new ArrayList<Work>();
		String sql = "SELECT wid, eid, aid, olddid, newdid, oldjid, newjid, cdate, olddname, oldjob, newdname, newjob, reason, note FROM work WHERE "
				+ column + " LIKE ? LIMIT ?, ? ";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, "%" + keyWord + "%");
		super.pstmt.setInt(2, (currentPage - 1) * lineSize);
		super.pstmt.setInt(3, lineSize);
		ResultSet rs = super.pstmt.executeQuery();
		while (rs.next()) {
			Work vo = new Work();
			vo.setWid(rs.getInt(1));
			vo.getEmployee().setEid(rs.getInt(2));
			vo.getAdmin().setAid(rs.getString(3));
			vo.getOlddept().setDid(rs.getInt(4));
			vo.getNewdept().setDid(rs.getInt(5));
			vo.getOldjobs().setJid(rs.getInt(6));
			vo.getNewJobs().setJid(rs.getInt(7));
			vo.setCdate(rs.getTimestamp(8));
			vo.setOlddname(rs.getString(9));
			vo.setOldjob(rs.getString(10));
			vo.setNewdname(rs.getString(11));
			vo.setNewjob(rs.getString(12));
			vo.setReason(rs.getString(13));
			vo.setNote(rs.getString(14));
			all.add(vo);
		}
		return all;
	}

	@Override
	public Integer getAllCount(String column, String keyWord)
			throws SQLException {
		return super.countHandle("work", column, keyWord);
	}



}
