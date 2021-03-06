package movie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.beans.UserInfoBeans;
import movie.model.UserModel;
@WebServlet("/userDelete")
public class UserDeleteServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserInfoBeans member=(UserInfoBeans)req.getSession().getAttribute("loginInfo");
		UserModel userModel=new UserModel();
		userModel.deleteUser(member.getMemberNumber());
		resp.sendRedirect("userDeleteFinish");
	}

}
