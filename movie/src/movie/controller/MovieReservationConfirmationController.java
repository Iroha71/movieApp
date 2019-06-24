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

import movie.beans.MovieListBeans;
import movie.model.MovieModel;

@WebServlet("/reservelist")
public class MovieReservationConfirmationController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	//////////////////////////////
	//映画の一覧リストを取得
	List<MovieListBeans> reservelist=new ArrayList<MovieListBeans>();
    MovieModel movieModel=new MovieModel();
    reservelist=movieModel.getReserveList();

	/////////////////////////////
	//映画の一覧をリクエストにセット
	request.setAttribute("list", reservelist);

	RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/reservationConfirm.jsp");
	dispatcher.forward(request, response);


}
}
