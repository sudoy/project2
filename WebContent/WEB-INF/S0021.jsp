<%@ page language="java" contentType="text/html; charset=UTF-8"%>
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

	<tr><td class="col-sm-1"><a class="btn btn-primary" href="S0022_売上詳細表示画面.html" role="button">
	<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 詳細</a></td>
	<td class="right">1</td><td>2015/1/15</td><td>イチロー</td><td>食料品</td><td>からあげ弁当</td><td>450</td><td>3</td><td>1,350</td></tr>

	<tr><td class="col-sm-1"><a class="btn btn-primary" href="S0022_売上詳細表示画面.html" role="button">
	<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 詳細</a></td>
	<td class="right">2</td><td>2015/1/15</td><td>イチロー</td><td>食料品</td><td>あんぱん</td><td>120</td><td>10</td><td>1200</td></tr>

	<tr><td class="col-sm-1"><a class="btn btn-primary" href="S0022_売上詳細表示画面.html" role="button">
	<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 詳細</a></td>
	<td class="right">3</td><td>2015/1/15</td><td>イチロー</td><td>飲料</td><td>コカコーラ500ml</td><td>130</td><td>5</td><td>650</td></tr>

	<tr><td class="col-sm-1"><a class="btn btn-primary" href="S0022_売上詳細表示画面.html" role="button">
	<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 詳細</a></td>
	<td class="right">4</td><td>2015/1/15</td><td>本田圭佑</td><td>その他</td><td>Yシャツ白 M</td><td>1,200</td><td>2</td><td>2,400</td></tr>

	<tr><td class="col-sm-1"><a class="btn btn-primary" href="S0022_売上詳細表示画面.html" role="button">
	<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 詳細</a></td>
	<td class="right">5</td><td>2015/1/14</td><td>錦織圭</td><td>食料品</td><td>とろっと玉子のオムライスドリア</td><td>380</td><td>8</td><td>3,040</td></tr>

	<tr><td class="col-sm-1"><a class="btn btn-primary" href="S0022_売上詳細表示画面.html" role="button">
	<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 詳細</a></td>
	<td class="right">6</td><td>2015/1/14</td><td>錦織圭</td><td>本・雑誌</td><td>週刊少年マガジン2015年7号</td><td>250</td><td>15</td><td>3,750</td></tr>

	<tr><td class="col-sm-1"><a class="btn btn-primary" href="S0022_売上詳細表示画面.html" role="button">
	<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 詳細</a></td>
	<td class="right">7</td><td>2015/1/14</td><td>池田勇太</td><td>本・雑誌</td><td>NHKラジオ ラジオ英会話2015年02月号</td><td>780</td><td>1</td><td>780</td></tr>

	<tr><td class="col-sm-1"><a class="btn btn-primary" href="S0022_売上詳細表示画面.html" role="button">
	<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 詳細</a></td>
	<td class="right">8</td><td>2015/1/14</td><td>本田圭佑</td><td>食料品</td><td>チーズケーキ</td><td>220</td><td>4</td><td>880</td></tr>

	<tr><td class="col-sm-1"><a class="btn btn-primary" href="S0022_売上詳細表示画面.html" role="button">
	<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 詳細</a></td>
	<td class="right">9</td><td>2015/1/14</td><td>本田圭佑</td><td>食料品</td><td>麻婆&海老チリ炒飯</td><td>520</td><td>9</td><td>4,680</td></tr>

	<tr><td class="col-sm-1"><a class="btn btn-primary" href="S0022_売上詳細表示画面.html" role="button">
	<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 詳細</a></td>
	<td class="right">10</td><td>2015/1/14</td><td>本田圭佑</td><td>飲料</td><td>アクエリアス 2L</td><td>260</td><td>2</td><td>520</td></tr>

	<tr><td class="col-sm-1"><a class="btn btn-primary" href="S0022_売上詳細表示画面.html" role="button">
	<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 詳細</a></td>
	<td class="right">11</td><td>2015/1/14</td><td>錦織圭</td><td>その他</td><td>靴下 黒28cm</td><td>800</td><td>1</td><td>800</td></tr>




	</table>

	</div><!-- /.container-fluid -->


	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script	src="js/bootstrap.min.js"></script>
	</body>