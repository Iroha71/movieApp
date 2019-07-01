package movie.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

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

	public String[] property() throws IOException {
		Properties pro = new Properties();
		String pass = "sheet.properties";
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(pass);

		pro.load(is);
		String sheet = pro.getProperty("sheet1");

		String[] sheets = sheet.split(",");

		return sheets;
	}
}
