package movie.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import movie.beans.ReservationBeans;


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
				reservationItem.setInt(3,Integer.parseInt((String)FeeType.get(len)));

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

	public List<ReservationBeans> select(int memberNumber) {
		if(con == null) {

			return null;
	}
		ReservationBeans beans;
		List<ReservationBeans>list = new ArrayList<ReservationBeans>();
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


			stmt.setInt(1,memberNumber);
			rs=stmt.executeQuery();

		while(rs.next()) {
			beans = new ReservationBeans();

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

	public void delete(int memberNumber,int reservationNumber){
		if(con == null) {
			//接続していない場合は何もしない
						return;
		}

		PreparedStatement stmt = null;
		PreparedStatement stmt2=null;
		try {
			//予約明細用DELETE文の発行
			stmt = con.prepareStatement("delete from movie_reservation_item where reservation_number=?");
			stmt.setInt(1,reservationNumber);
			stmt2=con.prepareStatement("DELETE  FROM movie_reservation  WHERE reservation_number = ?");
			stmt2.setInt(1, reservationNumber);
			stmt.executeUpdate();
			stmt2.executeUpdate();

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

	public List<Integer> selectReserveSheet(int termNum,String theaterId,int ScreenNum) {
		if(con==null) {
			return null;
		}
		List<Integer> reserveList=new ArrayList<Integer>();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			String sql="select movie_reservation_item.sheet_number from movie_reservation "
					+ "inner join movie_reservation_item on movie_reservation.reservation_number=movie_reservation_item.reservation_number "
					+ "where movie_term_number=? and theater_id=? and screen_number=?";
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, termNum);
			stmt.setString(2, theaterId);
			stmt.setInt(3, ScreenNum);
			rs=stmt.executeQuery();
			while(rs.next()) {
				reserveList.add(rs.getInt("movie_reservation_item.sheet_number"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(con!=null) {
				try {
					con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return reserveList;
	}

	public List<ReservationBeans> selectReservation(int memberNumber){
		if(con==null) {
			return null;
		}
		List<ReservationBeans> list=new ArrayList<ReservationBeans>();
		ResultSet rs =null;
		PreparedStatement stmt=null;
		try {
			String sql="select sheet_number,movie_reservation.reservation_number,movie.movie_name from movie_reservation_item " +
					"	inner join movie_reservation on movie_reservation_item.reservation_number = movie_reservation.reservation_number" +
					"    inner join movie_term on movie_term.movie_term_number = movie_reservation.movie_term_number" +
					"    inner join movie on movie_term.movie_id = movie.movie_id "+
					"    where member_number = ?";
			String sqlOnlyReservation="select reservation_number,movie_name from movie_reservation " +
					" inner join movie_term on movie_reservation.movie_term_number = movie_term.movie_term_number" +
					" inner join movie on movie_term.movie_id = movie.movie_id" +
					" where movie_reservation.member_number = ?";
			stmt=con.prepareStatement(sqlOnlyReservation);
			stmt.setInt(1, memberNumber);
			rs=stmt.executeQuery();
			while(rs.next()) {
				ReservationBeans rb=new ReservationBeans();
				rb.setReservationNumber(rs.getInt("reservation_number"));
				rb.setMovieName(rs.getString("movie_name"));
				list.add(rb);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(stmt!=null) {
				try {
					stmt.close();
					stmt=null;
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
					rs=null;
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
}
