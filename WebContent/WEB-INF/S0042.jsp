<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.abc.asms.goods.utils.HTMLUtils" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント編集｜物品売上管理システム</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<jsp:include page="header.jsp">
<jsp:param name="bar"
		value='<li><a href="C0020.html">ダッシュボード</a></li>
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

		<h1>アカウント編集</h1>

		<jsp:include page="message.jsp"/>

		<form class="form-horizontal" action="S0042.html" method="post">

			<input type="hidden" name="id" value="${S0042Form.id}">
			<input type="hidden" name="authority" value="${S0042Form.authority}">
			<input type="hidden" name="version" value="${S0042Form.version}">

			<div class="form-group">
				<label class="col-sm-3 control-label">氏名 <span
					class="badge badge-default"> 必須 </span></label>
				<div class="col-sm-5">
					<input type="text" class="form-control" placeholder="氏名"
						name="name" value="${S0042Form.name}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">メールアドレス
					<span class="badge badge-default"> 必須 </span>
				</label>
				<div class="col-sm-5">
					<input type="email" class="form-control" placeholder="メールアドレス"
						name="mail" value="${S0042Form.mail}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">パスワード</label>
				<div class="col-sm-5">
					<input type="password" class="form-control" name="password" placeholder="パスワード"
						value="">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">パスワード(確認)</label>
				<div class="col-sm-5">
					<input type="password" class="form-control" name="check" placeholder="パスワード(確認)"
						value="${check}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">売上登録権限 <span
					class="badge badge-default"> 必須 </span></label>


				<div class="col-sm-9">
					<label class="radio-inline"> <input type="radio"
						name="sale" value="0"
						<c:if test="${S0042Form.authority.equals('0') || S0042Form.authority.equals('10') }">checked</c:if>>
						権限なし
					</label> <label class="radio-inline"> <input type="radio"
						name="sale" value="1"
						<c:if test="${S0042Form.authority.equals('1') || S0042Form.authority.equals('11') }">checked</c:if>>
						権限あり
					</label>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label">アカウント登録期限 <span
					class="badge badge-default">必須</span></label>


				<div class="col-sm-9">
					<label class="radio-inline"> <input type="radio"
						name="account" value="0"
						<c:if test="${S0042Form.authority.equals('0') || S0042Form.authority.equals('1') }">checked</c:if>>権限なし
					</label> <label class="radio-inline"> <input type="radio"
						name="account" value="1"
						<c:if test="${S0042Form.authority.equals('10') || S0042Form.authority.equals('11') }">checked</c:if>>権限あり
					</label>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-4 col-sm-8">
					<button type="submit" class="btn btn-primary">
						<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 更
						新
					</button>

					<a class="btn btn-default" href="S0041.html">キャンセル</a>
				</div>
			</div>
		</form>
	</div>

	<script src="js/jquery-3.4.1.min.js"></script>
	<script	src="js/bootstrap.min.js"></script>

</body>
</html>