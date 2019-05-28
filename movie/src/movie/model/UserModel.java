package movie.model;

import movie.dao.MemberDao;

public class UserModel {
	//ビーンズを返す
	public String login(String mail,String password) {
		MemberDao memberDao=new MemberDao();
		//ビーンズに変更
		String loginInfo=null;
		try {
			memberDao.connect();
			loginInfo=memberDao.getBy(mail, password);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(memberDao!=null) {
				memberDao.close();
				memberDao=null;
			}
		}
		return loginInfo;
	}
}
