<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>売上登録確認|物品売上管理システム</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/S0011_売上登録確認画面.css" rel="stylesheet">
</head>

<body>
<jsp:include page="header.jsp">
<jsp:param name="bar"
		value='<li><a href="C0020.html">ダッシュボード<span class="sr-only">(current)</span></a></li>
		<li  class="active"><a href="S0010.html">売上登録</a></li>
		<li><a href="S0020.html">売上検索</a></li>
		<li><a href="S0030.html">アカウント登録</a></li>
		<li><a href="S0040.html">アカウント検索</a></li>'/>
</jsp:include>
<div class="container">

<h1>売上を登録してよろしいですか？</h1>


<form class="form-horizontal" method="POST" action="S0010.html">
	<table>

	<tr><th>販売日</th>
		<td>
		<div class="col-md-3">
		<input type="text" class="form-control" value="${saleform.saledate}" placeholder="販売日" disabled>
		</div>
		</td>
	</tr>

	<tr><th>担当</th>
	<td><div class="col-md-8">
		<select class="form-control" disabled>
			<option <c:if test="${(saleform.accountid).equals('0')}">selected</c:if>>イチロー</option>
			<option <c:if test="${(saleform.accountid).equals('1')}">selected</c:if>>ダルビッシュ</option>
			<option <c:if test="${(saleform.accountid).equals('2')}">selected</c:if>>4</option>
			<option <c:if test="${(saleform.accountid).equals('3')}">selected</c:if>>5</option>
		</select></div>
	</td></tr>
	<tr><th>商品カテゴリー</th>
	<td><div class="col-md-8">
		<select class="form-control" disabled>
			<option <c:if test="${saleform.categoryid.equals('0')}">selected</c:if>>食料品</option>
			<option <c:if test="${saleform.categoryid.equals('1')}">selected</c:if>>日用品</option>
			<option <c:if test="${saleform.categoryid.equals('2')}">selected</c:if>>4</option>
			<option <c:if test="${saleform.categoryid.equals('3')}">selected</c:if>>5</option>
		</select></div>
	</td></tr>

	<tr><th>商品名</th>
	<td><div class="col-md-8">
	<input class="form-control" type="text" placeholder="${saleform.tradename}" disabled>
	</div></td></tr>
	<tr><th>単価</th>
	<td><div class="col-md-3">
	<input class="form-control" id="right" type="text" placeholder="${saleform.price}" disabled>
	</div></td></tr>
	<tr><th>個数</th>
	<td><div class="col-md-3">
	<input class="form-control" id="right" type="text" placeholder="${saleform.salenumber}" disabled>
	</div></td></tr>
	<tr><th>小計</th>
	<td><div class="col-md-3">
	<input class="form-control" id="right" type="text" placeholder="${total}" disabled>
	</div></td></tr>
	<tr><th id="remarks">備考</th>
	<td><div class="col-md-8">
	<textarea class="form-control" id="detail" rows="3" placeholder="${saleform.note}" disabled></textarea>
	</div></td>
	</tr>


	<tr><th></th>
	<td id="canto">
	<div class="form-group">

		<button type="submit" class="btn btn-primary" id="entry">
		<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> O K</button>
		<a type="button" class="btn btn-default" href="S0010.html">キャンセル</a>
	</div>
	</td></tr>
	</table>
</form>

</div><!--/container-->


	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>

	</body>
</html>