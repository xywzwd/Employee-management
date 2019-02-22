package cn.yuwei.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.yuwei.dao.EmpDao;
import cn.yuwei.entity.Emp;
import cn.yuwei.util.DBUtils;

public class EmpDaoImpl implements EmpDao{

	@Override
	public List<Emp> findAllEmp() {
		
		Connection conn=null;
		Statement sta=null;
		List<Emp> list = new ArrayList<Emp>();
		try {
			//Class.forName("oracle.jdbc.OracleDriver");
			//conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			conn=DBUtils.getConnection();
			sta=conn.createStatement();
			String sql="select * from emp";
			ResultSet rs=sta.executeQuery(sql);
			while(rs.next()) {
				int eno=rs.getInt("empno");
				String ename=rs.getString("ename");
				String job=rs.getString("job");
				int mgr=rs.getInt("mgr");
				Date d = rs.getDate("hiredate");
				double sal=rs.getDouble("sal");
				double comm = rs.getDouble("comm");
				int deptno=rs.getInt("deptno");
				
				Emp emp = new Emp(eno,ename,job,mgr,d,sal,comm,deptno);
				list.add(emp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(sta!=null) {
				try {
					sta.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public Emp findOneEmp(int empno) {
		Connection conn=null;
		Statement sta=null;
		Emp emp=null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			sta=conn.createStatement();
			String sql="select * from emp where empno="+empno;
			ResultSet rs=sta.executeQuery(sql);
			if(rs.next()) {
				int eno=rs.getInt("empno");
				String ename=rs.getString("ename");
				String job=rs.getString("job");
				int mgr=rs.getInt("mgr");
				Date d = rs.getDate("hiredate");
				double sal=rs.getDouble("sal");
				double comm = rs.getDouble("comm");
				int deptno=rs.getInt("deptno");
				
				emp = new Emp(eno,ename,job,mgr,d,sal,comm,deptno);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(sta!=null) {
				try {
					sta.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return emp;
	}

	@Override
	public int saveEmp(Emp e) {
	
		Connection conn=null;
		PreparedStatement ps = null;
		int n = 0;
		
		conn=DBUtils.getConnection();
		try {
			ps = conn.prepareStatement("insert into emp values (?,?,?,?,?,?,?,?)");
			ps.setInt(1, e.getEmpno());
			ps.setString(2, e.getEname());
			ps.setString(3, e.getJob());
			ps.setInt(4, e.getMgr());
			java.sql.Date d = new java.sql.Date(e.getHiredate().getTime());
			ps.setDate(5, d);
			ps.setDouble(6, e.getSal());
			ps.setDouble(7, e.getComm());
			ps.setInt(8, e.getDeptno());
			
			n=ps.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		}
		return n;
	}

	@Override
	public int deleteEmp(int empno) {
		
		Connection conn=null;
		PreparedStatement ps = null;
		int n = 0;
		
		conn=DBUtils.getConnection();
		try {
			ps = conn.prepareStatement("delete from emp where empno=?");
			ps.setInt(1, empno);
			
			n=ps.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		}
		return n;
	
	}

}
