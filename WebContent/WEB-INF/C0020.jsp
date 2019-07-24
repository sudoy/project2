<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.abc.asms.goods.utils.HTMLUtils" %>
<!DOCTYPE html>
<html>

<head>
	<meta	charset="utf-8">
		<meta	http-equiv="X-UA-Compatible"	content="IE=edge">
		<meta	name="viewport"	content="width=device-width,	initial-scale=1">

		<title>ダッシュボード|物品売上管理システム</title>
		<link	href="css/bootstrap.min.css"	rel="stylesheet">
		<link href="css/C0020_ダッシュボード.css" rel="stylesheet">
</head>

	<body>

	<jsp:include page="header.jsp">
		<jsp:param name="bar" value='<li class="active"><a href="C0020.html">ダッシュボード<span class="sr-only">(current)</span></a></li>

		${HTMLUtils.judgeSale(accounts.authority, "C2")}
		<li><a href="S0020.html">売上検索</a></li>
		${HTMLUtils.judgeAccount(accounts.authority, "C2")}
		<li><a href="S0040.html">アカウント検索</a></li>'/>
	</jsp:include>

	<div class="container">

	<h1>ダッシュボード</h1>

	<jsp:include page="message.jsp"/>

<div class="row">
	<div class="col-md-4" id="hidari">
	<nav aria-label="Page navigation">
		<ul class="pagination">
			<li>
				<a href="C0020.html?value=-y1" aria-label="Previous">
					<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>前年
				</a>
			</li>
			<li>
				<a href="C0020.html?value=-M1" aria-label="Next">
					<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>前月
				</a>
			</li>
		</ul>
	</nav>
	</div>
	<div class="col-md-4" id="mannaka"><h3>${variousForm.yearMonth}</h3></div>
	<div class="col-md-4" id="migi">
	<nav aria-label="Page navigation">
		<ul class="pagination">
			<li>
				<a href="C0020.html?value=M1" aria-label="Previous">
					翌月<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				</a>
			</li>
			<li>
				<a href="C0020.html?value=y1" aria-label="Next">
					翌年<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				</a>
			</li>
		</ul>
	</nav>
	</div>
	</div>

	<div class="center">

	<div class="row">
	<div class="col-md-4">
	<div class="panel panel-default" id="panel1">
		<div class="panel-heading">
			<h2 class="panel-title">
			 前月(${variousForm.lastM})の売上合計</h2>
		</div>
		<div class="panel-body">
			${HTMLUtils.formatTotal(variousForm.lastMonthTotal)}円
		</div>
	</div>
	</div>

	<div class="col-md-4">
	<div class="panel panel-default" id="panel2">
		<div class="panel-heading">
			<h2 class="panel-title">
			今月(${variousForm.thisM})の売上合計</h2>
		</div>
		<div class="panel-body">
			${HTMLUtils.formatTotal(variousForm.thisMonthTotal)}円
		</div>
	</div>
	</div>

	<div class="col-md-4">
	<div class="panel panel-default" id="panel3">
		<div class="panel-heading">
			<h2 class="panel-title">
			前月比</h2>
		</div>
		<div class="panel-body">

		<c:if test="${Double.parseDouble(variousForm.ratio) < 100}">
			<p id="down">
				<span class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span>
				${variousForm.ratio}%
			</p>
		</c:if>
		<c:if test="${100 <= Double.parseDouble(variousForm.ratio)}">
			<p id="up">
				<span class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span>
				${variousForm.ratio}%
			</p>
		</c:if>

		</div>
	</div>
	</div>


	</div>
	</div><!--center-->

	</div><!--comtainer-->

	<div class="container">

		<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading"><h2>今月の${accounts.name}さんの売上</h2></div>

		<table class="table">
		<tr><th>No</th><th>販売日</th><th>商品カテゴリー</th><th>商品名</th><th>単価</th><th>個数</th><th>小計</th></tr>
		<c:forEach items="${C0020Form}" var="i">
			<tr><td class="right">${i.saleId}</td><td>${HTMLUtils.formatDate(i.saleDate)}</td><td>${i.categoryName}</td>
			<td>${i.tradeName}</td><td class="right">${HTMLUtils.formatTotal(i.unitPrice)}</td>
			<td class="right">${HTMLUtils.formatTotal(i.saleNumber)}</td><td class="right">${HTMLUtils.formatTotal(i.total)}</td></tr>
		</c:forEach>
		<tr><td colspan="5"></td><td class="right">合計</td><td class="right">${HTMLUtils.formatTotal(variousForm.individualTotal)}</td></tr>

		</table>
		</div>

	</div><!--container-->


	<script src="js/jquery-3.4.1.min.js"></script>
	<script	src="js/bootstrap.min.js"></script>
	</body>