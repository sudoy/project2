<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>売上登録|物品売上管理システム</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/S0010_売上登録画面.css" rel="stylesheet">
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

<jsp:include page="message.jsp"/>

<h1>売上登録</h1>

<form class="form-horizontal" method="POST" action="S0011.html">
	<table>

	<tr><th>販売日 <span class="badge">必須</span></th>
		<td>
		<div class="col-md-3">
		<input type="text" class="form-control" placeholder="販売日" name="saledate"  value="${saleform.saledate}">
		</div>
		</td>
	</tr>

	<tr><th>担当 <span class="badge">必須</span></th>
	<td><div class="col-md-8">
		<select class="form-control" name="accountid">
			<option >選択してください</option>
			<option value ="0" <c:if test="${(saleform.accountid).equals('0')}">selected</c:if>>イチロー</option>
			<option value ="1" <c:if test="${(saleform.accountid).equals('1')}">selected</c:if>>ダルビッシュ</option>
			<option value ="2" <c:if test="${(saleform.accountid).equals('2')}">selected</c:if>>4</option>
			<option value ="3" <c:if test="${(saleform.accountid).equals('3')}">selected</c:if>>5</option>
		</select></div>
	</td></tr>
	<tr><th>商品カテゴリー <span class="badge">必須</span></th>
	<td><div class="col-md-8">
		<select class="form-control" name="categoryid">
			<option>選択してください</option>
			<option value ="0" <c:if test="${saleform.categoryid.equals('0')}">selected</c:if>>食料品</option>
			<option value ="1" <c:if test="${saleform.categoryid.equals('1')}">selected</c:if>>日用品</option>
			<option value ="2" <c:if test="${saleform.categoryid.equals('2')}">selected</c:if>>4</option>
			<option value ="3" <c:if test="${saleform.categoryid.equals('3')}">selected</c:if>>5</option>
		</select></div>
	</td></tr>

	<tr><th>商品名 <span class="badge">必須</span></th>
	<td><div class="col-md-8">
	<input class="form-control" type="text" placeholder="商品名" name="tradename" value="${saleform.tradename}">
	</div></td></tr>
	<tr><th>単価 <span class="badge">必須</span></th>
	<td><div class="col-md-3">
	<input class="form-control" id="right" type="text" placeholder="単価" name="price" value="${saleform.price}">
	</div></td></tr>
	<tr><th>個数 <span class="badge">必須</span></th>
	<td><div class="col-md-3">
	<input class="form-control" id="right" type="text" placeholder="個数" name="salenumber" value="${saleform.salenumber}">
	</div></td></tr>
	<tr><th id="remarks">備考</th>
	<td><div class="col-md-8">
	<textarea class="form-control" id="remarks" rows="3" placeholder="備考" name="note" value="${saleform.note}"></textarea>
	</div></td>
	</tr>


	<tr><th></th>
	<td id="canto">
	<div class="form-group">

		<button type="submit" class="btn btn-primary" id="entry">
		<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 登　録</button>
	</div>
	</td></tr>
	</table>
</form>

</div><!--/container-->


	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>

	</body>
</html>