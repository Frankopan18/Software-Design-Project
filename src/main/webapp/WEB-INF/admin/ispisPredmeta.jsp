<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>E-Dnevnik - Ispis predmeta</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
<link rel="stylesheet" href="/ednevnik/style1.css">
</head>
<body>

	<a href="/ednevnik/servleti/admin"  class="icon-block"><font color="000000">Početna</font></a>
	<c:out value="${sessionScope[\"current.user.mail\"]}"></c:out>
	<a class="logout" href="/ednevnik/servleti/logout" style="float:right">Logout</a><br>
	<p>&nbsp;</p>
	<a href="/ednevnik/servleti/admin/dodajPredmet">Dodaj Predmet</a>
	<table class="table-bordered flex-table number7">
		<tr>
			<th>Naziv</th>
			<th>Školska godina</th>
			<th>Opis</th>
			<th class="rest">Akcije</th>
		</tr>
		<c:forEach items="${predmeti }" var="p">
			<tr>
				<td>${p.naziv }</td>
				<td>${p.skolskaGodina }</td>
				<td>${p.opis }</td>
				<td><a href="/ednevnik/servleti/admin/urediPredmet?id=${p.id }">Uredi</a></td>
				<td><a href="/ednevnik/servleti/admin/predmetProfesor?idPredmet=${p.id }">Dodaj profesora</a></td>
				<td><a href="/ednevnik/servleti/predmet?id=${p.id }">Pregledaj</a></td>
				<td><a href="/ednevnik/servleti/admin/dodajTermin?idPredmet=${p.id }">Dodaj termin</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>