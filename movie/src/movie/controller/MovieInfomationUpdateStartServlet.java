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

import movie.beans.FeeBeans;
import movie.beans.MovieListBeans;
import movie.model.MovieModel;
@WebServlet("/movieUpdateStart")
public class MovieInfomationUpdateStartServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			List<MovieListBeans> list = new ArrayList<MovieListBeans>();
			MovieModel model = new MovieModel();
			List<FeeBeans>feeType = new ArrayList<FeeBeans>();
			List<MovieListBeans>screenList = new ArrayList<MovieListBeans>();
			List<MovieListBeans>termList = new ArrayList<MovieListBeans>();

			//詳細で選択された映画を特定した状態。movieID
			//MovieListBeans movieInfo = (MovieListBeans)request.getSession().getAttribute("movieID");

			feeType = model.getFee();
			screenList = model.getScreen();
			termList = model.getTerm();
			list = model.getMovie();

			request.setAttribute("list", list);
			request.setAttribute("feeType",feeType);
			request.setAttribute("screenList", screenList);
			request.setAttribute("termList", termList);


		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/updateStart.jsp");
		dispatcher.forward(request, response);
	}
}
