package movie.model;

import movie.dao.UserDao;

public class UserModel {
	//ビーンズを返す
	public String login(String mail,String password) {
		UserDao userDao=new UserDao();
		//ビーンズに変更
		String loginInfo=null;
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

	public String loginAdmin(String mail,String password) {
		UserDao userDao=new UserDao();
		String loginInfo=null;
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
}
