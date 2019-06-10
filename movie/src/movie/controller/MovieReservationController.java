package movie.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.model.reserveModel;

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

		String[] sheet = request.getParameterValues("sheet");
		for(String sheets : sheet) {
			System.out.print(sheets);
		}
		//とりあえず、予約を一つだけ登録できるようにする
		//複数同時予約の場合は、splitか何か使ってリストに入れれるようにしたいかも
			SheetNumber.add(Integer.parseInt((String)request.getParameter("sheet")));
			FeeType.add((String)request.getParameter("fee"));

		//映画予約するreserveModelを呼び出す
		reserveModel reserve = new reserveModel();

		reserve.reserveMovie(MovieTermNumber, TheaterId, ScreenNumber, MemberNumber, SheetNumber, FeeType);

		response.sendRedirect("MovieReservationFinishController");
	}
}
