package movie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.beans.MovieListBeans;
import movie.model.MovieModel;
@WebServlet("/movieUpdate")
public class MovieInfomationUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MovieListBeans update = new MovieListBeans();
		MovieModel movieModel = new MovieModel();

		//Integer movieId= Integer.parseInt((String)request.getParameter(movieId));
		int movieId = 1;

		String movieName = request.getParameter("movieName");
		String startDate = request.getParameter("startDate");
		String finishDate = request.getParameter("finishDate");
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//Date startDate = null,finishDate =null;
		//try {
			//startDate = sdf.parse(Sanitize.sanitizing(request.getParameter("startDate")));
			//if(request.getParameter("FinishDate")!= null) {
				//finishDate=sdf.parse(Sanitize.sanitizing(request.getParameter("finishDate")));
			 //}
		//	}catch(ParseException e) {
			//	e.printStackTrace();
		//	}
		String cast = request.getParameter("cast");
		String directed = request.getParameter("directed");
		String detail = request.getParameter("detail");

		 movieModel.update(movieId,movieName,startDate,finishDate,cast,directed,detail);


		response.sendRedirect("movieUpdateFinish");
	}

//	public java.sql.Date convertToSqlDate(java.util.Date startDate){
	//	return new java.sql.Date(startDate.getTime());
//	}
	//public java.sql.Date converToSqlDate(java.util.Date finishDate){
		//return new java.sql.Date(finishDate.getTime());
	//}
}