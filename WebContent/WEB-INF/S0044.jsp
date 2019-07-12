<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>アカウント削除確認｜物品売上管理システム</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

	<jsp:include page="header.jsp">
		<jsp:param name="bar"
		value='<li><a href="C0020.html">ダッシュボード</a></li>
		<li><a href="S0010.html">売上登録</a></li>
		<li><a href="S0020.html">売上検索</a></li>
		<li><a href="S0030.html">アカウント登録</a></li>
		<li class="active"><a href="S0040.html">アカウント検索<span class="sr-only">(current)</span></a></li>'/>
	</jsp:include>

	<div class="container">

		<h1>アカウントを削除してよろしいですか？</h1>
		<form class="form-horizontal" action="S0044.html" method="post">


			<div class="form-group">
				<label for="text" class="col-sm-3 control-label">氏名</label>
				<div class="col-sm-5">
					<fieldset disabled>
						<input type="text" class="form-control" value="${S0044Form.name}" name="name">
					</fieldset>
				</div>
			</div>
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-3 control-label">メールアドレス</label>
				<div class="col-sm-5">
					<fieldset disabled>
						<input type="text" class="form-control" value="${S0044Form.mail}"
							name="mail">
					</fieldset>
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-3 control-label">パスワード</label>
				<div class="col-sm-5">
					<fieldset disabled>
						<input type="password" class="form-control" name="password">
					</fieldset>
				</div>
			</div>
			<div class="form-group">
				<label for="inputPasswordCheck3" class="col-sm-3 control-label">パスワード(確認)</label>
				<div class="col-sm-5">
					<fieldset disabled>
						<input type="password" class="form-control" name="check">
					</fieldset>
				</div>
			</div>

			<fieldset disabled>
				<div class="form-group">
					<label class="col-sm-3 control-label">売上登録権限</label>
					<div class="col-sm-9">
						<label class="radio-inline"> <input type="radio"
							name="sales" value="0"
							<c:if test="${S0044Form.authority.equals('0') || S0044Form.authority.equals('10') }">checked</c:if>
							 > 権限なし
						</label> <label class="radio-inline"> <input type="radio"
							name="sales" value="1"
							<c:if test="${S0044Form.authority.equals('1') || S0044Form.authority.equals('11') }">checked</c:if>
							> 権限あり
						</label>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-3 control-label">アカウント登録確認</label>
					<div class="col-sm-9">
						<label class="radio-inline"> <input type="radio"
							name="account" value="0"
							<c:if test="${S0044Form.authority.equals('0') || S0044Form.authority.equals('1') }">checked</c:if>
							 > 権限なし
						</label> <label class="radio-inline"> <input type="radio"
							name="account" value="1"
							<c:if test="${S0044Form.authority.equals('10') || S0044Form.authority.equals('11') }">checked</c:if>
							> 権限あり
						</label>
					</div>
				</div>
			</fieldset>


			<div class="form-group">
				<div class="col-sm-offset-4 col-sm-8">
					<button type="submit" class="btn btn-danger">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
						O K
					</button>
					<a type="button" class="btn btn-default" href="S0041.html">キャンセル</a>
				</div>
			</div>
		</form>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>