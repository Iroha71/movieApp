package movie.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import movie.beans.MovieReservationBeans;

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

	public List<MovieReservationBeans> select(int memberNumber) {
		if(con == null) {
			//戻り方をリストに変更した方がいいのでは？
			return null;
	}
		MovieReservationBeans beans;
		List<MovieReservationBeans>list = new ArrayList<MovieReservationBeans>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			//映画一覧用SELECT文
			stmt = con.prepareStatement("select movie.movie_id,movie.movie_name,movie_reservation.member_number,movie_reservation.reservation_number,term.term_start,term.term_finish,movie_reservation_item.sheet_number,fee.fee_base"+
					" from movie_reservation "+
					"INNER JOIN movie_term ON movie_reservation.movie_term_number = movie_term.movie_term_number " +
					"INNER JOIN movie ON movie_term.movie_id = movie.movie_id " +
					"INNER JOIN movie_fee ON movie.movie_id = movie_fee.movie_id "+
					"INNER JOIN fee ON movie_fee.fee_type = fee.fee_type "+
					"INNER JOIN term ON movie_term.term_type = term.term_type "+
					"INNER JOIN screen ON movie_reservation.theater_id = screen.theater_id "+
					"INNER JOIN theater ON screen.theater_id = theater.theater_id "+
					"INNER JOIN movie_reservation_item ON movie_reservation_item.reservation_number = movie_reservation.reservation_number " +
					"Where movie_reservation.member_number = ?;");
			//Column 'movie_id' in field list is ambiguous?

			stmt.setInt(1,memberNumber);
			rs=stmt.executeQuery();

		while(rs.next()) {
			beans = new MovieReservationBeans();

			beans.setMovieId(rs.getInt("movie.movie_id"));
			beans.setMovieName(rs.getString("movie.movie_name"));
			beans.setMemberNumber(rs.getInt("movie_reservation.member_number"));
			beans.setReservationNumber(rs.getInt("movie_reservation.reservation_number"));
			beans.setFeeBase(rs.getInt("fee.fee_base"));
			beans.setSheetNumber(rs.getInt("movie_reservation_item.sheet_number"));
			beans.setTermStart(rs.getTimestamp("term.term_start"));
			beans.setTermFinish(rs.getTimestamp("term.term_finish"));

			list.add(beans);
		}

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(con != null) {
					try {
						con.close();
					}
					catch(SQLException e) {
						e.printStackTrace();
				}
			}
		}

		return list;
	}

	public void delete(int memberNumber,int sheetNumber,int reservationNumber){
		if(con == null) {
			//接続していない場合は何もしない
						return;
		}

		PreparedStatement stmt = null;
		try {
			//予約明細用DELETE文の発行
			stmt = con.prepareStatement("DELETE  FROM movie_reservation_item  WHERE sheet_number = ? and reservation_number = ?");

			stmt.setInt(1, sheetNumber);
			stmt.setInt(2,reservationNumber);

			stmt.executeUpdate();

		}catch(SQLException e) {
			//errorが発生した場合にコンソールにログを出力する
			e.printStackTrace();
		}finally {
			//接続（コネクション）を閉じる
			if(con != null) {
				try {
					con.close();
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
