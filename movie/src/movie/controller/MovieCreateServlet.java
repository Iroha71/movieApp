package movie.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie.model.MovieModel;
import movie.util.Sanitize;

@WebServlet("/movieCreate")
public class MovieCreateServlet extends HttpServlet {

	//サニタイジング追加
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		HttpSession session=req.getSession();
		//beans化
		int loginInfo=1;
		String movieName=Sanitize.sanitizing(req.getParameter("movieName"));
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date releaseDate=null,finishDate=null;
		try {
			releaseDate=sdf.parse(Sanitize.sanitizing(req.getParameter("releaseStartDate")));
			if(req.getParameter("releaseFinishDate")!=null) {
				finishDate=sdf.parse(Sanitize.sanitizing(req.getParameter("releaseFinishDate")));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String directed=Sanitize.sanitizing(req.getParameter("directed"));
		String cast=Sanitize.sanitizing(req.getParameter("cast"));
		String detail=Sanitize.sanitizing(req.getParameter("movieDetail"));
		String fee_type=Sanitize.sanitizing(req.getParameter("feeType"));
		MovieModel movieModel=new MovieModel();
		movieModel.createMovie(loginInfo, movieName, releaseDate, finishDate, directed, cast, fee_type, detail);
		resp.sendRedirect("movieCreateFinish");
	}
}
