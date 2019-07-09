<%@ page language="java" contentType="text/html; charset=UTF-8 %>
<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>売上登録|物品売上管理システム</title>
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/S0010_売上登録画面.css" rel="stylesheet">
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
			<li><a href="C0020_ダッシュボード.html">ダッシュボード</a></li>
			<li class="active"><a href="#">売上登録 <span class="sr-only">(current)</span></a></li>
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

<h1>売上登録</h1>

<div class="container">


<form class="form-horizontal" method="POST" action="S0011_売上登録確認画面.html">
	<table>

	<tr><th>販売日 <span class="badge">必須</span></th>
		<td>
		<div class="col-md-3">
		<input type="text" class="form-control" value="2015/01/15" placeholder="販売日">
		</div>
		</td>
	</tr>

	<tr><th>担当 <span class="badge">必須</span></th>
	<td><div class="col-md-8">
		<select class="form-control">
			<option>選択してください</option>
			<option>イチロー</option>
			<option>ダルビッシュ</option>
			<option>4</option>
			<option>5</option>
		</select></div>
	</td></tr>
	<tr><th>商品カテゴリー <span class="badge">必須</span></th>
	<td><div class="col-md-8">
		<select class="form-control">
			<option>選択してください</option>
			<option>食料品</option>
			<option>日用品</option>
			<option>4</option>
			<option>5</option>
		</select></div>
	</td></tr>

	<tr><th>商品名 <span class="badge">必須</span></th>
	<td><div class="col-md-8">
	<input class="form-control" type="text" placeholder="商品名">
	</div></td></tr>
	<tr><th>単価 <span class="badge">必須</span></th>
	<td><div class="col-md-3">
	<input class="form-control" id="right" type="text" placeholder="単価">
	</div></td></tr>
	<tr><th>個数 <span class="badge">必須</span></th>
	<td><div class="col-md-3">
	<input class="form-control" id="right" type="text" placeholder="個数">
	</div></td></tr>
	<tr><th id="remarks">備考</th>
	<td><div class="col-md-8">
	<textarea class="form-control" id="remarks" rows="3" placeholder="備考"></textarea>
	</div></td>
	</tr>


	<tr><th></th>
	<td id="canto">
	<div class="form-group">

		<button type="submit" class="btn btn-primary" id="entry">
		<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 登　録</button>
	</div>
	</td></tr>
	</table>
</form>

</div><!--/container-->


	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>

	</body>
</html>