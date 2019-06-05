package movie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie.model.MovieModel;

@WebServlet("/movieInfomationDelte")
public class MovieInfomationDeleteServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//削除する映画パラメータの取得
		String movieId=req.getParameter("movieId");
		String movieName=req.getParameter("movieName");
		HttpSession session=req.getSession();
		session.setAttribute("movieName", movieName);
		MovieModel movieModel=new MovieModel();
		movieModel.deleteMovie(movieId);
		resp.sendRedirect("movieInfomationDeleteFinish");
	}

}
