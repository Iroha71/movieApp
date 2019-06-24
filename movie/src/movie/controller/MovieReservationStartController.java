package movie.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MovieReservationStartContorller")
public class MovieReservationStartController extends HttpServlet {
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
		Properties pro = new Properties();
		String pass = "sheet.properties";
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(pass);

		pro.load(is);
		//screenの値によって座席が異なるため、screenの値と"sheet"にくっつけることによって座席を取得する
		String sheet = pro.getProperty("sheet1");

		String[] sheets = sheet.split(",");

		request.setAttribute("sheets", sheets);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/reservation.jsp");

		dispatcher.forward(request,response);

	}
}
