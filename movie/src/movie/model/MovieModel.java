package movie.model;

import java.sql.Date;

import movie.dao.MovieDao;

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
		try {
			movieDao.connect();
			movieDao.insert(adminId, movieName, releaseDate, finishDate, directed, cast, fee_type, movieDetail);
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
