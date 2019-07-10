<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta	charset="utf-8">
		<meta	http-equiv="X-UA-Compatible"	content="IE=edge">
		<meta	name="viewport"	content="width=device-width,	initial-scale=1">

		<title>アカウント検索結果一覧画面|物品管理システム</title>
		<link	href="css/bootstrap.min.css"	rel="stylesheet">
		<link href="css/S0041_アカウント検索結果表示画面.css" rel="stylesheet">
</head>
<body>

	<jsp:include page="header.jsp">
		<jsp:param name="bar"
		value='<li><a href="C0020_ダッシュボード.html">ダッシュボード<span class="sr-only">(current)</span></a></li>
		<li><a href="S0010_売上登録画面.html">売上登録</a></li>
		<li><a href="S0020_売上検索条件入力画面.html">売上検索</a></li>
		<li><a href="S0030_アカウント登録.html">アカウント登録</a></li>
		<li class="active"><a href="S0040_アカウント検索条件入力.html">アカウント検索</a></li>'/>
	</jsp:include>

	<div class="container-fluid">

	<h1>アカウント検索結果表示</h1>

	<table class="table">

	<tr><th>操作</th><th>No</th><th>氏名</th><th>メールアドレス</th><th>権限</th></tr>

	<c:forEach items="${S0041Form}" var="i">

		<tr>
			<td class="col-sm-2">
			<c:if test="${(i.authority == '売上登録/アカウント登録') || (i.authority == 'アカウント登録')}">
			<a class="btn btn-primary" href="S0042.html?id=${i.id}" role="button">
			<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 編集</a>
			<a class="btn btn-danger" href="S0044.html?id=${i.id}" role="button">
			<span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 削除</a>
			</c:if>
			</td>
			<td class="right">${i.id}</td>
			<td>${i.name}</td>
			<td class="col-sm-3">${i.mail}</td>
			<td>${i.authority}</td>
		</tr>
	</c:forEach>


	</table>

	</div><!-- /.container-fluid -->


	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script	src="js/bootstrap.min.js"></script>

</body>
</html>