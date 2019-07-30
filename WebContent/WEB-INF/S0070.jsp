<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>商品カテゴリー登録｜物品売上管理システム</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/S0070_商品カテゴリー登録.css" rel="stylesheet">

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
			<li><a href="C0020_ダッシュボード.html">ダッシュボード</a></li>
			<li><a href="S0010_売上登録画面.html">売上登録 <span class="sr-only">(current)</span></a></li>
			<li><a href="S0020_売上検索条件入力画面.html">売上検索</a></li>
			<li><a href="S0030_アカウント登録.html">アカウント登録</a></li>
			<li><a href="S0040_アカウント検索条件入力.html">アカウント検索</a></li>
			<li><a href="S0050_商品カテゴリー登録.html">商品カテゴリー登録</a></li>
			<li><a href="S0060_商品カテゴリー一覧.html">商品カテゴリー一覧</a></li>
			<li  class="active"><a href="#">商品カテゴリー登録</a></li>
			<li><a href="S0080_商品カテゴリー一覧.html">商品カテゴリー一覧</a></li>
		</ul>

		 <ul class="nav navbar-nav navbar-right">
			<li><a href="C0010_ログイン画面.html">ログアウト</a></li>
			<li class="dropdown"></li>
		</ul>

	</div><!-- /.navbar-collapse -->
</div><!-- /.container-fluid -->
</nav>


<div class="container">

<h1>商品カテゴリー登録</h1>

	<form class="form-horizontal" action="S0070_商品カテゴリー登録確認.html" method="post">
		<div class="form-group">
		    <label for="text" class="col-sm-3 control-label">カテゴリー名 <span class="badge badge-default"> 必須 </span></label>
		    <div class="col-sm-5">
		      <input type="text" class="form-control" placeholder="商品カテゴリー" value="" name="categoryname">
		    </div>
		</div>

		<div class="form-group">
			<label  class="col-sm-3 control-label">有効/無効 <span class="badge badge-default"> 必須 </span></label>

			<div class="col-sm-9">
			<label class="radio-inline">
				<input type="radio" name="active" value="0" checked="checked" > 有効
			</label>
				<label class="radio-inline">
				<input type="radio" name="active" value="1" > 無効
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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>