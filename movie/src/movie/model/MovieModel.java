package movie.model;

import java.util.ArrayList;
import java.util.List;

import movie.beans.MovieListBeans;
import movie.dao.MovieDao;

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
