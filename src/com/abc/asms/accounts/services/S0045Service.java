package com.abc.asms.accounts.services;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.abc.asms.accounts.forms.S0045Form;
import com.abc.asms.goods.utils.DBUtils;

public class S0045Service {

	public boolean service(S0045Form form) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		boolean exist = false;

		try {
			//データベースの接続を確立
			con = DBUtils.getConnection();
			//sql
			sql = "select mail from accounts where mail = ?";

			// プレースホルダに値を設定
			ps = con.prepareStatement(sql);

			ps.setString(1, form.getMail());

			rs = ps.executeQuery();

			if (rs.next()) {
				exist = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			DBUtils.close(con, ps, rs);
		}
		return exist;

	}

	public List<String> sendMail(String mail) {

		String ip = getIp();//ipアドレス取得

		List<String> error = new ArrayList<>();
		try {
			// GmailのSMTPを使用する
			Properties property = new Properties();
			property.put("mail.smtp.host", "smtp.gmail.com");
			property.put("mail.smtp.auth", "true");
			property.put("mail.smtp.starttls.enable", "true");
			property.put("mail.smtp.host", "smtp.gmail.com");
			property.put("mail.smtp.port", "587");
			property.put("mail.smtp.debug", "true");

			Session session = Session.getInstance(property, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("sie.tsd2018@gmail.com", "yuwoycimzylymdag");
				}
			});

			// toアドレス
			InternetAddress toAddress = new InternetAddress(mail);
			// fromアドレス
			InternetAddress fromAddress = new InternetAddress("sie.tsd2018@gmail.com", "物品売上管理システム");

			MimeMessage mimeMessage = new MimeMessage(session);
			mimeMessage.setRecipient(Message.RecipientType.TO, toAddress);
			mimeMessage.setFrom(fromAddress);
			mimeMessage.setSubject("【物品売上管理システム】パスワード再設定", "ISO-2022-JP");
			mimeMessage.setText("パスワードの再設定を行います。\r\n"
					+ "以下のURLより新パスワードの入力・変更を行って下さい。\r\n"
					+ "http://" + ip + ":8080/project2/S0046.html?user=" + mail, "ISO-2022-JP");

			Transport.send(mimeMessage);

		} catch (Exception e) {
			e.printStackTrace();
			error.add("予期しないエラーが発生しました。");
		}
		return error;
	}

	public String getIp() {
		//IPアドレスを取得
		String ip = null;
		try {
			InetAddress addr = InetAddress.getLocalHost();
			ip = addr.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return ip;
	}
}
