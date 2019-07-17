package accounts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import accounts.forms.S0040Form;
import accounts.forms.S0041Form;
import accounts.services.S0041Service;

@WebServlet("/S0041.html")
public class S0041Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		boolean login = false;
		List<String> error = new ArrayList<>();

		if (session.getAttribute("login") != null) {//そもそもsessionが存在してないとエラーになるので
			//loginがtrue(ログイン状態にある)じゃないと入れないように
			login = (boolean) session.getAttribute("login");
		}

		if (login == false) {
			session.setAttribute("error", "ログインしてください。");
			resp.sendRedirect("C0010.html");
		} else {

			S0040Form form = (S0040Form) session.getAttribute("S0040Form");//sessionから検索時に入力したデータを取得

			List<S0041Form> list = new ArrayList<>();
			S0041Service serv = new S0041Service();
			list = serv.service(form);//S0040のサービスにぶち込んで一覧を取得

			if (list.size() == 0) {//検索結果なしの場合
				error.add("検索結果はありません。");
				session.setAttribute("error", error);//sessionにエラーメッセージを格納
				//				session.setAttribute("S0040Form", form);//初期表示用

				resp.sendRedirect("S0040.html");
//				session.removeAttribute("error");//送ったら削除
			} else {

				session.setAttribute("S0041Form", list);//sessionに格納

				getServletContext().getRequestDispatcher("/WEB-INF/S0041.jsp").forward(req, resp);//遷移
				session.removeAttribute("complete");
			}

		}
	}

}
