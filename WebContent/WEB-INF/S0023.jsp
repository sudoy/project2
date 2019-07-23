<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.abc.asms.goods.utils.HTMLUtils" %>
<%@ page import="org.apache.commons.lang3.StringEscapeUtils" %>



<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>売上詳細編集|物品売上管理システム</title>
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/S0023_売上詳細編集画面.css" rel="stylesheet">
	</head>
	<body>

	<jsp:include page="header.jsp">
		<jsp:param name="bar"
		value='<li><a href="C0020.html">ダッシュボード</a></li>
		<li><a href="S0010.html">売上登録</a></li>
		<li class="active"><a href="S0020.html">売上検索<span class="sr-only">(current)</span></a></li>
		<li><a href="S0030.html">アカウント登録</a></li>
		<li><a href="S0040.html">アカウント検索</a></li>'/>
	</jsp:include>

	<div class="container">

	<h1>売上詳細編集</h1>

	<form class="form-horizontal" method="POST" action="S0023.html">

	<jsp:include page="message.jsp"/>

	<input type="hidden" name="id" value="${S0023Form.id}">


	<table>

	<tr><th>販売日 <span class="badge">必須</span></th>
		<td>
		<div class="col-md-3">
		<input type="text" class="form-control" name="saledate" value="${HTMLUtils.formatDate(S0023Form.saledate)}" placeholder="販売日">
		</div>
		</td>
	</tr>

	<tr><th>担当 <span class="badge">必須</span></th>
	<td><div class="col-md-8">
		<select class="form-control" name="name">
			<option disabled <c:if test="${S0023Form.name == null}">selected</c:if>>
			選択してください</option>
			<c:forEach items="${accounts}" var="i">
				<option value ="${i.name}"
				 ${HTMLUtils.judgeStaffSelected(i.name, S0023Form.name)}>${i.name}</option>
			</c:forEach>

		</select></div>
	</td></tr>

	<tr><th>商品カテゴリー <span class="badge">必須</span></th>
	<td><div class="col-md-8">
		<c:forEach items="${categories}" var="i">
			<label class="radio-inline">
			<input type="radio" name="categoryname" value="${i}"
			 ${HTMLUtils.judgeCategorynameChecked(i, S0023Form.categoryname)}> ${i}
			</label>
		</c:forEach></div>
	</td></tr>

	<tr><th>商品名 <span class="badge">必須</span></th>
	<td><div class="col-md-8">
	<input class="form-control" type="text" name="tradename" placeholder="商品" value="${S0023Form.tradename}">
	</div></td></tr>
	<tr><th>単価 <span class="badge">必須</span></th>
	<td><div class="col-md-3">
	<input class="form-control" id="right1" type="text" name="price" placeholder="単価" value="${S0023Form.price}">
	</div></td></tr>
	<tr><th>個数 <span class="badge">必須</span></th>
	<td><div class="col-md-3">
	<input class="form-control"  id="right2" type="text" name="salenumber" placeholder="個数" value="${S0023Form.salenumber}">
	</div></td></tr>
	<tr><th id="remarks">備考</th>
	<td><div class="col-md-8">
	<textarea class="form-control" class="detail" rows="3" name="note" placeholder="備考">${S0023Form.note}</textarea>
	</div></td>
	</tr>


	<tr><th></th>
	<td id="canto">
	<div class="form-group">

		<button type="submit" class="btn btn-primary" id="touroku">
		<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 更　新</button>
		<a class="btn btn-default" class="cancel" href="S0022.html?id=${S0023Form.id}">キャンセル</a>
	</div>
	</td></tr>
	</table>
</form>

</div><!--/container-->

	<script src="js/jquery-3.4.1.min.js"></script>
	<script	src="js/bootstrap.min.js"></script>
</body>
</html>