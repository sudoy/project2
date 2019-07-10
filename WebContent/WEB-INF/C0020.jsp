<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta	charset="utf-8">
		<meta	http-equiv="X-UA-Compatible"	content="IE=edge">
		<meta	name="viewport"	content="width=device-width,	initial-scale=1">

		<title>ダッシュボード|物品売上管理システム</title>
		<link	href="css/bootstrap.min.css"	rel="stylesheet">
		<link href="css/C0020_ダッシュボード.css" rel="stylesheet">
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
			<a class="navbar-brand">物品売上管理システム</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">ダッシュボード <span class="sr-only">(current)</span></a></li>
				<li><a href="S0010_売上登録画面.html">売上登録</a></li>
				<li><a href="S0020_売上検索条件入力画面.html">売上検索</a></li>
				<li><a href="S0030_アカウント登録.html">アカウント登録</a></li>
				<li><a href="S0040_アカウント検索条件入力画面.html">アカウント検索</a></li>
			</ul>

			 <ul class="nav navbar-nav navbar-right">
				<li><a href="C0010_ログイン画面.html">ログアウト</a></li>
				<li class="dropdown"></li>
			</ul>

		</div><!-- /.navbar-collapse -->
		</div><!-- /.container-fluid -->
	</nav>

	<div class="container">

	<h1>ダッシュボード</h1>

	<div class="center">

	<div class="row">
	<div class="col-md-4">
	<div class="panel panel-default" id="panel1">
		<div class="panel-heading">
			<h2 class="panel-title">
			 前月(5月)の収入合計</h2>
		</div>
		<div class="panel-body">
			1,000,000円
		</div>
	</div>
	</div>

	<div class="col-md-4">
	<div class="panel panel-default" id="panel2">
		<div class="panel-heading">
			<h2 class="panel-title">
			今月(6月)の売上合計</h2>
		</div>
		<div class="panel-body">
			1,200,000円
		</div>
	</div>
	</div>

	<div class="col-md-4">
	<div class="panel panel-default" id="panel2">
		<div class="panel-heading">
			<h2 class="panel-title">
			前月比</h2>
		</div>
		<div class="panel-body">
		<span class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span>
			120.00%
		</div>
	</div>
	</div>


	</div>
	</div><!--center-->

	</div><!--comtainer-->

	<div class="container">

		<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading"><h2>今月のイチローさんの売上</h2></div>

		<table class="table">
		<tr><th>No</th><th>販売日</th><th>商品カテゴリー</th><th>商品名</th><th>単価</th><th>個数</th><th>小計</th></tr>

		<tr><td>1</td><td>2015/1/15</td><td>食料品</td><td>からあげ弁当</td>
		<td>450</td><td>3</td><td>1,350</td></tr>

		<tr><td>2</td><td>2015/1/15</td><td>食料品</td><td>あんぱん</td>
		<td>120</td><td>10</td><td>1200</td></tr>

		<tr><td>3</td><td>2015/1/15</td><td>飲料</td><td>コカコーラ500ml</td>
		<td>130</td><td>5</td><td>650</td></tr>

		<tr><td colspan="5"></td><td>合計</td><td>3,200</td></tr>

		</table>
		</div>

	</div><!--comtainer-->


	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script	src="js/bootstrap.min.js"></script>
	</body>