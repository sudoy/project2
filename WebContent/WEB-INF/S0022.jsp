<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>売上詳細表示|物品管理システム</title>
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/S0022_売上詳細表示画面.css" rel="stylesheet">
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

<h1>売上詳細表示</h1>

<div class="container">


<form class="form-horizontal" method="POST" action="S0023_売上詳細編集画面.html">
	<table>

	<tr><th>販売日</th>
		<td>
		<div class="col-md-3">
		<p>2015/01/15</p>
		</div>
		</td>
	</tr>

	<tr><th>担当</th>
	<td><div class="col-md-8"><p>イチロー</P></div>
	</td></tr>
	<tr><th>商品カテゴリー</th>
	<td><div class="col-md-8"><p>食料品</p></div>
	</td></tr>

	<tr><th>商品名</th>
	<td><div class="col-md-8"><p>からあげ弁当</p>
	</div></td></tr>
	<tr><th>単価</th>
	<td><div class="col-md-3"><p id="right">450</p>
	</div></td></tr>
	<tr><th>個数</th>
	<td><div class="col-md-3"><p id="right">3</p>
	</div></td></tr>
	<tr><th>小計</th>
	<td><div class="col-md-3">
	</div></td></tr>
	<tr><th id="remarks">備考</th>
	<td><p>今日からの新商品</p></div></td>
	</tr>


	<tr><th></th>
	<td id="canto">
	<div class="form-group">
		<button type="submit" class="btn btn-primary" id="edit">
		<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 編　集</button>
		<a type="button" class="btn btn-danger" id="delete" href="S0025_売上詳細削除確認画面.html">
		<span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 削　除</a>
		<a type="button" class="btn btn-default" id="cancel" href="S0021_売上検索結果一覧画面.html">キャンセル</a>
	</div>
	</td></tr>
	</table>
</form>

</div><!--/container-->


	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>

	</body>
</html>