package movie.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie.beans.UserInfoBeans;
import movie.model.ReserveModel;

@WebServlet("/MovieReservationStartContorller")
public class MovieReservationStartController extends HttpServlet {
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{

		ReserveModel reserve = new ReserveModel();
		HttpSession session = request.getSession(true);

		UserInfoBeans loginInfo = (UserInfoBeans) session.getAttribute("loginInfo");
		Integer flag = (Integer)session.getAttribute("flag");
		if(loginInfo == null) {

			Integer MovieTermNumber = Integer.parseInt((String)request.getParameter("term"));
			String TheaterId = (String)request.getParameter("theater");
			Integer ScreenNumber = Integer.parseInt((String)request.getParameter("screen"));
			Integer MemberNumber = Integer.parseInt((String)request.getParameter("member"));
			flag = 1;

			session.setAttribute("MovieTermNumber", MovieTermNumber);
			session.setAttribute("TheaterId", TheaterId);
			session.setAttribute("ScreenNumber", ScreenNumber);
			session.setAttribute("MemberNumber", MemberNumber);
			session.setAttribute("flag", flag);

			response.sendRedirect("userLogin");

		}else if(loginInfo != null && flag == null) {
			Integer MovieTermNumber = Integer.parseInt((String)request.getParameter("term"));
			String TheaterId = (String)request.getParameter("theater");
			Integer ScreenNumber = Integer.parseInt((String)request.getParameter("screen"));
			Integer MemberNumber = Integer.parseInt((String)request.getParameter("member"));
			flag = 1;

			session.setAttribute("MovieTermNumber", MovieTermNumber);
			session.setAttribute("TheaterId", TheaterId);
			session.setAttribute("ScreenNumber", ScreenNumber);
			session.setAttribute("MemberNumber", MemberNumber);
			session.setAttribute("flag", flag);

			String[] sheets = reserve.property();
			request.setAttribute("sheets", sheets);
			RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/reservation.jsp");
			dispatcher.forward(request,response);

		}else {
			String[] sheets = reserve.property();
			request.setAttribute("sheets", sheets);
			RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/reservation.jsp");
			dispatcher.forward(request,response);
		}




	}
}
