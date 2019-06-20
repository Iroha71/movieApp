package movie.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import movie.beans.MovieListBeans;

public class MovieDao extends DaoBase {

	    public List<MovieListBeans>getList(){

	  	  if(con == null){
	  				//接続していない場合何もしない
	  				return null;
	  	  }

	  	  List<MovieListBeans> list=new ArrayList<MovieListBeans>();

	  	  PreparedStatement stmt=null;
	  	  ResultSet rs=null;

	        try{
	  			///////////////////////////////////
	  			//SELECT文の発行
	  			stmt = con.prepareStatement("SELECT *,screen.sheet_total,(SELECT COUNT(*) "
	  					+ "FROM(screen INNER JOIN movie_reservation ON screen.theater_id=movie_reservation.theater_id) "
	  					+ "INNER JOIN movie_reservation_item ON movie_reservation.reservation_number=movie_reservation_item.reservation_number) as count "
	  					+ "FROM ((movie INNER JOIN movie_term ON movie.movie_id=movie_term.movie_id) "
	  					+ "INNER JOIN movie_reservation ON movie_reservation.movie_term_number) "
	  					+ "INNER JOIN screen ON screen.theater_id=movie_reservation.theater_id "
	  					+ "INNER JOIN term ON movie_term.movie_term_number=term.term_type");

	  			rs=stmt.executeQuery();

	  			while(rs.next()){
	  				//////////////////////////////////
	  				//ビーンズのリストに格納する
	  				MovieListBeans beans = new MovieListBeans();

	  				beans.setMovieName(rs.getString("movie_name"));
	  				beans.setTermStart(rs.getTimestamp("term_start"));
	  				beans.setTermFinish(rs.getTimestamp("term_finish"));
	  				beans.setCount(rs.getInt("count"));
	  				beans.setSheet(rs.getInt("sheet_total"));

	  				list.add(beans);
	  			}
	  		}catch(SQLException e) {
	  			//エラー発生した場合にコンソールにログを出力する
	  			e.printStackTrace();
	  		}
	  		finally {
	  			//接続（コネクション）を閉じる
	  			if(con!=null) {
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

	    public List<MovieListBeans>getReserveList(){

		  	  if(con == null){
		  				//接続していない場合何もしない
		  				return null;
		  	  }

		  	  List<MovieListBeans> reservelist=new ArrayList<MovieListBeans>();

		  	  PreparedStatement stmt=null;
		  	  ResultSet rs=null;

		        try{
		  			///////////////////////////////////
		  			//SELECT文の発行
		  			stmt = con.prepareStatement("SELECT movie_name,COUNT(*) as count FROM (movie INNER JOIN movie_term ON movie.movie_id=movie_term.movie_id) "
		  					+ "INNER JOIN movie_reservation ON movie_term.movie_term_number=movie_reservation.movie_term_number "
		  					+ "INNER JOIN movie_reservation_item ON movie_reservation.reservation_number=movie_reservation_item.reservation_number "
		  					+ "GROUP BY movie_name");

		  			rs=stmt.executeQuery();

		  			while(rs.next()){
		  				//////////////////////////////////
		  				//ビーンズのリストに格納する
		  				MovieListBeans beans = new MovieListBeans();

		  				beans.setMovieName(rs.getString("movie_name"));
		  				beans.setCount(rs.getInt("count"));


		  				reservelist.add(beans);
		  			}
		  		}catch(SQLException e) {
		  			//エラー発生した場合にコンソールにログを出力する
		  			e.printStackTrace();
		  		}
		  		finally {
		  			//接続（コネクション）を閉じる
		  			if(con!=null) {
		  				try {
		  					con.close();
		  				}
		  				catch(SQLException e) {
		  					e.printStackTrace();
		  				}
		  			}
		  		}

		  		return reservelist;
		  	}
 }


