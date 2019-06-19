package movie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.model.UserModel;

@WebServlet("/subscribe")
public class SubscribeServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{

			UserModel usermodel = new UserModel();
			String mail = (String)request.getParameter("mail");
			String pass = (String)request.getParameter("pass");
			String name = (String)request.getParameter("name");
			String birth = (String)request.getParameter("birthday");
			String jender = (String)request.getParameter("jender");
			String phone = (String)request.getParameter("phone");

			usermodel.subscribeUser(mail, pass, name, birth, jender, phone);

			response.sendRedirect("subscribefinish");
		}
	}
