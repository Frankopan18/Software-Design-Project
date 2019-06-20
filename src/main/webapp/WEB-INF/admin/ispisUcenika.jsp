<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
<link rel="stylesheet" href="/ednevnik/style1.css">
</head>
<body>
	<a href="/ednevnik/servleti/admin" class="icon-block">Početna</a>
	<c:out value="${sessionScope[\"current.user.mail\"]}"></c:out>
	<a class="logout" href="/ednevnik/servleti/logout" style="float:right">Logout</a><br>
	<p>&nbsp;</p>
	<a href="/ednevnik/servleti/admin/dodajUcenika">Dodaj Ucenika</a>
	<table class="table-bordered flex-table">
		<tr>
			<th>Ime</th>
			<th>Prezime</th>
			<th>E-Mail</th>
			<th>OIB</th>
			<th>Datum rođenja</th>
			<th>Mjesto rođenja</th>
			<th>Slika</th>
			<th>Uredi</th>
		</tr>
		<c:forEach items="${ucenici }" var="u">
			<tr>
				<td>${u.ime }</td>
				<td>${u.prezime }</td>
				<td>${u.mail }</td>
				<td>${u.oib }</td>
				<td>${u.datumRodenja }</td>
				<td>${u.mjestoRodenja }</td>
				<c:choose>
					<c:when test="${empty u.slika }"> <td><img src="/ednevnik/Default-avatar.jpg" height="70" width="70"></img></td></c:when>
					<c:otherwise><td><img src="/ednevnik/servleti/ucenik/slika?mail=${u.mail }" height="70" width="70"></img></td></c:otherwise>
				</c:choose>	
				<td><a href="/ednevnik/servleti/admin/urediUcenika?mail=${u.mail }">Uredi</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>