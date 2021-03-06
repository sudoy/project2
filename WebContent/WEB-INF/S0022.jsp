<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.abc.asms.goods.utils.HTMLUtils" %>
<%@ page import="org.apache.commons.lang3.StringEscapeUtils" %>
<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>売上詳細表示|物品管理システム</title>
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/S0022_売上詳細表示画面.css" rel="stylesheet">
	</head>

<body>
<jsp:include page="header.jsp">
<jsp:param name="bar"
		value='<li><a href="C0020.html">ダッシュボード</span></a></li>
		${HTMLUtils.judgeSale(accounts.authority, "S2")}
		<li class="active"><a href="S0020.html">売上検索<span class="sr-only">(current)</span></a></li>
		${HTMLUtils.judgeAccount(accounts.authority, "S2")}
		<li><a href="S0040.html">アカウント検索</a></li>
		${HTMLUtils.judgeCategory50(accounts.authority, "S2")}
		<li><a href="S0060.html">商品カテゴリー一覧</a></li>
		${HTMLUtils.judgeCategory(accounts.authority, "S2")}
		<li><a href="S0080.html">商品カテゴリー一覧</a></li>'/>

</jsp:include>

<h1>売上詳細表示</h1>

<div class="container">


<form class="form-horizontal" method="POST" action="S0023.html">
	<table>

	<tr><th>販売日</th>
		<td>
		<div class="col-md-3">
		<p>${HTMLUtils.formatDate(S0022Form.saleDate)}</p>
		</div>
		</td>
	</tr>

	<tr><th>担当</th>
	<td><div class="col-md-8"><p>${StringEscapeUtils.escapeHtml4(S0022Form.name)}</P></div>
	</td></tr>
	<tr><th>商品カテゴリー</th>
	<td><div class="col-md-8"><p>${StringEscapeUtils.escapeHtml4(S0022Form.categoryName)}</p></div>
	</td></tr>

	<tr><th>商品名</th>
	<td><div class="col-md-8"><p>${StringEscapeUtils.escapeHtml4(S0022Form.tradeName)}</p>
	</div></td></tr>
	<tr><th>単価</th>
	<td><div class="col-md-3"><p id="right1">${S0022Form.unitPrice}</p>
	</div></td></tr>
	<tr><th>個数</th>
	<td><div class="col-md-3"><p id="right2">${S0022Form.saleNumber}</p>
	</div></td></tr>
	<tr><th>小計</th>
	<td><div class="col-md-3"><p id="right3">${S0022Form.total}</p>
	</div></td></tr>
	<tr><th id="remarks">備考</th>



	<td><div><pre>${StringEscapeUtils.escapeHtml4(S0022Form.note)}</pre></div></td>
	</tr>


	<tr><th></th>
	<td id="canto">
	<div class="form-group">
		<c:if test="${(accounts.authority == '1') || (accounts.authority == '11')}">
			<a class="btn btn-primary" id="edit" href="S0023.html?id=${S0022Form.saleId}">
			<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 編　集</a>
			<a class="btn btn-danger" id="delete" href="S0025.html?id=${S0022Form.saleId}">
			<span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 削　除</a>
		</c:if>
		<a class="btn btn-default" id="cancel" href="S0021.html">キャンセル</a>
	</div>
	</td></tr>
	</table>
</form>

</div><!--/container-->


	<script src="js/jquery-3.4.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>

	</body>
</html>