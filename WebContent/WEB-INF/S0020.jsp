<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>売上検索条件入力|物品売上管理システム</title>
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/S0020_売上検索条件入力画面.css" rel="stylesheet">
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

<h1>売上検索条件入力</h1>

<div class="container">


<form class="form-horizontal" method="POST" action="S0020.html">
	<table>

	<tr><th>販売日</th>
		<td>
		<div class="col-md-3">
		<input type="text" class="form-control" value="販売日（検索開始日）" placeholder="販売日">
		</div>
		<div class="col-md-2">～</div>
		<div class="col-md-3">
		<input type="text" class="form-control" value="販売日（検索終了日）" placeholder="販売日">
		</div>
		</td>
	</tr>

	<tr><th>担当</th>
	<td><div class="col-md-8">
		<select class="form-control">
			<option>選択してください</option>
			<option>イチロー</option>
			<option>ダルビッシュ</option>
			<option>4</option>
			<option>5</option>
		</select></div>
	</td></tr>
	<tr><th>商品カテゴリー</th>
	<td><div class="col-md-8">
		<select class="form-control">
			<option>選択してください</option>
			<option>食料品</option>
			<option>日用品</option>
			<option>4</option>
			<option>5</option>
		</select></div>
	</td></tr>

	<tr><th>商品名 <span class="badge">部分一致</span></th>
	<td><div class="col-md-8">
	<input class="form-control" type="text" placeholder="商品名">
	</div></td></tr>
	<tr><th id="remarks">備考 <span class="badge">部分一致</span></th>
	<td><div class="col-md-8">
	<textarea class="form-control" id="detail" rows="3" placeholder="備考"></textarea>
	</div></td>
	</tr>


	<tr><th></th>
	<td id="canto">
	<div class="form-group">

		<button type="submit" class="btn btn-primary" id="touroku">
		<span class="glyphicon glyphicon-search" aria-hidden="true"></span> 検　索</button>
		<input type="reset" class="btn btn-default" value="クリア">
	</div>
	</td></tr>
	</table>
</form>

</div><!--/container-->


	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>

	</body>
</html>