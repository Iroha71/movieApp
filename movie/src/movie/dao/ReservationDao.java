package movie.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReservationDao extends DaoBase{
	public void insertReservation(Integer MovieTermNumber,String TheaterId,Integer ScreenNumber,
			Integer MemberNumber) throws SQLException{
		if(con == null) {

			return;
		}
		PreparedStatement reservation = null;
		try {
			//映画予約へINSERT文を発行
			String sql = "insert into "
					+ "movie_reservation(movie_term_number,theater_id,screen_number,member_number) "
					+ "values(?,?,?,?)";

			reservation = con.prepareStatement(sql);

			reservation.setInt(1,MovieTermNumber);
			reservation.setString(2,TheaterId);
			reservation.setInt(3,ScreenNumber);
			reservation.setInt(4,MemberNumber);

			reservation.executeUpdate();

		}catch(SQLException e) {

			e.printStackTrace();
		}
	}

	public void insertReservationItem(Integer MovieTermNumber,String TheaterId,Integer ScreenNumber,
			Integer MemberNumber,List SheetNumber,List FeeType)throws SQLException{

		if(con == null) {

			return;
		}

		int reservationNumber = 0;
		PreparedStatement reservationItem = null;
		ResultSet rs = null;
		try {
			//映画予約番号を取得するselect文

			String sql = "select reservation_number from movie_reservation where movie_term_number = ? and theater_id = ? and screen_number = ? and member_number = ?";

			reservationItem = con.prepareStatement(sql);

			reservationItem.setInt(1,MovieTermNumber);
			reservationItem.setString(2, TheaterId);
			reservationItem.setInt(3,ScreenNumber);
			reservationItem.setInt(4,MemberNumber);

			rs = reservationItem.executeQuery();

			while(rs.next()) {

				reservationNumber = rs.getInt("reservation_number");
			}

			//映画予約明細にinsert文を発行
			reservationItem = con.prepareStatement("INSERT INTO movie_reservation_item "
					+ "values(?,?,?)");

			//同じ映画予約番号に座席と料金が複数ある場合における、DBに登録するためのfor文
			for(int len = 0;len<SheetNumber.size();len++) {
				reservationItem.setInt(1,reservationNumber);
				reservationItem.setInt(2,(Integer)SheetNumber.get(len));
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
