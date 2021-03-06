package movie.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.model.ReserveModel;

@WebServlet("/MovieReservationController")
public class MovieReservationController extends HttpServlet {
	protected void doGet(HttpServletRequest request , HttpServletResponse response)
			throws ServletException , IOException{

		//予約を複数同時に行えるように、座席番号と料金種別を保持するリストを作成する
		List SheetNumber = new ArrayList<Integer>();
		List FeeType = new ArrayList<String>();


		Integer MovieTermNumber = Integer.parseInt((String)request.getParameter("term"));
		String TheaterId = (String)request.getParameter("theater");
		Integer ScreenNumber = Integer.parseInt((String)request.getParameter("screen"));
		Integer MemberNumber = Integer.parseInt((String)request.getParameter("member"));

		String[] sheets = request.getParameterValues("sheet");
		String[] fees = request.getParameterValues("fee");

		//
		for(String sheet : sheets) {
			SheetNumber.add(Integer.parseInt(sheet));
		}
		for(String fee : fees) {
			FeeType.add(fee);
		}
		//映画予約するreserveModelを呼び出す
		ReserveModel reserve = new ReserveModel();

		reserve.reserveMovie(MovieTermNumber, TheaterId, ScreenNumber, MemberNumber, SheetNumber, FeeType);

		response.sendRedirect("MovieReservationFinishController");
	}
}
