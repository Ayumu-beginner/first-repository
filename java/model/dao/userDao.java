package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class userDao {

	public userDao(){
		
	}
	
//	パスワード変更
	public int chgPass(int id,String hashedPassword) throws Exception{
		
		String sql = "update m_user set password = ? where user_id = ?";
		int cnt = 0;
		
		try(Connection con = MyConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setString(1,hashedPassword);
			pstmt.setInt(2,id);
			
			cnt = pstmt.executeUpdate();
		}
		return cnt;
	}
	
//	idで検索し、passwordを返す
	public  String getPass(int id) throws Exception{
		
		String sql = "select password from m_user where user_id = ?";
		
		try(Connection con = MyConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setInt(1,id);
			
			try(ResultSet res = pstmt.executeQuery()){
				if(res.next()) {
					
					return res.getString("password");
					
				}else {
					throw new Exception();
				}
			}
		}
	}
//	idで検索し、userを返す
	public user getUser(int id) throws Exception{
		
		String sql = "select user_name from m_user where user_id = ?";
		
		try(Connection con = MyConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setInt(1,id);
			
			try(ResultSet res = pstmt.executeQuery()){
				
				if(res.next()) {
					user user = new user(id,res.getString("user_name"));
					return user;
				}else {
					throw new Exception();
				}	
			}
		}
	}
	
//	出勤確認 出勤していなければtrue、していればfalseを返す
	
	public boolean checkBegins(int id) throws Exception{
		
		String sql = "select * from m_time where user_id = ? and work_date = CURDATE()";
		
		try(Connection con = MyConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setInt(1,id);
			
			try(ResultSet res = pstmt.executeQuery()){
				
				if(res.next()) {
					return false;
				}else {
					return true;
				}
			}
		}
	}
	
//	idで出勤の行を追加し、処理件数を返すメソッド
	
	public int timeBegins(int id) throws Exception{
		
		int cnt = 0;
		String sql = "insert into m_time (user_id,work_date) values(?,CURDATE())";
		
		try(Connection con = MyConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setInt(1,id);
			
			cnt = pstmt.executeUpdate();
		}
		return cnt;
	}
	
//出勤時刻を取得し、00:00の型で返すメソッド
	public String getBeginsTime(int id)throws Exception{
		
		String beginsTime = null;
		String sql = "select begins_time from m_time where user_id = ? and work_date = CURDATE()";
		
		try(Connection con = MyConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setInt(1,id);
			
			try(ResultSet res = pstmt.executeQuery()){
				
				if(res.next()) {
					Timestamp timestamp = res.getTimestamp("begins_time");
					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			        beginsTime = sdf.format(timestamp);
				}
			}
		}
		return beginsTime;
	}
	
//	退勤確認 退勤していなければtrue、していればfalseを返す。そもそも出勤していなければエラーを投げるメソッド
	
	public boolean checkEnds(int id) throws Exception{
		
		String sql = "select ends_time from m_time where user_id = ? and work_date = CURDATE()";
		
		try(Connection con = MyConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setInt(1,id);
			
			try(ResultSet res = pstmt.executeQuery()){
				
				if(res.next()) {
					Timestamp endsTime = res.getTimestamp("ends_time");
					if(endsTime == null) {
						return true;
					}else {
						return false;
					}
				}else {
					throw new Exception();
				}
			}
		}
	}
	
//idを受取り、idと現在の日付で検索し退勤時刻を更新するメソッド
	
	public int timeEnds(int id) throws Exception{
		
		int cnt = 0;
		String sql = "update m_time set ends_time = CURRENT_TIMESTAMP where user_id = ? and work_date = CURDATE()";
		
		try(Connection con = MyConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setInt(1,id);
			
			cnt = pstmt.executeUpdate();
		}
		return cnt;
	}
	
//退勤時刻を取得し、00:00の型で返すメソッド
	
	public String getEndsTime(int id)throws Exception{
		
		String endsTime = null;
		String sql = "select ends_time from m_time where user_id = ? and work_date = CURDATE()";
		
		try(Connection con = MyConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setInt(1,id);
			
			try(ResultSet res = pstmt.executeQuery()){
				
				if(res.next()) {
					Timestamp timestamp = res.getTimestamp("ends_time");
					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			        endsTime = sdf.format(timestamp);
				}
			}
		}
		return endsTime;
	}
}
