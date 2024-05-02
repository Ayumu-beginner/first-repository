package model.dao;

public class user {

	private int userId;
	private String userName;
	
	public user(int userId,String userName) {
		
		this.userId = userId;
		this.userName = userName;

	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
