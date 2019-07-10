<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${sessionScope.kousintouroku != null}">
<div class="alert alert-success alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">
	&times;</span></button>
	<h4><strong>完了しました！</strong></h4>
	<ul>
	 <li>${sessionScope.kousintouroku}</li>
	 </ul>
</div>
</c:if>


<c:if test="${sessionScope.error != null}">
<div class="alert alert-danger alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">
	&times;</span></button>
	<h4><strong>エラーが発生しました！</strong></h4>
	<ul>
	<c:forEach items="${sessionScope.error}" var="i">
	<li>${i}</li>
	</c:forEach>
	</ul>
</div>
</c:if>