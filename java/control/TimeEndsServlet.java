package control;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Operation;

public class TimeEndsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		更新件数1・・・退勤しました。おつかれさまでした。
//		更新件数0・・・退勤時間は更新できません。
//		error・・・出勤時刻が登録されていません。
		
//		opに全て任せ、更新件数を受取り、メッセージをrequestに格納、home.jspに戻す
		HttpSession session = request.getSession();
		Operation op = new Operation(session);
		
		int cnt = 0;
		try {
			cnt = op.timeEnds();
			if(cnt <= 0) {
				request.setAttribute("errorMsg","退勤時間は更新できません");
			}else {
				request.setAttribute("errorMsg","退勤しました。おつかれさまでした。");
			}
		}catch(Exception e) {
			request.setAttribute("errorMsg","出勤時刻が登録されていません");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
