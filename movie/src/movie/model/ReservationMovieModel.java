package movie.model;

import java.util.ArrayList;
import java.util.List;

import movie.beans.MovieReservationBeans;
import movie.dao.ReservationDao;

public class ReservationMovieModel {
	public List<MovieReservationBeans> getList(int memberNuber) {
		List<MovieReservationBeans> list = new ArrayList<MovieReservationBeans>();

		ReservationDao Reservationdao = new ReservationDao();

		try {
			Reservationdao.connect();

			list = Reservationdao.select(memberNuber);

		}catch(Exception e){
			//errorが発生した場合にコンソールにログを出力する
			e.printStackTrace();
		}
		finally {
			//接続（コネクション）を閉じる
				Reservationdao.close();
			}
		return list;
		}


	public void delete(int memberNumber , int sheetNumber ,int reservationNumber) {

		ReservationDao Reservationdao = new ReservationDao();
		try {
			Reservationdao.connect();

		Reservationdao.delete(memberNumber,sheetNumber,reservationNumber);
		}catch(Exception e){
			//errorが発生した場合にコンソールにログを出力する
			e.printStackTrace();
		}
		finally {
			//接続（コネクション）を閉じる
			if(Reservationdao != null) {
				Reservationdao.close();
			}
		}
	}
}
