package control;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Operation;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String sUserId = request.getParameter("id");
		int userId = 0;
		try{
			userId = Integer.parseInt(sUserId);
		}catch(Exception e) {
			request.setAttribute("errorMsg","社員IDは数字で入力してください");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
	        dispatcher.forward(request, response);
		}
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		Operation op = new Operation(session);
		
		String url ="home.jsp";
		
//		idとパスをopに渡し、booleanを返してもらう.trueならhome.jspに転送、falseならreqにerrorMsg格納しlogin.jspに戻す
		
		try{
			boolean result = op.loginOp(userId,password);
			if(!result) {
				request.setAttribute("errorMsg","社員IDまたはパスワードに誤りがあります");
				url = "login.jsp";
			}
		}catch(Exception e) {
			request.setAttribute("errorMsg","ログイン処理時にエラーが発生しました");
			url = "login.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
