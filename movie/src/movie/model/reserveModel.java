package movie.model;

import java.util.List;

import movie.dao.ReservationDao;

public class reserveModel {
	public void reserveMovie(int MovieTermNumber,String TheaterId,int ScreenNumber,
			int MemberNumber,List SheetNumber,List FeeType) {

		ReservationDao reservation = new ReservationDao();

		try {

			reservation.connect();

			reservation.insert(MovieTermNumber,TheaterId,ScreenNumber,
					MemberNumber,SheetNumber,FeeType);
		}catch(Exception e) {

			e.printStackTrace();

		}finally {
			reservation.close();
		}
	}
}
