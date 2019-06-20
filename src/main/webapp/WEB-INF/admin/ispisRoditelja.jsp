<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>E-Dnevnik - Ispis roditelja</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css"></head>
<link rel="stylesheet" href="/ednevnik/style1.css">
<body>
	<a href="/ednevnik/servleti/admin" class="icon-block">Početna</a>
	<c:out value="${sessionScope[\"current.user.mail\"]}"></c:out>
	<a class="logout" href="/ednevnik/servleti/logout" style="float:right">Logout</a><br>
	<p>&nbsp;</p>
	<a href="/ednevnik/servleti/admin/dodajRoditelja">Dodaj Roditelja</a>
	<table class="table-bordered flex-table">
		<tr>
			<th>Ime</th>
			<th>Prezime</th>
			<th>Broj telefona</th>
			<th>Uredi</th>
		</tr>
		<c:forEach items="${roditelji }" var="r">
			<tr>
				<td>${r.ime }</td>
				<td>${r.prezime }</td>
				<td>${r.brojTelefona }</td>
				<td><a href="/ednevnik/servleti/admin/urediRoditelja?mail=${r.mail }">Uredi</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>