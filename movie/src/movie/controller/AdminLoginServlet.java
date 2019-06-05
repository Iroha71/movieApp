package movie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.model.UserModel;

@WebServlet("/adminAuth")
public class AdminLoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mail=(String)req.getParameter("mail");
		String password=(String)req.getParameter("password");
		UserModel userModel=new UserModel();
		String loginInfo=userModel.loginAdmin(mail, password);
		if(loginInfo!=null) {
			resp.sendRedirect("top");
		}else {
			resp.sendRedirect("adminLogin?error=mistake");
		}
	}

}
