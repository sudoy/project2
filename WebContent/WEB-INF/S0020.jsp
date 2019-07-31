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
		<title>売上検索条件入力|物品売上管理システム</title>
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/S0020_売上検索条件入力画面.css" rel="stylesheet">
	</head>

<body>
<jsp:include page="header.jsp">
<jsp:param name="bar"
		value='<li><a href="C0020.html">ダッシュボード</a></li>
		${HTMLUtils.judgeSale(accounts.authority, "S2")}
		<li class="active"><a href="S0020.html">売上検索<span class="sr-only">(current)</span></a></li>
		${HTMLUtils.judgeAccount(accounts.authority, "S2")}
		<li><a href="S0040.html">アカウント検索</a></li>
		${HTMLUtils.judgeCategory50(accounts.authority, "S2")}
		<li><a href="S0060.html">商品カテゴリー一覧</a></li>
		${HTMLUtils.judgeCategory(accounts.authority, "S2")}
		<li><a href="S0070.html">商品カテゴリー一覧</a></li>'/>

</jsp:include>

<h1>売上検索条件入力</h1>

<div class="container">

	<jsp:include page="message.jsp"/>

<form class="form-horizontal" method="POST" action="S0020.html">
	<table>

	<tr><th>販売日</th>
		<td>
		<div class="col-md-3">
		<input type="text" class="form-control" name="dateBegin" value="${HTMLUtils.S0020today(S0020Form)}${S0020Form.dateBegin}" placeholder="販売日(検索開始日)">
		</div>
		<div class="col-md-2">～</div>
		<div class="col-md-3">
		<input type="text" class="form-control" name="dateEnd" value="${HTMLUtils.S0020today(S0020Form)}${S0020Form.dateEnd}" placeholder="販売日(検索終了日)">
		</div>
		</td>
	</tr>

	<tr><th>担当</th>
	<td><div class="col-md-8">
		<select name="name" class="form-control">
			<option disabled <c:if test="${S0020Form.name == null}">selected</c:if>>
			選択してください</option>
			<c:forEach items="${allName}" var="i">
				<option value="${StringEscapeUtils.escapeHtml4(i.accountName)}" ${HTMLUtils.judgeStaffSelected(i.accountName, StringEscapeUtils.escapeHtml4(S0020Form.name))}>
				${StringEscapeUtils.escapeHtml4(i.accountName)}</option>
			</c:forEach>
		</select></div>
	</td></tr>
	<tr><th>商品カテゴリー</th>
	<td><div class="col-md-8">
		<c:forEach items="${allCategory}" var="i">
			<label class="checkbox-inline">
			<input type="checkbox" name="categoryName" value="${i}"
			 ${HTMLUtils.judgeCategoryChecked(i, S0020Form.cateName)}${HTMLUtils.judgeDefault(S0020Form)}> ${i}
			</label>
		</c:forEach>
	</div>
	</td></tr>

	<tr><th>商品名 <span class="badge">部分一致</span></th>
	<td><div class="col-md-8">
	<input class="form-control" type="text" placeholder="商品名" name="tradeName" value="${S0020Form.tradeName}">
	</div></td></tr>
	<tr><th id="remarks">備考 <span class="badge">部分一致</span></th>
	<td><div class="col-md-8">
	<textarea class="form-control" id="detail" rows="3" placeholder="備考" name="note">${S0020Form.note}</textarea>
	</div></td>
	</tr>


	<tr><th></th>
	<td id="canto">
	<div class="form-group">

		<button type="submit" class="btn btn-primary">
		<span class="glyphicon glyphicon-search" aria-hidden="true"></span> 検　索</button>
		<a class="btn btn-default" href="S0020.html?clear=clear">クリア</a>
	</div>
	</td></tr>
	</table>
</form>

</div><!--/container-->


	<script src="js/jquery-3.4.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>

	</body>
</html>