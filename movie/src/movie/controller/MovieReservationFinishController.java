package movie.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MovieReservationFInishController")
public class MovieReservationFinishController {
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{

		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/reservationfinish.jsp");

		dispatcher.forward(request, response);
	}
}