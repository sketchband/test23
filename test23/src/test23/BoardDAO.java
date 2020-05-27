package test23;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class BoardDAO {
	
	private DBConnectionMgr pool;
	
	public BoardDAO() {
		try {
			pool = DBConnectionMgr.getInstance();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void postProc(BoardBean bean) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int ref = 0;
		int pos = 1;
		int depth = 1;
		
		try {
			con = pool.getConnection();
			String refsql = "select max(ref) from Board9";
			stmt = con.prepareStatement(refsql);
			rs = stmt.executeQuery();
			if(rs.next())
				ref = rs.getInt(1)+1;
			String sql = "insert into Board9 values(?,?,?,?,0,?,?,?,?,?,now())";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, bean.getNum());
			stmt.setInt(2, ref);
			stmt.setInt(3, pos);
			stmt.setInt(4, depth);
			stmt.setString(5, bean.getSubject());
			stmt.setString(6, bean.getName());
			stmt.setString(7, bean.getPw());
			stmt.setString(8, bean.getEmail());
			stmt.setString(9, bean.getContent());
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con,stmt,rs);
		}
	}
	
	public Vector<BoardBean> BoardList(int start,int end){
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Vector<BoardBean> v = new Vector<BoardBean>();
		
		try {
			con = pool.getConnection();
			String sql = "select * from Board9 order by ref desc,pos asc,pos limit ?,?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, start-1);
			stmt.setInt(2, end);
			rs = stmt.executeQuery();
			while(rs.next()) {
				BoardBean bean = new BoardBean();
				bean.setNum(rs.getInt("num"));
				bean.setRef(rs.getInt("ref"));
				bean.setPos(rs.getInt("pos"));
				bean.setDepth(rs.getInt("depth"));
				bean.setCount(rs.getInt("count"));
				bean.setSubject(rs.getString("subject"));
				bean.setName(rs.getString("name"));
				bean.setPw(rs.getString("pw"));
				bean.setEmail(rs.getString("email"));
				bean.setContent(rs.getString("content"));
				bean.setRegdate(rs.getString("regdate"));
				v.add(bean);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con,stmt,rs);
		}
		return v;
	}
	
	public BoardBean BoardReda(int num) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		BoardBean bean = new BoardBean();
		
		try {
			con = pool.getConnection();
			String sql = "select * from Board9 where num = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, num);
			rs = stmt.executeQuery();
			if(rs.next()) {
				bean.setNum(rs.getInt("num"));
				bean.setRef(rs.getInt("ref"));
				bean.setPos(rs.getInt("pos"));
				bean.setDepth(rs.getInt("depth"));
				bean.setCount(rs.getInt("count"));
				bean.setSubject(rs.getString("subject"));
				bean.setName(rs.getString("name"));
				bean.setPw(rs.getString("pw"));
				bean.setEmail(rs.getString("email"));
				bean.setContent(rs.getString("content"));
				bean.setRegdate(rs.getString("regdate"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con,stmt,rs);
		}
		return bean;
	}
	
	public String PwCheck(int num) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String pw = null;
		
		try {
			con = pool.getConnection();
			String sql = "select pw from Board9 where num = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, num);
			rs = stmt.executeQuery();
			if(rs.next())
				pw = rs.getString(1);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con,stmt,rs);
		}
		return pw;
	}
	
	public void BoardDelete(int num) {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = pool.getConnection();
			String sql = "delete from Board9 where num = ?";
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, num);
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con,stmt);
		}
	}
	
	public void BoardUpdate(BoardBean bean) {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = pool.getConnection();
			String sql = "update Board9 set subject = ?,name = ?,email = ?, content = ? where num = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, bean.getSubject());
			stmt.setString(2, bean.getName());
			stmt.setString(3, bean.getEmail());
			stmt.setString(4, bean.getContent());
			stmt.setInt(5, bean.getNum());
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con,stmt);
		}
	}
	
}
