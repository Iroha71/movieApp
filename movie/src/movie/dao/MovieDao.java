package movie.dao;

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
}
