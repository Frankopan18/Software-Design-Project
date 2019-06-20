<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>E-Dnevnik - Ispis razreda</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
<link rel="stylesheet" href="/ednevnik/style1.css">
</head>
<body>
	<a href="/ednevnik/servleti/admin" class="icon-block">Početna</a>
	<c:out value="${sessionScope[\"current.user.mail\"]}"></c:out>
	<a class="logout" href="/ednevnik/servleti/logout" style="float:right">Logout</a><br>
	<p>&nbsp;</p>
	<a href="/ednevnik/servleti/admin/dodajRazred">Dodaj razred</a>
	<table class="flex-table">
		<tr>
			<th>Oznaka</th>
			<th>Školska godina</th>
			<th>Dodaj učenika</th>
			<th>Dodaj razrednika i zamjenika</th>
			<th>Dodaj predmet</th>
		</tr>
		<c:forEach items="${razredi }" var="r">
			<tr>
				<td>${r.oznaka }</td>
				<td>${r.godina }</td>
				<td><a href="/ednevnik/servleti/admin/ucenikRazred?idRazred=${r.id }">Dodaj ucenika</a></td>
				<td><a href="/ednevnik/servleti/admin/razrednikRazred?idRazred=${r.id }">Dodaj razrednika i zamjenika razrednika</a></td>
				<td><a href="/ednevnik/servleti/admin/predmetRazred?idRazred=${r.id }">Dodaj predmet</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>