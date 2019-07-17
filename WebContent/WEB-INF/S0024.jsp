<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>売上詳細編集確認|物品売上管理システム</title>
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/S0024_売上詳細編集確認画面.css" rel="stylesheet">
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
			<li><a href="S0010_売上登録画面.html">売上登録</a></li>
			<li class="active"><a href="#">売上検索 <span class="sr-only">(current)</span></a></li>
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

<h1>売上を編集してよろしいですか？</h1>

<div class="container">


<form class="form-horizontal" method="POST" action="S0021.html">
	<table>

	<tr><th>販売日</th>
		<td>
		<div class="col-md-3">
		<input type="text" class="form-control" name="saledate" value="" placeholder="販売日" disabled>
		</div>
		</td>
	</tr>

	<tr><th>担当</th>
	<td><div class="col-md-8">
		<select class="form-control" disabled>
			<option>イチロー</option>
			<option>イチロー</option>
			<option>ダルビッシュ</option>
			<option>4</option>
			<option>5</option>
		</select></div>
	</td></tr>
	<tr><th>商品カテゴリー</th>
	<td><div class="col-md-7">
		<label class="radio-inline">
		<input type="radio" name="inlineRadioOptions" value="1" disabled checked> 食料品
		</label>
		<label class="radio-inline">
		<input type="radio" name="inlineRadioOptions" value="2" disabled> 飲料
		</label>
		<label class="radio-inline">
		<input type="radio" name="inlineRadioOptions" value="3" disabled> 雑誌
		</label>
		<label class="radio-inline">
		<input type="radio" name="inlineRadioOptions" value="9" disabled> 酒類
		</label>
		<label class="radio-inline">
		<input type="radio" name="inlineRadioOptions" value="6" disabled> 冷凍食品
		</label>
		<label class="radio-inline">
		<input type="radio" name="inlineRadioOptions" value="10" disabled> たばこ
		</label>
		<label class="radio-inline">
		<input type="radio" name="inlineRadioOptions" value="5" disabled> その他
		</label></div>
	</td></tr>

	<tr><th>商品名</th>
	<td><div class="col-md-8">
	<input class="form-control" type="text" name="tradename" placeholder="商品" value="" disabled>
	</div></td></tr>
	<tr><th>単価</th>
	<td><div class="col-md-3">
	<input class="form-control" id="right" type="text" name="price" placeholder="単価" value="" disabled>
	</div></td></tr>
	<tr><th>個数</th>
	<td><div class="col-md-3">
	<input class="form-control" id="right" type="text" name="salenumber" placeholder="個数" value="" disabled>
	</div></td></tr>
	<tr><th>小計</th>
	<td><div class="col-md-3">
	<p disabled>1,350</p>
	</div></td></tr>
	<tr><th id="remarks">備考</th>
	<td><div class="col-md-8">
	<textarea class="form-control" id="detail" rows="3" name="note" placeholder="備考" disabled>今日からの新商品</textarea>
	</div></td>
	</tr>


	<tr><th></th>
	<td id="canto">
	<div class="form-group">

		<button type="submit" class="btn btn-primary" id="touroku">
		<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> O K</button>
		<a class="btn btn-default" id="cancel" href="S0023.html">キャンセル</a>
	</div>
	</td></tr>
	</table>
</form>

</div><!--/container-->

	<script src="js/jquery-3.4.1.min.js"></script>
	<script	src="js/bootstrap.min.js"></script>

</body>
</html>