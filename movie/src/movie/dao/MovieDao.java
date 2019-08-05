
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
  	  String sql = "SELECT movie.movie_id,movie.movie_name,movie.cast,movie.directed,movie.movie_detail,movie_term.term_type,movie_term.movie_term_number,term.term_start,term.term_finish,movie_screen.theater_id,theater.theater_name,movie_screen.screen_number," +
  	  		"screen.sheet_total,count(movie_reservation_item.reservation_number) as reserve_sheet " +
  	  		"FROM movie LEFT OUTER JOIN movie_term ON movie.movie_id = movie_term.movie_id " +
  	  		"LEFT OUTER JOIN term ON movie_term.term_type = term.term_type " +
  	  		"LEFT OUTER JOIN movie_screen ON movie.movie_id = movie_screen.movie_id " +
  	  		"LEFT OUTER JOIN screen ON movie_screen.screen_number = screen.screen_number AND movie_screen.theater_id = screen.theater_id " +
  	  		"LEFT OUTER JOIN theater ON screen.theater_id = theater.theater_id \r\n" +
  	  		"LEFT OUTER JOIN movie_reservation ON movie_term.movie_term_number = movie_reservation.movie_term_number AND movie_screen.theater_id = movie_reservation.theater_id AND movie_screen.screen_number = movie_reservation.screen_number " +
  	  		"LEFT OUTER JOIN movie_reservation_item ON movie_reservation.reservation_number = movie_reservation_item.reservation_number " +
  	  		"GROUP BY movie.movie_id,movie.movie_name,movie.cast,movie.directed,movie.movie_detail,movie_term.term_type,term.term_start,term.term_finish,movie_screen.theater_id,theater.theater_name,movie_screen.screen_number";

        try{
  			///////////////////////////////////
  			//SELECT文の発行
  			stmt = con.prepareStatement(sql);

  			rs=stmt.executeQuery();

  			while(rs.next()){
  				//////////////////////////////////
  				//ビーンズのリストに格納する
  				MovieListBeans beans = new MovieListBeans();

  				beans.setMovieId(rs.getInt("movie_id"));
  				beans.setMovieName(rs.getString("movie_name"));
  				beans.setCast(rs.getString("cast"));
  				beans.setDirected(rs.getString("directed"));
  				beans.setDetail(rs.getString("movie_detail"));
  				beans.setTermStart(rs.getTimestamp("term_start"));
  				beans.setTermFinish(rs.getTimestamp("term_finish"));
  				beans.setTheaterId(rs.getString("theater_id"));
  				beans.setTheaterName(rs.getString("theater_name"));
  				beans.setScreenNumber(rs.getInt("screen_number"));
  				beans.setSheet(rs.getInt("sheet_total"));
  				beans.setCount(rs.getInt("reserve_sheet"));
  				beans.setTermNumber(rs.getInt("movie_term_number"));

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
    	  String sql = "SELECT movie.*,movie_screen.theater_id,theater.theater_name,movie_screen.screen_number,term.term_type,term.term_start,term.term_finish " +
    	  		"FROM movie INNER JOIN movie_screen ON movie.movie_id = movie_screen.movie_id " +
    	  		"INNER JOIN screen ON movie_screen.screen_number = screen.screen_number AND movie_screen.theater_id = screen.theater_id " +
    	  		"INNER JOIN theater ON screen.theater_id = theater.theater_id "+
    	  		"INNER JOIN movie_term ON movie.movie_id = movie_term.movie_id "+
    	  		"INNER JOIN term ON movie_term.term_type = term.term_type";
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
    				beans.setTheaterId(rs.getString("theater_id"));
    				beans.setTheaterName(rs.getString("theater_name"));
    				beans.setScreenNumber(rs.getInt("screen_number"));
    				beans.setTermType(rs.getString("term_type"));
    				beans.setTermStart(rs.getTimestamp("term_start"));
    				beans.setTermFinish(rs.getTimestamp("term_finish"));
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
	//映画登録と映画更新用のシアターとスクリーン情報を取得
	public List<MovieListBeans> ScreenList(){

		MovieListBeans beans;
		List<MovieListBeans> list = new ArrayList<MovieListBeans>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT theater.theater_id,theater.theater_name,screen.screen_number,screen.sheet_total "+
					 "FROM screen INNER JOIN theater ON screen.theater_id = theater.theater_id";
	  	try {
	  		stmt = con.prepareStatement(sql);

	  		rs = stmt.executeQuery();

	  		while(rs.next()) {
	  			beans = new MovieListBeans();

	  			beans.setTheaterId(rs.getString("theater_id"));
	  			beans.setTheaterName(rs.getString("theater_name"));
	  			beans.setScreenNumber(rs.getInt("screen_number"));
	  			beans.setSheet(rs.getInt("screen_number"));

	  			list.add(beans);

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

		return list;
	}

	public List<MovieListBeans> TermList(){
		MovieListBeans beans;
		List<MovieListBeans> list = new ArrayList<MovieListBeans>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM term";
	  	try {
	  		stmt = con.prepareStatement(sql);

	  		rs = stmt.executeQuery();

	  		while(rs.next()) {
	  			beans = new MovieListBeans();

	  			beans.setTermType(rs.getString("term_type"));
	  			beans.setTermStart(rs.getTimestamp("term_start"));
	  			beans.setTermFinish(rs.getTimestamp("term_finish"));
	  			list.add(beans);

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

		return list;
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
    	}
    }

    public void updateFee(int movieId,String feeType) {
    	if(con == null) {
    		return;
    	}
    	PreparedStatement stmt = null;

    	String sql = "UPDATE movie_fee SET fee_type = ? WHERE movie_id = ?";

    	try {
    		stmt = con.prepareStatement(sql);

    		stmt.setString(1,feeType);
    		stmt.setInt(2, movieId);

    		stmt.executeUpdate();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
    public void updateScreen(int movieId,String theaterId,int screenNumber) {
    	if(con == null) {
    		return;
    	}
    	PreparedStatement stmt = null;

    	String sql = "UPDATE movie_screen SET theater_id = ?,screen_number = ? WHERE movie_id = ?";

    	try {
    		stmt = con.prepareStatement(sql);

    		stmt.setString(1,theaterId);
    		stmt.setInt(2,screenNumber);
    		stmt.setInt(3, movieId);

    		stmt.executeUpdate();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
    public void updateTerm(int movieId,String termType) {
    	if(con == null) {
    		return;
    	}
    	PreparedStatement stmt = null;

    	String sql = "UPDATE movie_term SET term_type = ? WHERE movie_id = ?";

    	try {
    		stmt = con.prepareStatement(sql);

    		stmt.setString(1,termType);
    		stmt.setInt(2, movieId);

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

	public void insertMovie(int adminId,String movieName,Date releaseDate,Date finishDate,String directed,String cast,String movieDetail,String thumbnail) {
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
		}
	}
	public void insertScreen(String movieName,Date releaseDate,Date finishDate,String directed,String cast,String movieDetail,String thumbnail,String theaterId,Integer screenNumber) {
		if(con==null) {
			return;
		}
		PreparedStatement stmt=null;
		ResultSet rs = null;
		int movieId = 0;
		String sql = "SELECT movie_id FROM movie WHEHE movie_name = ? AND release_start_date = ? AND release_finish_date = ? AND cast = ? AND directed = ? AND movie_detail = ?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, movieName);
			stmt.setDate(2,releaseDate);
			stmt.setDate(3, finishDate);
			stmt.setString(4, cast);
			stmt.setString(5, directed);
			stmt.setString(6, movieDetail);
			rs = stmt.executeQuery();

			while(rs.next()) {
				movieId = rs.getInt("movie_id");
			}

			sql = "INSERT INTO movie_screen VALUES(?,?,?)";

			stmt =con.prepareStatement(sql);

			stmt.setInt(1,movieId);
			stmt.setString(2,theaterId);
			stmt.setInt(3, screenNumber);

			stmt.executeUpdate();
		}catch(SQLException e) {

			e.printStackTrace();
		}
	}
	public void insertTerm(String movieName,Date releaseDate,Date finishDate,String directed,String cast,String movieDetail,String thumbnail,String termType) {
		if(con==null) {
			return;
		}
		PreparedStatement stmt=null;
		ResultSet rs = null;
		int movieId = 0;
		String sql = "SELECT movie_id FROM movie WHEHE movie_name = ? AND release_start_date = ? AND release_finish_date = ? AND cast = ? AND directed = ? AND movie_detail = ?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, movieName);
			stmt.setDate(2,releaseDate);
			stmt.setDate(3, finishDate);
			stmt.setString(4, cast);
			stmt.setString(5, directed);
			stmt.setString(6, movieDetail);
			rs = stmt.executeQuery();

			while(rs.next()) {
				movieId = rs.getInt("movie_id");
			}

			sql = "INSERT INTO movie_term(movie_id,movie_term) VALUES(?,?)";

			stmt =con.prepareStatement(sql);

			stmt.setInt(1,movieId);
			stmt.setString(2,termType);

			stmt.executeUpdate();
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
