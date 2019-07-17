<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta	charset="utf-8">
		<meta	http-equiv="X-UA-Compatible"	content="IE=edge">
		<meta	name="viewport"	content="width=device-width,	initial-scale=1">

		<title>ログイン画面|物品管理システム</title>
		<link	href="css/bootstrap.min.css"	rel="stylesheet">
		<link href="css/C0010_ログイン画面.css" rel="stylesheet">
</head>
	<body>

		<div class="center">
		<h2>物品売上管理システム</h2>

		<jsp:include page="message.jsp"/>

		<form action="C0010.html" method="post">

		<div class="form-group">
			<label for="exampleInputEmail1"></label>
			<input type="email" class=" form-control" name="mail" placeholder="メールアドレス" value="${C0010Form.mail}">
			<label for="exampleInputPassword1"></label>
			<input type="password" class="form-control" name="password" placeholder="パスワード">
		</div>
		<div class="form-group">
		<button type="submit" class="btn btn-primary">ログイン</button>
		</div>
		</form>
		<a href="S0045.html">パスワードを忘れた方はこちら</a>


		</div>

	<script src="js/jquery-3.4.1.min.js"></script>
	<script	src="js/bootstrap.min.js"></script>
	</body>

		</html>