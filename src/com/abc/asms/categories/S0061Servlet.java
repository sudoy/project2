package com.abc.asms.categories;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.categories.forms.S0061Form;
import com.abc.asms.categories.services.S0061Service;
import com.abc.asms.others.forms.C0010Form;
@WebServlet("/S0061.html")
public class S0061Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		req.setCharacterEncoding("UTF-8");
		//ログインチェック

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
		} else {
			//権限チェック(権限が無い場合はダッシュボードへ遷移)
			C0010Form checkauthority1 = (C0010Form) session.getAttribute("accounts");

			if (!checkauthority1.getAuthority().equals("1") && !checkauthority1.getAuthority().equals("11")) {
				error.add("不正なアクセスです。");
				session.setAttribute("error", error);
				resp.sendRedirect("C0020.html");
			} else {
				String name = req.getParameter("name");
				String flg = req.getParameter("flg");
				String id = req.getParameter("id");

				session.setAttribute("id",id);

				//s0062jspからキャンセルしたとき
				if(flg.equals("1")) {
					flg = flg.replace("1", "可");
				}else if(flg.equals("0")) {
					flg = flg.replace("0", "不可");
				}

				S0061Form s0061form = new S0061Form(name, flg,id);
				req.setAttribute("s0061form", s0061form);


				getServletContext().getRequestDispatcher("/WEB-INF/S0061.jsp").forward(req, resp);
			}

		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		req.setCharacterEncoding("UTF-8");
		//ログインチェック

		boolean login = false;
		List<String> loginerror = new ArrayList<>();

		if (session.getAttribute("login") != null) {//そもそもsessionが存在してないとエラーになるので
			//loginがtrue(ログイン状態にある)じゃないと入れないように
			login = (boolean) session.getAttribute("login");
		}

		if (login == false) {
			loginerror.add("ログインしてください。");
			session.setAttribute("error", loginerror);
			resp.sendRedirect("C0010.html");
		} else {
			//権限チェック(権限が無い場合はダッシュボードへ遷移)
			C0010Form checkauthority1 = (C0010Form) session.getAttribute("accounts");

			if (!checkauthority1.getAuthority().equals("1") && !checkauthority1.getAuthority().equals("11")) {
				loginerror.add("不正なアクセスです。");
				session.setAttribute("error", loginerror);
				resp.sendRedirect("C0020.html");
			} else {

				String name = req.getParameter("name");
				String flg = req.getParameter("flg");
				String id = req.getParameter("id");



				S0061Form s0061form = new S0061Form(name, flg, id);
				req.setAttribute("s0061form", s0061form);

				// バリデーションチェック
				List<String> error = validate(s0061form);

				//エラーがある場合
				if (error.size() != 0) {

					if(flg.equals("1")) {
						flg = flg.replace("1", "可");
					}else if(flg.equals("0")) {
						flg = flg.replace("0", "不可");
					}
					s0061form = new S0061Form(name, flg);
					req.setAttribute("s0061form", s0061form);


					session.setAttribute("error", error);
					getServletContext().getRequestDispatcher("/WEB-INF/S0061.jsp").forward(req, resp);
					session.removeAttribute("error");

				} else {
					getServletContext().getRequestDispatcher("/WEB-INF/S0062.jsp").forward(req, resp);
				}
			}
		}
	}
	private List<String> validate(S0061Form s0061form) {

		String name = s0061form.getName();
		String flg = s0061form.getFlg();
		List<String> error = new ArrayList<String>();

		S0061Service service = new S0061Service();
		boolean exist = service.exist(s0061form);//カテゴリー名が登録済みのものと被らないかチェック

		if (name.equals("")) {
			error.add("カテゴリー名を入力して下さい。");
		} else if (51 <= name.length()) {
			error.add("カテゴリー名が長すぎます。");
		} else if (exist == false) {
			error.add("カテゴリー名が既に登録されています。");
		}

		if (flg == null) {
			error.add("使用可/不可を入力して下さい。");
		}else if(! flg.equals("0") && !flg.equals("1")){
			error.add("使用可/不可に正しい値を入力して下さい。");
		}


		return error;
	}
}
