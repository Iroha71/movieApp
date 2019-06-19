package movie.model;

import java.util.Date;

import movie.dao.MovieDao;
import movie.dao.MovieFeeDao;

public class MovieModel {
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

	public void createMovie(int adminId,String movieName,Date releaseDate,Date finishDate,String directed,String cast,String fee_type,String movieDetail) {
		MovieDao movieDao=new MovieDao();
		MovieFeeDao movieFeeDao=new MovieFeeDao();
		try {
			adminId=1;
			movieDao.connect();
			movieDao.insert(adminId, movieName, new java.sql.Date(releaseDate.getTime()), new java.sql.Date(finishDate.getTime()), directed, cast, fee_type,movieDetail);
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
}
