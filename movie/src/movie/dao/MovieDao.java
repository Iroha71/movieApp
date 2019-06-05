package movie.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MovieDao extends DaoBase{
	public void delete(String movieId) {
		if(con==null) {
			return;
		}
		PreparedStatement stmt=null;
		try {
			String sql="delete from movie where movie_id=?";
			stmt=con.prepareStatement(sql);
			stmt.setString(1, movieId);
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

	public void insert(int adminId,String movieName,Date releaseDate,Date finishDate,String directed,String cast,int ticketPrice,String movieDetail) {
		if(con==null) {
			return;
		}
		PreparedStatement stmt=null;
		try {
			String sql="insert into movie values(?,?,?,?,?,?,?)";
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, adminId);
			stmt.setString(2, movieName);
			stmt.setString(3, cast);
			stmt.setString(4, directed);
			stmt.setDate(5, releaseDate);
			stmt.setDate(6, finishDate);
			stmt.setString(7, movieDetail);
			stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
