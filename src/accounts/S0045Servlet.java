package accounts;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

@WebServlet("/S0045.html")
public class S0045Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		getServletContext().getRequestDispatcher("/WEB-INF/S0045.jsp").forward(req, resp);

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//mailをformにいれる
		String mail = req.getParameter("mail");
		S0045Form s0045form = new S0045Form(mail);

		HttpSession session = req.getSession();
		session = req.getSession();
		session.setAttribute("s0045form", s0045form);

		//入力チェック
			List<String> error = validate(s0045form);

		//エラー時はS0045.jspを再表示
		if(error.size() != 0) {
			session.setAttribute("error", error );
			session.setAttribute("S0045Form", s0045form);

			getServletContext().getRequestDispatcher("/WEB-INF/S0045.jsp").forward(req, resp);

			//errorメッセージ除去
			session.removeAttribute("error");

		}else {
			//メール送信


			getServletContext().getRequestDispatcher("/WEB-INF/S0045.jsp").forward(req, resp);
		}
	}




	private List<String> validate(S0045Form s0045form) throws UnsupportedEncodingException{
		List<String> error = new ArrayList<String>();


		String mail = s0045form.getMail();

		System.out.println(mail);

		if(mail.equals("")) {
			error.add( "メールアドレスを入力して下さい。");
		}

		//mailの形式チェック
		//「＠」が含まれていること。先頭は「a-zA-Z0-9」で、2文字目以降はさらに「._-」の文字が許容される。
		//「＠」以降は「a-zA-Z0-9._-」が1文字以上続き、必ず「.」が含まれていること
		if (!(s0045form.getMail().equals("")) &&
				(s0045form.getMail().getBytes("UTF-8").length) < 101) {//mailが入力されており且101バイトより小さい
			String mailFormat = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
			Pattern pattern = Pattern.compile(mailFormat);
			Matcher matcher = pattern.matcher(s0045form.getMail());
			if (!matcher.find()) {
				error.add("メールアドレスを入力して下さい。");
			}
		}

		return error;


	}

}
