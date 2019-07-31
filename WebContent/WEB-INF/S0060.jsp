<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page import="com.abc.asms.goods.utils.HTMLUtils" %>


<!DOCTYPE html>
<html>

<head>
	<meta	charset="utf-8">
		<meta	http-equiv="X-UA-Compatible"	content="IE=edge">
		<meta	name="viewport"	content="width=device-width,	initial-scale=1">

		<title>商品カテゴリー一覧画面|物品管理システム</title>
		<link	href="css/bootstrap.min.css"	rel="stylesheet">
		<link href="css/S0041_アカウント検索結果表示画面.css" rel="stylesheet">
</head>

<body>
<jsp:include page="header.jsp">
<jsp:param name="bar"
		value='
		<li><a href="C0020.html">ダッシュボード</a></li>
		${HTMLUtils.judgeSale(accounts.authority, "S6")}
		<li><a href="S0020.html">売上検索</a></li>
		${HTMLUtils.judgeAccount(accounts.authority, "S6")}
		<li><a href="S0040.html">アカウント検索</a></li>
		${HTMLUtils.judgeCategory50(accounts.authority, "S6")}
		<li class="active"><a href="S0060.html">商品カテゴリー一覧<span class="sr-only">(current)</span></a></li>
		${HTMLUtils.judgeCategory(accounts.authority, "S6")}
		<li><a href="S0080.html">商品カテゴリー一覧</a></li>'/>

</jsp:include>
<div class="container-fluid">

<h1>商品カテゴリー一覧</h1>

<jsp:include page="message.jsp"/>


<table class="table">

<tr><th>操作</th><th>No</th><th>カテゴリー名 </th><th>使用可/不可</th></tr>

<c:forEach items="${categories}" var="i">

	<tr><c:if test="${(accounts.authority == '1') || (accounts.authority == '11')}">
	<td class="col-sm-2"><a class="btn btn-primary" href="S0061.html?id=${i.categoryid}&name=${i.name}&flg=${i.activeflg}" role="button">
	<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 編集</a>
	<a class="btn btn-danger" href="S0063.html?id=${i.categoryid}&name=${i.name}&flg=${i.activeflg}" role="button">
	<span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 削除</a></td>
	</c:if>

	<c:if test="${(accounts.authority == '0') || (accounts.authority == '10')}">
	<td class="col-sm-2">
	</c:if>

	<td class="right">${i.categoryid}</td><td>${i.name}</td><td>${i.activeflg}</td></tr>


</c:forEach>


</table>

</div><!-- /.container-fluid -->


<script src="js/jquery-3.4.1.min.js"></script>
<script	src="js/bootstrap.min.js"></script>
</body>