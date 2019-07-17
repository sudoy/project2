package sales;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

		session.setAttribute("S0023Form", form);

		getServletContext().getRequestDispatcher("/WEB-INF/S0023.jsp").forward(req, resp);



	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();

		String saledate = req.getParameter("saledate");//販売日
		String name = req.getParameter("name");//担当者名
		String categoryname = req.getParameter("categoryname");//商品カテゴリー名
		String tradename = req.getParameter("tradename");//商品名
		String price = req.getParameter("price");//単価
		String salenumber = req.getParameter("salenumber");//個数
		String note = req.getParameter("note");//備考

		S0023Form form = new S0023Form(saledate, name, categoryname, tradename, price, salenumber, note);

		session.setAttribute("S0023Form", form);

		List<String> error = validate(form);

		getServletContext().getRequestDispatcher("/WEB-INF/S0023.jsp").forward(req, resp);


	}
	private List<String> validate(S0023Form form) {

		List<String> e = new ArrayList<>();

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
//		if() {
			e.add("販売日を正しく入力して下さい。");
//		}
		//担当必須入力チェック
		if(name.equals("")) {
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
		//
		//
		//
		//


		return null;
	}

}
