<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<meta	charset="utf-8">
		<meta	http-equiv="X-UA-Compatible" content="IE=edge">
		<meta	name="viewport"	content="width=device-width,	initial-scale=1">

		<title>パスワード再設定|物品管理システム</title>
		<link	href="css/bootstrap.min.css"	rel="stylesheet">
		<link href="css/S0045_パスワード再設定.css" rel="stylesheet">
</head>
	<body>
	<div class="container" id="color">
		<jsp:include page="message.jsp"/>


		<div class="center">
		<h2>物品売上管理システム</h2>
		<h3>パスワード再設定</h3>

		<form action="S0045.html" method="post">

		<div class="form-group">
			<label for="exampleInputEmail1"></label>
			<input type="email" class=" form-control" name="mail" value=""  placeholder="メールアドレス">

		</div>
		<div class="form-group">
			<button type="submit" class="btn btn-primary" >メール送信</button>
		</div>
		</form>


		</div><!--center-->
	</div>



	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script	src="js/bootstrap.min.js"></script>
	</body>

</html>