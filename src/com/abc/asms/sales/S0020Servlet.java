package com.abc.asms.sales;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.sales.forms.S0020Form;
import com.abc.asms.sales.services.S0020Service;

@WebServlet("/S0020.html")
public class S0020Servlet extends HttpServlet {
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
			error.add("ログインしてください。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0010.html");
		} else {//以下ログイン状態にあるときの処理

			//DB接続してすべてのユーザー名を取得する
			S0020Service disp = new S0020Service();
			List<S0020Form> staffList = disp.staff();
			List<String> categoryList = disp.category();

			//今日の日付を取得
//			if (session.getAttribute("S0020Form") == null) {
//				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/M/d");
//				String today = dtf.format(LocalDate.now()).toString();
//				S0020Form todayForm = new S0020Form(today, today);
//				req.setAttribute("S0020Form", todayForm);
//			}

			//sessionに入れる（session名 allName）
			session.setAttribute("allName", staffList);
			session.setAttribute("allCategory", categoryList);

			if (req.getParameter("clear") != null) {//クリアボタンが押された場合
				session.removeAttribute("S0020Form");
				session.removeAttribute("error");

			}

			getServletContext().getRequestDispatcher("/WEB-INF/S0020.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
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
			error.add("ログインしてください。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0010.html");
		} else {//以下ログイン状態にあるときの処理

			String dateBegin = req.getParameter("dateBegin");
			String dateEnd = req.getParameter("dateEnd");
			String name = req.getParameter("name");
			String[] categoryName = req.getParameterValues("categoryName");
			String tradeName = req.getParameter("tradeName");
			String note = req.getParameter("note");

			S0020Form form = new S0020Form(dateBegin, dateEnd, name, categoryName, tradeName, note);

			error = validate(form);

			if (error.size() == 0) {

				session.setAttribute("S0020Form", form);//更新時の再表示用
				resp.sendRedirect("S0021.html");//検索結果一覧画面へ

				session.removeAttribute("error");//「検索結果はありません」を消す用

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

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/M/d");
		LocalDate begin = null;
		LocalDate end = null;

		//販売日の形式チェック
		//開始日のチェック
		if (!(dateBegin.equals(""))) {//開始日が空じゃない時
			try {
				begin = LocalDate.parse(dateBegin, dtf);//ここで変換できればok
			} catch (Exception e) {
				error.add("販売日（検索開始日）を正しく入力して下さい。");
			}
		}
		//終了日のチェック
		if (!(dateEnd.equals(""))) {//終了日が空じゃない時
			try {
				end = LocalDate.parse(dateEnd, dtf);//ここで変換できればok
			} catch (Exception e) {
				error.add("販売日（検索終了日）を正しく入力して下さい。");
			}
		}
		//開始日が終了日より前の日付になっているかチェック
		if (!(dateBegin.equals("")) && !(dateEnd.equals("")) && (begin != null) && (end != null)) {
			int judgement = begin.compareTo(end);
			if (judgement > 0) {
				error.add("販売日（検索開始日）が販売日（検索終了日）より後の日付となっています。");
			}
		}

		return error;
	}

}
