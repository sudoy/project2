package com.abc.asms.sales;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.others.forms.C0010Form;
import com.abc.asms.sales.forms.S0023Form;
import com.abc.asms.sales.services.S0023Service;

@WebServlet("/S0023.html")
public class S0023Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		//ログインチェック
		HttpSession session = req.getSession();
		boolean login = false;
		List<String> error = new ArrayList<>();

		if (session.getAttribute("login") != null) {
			login = (boolean) session.getAttribute("login");
		}

		if (login == false) {
			error.add("ログインしてください。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0010.html");
		} else {

			//権限チェック(権限が無い場合はダッシュボードへ遷移) 売り上げに未変更
			C0010Form checkauthority = (C0010Form) session.getAttribute("userinfo");

			if (!checkauthority.getAuthority().equals("1") && !checkauthority.getAuthority().equals("11")) {
				error.add("不正なアクセスです。");
				session.setAttribute("error", error);
				resp.sendRedirect("C0020.html");
			} else {

				S0023Service service = new S0023Service();

				if (req.getParameter("id") != null) {
					S0023Form form = service.select(req.getParameter("id"));
					session.setAttribute("S0023Form", form);//前の画面の値を表示させるためのもの(編集前の売上情報)


					//jspで一覧を出すためのもの
					List<S0023Form> accounts = service.accounts();
					session.setAttribute("accountsinfo", accounts);//アカウント情報一覧(idとname)

					List<String> categories = service.categories();
					session.setAttribute("categories", categories);//商品カテゴリー名一覧
				}

				getServletContext().getRequestDispatcher("/WEB-INF/S0023.jsp").forward(req, resp);
			}
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		boolean login = false;
		List<String> error = new ArrayList<>();

		int intprice = 0;
		int intsalenumber = 0;
		int total = 0;

		//ログインチェック
		if (session.getAttribute("login") != null) {
			login = (boolean) session.getAttribute("login");
		}

		if (login == false) {
			error.add("ログインしてください。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0010.html");
		} else {

			//権限チェック(権限が無い場合はダッシュボードへ遷移)
			C0010Form checkauthority = (C0010Form) session.getAttribute("userinfo");

			if (!checkauthority.getAuthority().equals("1") && !checkauthority.getAuthority().equals("11")) {
				error.add("不正なアクセスです。");
				session.setAttribute("error", error);
				resp.sendRedirect("C0020.html");
			} else {

				//入力した値を取得
				String id = req.getParameter("id");//id
				String saledate = req.getParameter("saledate");//販売日
				String name = req.getParameter("name");//担当者名
				String categoryname = req.getParameter("categoryname");//商品カテゴリー名
				String tradename = req.getParameter("tradename");//商品名
				String price = req.getParameter("price");//単価
				String salenumber = req.getParameter("salenumber");//個数
				String note = req.getParameter("note");//備考

				//categoryidの取得
				S0023Service service1 = new S0023Service();
				String categoryid = service1.selectCategoryid(categoryname);

				//NumberFormatException回避 正規表現で半角数字以外をはじく
			      Pattern p = Pattern.compile("^[0-9]*$");
			        Matcher mp = p.matcher(price);
			        Matcher ms = p.matcher(salenumber);
			        boolean bp = mp.matches();
			        boolean bs =  ms.matches();

				if (!price.equals("") && !salenumber.equals("") && bp && bs) {
					intprice = Integer.parseInt(price);
					intsalenumber = Integer.parseInt(salenumber);
					total = intprice * intsalenumber;
				}

				S0023Form form = new S0023Form(id, saledate, name, categoryid, categoryname, tradename, price, salenumber, note,
						total);

				//入力チェック
				error = validate(form);

				//エラー発生時はs0023を再表示
				if (error.size() != 0) {
					session.setAttribute("error", error);
					req.setAttribute("S0023Form", form);//再表示するためのもの(編集前の値)

					getServletContext().getRequestDispatcher("/WEB-INF/S0023.jsp").forward(req, resp);

					session.removeAttribute("error");//エラーメッセージ消去
				} else {

					session.setAttribute("S0023Form", form);//入力した値

					//入力チェッククリア後、S0024に遷移
					resp.sendRedirect("S0024.html");
				}
			}
		}

	}

	private List<String> validate(S0023Form form) {

		List<String> e = new ArrayList<>();

		//日付チェック
		DateFormat format = new SimpleDateFormat("yyyy/M/d");
		format.setLenient(false);

		S0023Service service2 = new S0023Service();

		boolean accountexist = service2.accountexist(form);
		boolean categoryexist = service2.categoryexist(form);

		String saledate = form.getSaledate();
		String name = form.getName();
		String categoryname = form.getCategoryname();
		String tradename = form.getTradename();
		String price = form.getPrice();
		String salenumber = form.getSalenumber();
		String note = form.getNote();

		//販売日必須入力チェック
		if (saledate.equals("")) {
			e.add("販売日を入力してください。");
		}
		//販売日形式チェック
		if (!saledate.equals("")) {
			try {
				format.parse(saledate);
			} catch (ParseException pe) {
				e.add("販売日を正しく入力して下さい。");
			}
		}
		//担当必須入力チェック
		if (name.equals("0")) {
			e.add("担当が未選択です。");
		} else {
			if (accountexist == true) {
				e.add("アカウントテーブルに存在しません。");
			}
		}
		//商品カテゴリー必須入力チェック
		if (categoryname == null || categoryname.equals("")) {
			e.add("商品カテゴリーが未選択です。");
		} else {//商品カテゴリーテーブル存在チェック
			if (categoryexist == true) {
				e.add("商品カテゴリーテーブルに存在しません。");
			}
		}

		//商品名必須入力チェック
		if (tradename.equals("")) {
			e.add("商品名を入力して下さい。");
		} else if (101 <= tradename.length()) {//商品名長さチェック
			e.add("商品名が長すぎます。");
		}

		//単価必須入力チェック
		if (price.equals("")) {
			e.add("単価を入力して下さい。");
		} else if (10 <= price.length()) {
			e.add("単価が長すぎます。");
		} else if (!price.equals("")) {
			//数字かどうか
			try {
				int intPrice = Integer.parseInt(price);
				if (intPrice <= 0) {
					e.add("単価を正しく入力して下さい。");
				}
			} catch (NumberFormatException ne) {
				e.add("単価を正しく入力して下さい。");
			}
		}
		//個数必須入力チェック
		if (salenumber.equals("")) {
			e.add("個数を入力してください。");
		} else if (10 <= salenumber.length()) {
			e.add("個数が長すぎます。");
		} else if (!price.equals("")) {
			try {
				int intSaleNumber = Integer.parseInt(salenumber);
				if (intSaleNumber <= 0) {
					e.add("個数を正しく入力して下さい。");
				}
			} catch (NumberFormatException ne) {
				e.add("個数を正しく入力してください。");
			}
		}
		//備考長さチェック(401文字以上の時エラー)
		if (401 <= note.length()) {
			e.add("備考が長すぎます。");
		}
		return e;
	}

}
