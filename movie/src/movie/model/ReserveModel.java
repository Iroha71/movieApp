package movie.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import movie.beans.ReservationBeans;
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

	public String[] property(Integer ScreenNumber) throws IOException {
		Properties pro = new Properties();
		String pass = "sheet.properties";
		String sheetPass = "sheet"+ScreenNumber;
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(pass);

		pro.load(is);
		String sheet = pro.getProperty(sheetPass);

		String[] sheets = sheet.split(",");

		return sheets;
	}

	public List<Integer> getReserveSheet(int termNum,String theaterId,int screenNum){
		List<Integer> reserveList=new ArrayList<Integer>();
		ReservationDao reservationDao=new ReservationDao();
		try {
			reservationDao.connect();
			reserveList=reservationDao.selectReserveSheet(termNum, theaterId, screenNum);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(reservationDao!=null) {
				reservationDao.close();
			}
		}
		return reserveList;
	}

	public List<ReservationBeans> getReserveList(int memberNumber){
		List<ReservationBeans>list =new ArrayList<ReservationBeans>();
		ReservationDao reservation = new ReservationDao();
		try {
			reservation.connect();
			list=reservation.selectReservation(memberNumber);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(reservation!=null) {
				reservation.close();
				reservation = null;
			}
		}
		return list;
	}
}
