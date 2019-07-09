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
		<link href="css/S0041_アカウント検索結果一覧画面.css" rel="stylesheet">
</head>
<body>

	<nav class="navbar navbar-default">
		<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">物品売上管理システム</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="C0020_ダッシュボード.html">ダッシュボード<span class="sr-only">(current)</span></a></li>
				<li><a href="S0010_売上登録画面.html">売上登録</a></li>
				<li><a href="S0020_売上検索条件入力画面.html">売上検索</a></li>
				<li><a href="S0030_アカウント登録.html">アカウント登録</a></li>
				<li><a href="S0040_アカウント検索条件入力.html">アカウント検索</a></li>
			</ul>

			 <ul class="nav navbar-nav navbar-right">
				<li><a href="C0010_ログイン画面.html">ログアウト</a></li>
				<li class="dropdown"></li>
			</ul>

		</div><!-- /.navbar-collapse -->
		</div><!-- /.container-fluid -->
	</nav>

	<div class="container-fluid">

	<h1>アカウント検索結果表示</h1>

	<table class="table">

	<tr><th>操作</th><th>No</th><th>氏名</th><th>メールアドレス</th><th>権限</th></tr>

	<c:forEach items="${form}" var="i">

		<tr>
			<td class="col-sm-2"><a class="btn btn-primary" href="S0042_アカウント詳細編集.html" role="button">
			<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 編集</a>
			<a class="btn btn-danger" href="S0044_アカウント詳細削除確認.html" role="button">
			<span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 削除</a></td>

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