<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta	charset="utf-8">
		<meta	http-equiv="X-UA-Compatible"	content="IE=edge">
		<meta	name="viewport"	content="width=device-width,	initial-scale=1">

		<title>新パスワード入力|物品管理システム</title>
		<link	href="css/bootstrap.min.css"	rel="stylesheet">
		<link href="css/S0046_新パスワード入力.css" rel="stylesheet">
</head>
	<body>
	<div class="container">

	</div>
		<div class="center">
		<h2>物品売上管理システム</h2>
		<jsp:include page="message.jsp"/>
		<h3>新パスワード入力</h3>

		<form action="S0046.html" method="post">


		<div class="form-group">
			<label for="exampleInputPassword1"></label>
			<input type="password" class=" form-control" name="password" value="${S0046Form.password}"  placeholder="新パスワード">
			<label for="exampleInputPassword2"></label>
			<input type="password" class="form-control" name="check" value="${S0046Form.check}" placeholder="新パスワード確認">
		</div>
		<div class="form-group">
			<button type="submit" class="btn btn-primary">変更</button>
		</div>
		</form>


		</div>




	<script src="js/jquery-3.4.1.min.js"></script>
	<script	src="js/bootstrap.min.js"></script>
	</body>

</html>