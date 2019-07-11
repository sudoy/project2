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

import sales.forms.S0010Form;

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


		S0010Form saleform = new S0010Form(saledate, accountid, categoryid, tradename, price, salenumber, note);


		HttpSession session = req.getSession();
		session.setAttribute("saleform", saleform);


		// バリデーションチェック
		List<String> error = validate(saleform);


		//エラーがある場合
		if(error.size() != 0) {
			// S0030.htmlを再表示
			session.setAttribute("error", error);
			req.setAttribute("saleform", saleform);
			getServletContext().getRequestDispatcher("/WEB-INF/S0010.jsp")
				.forward(req, resp);
			session.removeAttribute("error");

			getServletContext().getRequestDispatcher("/WEB-INF/S010.jsp").forward(req, resp);
		}
		//エラーがない場合

		//小計をだす
		int pricenum = Integer.parseInt(price);
		int salenumbernum = Integer.parseInt(salenumber);
		int total = pricenum * salenumbernum;
		req.setAttribute("total", total);


		getServletContext().getRequestDispatcher("/WEB-INF/S0011.jsp").forward(req, resp);

	}


	private List<String> validate(S0010Form saleform) {
		List<String> error = new ArrayList<String>();  //list add
		//日付チェック
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		format.setLenient(false);

		String saledate = saleform.getSaledate();
		String accountid = saleform.getAccountid();
		String categoryid = saleform.getCategoryid();
		String tradename = saleform.getTradename();
		String price = saleform.getPrice();
		String salenumber = saleform.getSalenumber();



		if(saledate.equals("")) {
			error.add("販売日を入力してください。") ;
		}else {
			try {
			   format.parse(saledate);
			} catch (ParseException | java.text.ParseException  e) {
				error.add("販売日を正しく入力してください。") ;
			}
		}

		if(accountid.equals("")) {
			error.add( "担当を選択してください。");
		}
		if(categoryid.equals("")) {
			error.add("商品カテゴリーを選択してください。");
		}
		if(tradename.equals("")) {
			error.add("商品名を入力してください。");
		}
		if(price.equals("")) {
			error.add("単価を入力してください。");
		}else {
			//数字かどうか
			try {
				Integer.parseInt(price);
			}catch(NumberFormatException e){
				error.add("単価を正しく入力して下さい。");
			}
		}

		if(salenumber.equals("")) {
			error.add("個数を入力してください。");
		}else {
			try {
				Integer.parseInt(salenumber);
			}catch(NumberFormatException e){
				error.add("個数を正しく入力してください。");
			}
		}


		return error;

	}
}
