package control;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Operation;

public class ChgPassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String sUserId = request.getParameter("id");
		String password = request.getParameter("password");
		int userId = Integer.parseInt(sUserId);
		
		Operation op = new Operation();
		int cnt = 0;
		try {
			cnt = op.ChgPass(userId,password);
			if(cnt <= 0) {
				request.setAttribute("errorMsg", "パスワードを変更できませんでした");
			}else {
				request.setAttribute("errorMsg", "パスワードを変更しました");
			}
		}catch(Exception e) {
			request.setAttribute("errorMsg", "パスワード変更時にエラーが発生しました");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("management.jsp");
        dispatcher.forward(request, response);
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
