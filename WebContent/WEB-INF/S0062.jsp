<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>アカウント編集確認｜物品売上管理システム</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
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
		<a class="navbar-brand" href="#">物品売上管理システム</a>
	</div>

	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav">
			<li><a href="C0020.html">ダッシュボード<span class="sr-only">(current)</span></a></li>
			<li><a href="S0010.html">売上登録</a></li>
			<li><a href="S0020.html">売上検索</a></li>
			<li><a href="S0030.html">アカウント登録</a></li>
			<li><a href="S0040.html">アカウント検索</a></li>
			<li><a href="S0050.html">商品カテゴリー登録</a></li>
			<li class="active"><a href="#">商品カテゴリー一覧</a></li>
		</ul>

		 <ul class="nav navbar-nav navbar-right">
			<li><a href="C0010.html">ログアウト</a></li>
			<li class="dropdown"></li>
		</ul>

	</div><!-- /.navbar-collapse -->
</div><!-- /.container-fluid -->
</nav>
<div class="container">

<h1>商品カテゴリーを編集してよろしいですか？</h1>

<form class="form-horizontal" action="S0062.html" method="post">
	<div class="form-group">
	    <label for="text" class="col-sm-3 control-label">カテゴリー名  <span class="badge badge-default"> 必須 </span></label>
	    <div class="col-sm-5">
	      <input type="text" class="form-control" placeholder="カテゴリー名 " name="name" value="${s0061form.name}" disabled>
	      <input type="hidden" name="name" value="${s0061form.name}">

	    </div>
	</div>

	<div class="form-group">
		<label  class="col-sm-3 control-label">使用可/不可 <span class="badge badge-default"> 必須 </span></label>

		<div class="col-sm-9">
		<label class="radio-inline">
			<input type="radio" <c:if test="${s0061form.flg == '1'}"> checked</c:if> value="1" disabled > 可


		</label>
			<label class="radio-inline">
			<input type="radio" <c:if test="${s0061form.flg == '0'}"> checked</c:if> value="0" disabled > 不可

			<input type="hidden" name="flg1" <c:if test="${s0061form.flg == '1'}">value="1"</c:if>>
			<input type="hidden" name="flg0" <c:if test="${s0061form.flg == '0'}">value="0"</c:if>>

		</label>
		</div>
	</div>


		      <input type="hidden" name="id" value="${id}">


	<div class="form-group">
	    <div class="col-sm-offset-4 col-sm-8">
	      <button type="submit" class="btn btn-primary">
	      	<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> O K
	      </button>
	      	<a type="button" class="btn btn-default" href="S0061.html?name=${s0061form.name}&flg=${s0061form.flg}">キャンセル</a>
	    </div>
	</div>
</form>
</div>


<script src="js/jquery-3.4.1.min.js"></script>
<script	src="js/bootstrap.min.js"></script>
</body>
</html>