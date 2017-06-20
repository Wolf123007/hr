package cn.mldn.hr.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.mldn.hr.dao.IEmployeeDAO;
import cn.mldn.hr.vo.Employee;
import cn.mldn.util.dao.AbstractDAOImpl;

public class EmployeeDAOImpl extends AbstractDAOImpl implements IEmployeeDAO {

	public EmployeeDAOImpl(Connection conn) {
		super(conn);
	}

	@Override
	public boolean doCreate(Employee vo) throws SQLException {
		String sql = " INSERT INTO employee (eid, aid, did, levid, jid, ename, birthday, sex, idcard, dname, job, school, profession, grad, photo, indate, outdate, status, sal, note, edu) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, vo.getEid());
		super.pstmt.setString(2, vo.getAdmin().getAid());
		super.pstmt.setInt(3, vo.getDept().getDid());
		super.pstmt.setInt(4, vo.getLevel().getLevid());
		super.pstmt.setInt(5, vo.getJobs().getJid());
		super.pstmt.setString(6, vo.getEname());
		super.pstmt.setTimestamp(7, new Timestamp(vo.getBirthday().getTime()));
		super.pstmt.setString(8, vo.getSex());
		super.pstmt.setString(9, vo.getIdcard());
		super.pstmt.setString(10, vo.getDname());
		super.pstmt.setString(11, vo.getJob());
		super.pstmt.setString(12, vo.getSchool());
		super.pstmt.setString(13, vo.getProfession());
		super.pstmt.setTimestamp(14, new Timestamp(vo.getGrad().getTime()));
		super.pstmt.setString(15, vo.getPhoto());
		super.pstmt.setTimestamp(16, new Timestamp(vo.getIndate().getTime()));
		if (vo.getOutdate() == null) {
			super.pstmt.setNull(17, Types.NULL);
		} else {
			super.pstmt.setTimestamp(17, new Timestamp(vo.getOutdate().getTime()));
		}
		super.pstmt.setInt(18, vo.getStatus());
		super.pstmt.setDouble(19, vo.getSal());
		super.pstmt.setString(20, vo.getNote());
		super.pstmt.setString(21, vo.getEdu());
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doUpdate(Employee vo) throws SQLException {
		String sql = " UPDATE employee SET did=?, levid=?, jid=?, ename=?, birthday=?, sex=?, idcard=?, dname=?, job=?, school=?, profession=?, grad=?, photo=?, outdate=?, status=?, sal=?, note=?, edu=? "
				+ " WHERE eid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		
		super.pstmt.setInt(1, vo.getDept().getDid());
		super.pstmt.setInt(2, vo.getLevel().getLevid());
		super.pstmt.setInt(3, vo.getJobs().getJid());
		super.pstmt.setString(4, vo.getEname());
		super.pstmt.setTimestamp(5, new Timestamp(vo.getBirthday().getTime()));
		super.pstmt.setString(6, vo.getSex());
		super.pstmt.setString(7, vo.getIdcard());
		super.pstmt.setString(8, vo.getDname());
		super.pstmt.setString(9, vo.getJob());
		super.pstmt.setString(10, vo.getSchool());
		super.pstmt.setString(11, vo.getProfession());
		super.pstmt.setTimestamp(12, new Timestamp(vo.getGrad().getTime()));
		super.pstmt.setString(13, vo.getPhoto());
		if (vo.getOutdate() == null) {
			super.pstmt.setNull(14, Types.NULL);
		} else {
			super.pstmt.setTimestamp(14, new Timestamp(vo.getOutdate().getTime()));
		}
		super.pstmt.setInt(15, vo.getStatus());
		super.pstmt.setDouble(16, vo.getSal());
		super.pstmt.setString(17, vo.getNote());
		super.pstmt.setString(18, vo.getEdu());
		super.pstmt.setInt(19, vo.getEid());
		
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
		return super.removeHandle("employee", "eid", ids);
	}

	@Override
	public Employee findById(Integer id) throws SQLException {
		Employee vo = null;
		String sql = "SELECT eid, aid, did, levid, jid, ename, birthday, sex, idcard, dname, job, school, profession, grad, photo, indate, outdate, status, sal, note, edu "
				+ " FROM employee WHERE eid=? ";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, id);
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			vo = new Employee();
			vo.setEid(rs.getInt(1));
			vo.getAdmin().setAid(rs.getString(2));
			vo.getDept().setDid(rs.getInt(3));
			vo.getLevel().setLevid(rs.getInt(4));
			vo.getJobs().setJid(rs.getInt(5));
			vo.setEname(rs.getString(6));
			vo.setBirthday(rs.getTimestamp(7));
			vo.setSex(rs.getString(8));
			vo.setIdcard(rs.getString(9));
			vo.setDname(rs.getString(10));
			vo.setJob(rs.getString(11));
			vo.setSchool(rs.getString(12));
			vo.setProfession(rs.getString(13));
			vo.setGrad(rs.getTimestamp(14));
			vo.setPhoto(rs.getString(15));
			vo.setIndate(rs.getTimestamp(16));
			vo.setOutdate(rs.getTimestamp(17));
			vo.setStatus(rs.getInt(18));
			vo.setSal(rs.getDouble(19));
			vo.setNote(rs.getString(20));
			vo.setEdu(rs.getString(21));
		}
		return vo;
	}

	@Override
	public List<Employee> findAll() throws SQLException {
		List<Employee> all = new ArrayList<Employee>();
		String sql = "SELECT eid, aid, did, levid, jid, ename, birthday, sex, idcard, dname, job, school, profession, grad, photo, indate, outdate, status, sal, note, edu "
				+ " FROM employee ";
		super.pstmt = super.conn.prepareStatement(sql);
		ResultSet rs = super.pstmt.executeQuery();
		while (rs.next()) {
			Employee vo = new Employee();
			vo.setEid(rs.getInt(1));
			vo.getAdmin().setAid(rs.getString(2));
			vo.getDept().setDid(rs.getInt(3));
			vo.getLevel().setLevid(rs.getInt(4));
			vo.getJobs().setJid(rs.getInt(5));
			vo.setEname(rs.getString(6));
			vo.setBirthday(rs.getTimestamp(7));
			vo.setSex(rs.getString(8));
			vo.setIdcard(rs.getString(9));
			vo.setDname(rs.getString(10));
			vo.setJob(rs.getString(11));
			vo.setSchool(rs.getString(12));
			vo.setProfession(rs.getString(13));
			vo.setGrad(rs.getTimestamp(14));
			vo.setPhoto(rs.getString(15));
			vo.setIndate(rs.getTimestamp(16));
			vo.setOutdate(rs.getTimestamp(17));
			vo.setStatus(rs.getInt(18));
			vo.setSal(rs.getDouble(19));
			vo.setNote(rs.getString(20));
			vo.setEdu(rs.getString(21));
			all.add(vo);
		}
		return all;
	}

	@Override
	public List<Employee> findAllSplit(Integer currentPage, Integer lineSize,
			String column, String keyWord) throws SQLException {
		List<Employee> all = new ArrayList<Employee>();
		String sql = "SELECT eid, aid, did, levid, jid, ename, birthday, sex, idcard, dname, job, school, profession, grad, photo, indate, outdate, status, sal, note, edu "
				+ " FROM employee WHERE " + column + " LIKE ? LIMIT ?, ? ";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, "%" + keyWord + "%");
		super.pstmt.setInt(2, (currentPage - 1) * lineSize);
		super.pstmt.setInt(3, lineSize);
		ResultSet rs = super.pstmt.executeQuery();
		while (rs.next()) {
			Employee vo = new Employee();
			vo.setEid(rs.getInt(1));
			vo.getAdmin().setAid(rs.getString(2));
			vo.getDept().setDid(rs.getInt(3));
			vo.getLevel().setLevid(rs.getInt(4));
			vo.getJobs().setJid(rs.getInt(5));
			vo.setEname(rs.getString(6));
			vo.setBirthday(rs.getTimestamp(7));
			vo.setSex(rs.getString(8));
			vo.setIdcard(rs.getString(9));
			vo.setDname(rs.getString(10));
			vo.setJob(rs.getString(11));
			vo.setSchool(rs.getString(12));
			vo.setProfession(rs.getString(13));
			vo.setGrad(rs.getTimestamp(14));
			vo.setPhoto(rs.getString(15));
			vo.setIndate(rs.getTimestamp(16));
			vo.setOutdate(rs.getTimestamp(17));
			vo.setStatus(rs.getInt(18));
			vo.setSal(rs.getDouble(19));
			vo.setNote(rs.getString(20));
			vo.setEdu(rs.getString(21));
			all.add(vo);
		}
		return all;
	}

	@Override
	public Integer getAllCount(String column, String keyWord)
			throws SQLException {
		return super.countHandle("employee", column, keyWord);
	}

	@Override
	public boolean doUpdateStatus(Integer status, Set<Integer> ids, Date outdate)
			throws SQLException {
		if (ids.size() == 0) {
			return false;
		}
		String sql = "UPDATE employee SET status=?, outdate=? WHERE eid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		Iterator<Integer> iter = ids.iterator();
		while (iter.hasNext()) {
			super.pstmt.setInt(1, status);
			super.pstmt.setTimestamp(2, new Timestamp(outdate.getTime()));
			super.pstmt.setInt(3, iter.next());
			super.pstmt.addBatch();
		}
		boolean flag = true;
		int result [] = super.pstmt.executeBatch();
		for (int i = 0; i < result.length; i++) {
			if (result[i] == 0) {
				flag = false;
			}
		}
		return flag;
	}

	@Override
	public List<Employee> findAllSplitByStatus(Integer status,
			Integer currentPage, Integer lineSize, String column, String keyWord)
			throws SQLException {
		List<Employee> all = new ArrayList<Employee>();
		String sql = "SELECT eid, aid, did, levid, jid, ename, birthday, sex, idcard, dname, job, school, profession, grad, photo, indate, outdate, status, sal, note, edu "
				+ " FROM employee WHERE " + column + " LIKE ? AND status=? LIMIT ?, ? ";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, "%" + keyWord + "%");
		super.pstmt.setInt(2, status);
		super.pstmt.setInt(3, (currentPage - 1) * lineSize);
		super.pstmt.setInt(4, lineSize);
		ResultSet rs = super.pstmt.executeQuery();
		while (rs.next()) {
			Employee vo = new Employee();
			vo.setEid(rs.getInt(1));
			vo.getAdmin().setAid(rs.getString(2));
			vo.getDept().setDid(rs.getInt(3));
			vo.getLevel().setLevid(rs.getInt(4));
			vo.getJobs().setJid(rs.getInt(5));
			vo.setEname(rs.getString(6));
			vo.setBirthday(rs.getTimestamp(7));
			vo.setSex(rs.getString(8));
			vo.setIdcard(rs.getString(9));
			vo.setDname(rs.getString(10));
			vo.setJob(rs.getString(11));
			vo.setSchool(rs.getString(12));
			vo.setProfession(rs.getString(13));
			vo.setGrad(rs.getTimestamp(14));
			vo.setPhoto(rs.getString(15));
			vo.setIndate(rs.getTimestamp(16));
			vo.setOutdate(rs.getTimestamp(17));
			vo.setStatus(rs.getInt(18));
			vo.setSal(rs.getDouble(19));
			vo.setNote(rs.getString(20));
			vo.setEdu(rs.getString(21));
			all.add(vo);
		}
		return all;
	}

	@Override
	public Integer getAllCountByStatus(Integer status, String column,
			String keyWord) throws SQLException {
		String sql = " SELECT COUNT(*) FROM employee WHERE " + column + " LIKE ? AND status=? ";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, "%" + keyWord + "%");
		super.pstmt.setInt(2, status);
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}

	@Override
	public boolean doUpdateSal(Employee vo) throws SQLException {
		String sql = "UPDATE employee SET sal=?, levid=? WHERE eid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setDouble(1, vo.getSal());
		super.pstmt.setInt(2, vo.getLevel().getLevid());
		super.pstmt.setInt(3, vo.getEid());
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doUpdateDeptAndJob(Employee vo) throws SQLException {
		String sql = "UPDATE employee SET did=?, jid=?, dname=?, job=? WHERE eid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, vo.getDept().getDid());
		super.pstmt.setInt(2, vo.getJobs().getJid());
		super.pstmt.setString(3, vo.getDname());
		super.pstmt.setString(4, vo.getJob());
		super.pstmt.setInt(5, vo.getEid());
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public List<Employee> findAllByDept(Set<Integer> dids) throws SQLException {
		if (dids.size() == 0) {
			return null;
		}
		List<Employee> all = new ArrayList<Employee>();
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT eid, aid, did, levid, jid, ename, birthday, sex, idcard, dname, job, school, profession, grad, photo, indate, outdate, status, sal, note, edu FROM employee WHERE status=1 AND did IN (");
		Iterator<Integer> iter = dids.iterator();
		while (iter.hasNext()) {
			buf.append(iter.next()).append(",");
		}
		buf.delete(buf.length() -1, buf.length()).append(")");
		super.pstmt = super.conn.prepareStatement(buf.toString());
		ResultSet rs = super.pstmt.executeQuery();
		while (rs.next()) {
			Employee vo = new Employee();
			vo.setEid(rs.getInt(1));
			vo.getAdmin().setAid(rs.getString(2));
			vo.getDept().setDid(rs.getInt(3));
			vo.getLevel().setLevid(rs.getInt(4));
			vo.getJobs().setJid(rs.getInt(5));
			vo.setEname(rs.getString(6));
			vo.setBirthday(rs.getTimestamp(7));
			vo.setSex(rs.getString(8));
			vo.setIdcard(rs.getString(9));
			vo.setDname(rs.getString(10));
			vo.setJob(rs.getString(11));
			vo.setSchool(rs.getString(12));
			vo.setProfession(rs.getString(13));
			vo.setGrad(rs.getTimestamp(14));
			vo.setPhoto(rs.getString(15));
			vo.setIndate(rs.getTimestamp(16));
			vo.setOutdate(rs.getTimestamp(17));
			vo.setStatus(rs.getInt(18));
			vo.setSal(rs.getDouble(19));
			vo.setNote(rs.getString(20));
			vo.setEdu(rs.getString(21));
			all.add(vo);
		}
		return all;
	}

}
