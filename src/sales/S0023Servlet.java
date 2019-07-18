package sales;

import java.io.IOException;
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

import sales.forms.S0023Form;
import sales.services.S0023Service;

@WebServlet("/S0023.html")
public class S0023Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();

		//ログインチェック
		//権限チェック

		S0023Service service = new S0023Service();

		S0023Form form = service.select(req.getParameter("id"));
		session.setAttribute("S0023Form", form);//前の画面の値を取得するためのもの

		List<S0023Form> accounts = service.accounts();
		session.setAttribute("accounts", accounts);//アカウント情報一覧(idとname)

		List<String> categories = service.categories();
		session.setAttribute("categories", categories);//商品カテゴリー名一覧

		getServletContext().getRequestDispatcher("/WEB-INF/S0023.jsp").forward(req, resp);



	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();

		//入力した値を取得
		String saledate = req.getParameter("saledate");//販売日
		System.out.println(req.getParameter("saledate"));
		String name = req.getParameter("name");//担当者名
		System.out.println(req.getParameter("name"));
		String categoryname = req.getParameter("categoryname");//商品カテゴリー名
		String tradename = req.getParameter("tradename");//商品名
		String price = req.getParameter("price");//単価
		String salenumber = req.getParameter("salenumber");//個数
		String note = req.getParameter("note");//備考

		S0023Form form = new S0023Form(saledate, name, categoryname, tradename, price, salenumber, note);

		session.setAttribute("S0023Form", form);//入力した値

		//入力チェック
		List<String> error = validate(form);

		//エラー発生時はs0023を再表示
		if(error.size() != 0) {
			session.setAttribute("error",error);
			session.setAttribute("S0023Form", form);//再表示するために値を保持

			getServletContext().getRequestDispatcher("/WEB-INF/S0023.jsp").forward(req, resp);

			session.removeAttribute("error");//エラーメッセージ消去
		}else {

			session.setAttribute("S0023Form", form);

			//入力チェッククリア後、S0024に遷移
			resp.sendRedirect("/WEB-INF/S0024.jsp");

		}

	}
	private List<String> validate(S0023Form form) {

		List<String> e = new ArrayList<>();

		//日付チェック
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		format.setLenient(false);

		String saledate = form.getSaledate();
		String name = form.getName();
		String categoryname = form.getCategoryname();
		String tradename = form.getCategoryname();
		String price = form.getPrice();
		String salenumber = form.getSalenumber();
		String note = form.getNote();

		//販売日必須入力チェック
		if(saledate.equals("")) {
			e.add("販売日を入力してください。");
		}
		//販売日形式チェック
		if(!saledate.equals("")) {
			try {
				format.parse(saledate);
			} catch (ParseException | java.text.ParseException pe) {
				e.add("販売日を正しく入力して下さい。");
			}
		}
		//担当必須入力チェック
		if(name.equals("0")) {
			e.add("担当が未選択です。");
		}
		//商品カテゴリー必須入力チェック
		if(categoryname.equals("")) {
			e.add("商品カテゴリーが未選択です。");
		}
		//商品名必須入力チェック
		if(categoryname.equals("")) {
			e.add("商品名を入力して下さい。");
		}
		//商品名長さチェック(101文字以上の時エラー)
		if(101 <= tradename.length()) {
			e.add("商品名が長すぎます。");
		}
		//単価必須入力チェック
		if (price.equals("")) {
			e.add("単価を入力して下さい。");
		}
		//単価長さチェック
		if (10 <= price.length()) {
			e.add("単価が長すぎます。");
		//単価型式チェック
		if (!price.equals("")) {
			try {
				Integer.parseInt(price);
			} catch (NumberFormatException ne) {
				e.add("単価を正しく入力して下さい。");
			}
		}
		if (Integer.parseInt(price) <= 0) {
			e.add("単価を正しく入力して下さい。");
		}
		//個数必須入力チェック
		if(salenumber.equals("")) {
			e.add("個数を入力して下さい。");
		}
		//個数形式チェック
		if(!salenumber.equals("")) {
			try {
				Integer.parseInt(salenumber);
			} catch (NumberFormatException ne) {
				e.add("個数を正しく入力して下さい。");
			}
		}
			e.add("個数を正しく入力して下さい。");
		}
		//個数長さチェック(10文字以上の時エラー)
		if(10 <= salenumber.length()) {

		}
		//備考長さチェック(401文字以上の時エラー)
		if(401<= note.length()) {
			e.add("備考が長すぎます。");
		}
		//商品カテゴリー存在チェック

		return e;
	}

}
