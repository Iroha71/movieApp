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

import movie.beans.MovieReservationBeans;
import movie.model.ReservationMovieModel;
@WebServlet("/show")
public class MovieReservationDeleteStartController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<MovieReservationBeans>list = new ArrayList<MovieReservationBeans>();
		ReservationMovieModel reservationmovieModel = new ReservationMovieModel();

		//UserInfoBeans userInfo = (UserInfoBeans)request.getSession().getAttribute("userInfo");
		//int MemberNumber = (int)userInfo.getMemberNumber();
		//list = reservationmovieModel.getList(MemberNumber);

		int MemberNumber = 1;
		list = reservationmovieModel.getList(MemberNumber);
		request.setAttribute("list",list);


		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/movieDelete.jsp");
		dispatcher.forward(request, response);

	}
}