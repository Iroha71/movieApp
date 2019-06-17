package movie.model;

import java.util.List;

import movie.dao.ReservationDao;

public class ReserveModel {
	public void reserveMovie(Integer MovieTermNumber,String TheaterId,Integer ScreenNumber,
			Integer MemberNumber,List SheetNumber,List FeeType) {

		ReservationDao reservation = new ReservationDao();

		try {

			reservation.connect();

			reservation.insertReservation(MovieTermNumber,TheaterId,ScreenNumber,MemberNumber);

			reservation.insertReservationItem(MovieTermNumber,TheaterId,ScreenNumber,MemberNumber,SheetNumber,FeeType);

		}catch(Exception e) {

			e.printStackTrace();

		}finally {
			reservation.close();
		}
	}
}
