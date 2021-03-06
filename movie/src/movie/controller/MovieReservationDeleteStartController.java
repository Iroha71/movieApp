package movie.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.beans.ReservationBeans;
import movie.beans.UserInfoBeans;
import movie.model.ReserveModel;


@WebServlet("/show")
public class MovieReservationDeleteStartController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<ReservationBeans>list = new ArrayList<ReservationBeans>();
		//ReservationMovieModel  reservationmovieModel = new ReservationMovieModel();
		ReserveModel reservationModel=new ReserveModel();

		UserInfoBeans userInfo = (UserInfoBeans)request.getSession().getAttribute("loginInfo");
		int MemberNumber = (int)userInfo.getMemberNumber();
		list = reservationModel.getReserveList(MemberNumber);

		//int MemberNumber = 1;
		//list = reservationmovieModel.getList(MemberNumber);
		for(ReservationBeans r:list) {
			System.out.println(r.getReservationNumber());
		}
		System.out.print(list);

		request.setAttribute("list",list);


		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/movieDelete.jsp");
		dispatcher.forward(request, response);

	}
}