<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.abc.asms.goods.utils.HTMLUtils" %>
<%@ page import="org.apache.commons.lang3.StringEscapeUtils" %>

<!DOCTYPE html>
<html>

<head>
	<meta	charset="utf-8">
		<meta	http-equiv="X-UA-Compatible"	content="IE=edge">
		<meta	name="viewport"	content="width=device-width,	initial-scale=1">

		<title>売上検索結果一覧画面|物品管理システム</title>
		<link	href="css/bootstrap.min.css"	rel="stylesheet">
		<link href="css/S0021_売上検索結果一覧画面.css" rel="stylesheet">
</head>

	<body>


	<jsp:include page="header.jsp">
		<jsp:param name="bar"
		value='<li><a href="C0020.html">ダッシュボード</a></li>
		${HTMLUtils.judgeSale(accounts.authority, "S2")}
		<li class="active"><a href="S0020.html">売上検索<span class="sr-only">(current)</span></a></li>
		${HTMLUtils.judgeAccount(accounts.authority, "S2")}
		<li><a href="S0040.html">アカウント検索</a></li>'/>
	</jsp:include>

	<div class="container-fluid">

	<h1>売上検索結果表示</h1>

	<jsp:include page="message.jsp"/>

	<table class="table">

	<tr><th>操作</th><th>No</th><th>販売日</th><th>担当</th><th>商品カテゴリー</th><th>商品名</th><th>単価</th><th>個数</th><th>小計</th></tr>

	<c:forEach items="${S0021Form}" var="i">
	<tr><td class="col-sm-1"><a class="btn btn-primary" href="S0022.html?id=${i.saleId}" role="button">
	<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 詳細</a></td>
	<td class="right">${i.saleId}</td><td>${HTMLUtils.formatDate(i.saleDate)}</td><td>${StringEscapeUtils.escapeHtml4(i.staff)}</td>
	<td>${StringEscapeUtils.escapeHtml4(i.categoryName)}</td>
	<td>${StringEscapeUtils.escapeHtml4(i.productName)}</td>
	<td class="right">${HTMLUtils.formatTotal(i.unitPrice)}</td><td class="right">${HTMLUtils.formatTotal(i.saleNumber)}</td>
	<td class="right">${HTMLUtils.formatTotal(i.total)}</td></tr>
	</c:forEach>
	</table>

	</div><!-- /.container-fluid -->


	<script src="js/jquery-3.4.1.min.js"></script>
	<script	src="js/bootstrap.min.js"></script>
	</body>