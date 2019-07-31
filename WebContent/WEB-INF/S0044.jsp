<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.apache.commons.lang3.StringEscapeUtils" %>
<%@ page import="com.abc.asms.goods.utils.HTMLUtils" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>アカウント削除確認｜物品売上管理システム</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<jsp:include page="header.jsp">
<jsp:param name="bar"
		value='<li><a href="C0020.html">ダッシュボード)</a></li>
		${HTMLUtils.judgeSale(accounts.authority, "S4")}
		<li><a href="S0020.html">売上検索</a></li>
		${HTMLUtils.judgeAccount(accounts.authority, "S4")}
		<li class="active"><a href="S0040.html">アカウント検索<span class="sr-only">(current)</span></a></li>
		${HTMLUtils.judgeCategory50(accounts.authority, "S4")}
		<li><a href="S0060.html">商品カテゴリー一覧</a></li>
		${HTMLUtils.judgeCategory(accounts.authority, "S4")}
		<li><a href="S0080.html">商品カテゴリー一覧</a></li>'/>

</jsp:include>

	<div class="container">

		<h1>アカウントを削除してよろしいですか？</h1>
		<form class="form-horizontal" action="S0044.html" method="post">


			<div class="form-group">
				<label class="col-sm-3 control-label">氏名</label>
				<div class="col-sm-5">
					<fieldset disabled>
						<input type="text" class="form-control" value="${StringEscapeUtils.escapeHtml4(S0044Form.name)}" name="name">
					</fieldset>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">メールアドレス</label>
				<div class="col-sm-5">
					<fieldset disabled>
						<input type="text" class="form-control" value="${StringEscapeUtils.escapeHtml4(S0044Form.mail)}"
							name="mail">
					</fieldset>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">パスワード</label>
				<div class="col-sm-5">
					<fieldset disabled>
						<input type="password" class="form-control" name="password" placeholder="パスワード">
					</fieldset>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">パスワード(確認)</label>
				<div class="col-sm-5">
					<fieldset disabled>
						<input type="password" class="form-control" name="check" placeholder="パスワード(確認)">
					</fieldset>
				</div>
			</div>

			<fieldset disabled>
				<div class="form-group">
					<label class="col-sm-3 control-label">売上登録権限</label>
					<div class="col-sm-9">
						<label class="radio-inline"> <input type="radio"
							name="sales" value="0"
							<c:if test="${S0044Form.authority.equals('0') || S0044Form.authority.equals('10') }">checked</c:if>
							 > 権限なし
						</label> <label class="radio-inline"> <input type="radio"
							name="sales" value="1"
							<c:if test="${S0044Form.authority.equals('1') || S0044Form.authority.equals('11') }">checked</c:if>
							> 権限あり
						</label>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-3 control-label">アカウント登録確認</label>
					<div class="col-sm-9">
						<label class="radio-inline"> <input type="radio"
							name="account" value="0"
							<c:if test="${S0044Form.authority.equals('0') || S0044Form.authority.equals('1') }">checked</c:if>
							 > 権限なし
						</label> <label class="radio-inline"> <input type="radio"
							name="account" value="1"
							<c:if test="${S0044Form.authority.equals('10') || S0044Form.authority.equals('11') }">checked</c:if>
							> 権限あり
						</label>
					</div>
				</div>
			</fieldset>


			<div class="form-group">
				<div class="col-sm-offset-4 col-sm-8">
					<button type="submit" class="btn btn-danger">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
						O K
					</button>
					<a  class="btn btn-default" href="S0041.html">キャンセル</a>
				</div>
			</div>
		</form>
	</div>

	<script src="js/jquery-3.4.1.min.js"></script>
	<script	src="js/bootstrap.min.js"></script>
</body>
</html>