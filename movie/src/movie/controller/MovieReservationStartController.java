package movie.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie.beans.FeeBeans;
import movie.beans.UserInfoBeans;
import movie.model.MovieModel;
import movie.model.ReserveModel;

@WebServlet("/MovieReservationStartContorller")
public class MovieReservationStartController extends HttpServlet {
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{

		ReserveModel reserve = new ReserveModel();
		HttpSession session = request.getSession(true);
		List<FeeBeans> feeList=new MovieModel().getFee();
		UserInfoBeans loginInfo = (UserInfoBeans) session.getAttribute("loginInfo");
		Integer flag = (Integer)session.getAttribute("flag");

		if(loginInfo == null) {

			Integer MovieTermNumber = Integer.parseInt((String)request.getParameter("term"));
			String TheaterId = (String)request.getParameter("theater");
			Integer ScreenNumber = Integer.parseInt((String)request.getParameter("screen"));
			flag = 1;

			session.setAttribute("MovieTermNumber", MovieTermNumber);
			session.setAttribute("TheaterId", TheaterId);
			session.setAttribute("ScreenNumber", ScreenNumber);
			session.setAttribute("feeList", feeList);
			session.setAttribute("flag", flag);

			response.sendRedirect("userLogin");

		}else if(loginInfo != null && flag == null) {
			Integer MovieTermNumber = Integer.parseInt((String)request.getParameter("term"));
			String TheaterId = (String)request.getParameter("theater");
			Integer ScreenNumber = Integer.parseInt((String)request.getParameter("screen"));
			Integer MemberNumber = Integer.parseInt((String)request.getParameter("member"));
			flag = 1;
			List<Integer> reserveSheetList=new ReserveModel().getReserveSheet(MovieTermNumber, TheaterId, ScreenNumber);

			session.setAttribute("MovieTermNumber", MovieTermNumber);
			session.setAttribute("TheaterId", TheaterId);
			session.setAttribute("ScreenNumber", ScreenNumber);
			session.setAttribute("MemberNumber", MemberNumber);
			session.setAttribute("feeList", feeList);
			session.setAttribute("reserveSheetList", reserveSheetList);
			session.setAttribute("flag", flag);

			String[] sheets = reserve.property(ScreenNumber);
			request.setAttribute("sheets", sheets);
			RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/reservation.jsp");
			dispatcher.forward(request,response);

		}else {
			Integer MemberNumber = loginInfo.getMemberNumber();
			session.setAttribute("MemberNumber", MemberNumber);
			Integer ScreenNumber = (Integer)session.getAttribute("ScreenNumber");
			String[] sheets = reserve.property(ScreenNumber);
			request.setAttribute("sheets", sheets);
			RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/reservation.jsp");
			dispatcher.forward(request,response);
		}




	}
}
