package movie.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieFeeDao extends DaoBase {
	public void insert(int adminId,String movieName,String fee_type) {
		if(con==null) {
			return;
		}
		PreparedStatement stmt=null;
		try {
			int movieId=findMovie(adminId,movieName);
			System.out.println(movieId);
			String sql="insert into movie_fee(movie_id,fee_type) values(?,?)";
			stmt=con.prepareStatement(sql);
			stmt.setInt(1,movieId);
			stmt.setString(2, fee_type);
			stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt!=null) {
					stmt.close();
					stmt=null;
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public int findMovie(int adminId,String movieName) {
		if(con==null) {
			return 0;
		}
		PreparedStatement stmt=null;
		ResultSet rs=null;
		int movieId=0;
		try {
			String sql="select * from movie where(administrator_number=? and movie_name=?)";
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, adminId);
			stmt.setString(2, movieName);
			rs=stmt.executeQuery();
			while(rs.next()) {
				movieId=rs.getInt("movie_id");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt!=null) {
					stmt.close();
					stmt=null;
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return movieId;
	}
}
