package com.abc.asms.sales;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.others.forms.C0010Form;
import com.abc.asms.sales.forms.S0010Form;
import com.abc.asms.sales.forms.S0011Form;
import com.abc.asms.sales.services.S0010Service;
import com.abc.asms.sales.services.S0011Service;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

@WebServlet("/S0011.html")
public class S0011Servlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		//ログインチェック
		HttpSession session = req.getSession();
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
		}else {

			//権限チェック(権限が無い場合はダッシュボードへ遷移)
			C0010Form checkauthority1 = (C0010Form) session.getAttribute("userinfo");

			//		System.out.println(checkaccount1.getAuthority());

			if (!checkauthority1.getAuthority().equals("10") && !checkauthority1.getAuthority().equals("11")) {
				session.setAttribute("error", "不正なアクセスです。" );
				resp.sendRedirect("C0020.html");
			}else {
				String saledate = req.getParameter("saledate");
				String accountid = req.getParameter("accountid");
				String categoryname = req.getParameter("categoryname");
				String tradename = req.getParameter("tradename");
				String price = req.getParameter("price");
				String salenumber = req.getParameter("salenumber");
				String note = req.getParameter("note");

				String name = null;

				S0011Service service = new S0011Service();

				//担当選択しているときのみDBからname呼び出し
				if(!accountid.equals("0")) {
					name = service.select2(accountid);
				}

				S0010Service s0010service = new S0010Service();
				String categoryid = s0010service.setCategoryid(categoryname);

				S0011Form s0011form = new S0011Form(saledate, accountid, categoryid, categoryname, tradename, price,
						salenumber, note, name);

				session.setAttribute("form", s0011form);

				// バリデーションチェック
				List<String> error = validate(s0011form);

				//エラーがある場合
				if (error.size() != 0) {
					// S0030.htmlを再表示
					session.setAttribute("error", error);

					//accountsテーブルから情報を取得
					S0010Service serv = new S0010Service();
					List<S0010Form> s0010form = serv.select();
					session.setAttribute("accounts", s0010form);
					req.setAttribute("accounts", s0010form);

					getServletContext().getRequestDispatcher("/WEB-INF/S0010.jsp").forward(req, resp);
					session.removeAttribute("error");
					session.removeAttribute("form");
				}
				//エラーがない場合


				//小計をだす
				int pricenum = Integer.parseInt(price);
				int salenumbernum = Integer.parseInt(salenumber);
				int total = pricenum * salenumbernum;
				req.setAttribute("total", total);



				getServletContext().getRequestDispatcher("/WEB-INF/S0011.jsp").forward(req, resp);


			}
		}
	}

	private List<String> validate(S0011Form form) throws UnsupportedEncodingException, ServletException {
		List<String> error = new ArrayList<String>(); //list add
		//日付チェック
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		format.setLenient(false);


		S0011Service s0011service = new S0011Service();
		boolean exist = s0011service.service1(form);
		boolean categorytable = s0011service.service2(form);



		String saledate = form.getSaledate();
		String accountid = form.getAccountid();
		String categoryname = form.getCategoryname();
		String tradename = form.getTradename();
		String price = form.getPrice();
		String salenumber = form.getSalenumber();
		String note = form.getNote();


		if (saledate.equals("")) {
			error.add("販売日を入力して下さい。");
		} else {
			try {
				format.parse(saledate);
			} catch (ParseException | java.text.ParseException e) {
				error.add("販売日を正しく入力して下さい。");
			}
		}
		if (accountid.equals("0")) {
			error.add("担当が未選択です。");
		}else {
			//account_idがテーブルに存在しない場合
			if(exist == true){
				error.add("アカウントテーブルに存在しません。") ;
			}
		}

		if (categoryname == null || categoryname.isEmpty()) {
			error.add("商品カテゴリーが未選択です。");
		}else {
			//category_idがテーブルに存在しない場合
			if(categorytable == true){
				error.add("商品カテゴリーテーブルに存在しません。") ;
			}
		}

		if (tradename.equals("")) {
			error.add("商品名を入力して下さい。");
		}else if(101 <= tradename.length()) {
			error.add("商品名が長すぎます。");
		}

		if (price.equals("")) {
			error.add("単価を入力して下さい。");
		} else if (10 <= price.length()) {
			error.add("単価が長すぎます。");
		} else if (!price.equals("")) {
			//数字かどうか
			try {
				Integer.parseInt(price);
			} catch (NumberFormatException e) {
				error.add("単価を正しく入力して下さい。");
			}
		} else if (Integer.parseInt(price) <= 0) {
			error.add("単価を正しく入力して下さい。");
		}

		if (salenumber.equals("")) {
			error.add("個数を入力してください。");
		} else if (10 <= salenumber.length()) {
			error.add("個数が長すぎます。");
		} else if (!price.equals("")) {
			try {
				Integer.parseInt(salenumber);
			} catch (NumberFormatException e) {
				error.add("個数を正しく入力してください。");
			}
		} else if (Integer.parseInt(salenumber) <= 0) {
			error.add("個数を正しく入力して下さい。");
		}
		if (400 <= note.length()) {
			error.add("備考が長すぎます。");
		}






		return error;

	}
}
