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
}
