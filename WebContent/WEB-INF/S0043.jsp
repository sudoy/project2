<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.apache.commons.lang3.StringEscapeUtils" %>
<%@ page import="com.abc.asms.goods.utils.HTMLUtils" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>アカウント編集確認｜物品売上管理システム</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/売上管理システム.css" rel="stylesheet">
</head>

<body>
<jsp:include page="header.jsp">
<jsp:param name="bar"
		value='<li><a href="c0020.html">ダッシュボード<span class="sr-only">(current)</span></a></li>
		${HTMLUtils.judgeSale(accounts.authority, "S4")}
		<li><a href="S0020.html">売上検索</a></li>
		${HTMLUtils.judgeAccount(accounts.authority, "S4")}
		<li class="active"><li><a href="S0040.html">アカウント検索<span class="sr-only">(current)</span></a></li>
		${HTMLUtils.judgeCategory50(accounts.authority, "S4")}
		<li><a href="S0060.html">商品カテゴリー一覧</a></li>
		${HTMLUtils.judgeCategory(accounts.authority, "S4")}
		<li><a href="S0070.html">商品カテゴリー一覧</a></li>'/>

</jsp:include>



<div class="container">

<h1>アカウントを編集してよろしいですか？</h1>

<form class="form-horizontal" action="S0043.html" method="post">

	<input type="hidden"  name="id" value="${S0042Form.id}">
	<input type="hidden"  name="authority" value="${S0042Form.authority}">


	<div class="form-group">
	    <label class="col-sm-3 control-label">氏名</label>
	    <div class="col-sm-5">
	    <fieldset disabled>
	    	<input type="text" class="form-control" value="${StringEscapeUtils.escapeHtml4(S0042Form.name)}" name="name">
	    </fieldset>
	    </div>
	</div>
	<div class="form-group">
	    <label class="col-sm-3 control-label">メールアドレス</label>
	    <div class="col-sm-5">
	      <fieldset disabled>
	    	<input type="text"  class="form-control" value="${StringEscapeUtils.escapeHtml4(S0042Form.mail)}" name="mail">
	      </fieldset>
	    </div>
	</div>
	<div class="form-group">
	    <label class="col-sm-3 control-label">パスワード</label>
	    <div class="col-sm-5">
	      <fieldset disabled>
	    	<input type="password" class="form-control" value="${StringEscapeUtils.escapeHtml4(S0042Form.password)}" name="password" placeholder="パスワード">
	      </fieldset>
	    </div>
	</div>
	<div class="form-group">
	    <label class="col-sm-3 control-label">パスワード(確認)</label>
	    <div class="col-sm-5">
	      <fieldset disabled>
	    	<input type="password" class="form-control" value="${StringEscapeUtils.escapeHtml4(S0042Form.check)}" name="check" placeholder="パスワード(確認)">
	      </fieldset>
	    </div>
	</div>

	<fieldset disabled>
	<div class="form-group">
		<label  class="col-sm-3 control-label">売上登録権限</label>
		<div class="col-sm-9">
		<label class="radio-inline">
			<input type="radio"  name="sale" value="0"
			<c:if test="${S0042Form.authority.equals('0') || S0042Form.authority.equals('10') }">checked</c:if>
			 > 権限なし
		</label>
			<label class="radio-inline">
			<input type="radio"  name="sale" value="1"
			<c:if test="${S0042Form.authority.equals('1') ||S0042Form.authority.equals('11') }">checked</c:if>
			>権限あり
		</label>
		</div>
	</div>

	<div class="form-group">
		<label  class="col-sm-3 control-label">アカウント登録確認</label>
		<div class="col-sm-9">
		<label class="radio-inline">
			<input type="radio" name="account" value="0"
			<c:if test="${S0042Form.authority.equals('0') || S0042Form.authority.equals('1') }">checked</c:if>
			  > 権限なし
		</label>
			<label class="radio-inline">
			<input type="radio" name="account" value="1"
			<c:if test="${S0042Form.authority.equals('10') || S0042Form.authority.equals('11') }">checked</c:if>
			 > 権限あり
		</label>
		</div>
	</div>
	</fieldset>

	<div class="form-group">
	    <div class="col-sm-offset-4 col-sm-8">
	      <button type="submit" class="btn btn-primary">
	      	<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> O K
	      </button>
	      	<a  class="btn btn-default" href="S0042.html">キャンセル</a>
	    </div>
	</div>
</form>
</div>


	<script src="js/jquery-3.4.1.min.js"></script>
	<script	src="js/bootstrap.min.js"></script>


</body>
</html>