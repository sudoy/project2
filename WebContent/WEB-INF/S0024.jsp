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
		<title>売上詳細編集確認|物品売上管理システム</title>
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/S0024_売上詳細編集確認画面.css" rel="stylesheet">
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

<h1>売上を編集してよろしいですか？</h1>

<div class="container">

<jsp:include page="message.jsp"/>


<form class="form-horizontal" method="POST" action="S0024.html">
	<table>

	<tr><th>販売日</th>
		<td>
		<div class="col-md-3">
		<input type="text" class="form-control" name="saledate" value="${S0023Form.saledate}" placeholder="販売日" disabled>
		</div>
		</td>
	</tr>

	<tr><th>担当</th>
	<td><div class="col-md-8">
		<select class="form-control" disabled>
			<option>${S0023Form.name}</option>
		</select></div>

	</td></tr>

	<tr><th>商品カテゴリー</th>
	<td><div class="col-md-7">
		<c:forEach items="${categories}" var="i">
			<label class="radio-inline">
			<input type="radio" name="categoryname" value="${StringEscapeUtils.escapeHtml4(i)}"
			 ${HTMLUtils.judgeCategorynameChecked(StringEscapeUtils.escapeHtml4(i), StringEscapeUtils.escapeHtml4(S0023Form.categoryname))} disabled> ${StringEscapeUtils.escapeHtml4(i)}
			</label>
		</c:forEach></div>
	</td></tr>

	<tr><th>商品名</th>
	<td><div class="col-md-8">

	<input class="form-control" type="text" name="tradename" placeholder="商品" value="${S0023Form.tradename}" disabled>
	</div></td></tr>
	<tr><th>単価</th>
	<td><div class="col-md-3">
	<input class="form-control"  id="right1" type="text" name="price" placeholder="単価" value="${HTMLUtils.formatTotal(S0023Form.price)}" disabled>
	</div></td></tr>
	<tr><th>個数</th>
	<td><div class="col-md-3">
	<input class="form-control"  id="right2" type="text" name="salenumber" placeholder="個数" value="${HTMLUtils.formatTotal(S0023Form.salenumber)}" disabled>
	</div></td></tr>
	<tr><th>小計</th>
	<td><div class="col-md-3">
	<input class="form-control"  id="right3" type="text" name="total" placeholder="小計" value="${HTMLUtils.formatTotal(S0023Form.total)}" disabled>
	</div></td></tr>
	<tr><th id="remarks">備考</th>
	<td><div class="col-md-8">
	<textarea class="form-control"  id="detail" rows="3" name="note" placeholder="備考" disabled>${S0023Form.note}</textarea>
	</div></td>
	</tr>


	<tr><th></th>
	<td id="canto">
	<div class="form-group">

		<button type="submit" class="btn btn-primary" id="touroku">
		<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> O K</button>
		<a class="btn btn-default" id="cancel" href="S0023.html">キャンセル</a>
	</div>
	</td></tr>
	</table>
</form>

</div><!--/container-->
${key}

	<script src="js/jquery-3.4.1.min.js"></script>
	<script	src="js/bootstrap.min.js"></script>

</body>
</html>