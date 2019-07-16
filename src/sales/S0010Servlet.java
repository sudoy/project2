package sales;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import accounts.forms.C0010Form;
import sales.forms.S0010Form;
import sales.forms.S0011Form;
import sales.services.S0010Service;


@WebServlet("/S0010.html")
public class S0010Servlet extends HttpServlet {
@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		//ログインチェック
		HttpSession session = req.getSession();
		boolean login = false;

		if (session.getAttribute("login") != null) {//そもそもsessionが存在してないとエラーになるので
			//loginがtrue(ログイン状態にある)じゃないと入れないように
			login = (boolean) session.getAttribute("login");
		}

		if (login == false) {
			session.setAttribute("error", "ログインしてください。");
			resp.sendRedirect("C0010.html");
		} else {

			//権限チェック(権限が無い場合はダッシュボードへ遷移)
			C0010Form checkauthority1 = (C0010Form) session.getAttribute("userinfo");

//					System.out.println(checkaccount1.getAuthority());

			if (!checkauthority1.getAuthority().equals("10") && !checkauthority1.getAuthority().equals("11")) {
				session.setAttribute("error", "不正なアクセスです。" );
				resp.sendRedirect("C0020.html");
			}else {
				//accountsテーブルからaccount_idを取得
				S0010Service service = new S0010Service();
				List<S0010Form> form = service.select();

				session.setAttribute("accounts", form);

				//商品カテゴリーの取得
				List<String> categoryList = service.category();

				session.setAttribute("allCategory", categoryList);

				//メッセージが出ないように
				session.removeAttribute("error");
				session.removeAttribute("complete");

				getServletContext().getRequestDispatcher("/WEB-INF/S0010.jsp").forward(req, resp);
			}
		}
	}


@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		try {
				S0011Form form2 = (S0011Form) session.getAttribute("form");

				//フォームへ値をセット
				S0010Form form = new S0010Form();

				form.setSaledate(form2.getSaledate());
				form.setCategoryid(form2.getCategoryid());
				form.setTradename(form2.getTradename());
				form.setPrice(form2.getPrice());
				form.setSalenumber(form2.getSalenumber());
				form.setNote(form2.getNote());
				form.setAccountid(form2.getAccountid());


				// DBへ登録
				S0010Service service = new S0010Service();

				service.register(form);

				session.removeAttribute("form");

				//accountsテーブルからaccount_idを取得
				S0010Service s0010service = new S0010Service();
				List<S0010Form> s0010form = s0010service.select();
				session.setAttribute("accounts", s0010form);
				req.setAttribute("accounts", s0010form);



				session.setAttribute("complete", "No" + service.Saleid(form) + "の売上を登録しました");


				getServletContext().getRequestDispatcher("/WEB-INF/S0010.jsp").forward(req, resp);
				session.removeAttribute("complete");


		}catch(Exception e) {
			e.printStackTrace();
		}

	}
}
