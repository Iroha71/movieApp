package movie.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/movieFinish")
//Jspの名前を後でリファクタリングすること
public class MovieReservationDeleteFinishController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/movieFinish.jsp");
		dispatcher.forward(request, response);
	}
}
