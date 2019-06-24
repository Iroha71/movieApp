package movie.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	public void createMovie(int adminId,String movieName,Date releaseDate,Date finishDate,String directed,String cast,String fee_type,String movieDetail,String thumbnail) {
		MovieDao movieDao=new MovieDao();
		MovieFeeDao movieFeeDao=new MovieFeeDao();
		try {
			adminId=1;
			movieDao.connect();
			movieDao.insert(adminId, movieName, new java.sql.Date(releaseDate.getTime()), new java.sql.Date(finishDate.getTime()), directed, cast, fee_type,movieDetail,thumbnail);
			movieFeeDao.connect();
			movieFeeDao.insert(adminId, movieName,fee_type);
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
}