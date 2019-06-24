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
		public void update( int movieId,String movieName,String startDate,String finishDate,String cast,String directed,String detail) {
			MovieDao moviedao = new MovieDao();
				try {
					moviedao.connect();

					moviedao.update(movieId,movieName, startDate, finishDate, cast,directed, detail);
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					if(moviedao != null) {
						moviedao.close();
					}
				}
		}

}
