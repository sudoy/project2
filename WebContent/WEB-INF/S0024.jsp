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

	<jsp:include page="header.jsp">
		<jsp:param name="bar"
		value='<li><a href="C0020_ダッシュボード.html">ダッシュボード<span class="sr-only">(current)</span></a></li>
		<li><a href="S0010.html">売上登録</a></li>
		<li class="active"><a href="S0020.html">売上検索</a></li>
		<li><a href="S0030.html">アカウント登録</a></li>
		<li><a href="S0040.html">アカウント検索</a></li>'/>
		</jsp:include>

	<div class="container">

	<h1>売上を編集してよろしいですか？</h1>



		<form class="form-horizontal" method="POST" action="S0021.html">
			<table>

				<tr>
					<th>販売日</th>
					<td>
						<div class="col-md-3">
							<input type="text" class="form-control" value="2015/01/15"
								placeholder="販売日" disabled>
						</div>
					</td>
				</tr>

				<tr>
					<th>担当</th>
					<td><div class="col-md-8">
							<select class="form-control" disabled>
								<option>イチロー</option>
								<option>イチロー</option>
								<option>ダルビッシュ</option>
								<option>4</option>
								<option>5</option>
							</select>
						</div></td>
				</tr>
				<tr>
					<th>商品カテゴリー</th>
					<td><div class="col-md-8">
							<label class="radio-inline"> <input type="radio"
								name="inlineRadioOptions" value="1" disabled checked>
								食料品
							</label> <label class="radio-inline"> <input type="radio"
								name="inlineRadioOptions" value="2" disabled> 飲料
							</label> <label class="radio-inline"> <input type="radio"
								name="inlineRadioOptions" value="3" disabled> 雑誌
							</label> <label class="radio-inline"> <input type="radio"
								name="inlineRadioOptions" value="9" disabled> 酒類
							</label> <label class="radio-inline"> <input type="radio"
								name="inlineRadioOptions" value="6" disabled> 冷凍食品
							</label> <label class="radio-inline"> <input type="radio"
								name="inlineRadioOptions" value="10" disabled> たばこ
							</label><br> <label class="radio-inline"> <input
								type="radio" name="inlineRadioOptions" value="5" disabled>
								その他
							</label>
						</div></td>
				</tr>

				<tr>
					<th>商品名</th>
					<td><div class="col-md-8">
							<input class="form-control" type="text" placeholder="商品"
								value="からあげ弁当" disabled>
						</div></td>
				</tr>
				<tr>
					<th>単価</th>
					<td><div class="col-md-3">
							<input class="form-control" id="right" type="text"
								placeholder="単価" value="450" disabled>
						</div></td>
				</tr>
				<tr>
					<th>個数</th>
					<td><div class="col-md-3">
							<input class="form-control" id="right" type="text"
								placeholder="個数" value="3" disabled>
						</div></td>
				</tr>
				<tr>
					<th>小計</th>
					<td><div class="col-md-3">
							<p disabled>1,350</p>
						</div></td>
				</tr>
				<tr>
					<th id="remarks">備考</th>
					<td><div class="col-md-8">
							<textarea class="form-control" id="detail" rows="3"
								placeholder="備考" disabled>今日からの新商品</textarea>
						</div></td>
				</tr>


				<tr>
					<th></th>
					<td id="canto">
						<div class="form-group">

							<button type="submit" class="btn btn-primary" id="touroku">
								<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
								O K
							</button>
							<a type="button" class="btn btn-default" id="cancel"
								href="S0023.html">キャンセル</a>
						</div>
					</td>
				</tr>
			</table>
		</form>

	</div>
	<!--/container-->


	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>

</body>
</html>