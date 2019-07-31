<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>商品カテゴリー編集確認｜物品売上管理システム</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/S0082_商品カテゴリー詳細編集確認.css" rel="stylesheet">
</head>
<body>

	<jsp:include page="header.jsp">
		<jsp:param name="bar"
			value='<li><a href="C0020.html">ダッシュボード</a></li>
		<li><a href="S0010.html">売上登録 <span class="sr-only">(current)</span></a></li>
		<li><a href="S0020.html">売上検索</a></li>
		<li><a href="S0030.html">アカウント登録</a></li>
		<li><a href="S0040.html">アカウント検索</a></li>
		<li><a href="S0050.html">商品カテゴリー登録</a></li>
		<li><a href="S0060.html">商品カテゴリー一覧</a></li>
		${HTMLUtils.judgeCategory(accounts.authority, "S8")}
		<li class="active"><a href="S0080.html">商品カテゴリー一覧</a></li>'/>
	</jsp:include>

	<div class="container">

<h1>商品カテゴリーを編集してよろしいですか？</h1>

	<form class="form-horizontal" action="S0082.html" method="post">

	<input type="hidden" name="categoryid" value="${S0082Form.categoryid}">

		<div class="form-group">
		    <label for="text" class="col-sm-3 control-label">カテゴリー名 </label>
		    <div class="col-sm-5">
		     <fieldset disabled>
		      <input type="text" class="form-control" placeholder="商品カテゴリー" value="${S0082Form.categoryname}" name="categoryname">
		     </fieldset>
		    </div>
		</div>

		<fieldset disabled>
			<div class="form-group">
				<label  class="col-sm-3 control-label">有効/無効 </label>

				<div class="col-sm-9">
				<label class="radio-inline">
					<input type="radio" name="active" value="1" <c:if test="${S0082Form.active == '1'}">checked </c:if> > 有効
				</label>
					<label class="radio-inline">
					<input type="radio" name="active" value="0"  <c:if test="${S0082Form.active == '0'}">checked </c:if> > 無効
				</label>
				</div>
			</div>
		</fieldset>

		<div class="form-group">
		    <div class="col-sm-offset-4 col-sm-8">
		      <button type="submit" class="btn btn-primary">
		      	<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> O K
		      </button>
		      	<a type="button" class="btn btn-default" href="S0081.html">キャンセル</a>
		    </div>
		</div>
	</form>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>