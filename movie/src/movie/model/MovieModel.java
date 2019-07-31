package movie.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import movie.beans.FeeBeans;
import movie.beans.MovieListBeans;
import movie.dao.MovieDao;
import movie.dao.MovieFeeDao;

public class MovieModel {
	public List<MovieListBeans> getList(){
		List<MovieListBeans> list = new ArrayList<MovieListBeans>();

		MovieDao dao=new MovieDao();

		try{
			///////////////////////////////////
			//DBの接続
			dao.connect();
			///////////////////////////////////
			//一覧の取得
			list=dao.getList();

		}catch(Exception e) {
		//エラー発生した場合にコンソールにログを出力する
			e.printStackTrace();
		}
		finally {
			//接続（コネクション）を閉じる
			dao.close();

		}

		return list;
	}
	public List<MovieListBeans> getMovie(){
		List<MovieListBeans> list = new ArrayList<MovieListBeans>();

		MovieDao dao=new MovieDao();

		try{
			///////////////////////////////////
			//DBの接続
			dao.connect();
			///////////////////////////////////
			//一覧の取得
			list=dao.getMovie();

		}catch(Exception e) {
		//エラー発生した場合にコンソールにログを出力する
			e.printStackTrace();
		}
		finally {
			//接続（コネクション）を閉じる
			dao.close();

		}

		return list;
	}
	public void update( int movieId,String movieName,String startDate,String finishDate,String cast,String directed,String detail,String termType,String theaterId,Integer screenNumber,String feeType) {
		MovieDao moviedao = new MovieDao();
			try {
				moviedao.connect();

				moviedao.update(movieId,movieName, startDate, finishDate, cast,directed, detail);

				moviedao.updateFee(movieId,feeType);

				moviedao.updateScreen(movieId, theaterId,screenNumber);

				moviedao.updateTerm(movieId,termType);
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(moviedao != null) {
					moviedao.close();
				}
			}
	}

	public void deleteMovie(String movieId) {
		MovieDao movieDao=new MovieDao();
		try {
			movieDao.connect();
			movieDao.delete(movieId);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(movieDao!=null) {
				movieDao.close();
				movieDao=null;
			}
		}
	}

	public void createMovie(int adminId,String movieName,Date releaseDate,Date finishDate,String directed,String cast,String fee_type,String movieDetail,String thumbnail,String termType,String theaterId,Integer screenNumber) {
		MovieDao movieDao=new MovieDao();
		MovieFeeDao movieFeeDao=new MovieFeeDao();
		try {
			adminId=1;
			movieDao.connect();
			movieDao.insertMovie(adminId, movieName, new java.sql.Date(releaseDate.getTime()), new java.sql.Date(finishDate.getTime()), directed, cast,movieDetail,thumbnail);
			movieFeeDao.connect();
			movieFeeDao.insert(adminId, movieName,fee_type);
			movieDao.insertScreen(movieName, new java.sql.Date(releaseDate.getTime()), new java.sql.Date(finishDate.getTime()), directed, cast,movieDetail,thumbnail,theaterId,screenNumber);
			movieDao.insertTerm(movieName, new java.sql.Date(releaseDate.getTime()), new java.sql.Date(finishDate.getTime()), directed, cast,movieDetail,thumbnail,termType);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(movieDao!=null) {
				movieDao.close();
				movieDao=null;
			}
		}
	}

	public List<MovieListBeans> getReserveList(){
		List<MovieListBeans> reservelist = new ArrayList<MovieListBeans>();

		MovieDao dao=new MovieDao();

		try{
			///////////////////////////////////
			//DBの接続
			dao.connect();
			///////////////////////////////////
			//一覧の取得
			reservelist=dao.getReserveList();

		}catch(Exception e) {
		//エラー発生した場合にコンソールにログを出力する
			e.printStackTrace();
		}
		finally {
			//接続（コネクション）を閉じる
			dao.close();

		}

		return reservelist;
	}

	public List<FeeBeans>getFee(){

		List<FeeBeans>feeType = new ArrayList<FeeBeans>();

		MovieDao dao = new MovieDao();

		try {
			dao.connect();

			feeType = dao.getFee();
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			dao.close();
		}

		return feeType;
	}


	public List<MovieListBeans>getScreen(){
		List<MovieListBeans> list = new ArrayList<MovieListBeans>();

		MovieDao dao = new MovieDao();

		try {
			dao.connect();

			list = dao.ScreenList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			dao.close();
		}

		return list;
	}
	public List<MovieListBeans>getTerm(){
		List<MovieListBeans> list = new ArrayList<MovieListBeans>();

		MovieDao dao = new MovieDao();

		try {
			dao.connect();

			list = dao.TermList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			dao.close();
		}

		return list;
	}
  
  public List<MovieListBeans> Search(String text){
		 List<MovieListBeans> list = new ArrayList<MovieListBeans>();
		MovieDao dao = new MovieDao();

		try{
		  //DBの接続
		dao.connect();
		//一覧の取得
		   list=dao.Search(text);
		}catch(Exception e){
		 //error発生した場合にコンソールログを出力する
		   e.printStackTrace();
		}finally{
		    dao.close();
		} return list;
		}
}
