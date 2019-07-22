package movie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.beans.AdminBeans;
import movie.model.UserModel;

@WebServlet("/adminAuth")
public class AdminLoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mail=(String)req.getParameter("mail");
		String password=(String)req.getParameter("password");
		AdminBeans loginInfo = new AdminBeans();
		UserModel userModel=new UserModel();
		loginInfo=userModel.loginAdmin(mail, password);
		if(loginInfo!=null) {
			resp.sendRedirect("top");
		}else {
			resp.sendRedirect("adminLogin?error=mistake");
		}
	}

}
