package movie.dao;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends DaoBase{
	public String getBy(String mail,String password)throws SQLException{
		//ビーンズを使う
		String loginInfo=null;
		if(con==null) {
			return null;
		}
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			String sql="select * from member where member_mail=? and member_pass=?";
			stmt=con.prepareStatement(sql);
			stmt.setString(1, mail);
			stmt.setString(2, password);
			rs=stmt.executeQuery();
			while(rs.next()) {
				loginInfo="ログイン";
			}
		}catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			try {
				if(stmt!=null) {
					stmt.close();
					stmt=null;
				}
				if(rs!=null) {
					rs.close();
					rs=null;
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return loginInfo;
	}

	public String getAdmin(String mail,String password)throws SQLException{
		String loginInfo=null;
		if(con==null) {
			return null;
		}
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			String sql="select * from administrator where administrator_mail=? and administrator_pass=?";
			stmt=con.prepareStatement(sql);
			stmt.setString(1, mail);
			stmt.setString(2, password);
			rs=stmt.executeQuery();
			while(rs.next()) {
				loginInfo="ログイン";
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt!=null) {
					stmt.close();
					stmt=null;
				}
				if(rs!=null) {
					rs.close();
					rs=null;
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return loginInfo;
	}

	public void subscribeInsert(String mail,String pass,String name,String birth,String jender,String phone)
		throws SQLException{

		if(con == null) {
			return;
		}

		PreparedStatement subscribe = null;
		//生年月日をString型からDate型へ変換
		Date birthday = Date.valueOf(birth);

		try {
			//会員へINSERT文を発行
			String sql = "insert into member(member_mail,member_name,"
					+ "member_birthday,member_phone,member_sei,member_pass) "
					+ "values(?,?,?,?,?,?)";

			subscribe = con.prepareStatement(sql);

			subscribe.setString(1, mail);
			subscribe.setString(2, name);
			subscribe.setDate(3, birthday);
			subscribe.setString(4,phone);
			subscribe.setString(5, jender);
			subscribe.setString(6, pass);

			subscribe.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
