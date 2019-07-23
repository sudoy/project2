<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${complete != null}">
<div class="alert alert-success alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">
	&times;</span></button>
	<h4><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
	<strong>完了しました！</strong></h4>
	<ul>
	 <li>${complete}</li>
	 </ul>
</div>
</c:if>


<c:if test="${sessionScope.error != null}">
<div class="alert alert-danger alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">
	&times;</span></button>
	<h4><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
	<strong>エラーが発生しました！</strong></h4>
	<ul>
	<c:forEach items="${sessionScope.error}" var="i">
	<li>${i}</li>
	</c:forEach>
	</ul>
</div>
</c:if>