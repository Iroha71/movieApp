
package movie.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import movie.beans.FeeBeans;
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
    public List<MovieListBeans>getMovie(){

    	  if(con == null){
    				//接続していない場合何もしない
    				return null;
    	  }

    	  List<MovieListBeans> list=new ArrayList<MovieListBeans>();
    	  MovieListBeans beans;
    	  PreparedStatement stmt=null;
    	  ResultSet rs=null;
    	  String sql = "SELECT * FROM movie";
          try{
    			///////////////////////////////////
    			//SELECT文の発行
    			stmt = con.prepareStatement(sql);
    			rs=stmt.executeQuery();

    			while(rs.next()){
    				//////////////////////////////////
    				//ビーンズのリストに格納する
    				beans = new MovieListBeans();

    				beans.setMovieName(rs.getString("movie_name"));
    				beans.setStartDate(rs.getDate("release_start_date"));
    				beans.setFinishDate(rs.getDate("release_finish_date"));
    				beans.setCast(rs.getString("cast"));
    				beans.setDirected(rs.getString("directed"));
    				beans.setDetail(rs.getString("movie_detail"));

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
	  			stmt = con.prepareStatement("SELECT movie_name,COUNT(*) as count ,sheet_total,release_start_date,release_finish_date,cast,movie_detail,directed "
	  					+ "FROM (movie INNER JOIN movie_term ON movie.movie_id=movie_term.movie_id) "
	  					+ "INNER JOIN movie_reservation ON movie_term.movie_term_number=movie_reservation.movie_term_number "
	  					+ "INNER JOIN screen ON movie_reservation.screen_number = screen.screen_number AND movie_reservation.theater_id = screen.theater_id "
	  					+ "INNER JOIN movie_reservation_item ON movie_reservation.reservation_number=movie_reservation_item.reservation_number "
	  					+ "GROUP BY movie_name");

	  			rs=stmt.executeQuery();

	  			while(rs.next()){
	  				//////////////////////////////////
	  				//ビーンズのリストに格納する
	  				MovieListBeans beans = new MovieListBeans();

	  				beans.setMovieName(rs.getString("movie_name"));
	  				beans.setCount(rs.getInt("count"));
	  				beans.setSheet(rs.getInt("sheet_total"));
	  				beans.setStartDate(rs.getDate("release_start_date"));
	  				beans.setFinishDate(rs.getDate("release_finish_date"));
	  				beans.setCast(rs.getString("cast"));
	  				beans.setDetail(rs.getString("movie_detail"));
	  				beans.setDirected(rs.getString("directed"));
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
	public List<FeeBeans> getFee(){

		FeeBeans feeBeans;
		List<FeeBeans> feeType = new ArrayList<FeeBeans>();
		PreparedStatement stmt=null;
	  	ResultSet rs=null;
	  	String sql = "SELECT fee_type,fee_base from fee";
	  	try {
	  		stmt = con.prepareStatement(sql);

	  		rs = stmt.executeQuery();

	  		while(rs.next()) {
	  			feeBeans = new FeeBeans();

	  			feeBeans.setFeeType(rs.getString("fee_type"));
	  			feeBeans.setFee(rs.getInt("fee_base"));

	  			feeType.add(feeBeans);
	  		}
	  	}catch(SQLException e) {
	  		e.printStackTrace();
	  	}

	  	finally{
	  		if(con != null) {
	  			try {
	  				con.close();
	  			}catch(SQLException e) {
	  				e.printStackTrace();
	  			}
	  		}
	  	}

	  	return feeType;

	}
    //UPDATE用
    public void update( int movieId,String movieName,String startDate,String finishDate,String cast,String directed,String detail) {
    	if(con == null) {
    		return;
    	}
    	PreparedStatement stmt = null;
    	Date start = Date.valueOf(startDate);
    	Date finish = Date.valueOf(finishDate);
    	try {
    		//映画情報を更新するUpdate文を発行
    		stmt = con.prepareStatement("update movie set movie_name = ?,release_start_date = ?,release_finish_date =?,cast = ?,directed = ? ,movie_detail = ? "+
    									"where movie_id = ?");
    		stmt.setString(1, movieName);
    		stmt.setDate(2, start);
    		stmt.setDate(3,finish);
    		stmt.setString(4,cast);
    		stmt.setString(5,directed);
    		stmt.setString(6,detail);
    		stmt.setInt(7,movieId);
    		stmt.executeUpdate();

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
    }
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

	public void insert(int adminId,String movieName,Date releaseDate,Date finishDate,String directed,String cast,String fee_type,String movieDetail,String thumbnail) {
		if(con==null) {
			return;
		}
		PreparedStatement stmt=null;
		try {
			String sql="insert into movie(administrator_number,movie_name,cast,directed,release_start_date,release_finish_date,movie_detail,thumbnail) values(?,?,?,?,?,?,?,?)";
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, adminId);
			stmt.setString(2, movieName);
			stmt.setString(3, cast);
			stmt.setString(4, directed);
			stmt.setDate(5, releaseDate);
			stmt.setDate(6, finishDate);
			stmt.setString(7, movieDetail);
			stmt.setString(8, thumbnail);
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

	public List<MovieListBeans>Search(String text){
			if( con== null ){
		   //接続していない場合は何もしない
		}

		List<MovieListBeans> list = new ArrayList<MovieListBeans>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		  try{
		        //検索に対するSELECT
		      stmt = con.prepareStatement("SELECT movie.movie_id,movie.movie_name,movie.cast,movie.directed,movie.movie_detail,movie_term.term_type,term.term_start,"
		      		+ "term.term_finish,movie_screen.theater_id,theater.theater_name,movie_screen.screen_number," +
		      		" screen.sheet_total,count(movie_reservation_item.reservation_number) as reserve_sheet" +
		      		" FROM movie LEFT OUTER JOIN movie_term ON movie.movie_id = movie_term.movie_id "+
		      		" LEFT OUTER JOIN term ON movie_term.term_type = term.term_type" +
		      		" LEFT OUTER JOIN movie_screen ON movie.movie_id = movie_screen.movie_id"+
		      		" LEFT OUTER JOIN screen ON movie_screen.screen_number = screen.screen_number AND movie_screen.theater_id = screen.theater_id" +
		      		" LEFT OUTER JOIN theater ON screen.theater_id = theater.theater_id" +
		      		" LEFT OUTER JOIN movie_reservation ON movie_term.movie_term_number = movie_reservation.movie_term_number AND "
		      		+ "movie_screen.theater_id = movie_reservation.theater_id AND movie_screen.screen_number = movie_reservation.screen_number" +
		      		" LEFT OUTER JOIN movie_reservation_item ON movie_reservation.reservation_number = movie_reservation_item.reservation_number"+
		      		" WHERE movie.movie_name LIKE ?" +
		      		" GROUP BY movie.movie_id,movie.movie_name,movie.cast,movie.directed,movie.movie_detail,movie_term.term_type,term.term_start,term.term_finish,movie_screen.theater_id,theater.theater_name,movie_screen.screen_number");

		stmt.setString(1,"%"+text+"%");
		rs = stmt.executeQuery();

		while(rs.next()){
		 //beansのリストに格納する
		MovieListBeans beans = new MovieListBeans();
		beans.setMovieName(rs.getString("movie_name"));
		beans.setTermStart(rs.getTimestamp("term_start"));
		beans.setTermFinish(rs.getTimestamp("term_finish"));
		beans.setSheet(rs.getInt("sheet_total"));
		beans.setCount(rs.getInt("reserve_sheet"));


		list.add(beans);
		}
		}catch(SQLException e){
		    //error発生した場合にコンソールにログを出力する
		    e.printStackTrace();
		}finally{
		     //接続（コネクション）を閉じる
		     if(con!= null){
		          try{
		                 con.close();
		               }catch(SQLException e){
		                  e.printStackTrace();
		               }
		       }
		}
		  return list;
	}

}
