package movie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie.beans.UserInfoBeans;
import movie.model.UserModel;
import movie.util.Sanitize;

@WebServlet("/auth")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		String mail=Sanitize.sanitizing(req.getParameter("mail"));
		String password=Sanitize.sanitizing(req.getParameter("password"));

		UserInfoBeans loginInfo = new UserInfoBeans();

		//ビーンズを使う
		UserModel userModel=new UserModel();
		loginInfo=userModel.login(mail, password);
		if(loginInfo!=null) {
			if(!loginInfo.getIsDeleted()) {
				session.setAttribute("loginInfo", loginInfo);
				resp.sendRedirect("top");
			}else {
				resp.sendRedirect("userLogin?error=noUser");
			}
		}else {
			resp.sendRedirect("userLogin?error=mistake");
		}
	}

}
