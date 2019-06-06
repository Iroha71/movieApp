package movie.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReservationDao extends DaoBase{
	public void insert(int MovieTermNumber,String TheaterId,int ScreenNumber,
			int MemberNumber,List SheetNumber,List FeeType) {
		if(con == null) {

			return;
		}

		int reservationNumber;
		PreparedStatement reservation = null;
		PreparedStatement reservationItem = null;
		ResultSet rs = null;

		try {
			//映画予約へINSERT文を発行

			reservation = con.prepareStatement("INSERT INTO "
					+ "movie_reservation(movie_term_number,theater_id,screen_number,member_number) "
					+ "values(?,?,?,?");

			reservation.setInt(1,MovieTermNumber);
			reservation.setString(2,TheaterId);
			reservation.setInt(3,ScreenNumber);
			reservation.setInt(4,MemberNumber);

			reservation.executeUpdate();

		}catch(SQLException e) {

			e.printStackTrace();
		}

		try {
			//映画予約明細へINSERT文を発行
			reservationItem = con.prepareStatement("SELECT reservation_number from movie_reservation_item"
					+ "where movie_term_number = ? and theater_id = ? and screen_number = ?"
					+ "and member_number = ?");

			reservationItem.setInt(1,MovieTermNumber);
			reservationItem.setString(2,TheaterId);
			reservationItem.setInt(3,ScreenNumber);
			reservationItem.setInt(4,MemberNumber);
			rs = reservationItem.executeQuery();

			reservationNumber = rs.getInt("reservation_number");

			reservationItem = con.prepareStatement("INSERT INTO movie_reservation_item"
					+ "values(?,?,?)");

			for(int len = 0;len<SheetNumber.size();len++) {
				reservationItem.setInt(1,reservationNumber);
				reservationItem.setInt(2,(int)SheetNumber.get(len));
				reservationItem.setString(3,(String)FeeType.get(len));

				reservationItem.executeUpdate();
			}
		}catch(SQLException e) {

			e.printStackTrace();
		}
		finally {
			if(con != null) {
				try {
					con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
