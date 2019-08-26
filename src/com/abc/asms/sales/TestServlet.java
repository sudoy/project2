package com.abc.asms.sales;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test.html")
public class TestServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		// CSV出力用データ
		String[][] rows = {
				{ "0", "ダイナミック", "dynamic@example.com" },
				{ "1", "ホゲ", "hoge@example.com" },
				{ "2", "モゲ", "moge@example.com" },
				{ "3", "マゲ", "mage@example.com" },
				{ "4", "フゥ", "foo@example.com" },
				{ "5", "バァ", "bar@example.com" },
				{ "6", "グゥ", "goo@example.com" }
		};

		// 文字コード設定
		resp.setContentType("text/html; charset=UTF-8");
		// ファイル名設定（ファイル名を設定しないと、htmlとして画面に表示されてしまいます
		resp.setHeader("Content-Disposition", "attachment; filename=\"dynamic.csv\"");

		// CSVデータ作成
		StringBuffer sb = new StringBuffer();
		for (String[] row : rows) {
			for (int i = 0; i < row.length; i++) {
				if (i == 0) {
					sb.append("\"");
				} else {
					sb.append("\",\"");
				}
				sb.append(row[i]);

				if (i == row.length - 1) {
					sb.append("\"\n");
				}
			}
		}

		// レスポンスにCSV出力
		PrintWriter w = resp.getWriter();
		w.print(sb.toString());
		w.flush();
		w.close();
	}
}
