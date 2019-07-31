package movie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.beans.MovieListBeans;
import movie.model.MovieModel;
import movie.util.Sanitize;
@WebServlet("/movieUpdate")
public class MovieInfomationUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MovieListBeans update = new MovieListBeans();
		MovieModel movieModel = new MovieModel();
		//Integer movieId= Integer.parseInt((String)request.getParameter(movieId));
		int movieId = 1;

		String movieName = request.getParameter("movieName");
		String startDate = request.getParameter("releaseStartDate");
		String finishDate = request.getParameter("releaseFinishDate");
		String cast = request.getParameter("cast");
		String directed = request.getParameter("directed");
		String detail = request.getParameter("movieDetail");
		String termType = Sanitize.sanitizing(request.getParameter("term"));
		String theaterId = Sanitize.sanitizing(request.getParameter("theater"));
		String screen = Sanitize.sanitizing(request.getParameter("screen"));
		Integer screenNumber = Integer.parseInt(screen);
		String feeType=Sanitize.sanitizing(request.getParameter("fee"));

		 movieModel.update(movieId,movieName,startDate,finishDate,cast,directed,detail,termType,theaterId,screenNumber,feeType);


		response.sendRedirect("movieUpdateFinish");
	}

}