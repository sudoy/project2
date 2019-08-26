package com.abc.asms.sales;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.sales.forms.S0020Form;
import com.abc.asms.sales.forms.S0021Form;
import com.abc.asms.sales.services.S0021Service;

@WebServlet("/S0021.html")
public class S0021Servlet extends HttpServlet {
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

			S0020Form form = (S0020Form) session.getAttribute("S0020Form");//sessionから検索時に入力したデータを取得

			List<S0021Form> list = new ArrayList<>();
			S0021Service serv = new S0021Service();
			list = serv.service(form);//formをS0021のサービスに渡して一覧を取得

			if (list.size() == 0) {//検索結果なしの場合
				error.add("検索結果はありません。");
				session.setAttribute("error", error);//sessionにエラーメッセージを格納

				resp.sendRedirect("S0020.html");

			} else {
				session.setAttribute("S0021Form", list);//sessionに格納

				session.removeAttribute("complete");
				session.removeAttribute("error");

				getServletContext().getRequestDispatcher("/WEB-INF/S0021.jsp").forward(req, resp);//遷移
			}

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

			S0020Form form = (S0020Form) session.getAttribute("S0020Form");//sessionから検索時に入力したデータを取得

			List<S0021Form> list = new ArrayList<>();
			S0021Service serv = new S0021Service();
			list = serv.service(form);//formをS0021のサービスに渡して一覧を取得

			// CSV出力用データ
//			String[][] rows = {
//					{ "0", "ダイナミック", "dynamic@example.com" },
//					{ "1", "ホゲ", "hoge@example.com" },
//					{ "2", "モゲ", "moge@example.com" },
//					{ "3", "マゲ", "mage@example.com" },
//					{ "4", "フゥ", "foo@example.com" },
//					{ "5", "バァ", "bar@example.com" },
//					{ "6", "グゥ", "goo@example.com" }
//			};

			// 文字コード設定
			resp.setContentType("text/html; charset=SJIS");
			// ファイル名設定（ファイル名を設定しないと、htmlとして画面に表示されてしまいます
			resp.setHeader("Content-Disposition", "attachment; filename=\"sale_data.csv\"");

			// CSVデータ作成
			StringBuffer sb = new StringBuffer();
			for (S0021Form f : list) {
//				for (int i = 0; i < 8; i++) {
//					if (i == 0) {
//						sb.append("\"");
//					} else {
//						sb.append("\",\"");
//					}
//					sb.append(row[i]);
//
//					if (i == row.length - 1) {
//						sb.append("\"\n");
//					}
//				}
				sb.append("\"");
				sb.append(f.getSaleId());
				sb.append("\",\"");
				sb.append(f.getSaleDate());
				sb.append("\",\"");
				sb.append(f.getStaff());
				sb.append("\",\"");
				sb.append(f.getCategoryName());
				sb.append("\",\"");
				sb.append(f.getProductName());
				sb.append("\",\"");
				sb.append(f.getUnitPrice());
				sb.append("\",\"");
				sb.append(f.getSaleNumber());
				sb.append("\",\"");
				sb.append(f.getTotal());
				sb.append("\"\n");

			}

			// レスポンスにCSV出力
			PrintWriter w = resp.getWriter();
			w.print(sb.toString());
			w.flush();
			w.close();
		}
	}

}
