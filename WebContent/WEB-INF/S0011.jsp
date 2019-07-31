<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.abc.asms.goods.utils.HTMLUtils" %>
<%@ page import="org.apache.commons.lang3.StringEscapeUtils" %>
<%@ page import="java.net.*" %>

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
		value='<li><a href="c0020.html">ダッシュボード<span class="sr-only">(current)</span></a></li>
		${HTMLUtils.judgeSale(accounts.authority, "S1")}
		<li><a href="S0020.html">売上検索</a></li>
		${HTMLUtils.judgeAccount(accounts.authority, "S1")}
		<li><a href="S0040.html">アカウント検索</a></li>
		${HTMLUtils.judgeCategory50(accounts.authority, "S1")}
		<li><a href="S0060.html">商品カテゴリー一覧</a></li>
		${HTMLUtils.judgeCategory(accounts.authority, "S1")}
		<li><a href="S0070.html">商品カテゴリー一覧</a></li>'/>

</jsp:include>
<div class="container">

<h1>売上を登録してよろしいですか？</h1>


<form class="form-horizontal" method="POST" action="S0010.html">
	<table>

	<tr><th>販売日</th>
		<td>
		<div class="col-md-3">
		<input type="text" class="form-control" name="saledate" value="${form.saledate}" placeholder="販売日" disabled>
		</div>
		</td>
	</tr>

	<tr><th>担当</th>
	<td><div class="col-md-8">
		<select class="form-control" name="accountid" disabled>
			<option>${StringEscapeUtils.escapeHtml4(form.name)}</option>
		</select></div>
	</td></tr>

	<tr><th>商品カテゴリー</th>
	<td><div class="col-md-8">
		<c:forEach items="${allCategory}" var="i">
			<label class="radio-inline">
			<input type="radio" name="categoryname" value="${StringEscapeUtils.escapeHtml4(i)}"
			${HTMLUtils.judgeCategorynameChecked(StringEscapeUtils.escapeHtml4(form.categoryname), StringEscapeUtils.escapeHtml4(i))} disabled> ${StringEscapeUtils.escapeHtml4(i)}
			</label>
		</c:forEach></div>
	</td></tr>

	<tr><th>商品名</th>
	<td><div class="col-md-8">
	<input class="form-control" type="text" name="tradename" placeholder="${StringEscapeUtils.escapeHtml4(form.tradename)}" disabled>
	</div></td></tr>
	<tr><th>単価</th>
	<td><div class="col-md-3">
	<input class="form-control" id="right1" type="text" name="price" placeholder="${HTMLUtils.formatTotal(form.price)}" disabled>
	</div></td></tr>
	<tr><th>個数</th>
	<td><div class="col-md-3">
	<input class="form-control" id="right2" type="text" name="salenumber" placeholder="${HTMLUtils.formatTotal(form.salenumber)}" disabled>
	</div></td></tr>
	<tr><th>小計</th>
	<td><div class="col-md-3">
	<input class="form-control" id="right3" type="text" placeholder="${HTMLUtils.formatTotal(form.total)}" disabled>
	</div></td></tr>
	<tr><th id="remarks">備考</th>
	<td><div class="col-md-8">
	<textarea class="form-control" id="detail" rows="3" name="note" placeholder="備考" disabled>${StringEscapeUtils.escapeHtml4(form.note)}</textarea>
	</div></td>
	</tr>


	<tr><th></th>
	<td id="canto">
	<div class="form-group">

<!-- キャンセルのときだけ送るため -->
	<input type="hidden" name="name" value="${form.name}">

		<button type="submit" class="btn btn-primary" id="entry">
		<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> O K</button>
		<a class="btn btn-default" href="S0010.html?saledate=${form.saledate}&accountid=${form.name}&categoryname=${form.categoryname}
		&tradename=${URLEncoder.encode(form.tradename,'UTF-8')}&price=${form.price}&salenumber=${form.salenumber}&note=${URLEncoder.encode(form.note,'UTF-8')}&name=${form.name}">キャンセル</a>
	</div>
	</td></tr>
	</table>
</form>

</div><!--/container-->


	<script src="js/jquery-3.4.1.min.js"></script>
	<script	src="js/bootstrap.min.js"></script>

	</body>
</html>