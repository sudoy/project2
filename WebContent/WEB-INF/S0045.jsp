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



		<div class="center">
		<h2>物品売上管理システム</h2>
		<jsp:include page="message.jsp"/>
		<h3>パスワード再設定</h3>

		<form action="S0045.html" method="post">

		<div class="form-group">
			<label for="exampleInputEmail1"></label>
			<input type="email" class=" form-control" name="mail" value="${S0045Form.mail}"  placeholder="メールアドレス">

		</div>
		<div class="form-group">
			<button type="submit" class="btn btn-primary" >メール送信</button>
		</div>
		</form>


		</div><!--center-->

	</div>

	<script src="js/jquery-3.4.1.min.js"></script>
	<script	src="js/bootstrap.min.js"></script>
	</body>

</html>