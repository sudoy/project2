package accounts;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import accounts.forms.S0020Form;
import accounts.services.S0020Service;

@WebServlet("/S0020.html")
public class S0020Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		boolean login = false;

		if (session.getAttribute("login") != null) {//そもそもsessionが存在してないとエラーになるので
			//loginがtrue(ログイン状態にある)じゃないと入れないように
			login = (boolean) session.getAttribute("login");
		}

		if (login == false) {
			session.setAttribute("error", "ログインしてください。");
			resp.sendRedirect("C0010.html");
		} else {//以下ログイン状態にあるときの処理

			//DB接続してすべてのユーザー名を取得する
			S0020Service disp = new S0020Service();
			List<S0020Form> staffList = disp.staff();
			List<String> categoryList = disp.category();

			//sessionに入れる（session名 allName）
			session.setAttribute("allName", staffList);
			session.setAttribute("allCategory", categoryList);

			getServletContext().getRequestDispatcher("/WEB-INF/S0020.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		boolean login = false;

		if (session.getAttribute("login") != null) {//そもそもsessionが存在してないとエラーになるので
			//loginがtrue(ログイン状態にある)じゃないと入れないように
			login = (boolean) session.getAttribute("login");
		}

		if (login == false) {
			session.setAttribute("error", "ログインしてください。");
			resp.sendRedirect("C0010.html");
		} else {//以下ログイン状態にあるときの処理

			String dateBegin = req.getParameter("dateBegin");
			String dateEnd = req.getParameter("dateEnd");
			String name = req.getParameter("name");
			String[] categoryName = req.getParameterValues("categoryName");
			String tradeName = req.getParameter("tradeName");
			String note = req.getParameter("note");

			S0020Form form = new S0020Form(dateBegin, dateEnd, name, categoryName, tradeName, note);

			List<String> error = validate(form);

			if (error.size() == 0) {

				List<S0020Form> list = new ArrayList<>();
				S0020Service serv = new S0020Service();
				list = serv.service(form);//Serviceで取得した一覧
				if (list.size() == 0) {//2つ下のelseとほぼ同じことをしているのでまとめたい
					error.add("検索結果はありません。");
					session.setAttribute("error", error);//sessionにエラーメッセージを格納
					session.setAttribute("S0020Form", form);//初期表示用

					getServletContext().getRequestDispatcher("/WEB-INF/S0020.jsp").forward(req, resp);//検索入力画面を再表示

					session.removeAttribute("error");//送ったら削除
				} else {

					session.setAttribute("S0021Form", list);//sessionに取得した一覧を格納
					session.setAttribute("S0020Form", form);//更新時の再表示用
					getServletContext().getRequestDispatcher("/S0021.html").forward(req, resp);
				}
			} else {
				session.setAttribute("error", error);//sessionにエラーメッセージを格納
				session.setAttribute("S0020Form", form);//初期表示用

				getServletContext().getRequestDispatcher("/WEB-INF/S0020.jsp").forward(req, resp);//検索入力画面を再表示

				session.removeAttribute("error");//送ったら削除
			}

		}
	}

	private List<String> validate(S0020Form form) throws UnsupportedEncodingException {

		List<String> error = new ArrayList<>();
		String dateBegin = form.getDateBegin();
		String dateEnd = form.getDateEnd();

		//販売日の形式チェック
		//開始日のチェック
		if (!(dateBegin.equals(""))) {//開始日が空じゃない時
			try {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
				dtf.format(LocalDate.parse(dateBegin, dtf));//ここで変換できればok
			} catch (DateTimeParseException e) {
				error.add("販売日（検索開始日）を正しく入力して下さい。");
			}
		}
		//終了日のチェック
		if (!(dateEnd.equals(""))) {//終了日が空じゃない時
			try {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
				dtf.format(LocalDate.parse(dateEnd, dtf));//ここで変換できればok
			} catch (DateTimeParseException e) {
				error.add("販売日（検索終了日）を正しく入力して下さい。");
			}
		}
		//開始日 < 終了日になっているかチェック
		if(!(dateBegin.equals("")) && !(dateEnd.equals(""))) {
			int begin = Integer.parseInt(dateBegin.replace("/", ""));
			int end = Integer.parseInt(dateEnd.replace("/", ""));
			if((end - begin) < 0) {

			}
		}



		return error;
	}

}
