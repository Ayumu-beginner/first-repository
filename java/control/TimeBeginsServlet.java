package control;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Operation;

public class TimeBeginsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		出勤ボタンが押されたらuser_idと今日の日付の行がなければ出勤処理をし、home.jspに戻す
//		すでに出勤処理済であればerrorMsg格納し、home.jspに戻す。
		
		HttpSession session = request.getSession();
		Operation op = new Operation(session);
	
		try{
			boolean result = op.timeBegins();
			if(!result) {
				request.setAttribute("errorMsg","出勤時間は更新できません");
			}else {
				request.setAttribute("errorMsg","今日も一日頑張りましょう");
			}
		}catch(Exception e) {
			request.setAttribute("errorMsg","出勤処理中にエラーが発生しました");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.forward(request, response);
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
