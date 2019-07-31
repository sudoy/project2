<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		value='<li><a href="c0020.html">ダッシュボード<span class="sr-only">(current)</span></a></li>
		${HTMLUtils.judgeSale(accounts.authority, "S6")}
		<li><a href="S0020.html">売上検索</a></li>
		${HTMLUtils.judgeAccount(accounts.authority, "S6")}
		<li><a href="S0040.html">アカウント検索</a></li>
		${HTMLUtils.judgeCategory50(accounts.authority, "S6")}
		<li class="active"><a href="S0060.html">商品カテゴリー一覧<span class="sr-only">(current)</span></a></li>
		${HTMLUtils.judgeCategory(accounts.authority, "S6")}
		<li><a href="S0080.html">商品カテゴリー一覧</a></li>'/>

</jsp:include>
<div class="container">

<h1>商品カテゴリーを削除してよろしいですか？</h1>
<form class="form-horizontal"action="S0063.html" method="post">
	<div class="form-group">
	    <label for="text" class="col-sm-3 control-label">カテゴリー名  <span class="badge badge-default"> 必須 </span></label>
	    <div class="col-sm-5">
	      <input type="text" class="form-control" placeholder="カテゴリー名 " name="name" value="${s0061form.name}" disabled>
	      <input type="hidden" name="name" value="${s0061form.name}">
	    </div>
	</div>

	<div class="form-group">
		<label  class="col-sm-3 control-label">使用可/不可 <span class="badge badge-default"> 必須 </span></label>

		<div class="col-sm-9" >
		<label class="radio-inline">
			<input type="radio" name="flg" <c:if test="${s0061form.flg == '可'}">checked</c:if> value="1" disabled> 可
		</label>
			<label class="radio-inline">
			<input type="radio" name="flg" <c:if test="${s0061form.flg == '不可'}">checked</c:if> value="0" disabled>  不可

			<input type="hidden" name="id" value="${s0061form.id}">


		</label>
		</div>
	</div>


	<div class="form-group">
	    <div class="col-sm-offset-4 col-sm-8">
	      <button type="submit" class="btn btn-danger">
	      	<span class="glyphicon glyphicon-remove" aria-hidden="true"></span> O K
	      </button>
	      <a type="button" class="btn btn-default" href="S0060.html">キャンセル</a>
	    </div>
	</div>
</form>
</div>

<script src="js/jquery-3.4.1.min.js"></script>
<script	src="js/bootstrap.min.js"></script>
</body>
</html>