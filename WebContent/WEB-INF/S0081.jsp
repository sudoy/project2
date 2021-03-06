<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.abc.asms.goods.utils.HTMLUtils" %>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>商品カテゴリー登録｜物品売上管理システム</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/S0081_商品カテゴリー詳細編集.css" rel="stylesheet">

</head>
<body>
		<jsp:include page="header.jsp">
		<jsp:param name="bar"
			value='<li><a href="C0020.html">ダッシュボード</a></li>
		${HTMLUtils.judgeSale(accounts.authority, "S8")}
		<li><a href="S0020.html">売上検索</a></li>
		${HTMLUtils.judgeAccount(accounts.authority, "S8")}
		<li><a href="S0040.html">アカウント検索</a></li>
		${HTMLUtils.judgeCategory50(accounts.authority, "S8")}
		<li><a href="S0060.html">商品カテゴリー一覧</a></li>
		${HTMLUtils.judgeCategory(accounts.authority, "S8")}
		<li class="active"><a href="S0080.html">商品カテゴリー一覧</a></li>'/>
	</jsp:include>


<div class="container">

	<h1>商品カテゴリー編集</h1>

		<jsp:include page="message.jsp" />

		<form class="form-horizontal" action="S0081.html" method="post">

 	<input type="hidden" name="categoryid" value="${S0081Form.categoryid}">



		<div class="form-group">
		    <label for="text" class="col-sm-3 control-label">カテゴリー名 <span class="badge badge-default"> 必須 </span></label>
		    <div class="col-sm-5">
		      <input type="text" class="form-control" placeholder="商品カテゴリー" value="${S0081Form.categoryname}" name="categoryname">
		    </div>
		</div>

		<div class="form-group">
			<label  class="col-sm-3 control-label">有効/無効 <span class="badge badge-default"> 必須 </span></label>

			<div class="col-sm-9">
			<label class="radio-inline">
				<input type="radio" name="active" value="1"  <c:if test="${S0081Form.active == '1'}">checked </c:if> checked > 有効
			</label>
			<label class="radio-inline">
				<input type="radio" name="active" value="0"  <c:if test="${S0081Form.active == '0'}">checked </c:if> > 無効
			</label>
			</div>
		</div>

		<div class="form-group">
		    <div class="col-sm-offset-4 col-sm-8">
		      <button type="submit" class="btn btn-primary">
		      	<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 更  新
		      </button>
		       <a type="button" class="btn btn-default" href="S0080.html">キャンセル</a>
		    </div>
		</div>

	</form>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>