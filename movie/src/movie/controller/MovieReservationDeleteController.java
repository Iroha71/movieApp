package movie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie.beans.UserInfoBeans;
import movie.model.ReservationMovieModel;
@WebServlet("/delete")
public class MovieReservationDeleteController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =request.getSession();
		String[] reservation = request.getParameterValues("reservation");

		UserInfoBeans loginInfo=(UserInfoBeans)session.getAttribute("loginInfo");
		Integer memberNumber = loginInfo.getMemberNumber();
		//Integer sheetNumber = Integer.parseInt((String)request.getParameter("sheetnumber"));
		Integer reservationNumber = Integer.parseInt((String)request.getParameter("reservation"));

		ReservationMovieModel Cancelreservation = new ReservationMovieModel();
		Cancelreservation.delete(memberNumber,reservationNumber);


			response.sendRedirect("movieFinish");

	}

}
