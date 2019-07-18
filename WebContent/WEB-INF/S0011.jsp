<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.abc.asms.goods.utils.HTMLUtils" %>

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
		<input type="text" class="form-control" value="${form.saledate}" placeholder="販売日" disabled>
		</div>
		</td>
	</tr>

	<tr><th>担当</th>
	<td><div class="col-md-8">
		<select class="form-control" name="accountid" disabled>
			<option>${form.name}</option>
		</select></div>
	</td></tr>

	<tr><th>商品カテゴリー</th>
	<td><div class="col-md-8">
		<c:forEach items="${allCategory}" var="i">
			<label class="radio-inline">
			<input type="radio" name="categoryname" value="${i}"
			${HTMLUtils.judgeCategoryChecked2(form.categoryname, i)} disabled> ${i}
			</label>
		</c:forEach></div>
	</td></tr>

	<tr><th>商品名</th>
	<td><div class="col-md-8">
	<input class="form-control" type="text" placeholder="${form.tradename}" disabled>
	</div></td></tr>
	<tr><th>単価</th>
	<td><div class="col-md-3">
	<input class="form-control" id="right" type="text" placeholder="${form.price}" disabled>
	</div></td></tr>
	<tr><th>個数</th>
	<td><div class="col-md-3">
	<input class="form-control" id="right" type="text" placeholder="${form.salenumber}" disabled>
	</div></td></tr>
	<tr><th>小計</th>
	<td><div class="col-md-3">
	<input class="form-control" id="right" type="text" placeholder="${total}" disabled>
	</div></td></tr>
	<tr><th id="remarks">備考</th>
	<td><div class="col-md-8">
	<textarea class="form-control" id="detail" rows="3" name="note" disabled>${form.note}</textarea>
	</div></td>
	</tr>


	<tr><th></th>
	<td id="canto">
	<div class="form-group">

		<button type="submit" class="btn btn-primary" id="entry">
		<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> O K</button>
		<a class="btn btn-default" href="S0010.html">キャンセル</a>
	</div>
	</td></tr>
	</table>
</form>

</div><!--/container-->


	<script src="js/jquery-3.4.1.min.js"></script>
	<script	src="js/bootstrap.min.js"></script>

	</body>
</html>