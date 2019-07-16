<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<li><a href="S0010.html">売上登録</a></li>
		<li class="active"><a href="S0020.html">売上検索<span class="sr-only">(current)</span></a></li>
		<li><a href="S0030.html">アカウント登録</a></li>
		<li><a href="S0040.html">アカウント検索</a></li>'/>
	</jsp:include>

	<div class="container-fluid">

	<h1>売上検索結果表示</h1>

	<table class="table">

	<tr><th>操作</th><th>No</th><th>販売日</th><th>担当</th><th>商品カテゴリー</th><th>商品名</th><th>単価</th><th>個数</th><th>小計</th></tr>

	<c:forEach items="${S0021Form}" var="i">
	<tr><td class="col-sm-1"><a class="btn btn-primary" href="S0022.html?id=${i.saleId}" role="button">
	<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 詳細</a></td>
	<td class="right">${i.saleId}</td><td>2015/1/15</td><td>${i.staff}</td><td>${i.categoryName}</td><td>${i.productName}</td>
	<td>${i.unitPrice}</td><td>${i.saleNumber}</td><td>${i.total}</td></tr>
	</c:forEach>
	</table>

	</div><!-- /.container-fluid -->


	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script	src="js/bootstrap.min.js"></script>
	</body>