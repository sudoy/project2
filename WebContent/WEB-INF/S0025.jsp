<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="com.abc.asms.goods.utils.HTMLUtils" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ page import="com.abc.asms.goods.utils.HTMLUtils" %>

<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>売上詳細削除確認|物品売上管理システム</title>
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/S0025_売上詳細削除確認画面.css" rel="stylesheet">
	</head>
	<body>
<jsp:include page="header.jsp">

<jsp:param name="bar"
		value='<li><a href="C0020.html">ダッシュボード<span class="sr-only">(current)</span></a></li>
		<li><a href="S0010.html">売上登録</a></li>
		<li  class="active"><a href="S0020.html">売上検索</a></li>
		<li><a href="S0030.html">アカウント登録</a></li>
		<li><a href="S0040.html">アカウント検索</a></li>'/>
</jsp:include>


<h1>売上を削除してよろしいですか？</h1>

<div class="container">


<form class="form-horizontal" method="POST" action="S0025.html">
	<table>

	<tr><th>販売日</th>
		<td>
		<div class="col-md-3">
		<input type="text" class="form-control" value="${S0025Form.saledate}" placeholder="販売日" name="saledate" disabled>
		</div>
		</td>
	</tr>

	<tr><th>担当</th>
	<td><div class="col-md-8">
		<select class="form-control"  disabled>
			<option>${name}</option>
		</select></div>
	</td></tr>
	<tr><th>商品カテゴリー</th>
	<td><div class="col-md-7">
		<c:forEach items="${allCategory}" var="i">
			<label class="radio-inline">
			<input type="radio" name="categoryname" value="${i}"
			${HTMLUtils.judgeCategoryChecked2(S0025Form.categoryname, i)} disabled> ${i}
			</label>
		</c:forEach></div>
	</td></tr>

	<tr><th>商品名</th>
	<td><div class="col-md-8">
	<input name="tradename" class="form-control" type="text" placeholder="商品" value="${S0025Form.tradename}" disabled>
	</div></td></tr>

	<tr><th>単価</th>
	<td><div class="col-md-3">
	<input class="form-control" id="right" type="text" placeholder="単価" name="price" value="${HTMLUtils.formatTotal(S0025Form.unitprice)}" disabled>
	</div></td></tr>

	<tr><th>個数</th>
	<td><div class="col-md-3">
	<input class="form-control" id="right" type="text" placeholder="個数" name="salenumber" value="${HTMLUtils.formatTotal(S0025Form.salenumber)}" disabled>
	</div></td></tr>

	<tr><th>小計</th>
	<td><div class="col-md-3">
	<input class="form-control" id="right" value="${HTMLUtils.formatTotal(total)}"  disabled>
	</div></td></tr>

	<tr><th id="remarks">備考</th>
	<td><div class="col-md-8">
	<textarea class="form-control" id="detail" rows="3" placeholder="備考" name="${S0025Form.note}" disabled>今日からの新商品</textarea>
	</div></td>
	</tr>


	<tr><th></th>
	<td id="canto">
	<div class="form-group">

		<button type="submit" class="btn btn-danger" id="touroku" onclick="location.href='index.html'">
		<span class="glyphicon glyphicon-remove" aria-hidden="true"></span> O K</button>
		<a class="btn btn-default" id="cancel" href="S0022.html?id=${S0025Form.id}">キャンセル</a>
	</div>
	</td></tr>
	</table>
</form>

</div><!--/container-->


	<script src="js/jquery-3.4.1.min.js"></script>
	<script	src="js/bootstrap.min.js"></script>

</body>
</html>