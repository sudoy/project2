<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.apache.commons.lang3.StringEscapeUtils" %>
<%@ page import="com.abc.asms.goods.utils.HTMLUtils" %>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>アカウント登録｜物品売上管理システム</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<jsp:include page="header.jsp">
<jsp:param name="bar"
		value='<li><a href="C0020.html">ダッシュボード<span class="sr-only">(current)</span></a></li>
		${HTMLUtils.judgeSale(accounts.authority, "S3")}
		<li><a href="S0020.html">売上検索</a></li>
		${HTMLUtils.judgeAccount(accounts.authority, "S3")}
		<li><a href="S0040.html">アカウント検索</a></li>'/>
</jsp:include>

<div class="container">


<h1>アカウント登録</h1>

	<jsp:include page="message.jsp"/>

	<form class="form-horizontal" action="S0031.html" method="post">
		<div class="form-group">
		    <label class="col-sm-3 control-label">氏名 <span class="badge badge-default"> 必須 </span></label>
		    <div class="col-sm-5">
		      <input type="text" class="form-control" placeholder="氏名" name="name" value="${StringEscapeUtils.escapeHtml4(S0031form.name)}">
		    </div>
		</div>
		<div class="form-group">
		    <label class="col-sm-3 control-label">メールアドレス <span class="badge badge-default"> 必須 </span></label>
		    <div class="col-sm-5">
		      <input type="email" class="form-control" placeholder="メールアドレス" name ="mail" value="${StringEscapeUtils.escapeHtml4(S0031form.mail)}">
		    </div>
		</div>
		<div class="form-group">
		    <label class="col-sm-3 control-label">パスワード <span class="badge badge-default"> 必須 </span></label>
		    <div class="col-sm-5">
		      <input type="password" class="form-control" placeholder="パスワード" name="password">
		    </div>
		</div>
		<div class="form-group">
		    <label class="col-sm-3 control-label">パスワード(確認) <span class="badge badge-default"> 必須 </span></label>
		    <div class="col-sm-5">
		      <input type="password" class="form-control" placeholder="パスワード(確認)" name="check">
		    </div>
		</div>
		<div class="form-group">
			<label  class="col-sm-3 control-label">売上登録権限 <span class="badge badge-default"> 必須 </span></label>
			<div class="col-sm-9">
			<label class="radio-inline">
				<input type="radio" name="sale" value="0" <c:if test="${S0031form.sale == '0'}">checked</c:if>> 権限なし
			</label>
				<label class="radio-inline">
				<input type="radio" name="sale" value="1" <c:if test="${S0031form.sale == '1'}">checked</c:if>> 権限あり
			</label>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-3 control-label">アカウント登録期限 <span class="badge badge-default">必須</span></label>

			<div class="col-sm-9">
			<label class="radio-inline">
				<input type="radio" name="account" value="0" <c:if test="${S0031form.account == '0'}">checked</c:if>> 権限なし
			</label>
				<label class="radio-inline">
				<input type="radio" name="account" value="1" <c:if test="${S0031form.account == '1'}">checked</c:if>> 権限あり
			</label>
			</div>
		</div>

		<div class="form-group">
		    <div class="col-sm-offset-4 col-sm-8">
		      <button type="submit" class="btn btn-primary">
		      	<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 登  録
		      </button>
		    </div>
		</div>
	</form>
</div>

<script src="js/jquery-3.4.1.min.js"></script>
	<script	src="js/bootstrap.min.js"></script>
</body>
</html>