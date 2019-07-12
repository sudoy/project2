package sales;

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

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import sales.forms.S0011Form;
import sales.services.S0011Service;

@WebServlet("/S0011.html")
public class S0011Servlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String saledate = req.getParameter("saledate");
		String accountid = req.getParameter("accountid");
		String categoryid = req.getParameter("categoryid");
		String tradename = req.getParameter("tradename");
		String price = req.getParameter("price");
		String salenumber = req.getParameter("salenumber");
		String note = req.getParameter("note");

		S0011Service service = new S0011Service();
		String name = service.select2(accountid);

		System.out.println(name);

		S0011Form form = new S0011Form(saledate, accountid, categoryid, tradename, price, salenumber, note, name);

		HttpSession session = req.getSession();
		session.setAttribute("form", form);

		// バリデーションチェック
		List<String> error = validate(form);

		//エラーがある場合
		if (error.size() != 0) {
			// S0030.htmlを再表示
			session.setAttribute("error", error);
			req.setAttribute("form", form);

			//accountsテーブルからaccount_idを取得
			req.setAttribute("accounts", form);

			getServletContext().getRequestDispatcher("/WEB-INF/S0010.jsp")
					.forward(req, resp);
			session.removeAttribute("error");
		}
		//エラーがない場合

		//小計をだす
		int pricenum = Integer.parseInt(price);
		int salenumbernum = Integer.parseInt(salenumber);
		int total = pricenum * salenumbernum;
		req.setAttribute("total", total);

		//accountsテーブルからaccount_idを取得
		req.setAttribute("accounts", form);

		getServletContext().getRequestDispatcher("/WEB-INF/S0011.jsp").forward(req, resp);
	}

	private List<String> validate(S0011Form form) throws UnsupportedEncodingException {
		List<String> error = new ArrayList<String>(); //list add
		//日付チェック
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		format.setLenient(false);

		String saledate = form.getSaledate();
		String accountid = form.getAccountid();
		String categoryid = form.getCategoryid();
		String tradename = form.getTradename();
		String price = form.getPrice();
		String salenumber = form.getSalenumber();

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
		}

		if (categoryid == null || categoryid.isEmpty()) {
			error.add("商品カテゴリーが未選択です。");
		}

		if (tradename.equals("")) {
			error.add("商品名を入力して下さい。");
		}

		if (price.equals("")) {
			error.add("単価を入力して下さい。");
		} else if (!(form.getPrice().equals("")) &&
				(11 <= form.getPrice().getBytes("UTF-8").length)) {
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
		} else if (!(form.getSalenumber().equals("")) &&
				(11 <= form.getSalenumber().getBytes("UTF-8").length)) {
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
		if (!(form.getNote().equals("")) &&
				(400 <= form.getNote().getBytes("UTF-8").length)) {
			error.add("備考が長すぎます。");
		}

		return error;

	}
}
