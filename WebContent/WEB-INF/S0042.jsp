<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>アカウント編集｜物品売上管理システム</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

	<jsp:include page="header.jsp">
		<jsp:param name="bar"
		value='<li><a href="C0020_ダッシュボード.html">ダッシュボード<span class="sr-only">(current)</span></a></li>
		<li><a href="S0010.html">売上登録</a></li>
		<li><a href="S0020.html">売上検索</a></li>
		<li><a href="S0030.html">アカウント登録</a></li>
		<li class="active"><a href="S0040.html">アカウント検索</a></li>'/>
	</jsp:include>
	<div class="container">


	<jsp:include page="message.jsp"/>



<h1>アカウント編集</h1>

<form class="form-horizontal" action="S0042.html" method="post">

	<input type="hidden"  name="id" value="${form.id}" >
	<input type="hidden"  name="authority" value="${form.authority}" >

	<div class="form-group">
	    <label for="text" class="col-sm-3 control-label">氏名 <span class="badge badge-default"> 必須 </span></label>
	    <div class="col-sm-5">
	      <input type="text" class="form-control" placeholder="氏名" name="name" value="${form.name}">
	    </div>
	</div>
	<div class="form-group">
	    <label for="inputEmail3" class="col-sm-3 control-label">メールアドレス <span class="badge badge-default"> 必須 </span></label>
	    <div class="col-sm-5">
	      <input type="email" class="form-control" placeholder="メールアドレス" name ="mail" value="${form.mail}">
	    </div>
	</div>
	<div class="form-group">
	    <label for="inputPassword3" class="col-sm-3 control-label">パスワード</label>
	    <div class="col-sm-5">
	      <input type="password" class="form-control"  name="password" value="">
	    </div>
	</div>
	<div class="form-group">
	    <label for="inputPasswordCheck3" class="col-sm-3 control-label">パスワード(確認)</label>
	    <div class="col-sm-5">
	      <input type="password" class="form-control" name="check" value="${check}">
	    </div>
	</div>
	<div class="form-group">
		<label  class="col-sm-3 control-label">売上登録権限 <span class="badge badge-default"> 必須 </span></label>


		<div class="col-sm-9">
		<label class="radio-inline">
			<input type="radio" name="sale" value="0"
			<c:if test="${form.authority.equals('0') || form.authority.equals('10') }">checked</c:if>
			  > 権限なし
		</label>
			<label class="radio-inline">
			<input type="radio" name="sale" value="1"
			<c:if test="${form.authority.equals('1') || form.authority.equals('11') }">checked</c:if>
			 > 権限あり
		</label>
		</div>
	</div>

	<div class="form-group">
		<label  class="col-sm-3 control-label">アカウント登録期限 <span class="badge badge-default">必須</span></label>


		<div class="col-sm-9">
			<label class="radio-inline">
				<input type="radio" name="account" value="0"
				<c:if test="${form.authority.equals('0') || form.authority.equals('1') }">checked</c:if>
				>権限なし
			</label>
				<label class="radio-inline">
				<input type="radio" name="account" value="1"
				<c:if test="${form.authority.equals('10') || form.authority.equals('11') }">checked</c:if>
				 >権限あり
			</label>
		</div>
	</div>

	<div class="form-group">
	   <div class="col-sm-offset-4 col-sm-8">
	     <button type="submit" class="btn btn-primary">
	     	<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 更 新
	     </button>

	     <a type="button" class="btn btn-default" href="S0041.html">キャンセル</a>
	   </div>
	</div>
</form>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</body>
</html>