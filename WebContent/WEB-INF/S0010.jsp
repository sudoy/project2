<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.apache.commons.lang3.StringEscapeUtils" %>

<%@ page import="com.abc.asms.goods.utils.HTMLUtils" %>

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
		value='<li><a href="C0020.html">ダッシュボード</a></li>
		${HTMLUtils.judgeSale(accounts.authority, "S1")}
		<li><a href="S0020.html">売上検索</a></li>
		${HTMLUtils.judgeAccount(accounts.authority, "S1")}
		<li><a href="S0040.html">アカウント検索</a></li>'/>
</jsp:include>

<div class="container">

<h1>売上登録</h1>

<jsp:include page="message.jsp"/>

<form class="form-horizontal" method="POST" action="S0011.html">
	<table>

	<tr><th>販売日 <span class="badge">必須</span></th>
		<td>
		<div class="col-md-3">
		<input type="text" class="form-control" placeholder="販売日" name="saledate"
		value="<c:if test="${form == null}">${HTMLUtils.formatDate(todayForm.today)}</c:if><c:if test="${form != null}">${form.saledate}</c:if>">
		</div>
		</td>
	</tr>

	<tr><th>担当 <span class="badge">必須</span></th>
	<td><div class="col-md-8">
		<select class="form-control" name="accountid">
			<option value="0">選択してください</option>

			<c:forEach items="${accountsInfo}" var="i">
				<option value ="${i.accountid}"
				 ${HTMLUtils.judgeStaffSelected(StringEscapeUtils.escapeHtml4(i.name), StringEscapeUtils.escapeHtml4(form.name))}>${StringEscapeUtils.escapeHtml4(i.name)}</option>
			</c:forEach>

		</select></div>
	</td></tr>

	<tr><th>商品カテゴリー <span class="badge">必須</span></th>
	<td><div class="col-md-8">
		<c:forEach items="${allCategory}" var="i">
			<label class="radio-inline">
			<input type="radio" name="categoryname" value="${i}"
			 ${HTMLUtils.judgeCategorynameChecked(StringEscapeUtils.escapeHtml4(i), StringEscapeUtils.escapeHtml4(form.categoryname))}> ${StringEscapeUtils.escapeHtml4(i)}
			</label>
		</c:forEach></div>
	</td></tr>

	<tr><th>商品名 <span class="badge">必須</span></th>
	<td><div class="col-md-8">
	<input class="form-control" type="text" placeholder="商品名" name="tradename" value="${StringEscapeUtils.escapeHtml4(form.tradename)}">
	</div></td></tr>
	<tr><th>単価 <span class="badge">必須</span></th>
	<td><div class="col-md-3">
	<input class="form-control" id="right1" type="text" placeholder="単価" name="price" value="${form.price}">
	</div></td></tr>
	<tr><th>個数 <span class="badge">必須</span></th>
	<td><div class="col-md-3">
	<input class="form-control" id="right2" type="text" placeholder="個数" name="salenumber" value="${form.salenumber}">
	</div></td></tr>
	<tr><th id="remarks1">備考</th>
	<td><div class="col-md-8">
	<textarea class="form-control" id="remarks2" rows="3"
	placeholder="備考" name="note" wrap="hard">${StringEscapeUtils.escapeHtml4(form.note)}</textarea>
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


	<script src="js/jquery-3.4.1.min.js"></script>
	<script	src="js/bootstrap.min.js"></script>

	</body>
</html>