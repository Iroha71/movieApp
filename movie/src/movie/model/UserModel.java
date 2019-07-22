package movie.model;

import movie.beans.AdminBeans;
import movie.beans.UserInfoBeans;
import movie.dao.UserDao;

public class UserModel {
	//ビーンズを返す
	public UserInfoBeans login(String mail,String password) {
		UserDao userDao=new UserDao();

		UserInfoBeans loginInfo = new UserInfoBeans();
		try {
			userDao.connect();
			loginInfo=userDao.getBy(mail, password);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(userDao!=null) {
				userDao.close();
				userDao=null;
			}
		}
		return loginInfo;
	}

	public AdminBeans loginAdmin(String mail,String password) {
		UserDao userDao=new UserDao();
		AdminBeans loginInfo=new AdminBeans();
		try {
			userDao.connect();
			loginInfo=userDao.getAdmin(mail, password);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(userDao!=null) {
				userDao.close();
				userDao=null;
			}
		}
		return loginInfo;
	}

	public void subscribeUser(String mail,String pass,String name,String birth,String jender,String phone) {

		UserDao userDao = new UserDao();

		try {

			userDao.connect();
			userDao.subscribeInsert(mail,pass,name,birth,jender,phone);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(userDao != null) {
				userDao.close();
				userDao=null;
			}
		}
	}

	/* 退会処理 */
	public void deleteUser(int memberNumber) {
		UserDao user=new UserDao();
		try {
			user.connect();
			user.delete(memberNumber);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(user!=null) {
				user.close();
				user=null;
			}
		}
	}
}
