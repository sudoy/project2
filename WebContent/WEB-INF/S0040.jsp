<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.abc.asms.goods.utils.HTMLUtils" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>アカウント検索｜物品売上管理システム</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

	<jsp:include page="header.jsp">
		<jsp:param name="bar"
		value='<li><a href="C0020.html">ダッシュボード<span class="sr-only">(current)</span></a></li>
		<li><a href="S0010.html">売上登録</a></li>
		<li><a href="S0020.html">売上検索</a></li>
		<li><a href="S0030.html">アカウント登録</a></li>
		<li class="active"><a href="S0040.html">アカウント検索</a></li>'/>
	</jsp:include>

<div class="container">

<h1>アカウント検索</h1>

<jsp:include page="message.jsp"/>

<form class="form-horizontal" action="S0040.html" method="post">
	<div class="form-group">
	    <label  class="col-sm-3 control-label">氏名 <span class="badge badge-default"> 部分一致 </span></label>
	    <div class="col-sm-5">
	      <input type="text" class="form-control" placeholder="氏名" name="name" value="${S0040Form.name}">
	    </div>
	</div>
	<div class="form-group">
	    <label  class="col-sm-3 control-label">メールアドレス </label>
	    <div class="col-sm-5">
	      <input type="email" class="form-control" placeholder="メールアドレス" name ="mail" value="${S0040Form.mail}">
	    </div>
	</div>

	<div class="form-group">
		<label  class="col-sm-3 control-label">売上登録権限</label>
		<div class="col-sm-9">
		<label class="radio-inline">
			<input type="radio" name="sale" value="all" ${HTMLUtils.judgeSaleAuthority("all", S0040Form.sale)}> 全て
		</label>
		<label class="radio-inline">
			<input type="radio" name="sale" value="0" ${HTMLUtils.judgeSaleAuthority("0", S0040Form.sale)}> 権限なし
		</label>
		<label class="radio-inline">
			<input type="radio"  name="sale" value="1" ${HTMLUtils.judgeSaleAuthority("1", S0040Form.sale)}> 権限あり
		</label>
		</div>
	</div>

	<div class="form-group">
		<label  class="col-sm-3 control-label">アカウント登録権限</label>
		<div class="col-sm-9">
			<label class="radio-inline">
				<input type="radio" name="account" value="all" ${HTMLUtils.judgeAccountAuthority("all", S0040Form.account)}> 全て
			</label>
			<label class="radio-inline">
				<input type="radio" name="account" value="0" ${HTMLUtils.judgeAccountAuthority("0", S0040Form.account)}> 権限なし
			</label>
			<label class="radio-inline">
				<input type="radio" name="account" value="1" ${HTMLUtils.judgeAccountAuthority("1", S0040Form.account)}> 権限あり
			</label>
		</div>
	</div>


	<div class="form-group">
	    <div class="col-sm-offset-4 col-sm-8">
	      <button type="submit" class="btn btn-primary">
	      	<span class="glyphicon glyphicon-search" aria-hidden="true"></span> 検  索
	      </button>
	      <a class="btn btn-default" href="S0040.html?clear=clear">クリア</a>
	    </div>
	</div>
</form>
</div>
<script src="js/jquery-3.4.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</body>
</html>