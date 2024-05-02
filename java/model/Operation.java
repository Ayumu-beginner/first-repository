package model;

import jakarta.servlet.http.HttpSession;
import model.dao.user;
import model.dao.userDao;

public class Operation {

	private HttpSession session;
	
	public Operation() {
		
	}
	
    public Operation(HttpSession session) {
		
    	this.session = session;
	}
	
	public int ChgPass(int id,String password) throws Exception{
//		ChgPassServletから受け取ったパスワードをハッシュ化してDAOのchgPassに渡す
		userDao userDao = new userDao();
		
		return userDao.chgPass(id,PassHasher.hashPass(password));
	}
	
//	idとパスを受取り、パスをハッシュ化。daoにidを渡しdbからuser(DTO)を取得。user(DTO)からdbPassを取得し、比較。true or falseを返す。
//	trueならsessionにuserを格納しておく
	public boolean loginOp(int id,String password) throws Exception{
		
		String hashedPass = PassHasher.hashPass(password);
		userDao userDao = new userDao();
		String dbPass = userDao.getPass(id);
		
		if (hashedPass.equals(dbPass)) {
			
			user user = userDao.getUser(id);
			session.setAttribute("user",user);
			session.setAttribute("beginsTime",userDao.getBeginsTime(id));
			session.setAttribute("endsTime",userDao.getEndsTime(id));
			return true;
		}else {
			return false;
		}
	}
	
//	userDaoで本日の出勤の有無を確認、なければuserDaoで出勤実行しsessionに格納、trueを返す。あればfalseを返す。
	
	public boolean timeBegins() throws Exception{
		
		user user = (user)session.getAttribute("user");
		int id = user.getUserId();
		userDao userDao = new userDao();
		
		boolean result = userDao.checkBegins(id);
		
		if(result) {
			int cnt = userDao.timeBegins(id);
			if(cnt <= 0) {
				throw new Exception();
			}else {
				session.setAttribute("beginsTime",userDao.getBeginsTime(id));
				return true;
			}
		}else {
			return false;
		}
	}
	
//	出勤済みかつ退勤時間null・・・退勤時間を更新、00:00の型に変換しsessionに格納
	
	public int timeEnds() throws Exception{
		
		user user = (user)session.getAttribute("user");
		int id = user.getUserId();
		userDao userDao = new userDao();
		int cnt = 0;
		
		boolean result = userDao.checkEnds(id);
		
		if(result) {
			cnt = userDao.timeEnds(id);
			session.setAttribute("endsTime",userDao.getEndsTime(id));
		}
		return cnt;
	}
	
}
