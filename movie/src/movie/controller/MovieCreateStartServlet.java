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

@WebServlet("/movieCreateStart")
public class MovieCreateStartServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		MovieModel movieModel = new MovieModel();
		List<FeeBeans> feeType = new ArrayList<FeeBeans>();
		List<MovieListBeans>screenList = new ArrayList<MovieListBeans>();
		List<MovieListBeans>termList = new ArrayList<MovieListBeans>();

		feeType = movieModel.getFee();
		screenList = movieModel.getScreen();
		termList = movieModel.getTerm();

		req.setAttribute("feeType", feeType);
		req.setAttribute("screenList", screenList);
		req.setAttribute("termList", termList);

		RequestDispatcher dis=req.getRequestDispatcher("WEB-INF/jsp/movieCreate.jsp");
		dis.forward(req, resp);
	}

}
