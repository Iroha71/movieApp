package movie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.model.ReservationMovieModel;
@WebServlet("/delete")
public class MovieReservationDeleteController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String[] reservation = request.getParameterValues("reservation");

		Integer memberNumber = Integer.parseInt((String)request.getParameter("membernumber"));
		Integer sheetNumber = Integer.parseInt((String)request.getParameter("sheetnumber"));
		Integer reservationNumber = Integer.parseInt((String)request.getParameter("reservationnumber"));

		ReservationMovieModel Cancelreservation = new ReservationMovieModel();
		Cancelreservation.delete(memberNumber,sheetNumber,reservationNumber);


			response.sendRedirect("movieFinish");

	}

}
