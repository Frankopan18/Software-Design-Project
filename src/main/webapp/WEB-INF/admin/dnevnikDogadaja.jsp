<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>E-Dnevnik - Dnevnik događaja</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
<link rel="stylesheet" href="/ednevnik/style1.css">
</head>
<body>
	<a href="/ednevnik/servleti/admin" class="icon-block">Početna</a>
	<c:out value="${sessionScope[\"current.user.mail\"]}"></c:out>
	<a class="logout" href="/ednevnik/servleti/logout" style="float:right">Logout</a><br>
	<p>&nbsp;</p>
	<h3>Dnevnik događaja</h3>
	<table class="table-bordered flex table">
		<tr>
			<th>Događaj</th>
			<th>Vrijeme</th>
		</tr>
		<c:forEach items="${dogadaji }" var="d">
			<tr>
				<td>${d.poruka }</td>
				<td>${d.vrijeme }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>