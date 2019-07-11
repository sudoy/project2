<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>アカウント登録確認｜物品売上管理システム</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="header.jsp">
<jsp:param name="bar"
		value='<li><a href="c0020.html">ダッシュボード<span class="sr-only">(current)</span></a></li>
		<li><a href="S0010.html">売上登録</a></li>
		<li><a href="S0020.html">売上検索</a></li>
		<li class="active"><a href="S0030.html">アカウント登録</a></li>
		<li><a href="S0040.html">アカウント検索</a></li>'/>
</jsp:include>
<div class="container">


<jsp:include page="message.jsp"/>


<h1>アカウントを登録してよろしいですか？</h1>

	<form class="form-horizontal" action="S0030.html" method="post">
	<div class="form-group">
	    <label for="text" class="col-sm-3 control-label">氏名 <span class="badge badge-default"> 必須 </span></label>
	    <div class="col-sm-5">
		    <fieldset disabled>
		    	<input type="text" class="form-control" name="name" value="${form.name}">
		    </fieldset>
	    </div>
	</div>
	<div class="form-group">
	    <label for="inputEmail3" class="col-sm-3 control-label">メールアドレス <span class="badge badge-default"> 必須 </span></label>
	    <div class="col-sm-5">
	      <fieldset disabled>
	    	<input type="text"  class="form-control" name="mail" value="${form.mail}">
	      </fieldset>
	    </div>
	</div>
	<div class="form-group">
	    <label for="inputPassword3" class="col-sm-3 control-label">パスワード <span class="badge badge-default"> 必須 </span></label>
	    <div class="col-sm-5">
	      <fieldset disabled>
	    	<input type="password" class="form-control" name="password" value="${form.password}" >
	      </fieldset>
	    </div>
	</div>
	<div class="form-group">
	    <label for="inputPasswordCheck3" class="col-sm-3 control-label">パスワード(確認) <span class="badge badge-default"> 必須 </span></label>
	    <div class="col-sm-5">
	      <fieldset disabled>
	    	<input type="password" class="form-control" name="password" value="${form.check}">
	      </fieldset>
	    </div>
	</div>

	<fieldset disabled>
	<div class="form-group">
		<label  class="col-sm-3 control-label">売上登録権限 <span class="badge badge-default"> 必須 </span></label>
		<div class="col-sm-9">
		<label class="radio-inline">
			<input type="radio"  name="sale" <c:if test="${form.sale == '0'}">checked</c:if> value="0" > 権限なし
		</label>
			<label class="radio-inline">
			<input type="radio"  name="sale" <c:if test="${form.sale == '1'}">checked</c:if>  value="1" > 権限あり
		</label>
		</div>
	</div>

	<div class="form-group">
		<label  class="col-sm-3 control-label">アカウント登録確認 <span class="badge badge-default">必須</span></label>
		<div class="col-sm-9">
		<label class="radio-inline">
			<input type="radio" name="account" <c:if test="${form.account == '0'}">checked</c:if> value="0" > 権限なし
		</label>
			<label class="radio-inline">
			<input type="radio" name="account" <c:if test="${form.account == '1'}">checked</c:if> value="1" > 権限あり
		</label>
		</div>
	</div>
	</fieldset>

	<div class="form-group">
	    <div class="col-sm-offset-4 col-sm-8">
	      <button type="submit" class="btn btn-primary">
	      	<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> O K
	      </button>
	      <a type="button" class="btn btn-default" href="S0030.html">キャンセル</a>
	    </div>
	</div>
</form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>