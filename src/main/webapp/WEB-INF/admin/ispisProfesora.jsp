<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>E-Dnevnik - Ispis profesora</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
<link rel="stylesheet" href="/ednevnik/style1.css">
</head>
<body>
	<a href="/ednevnik/servleti/admin" class="icon-block">Početna</a>
	<c:out value="${sessionScope[\"current.user.mail\"]}"></c:out>
	<a class="logout" href="/ednevnik/servleti/logout" style="float:right">Logout</a><br>
	<p>&nbsp;</p>
	<a href="/ednevnik/servleti/admin/dodajProfesora">Dodaj Profesora</a>
	<table class="flex-table same-number">
		<tr>
			<th>Ime</th>
			<th>Prezime</th>
			<th>OIB</th>
			<th>Broj telefona</th>
			<th>Akcija</th>
		</tr>
		<c:forEach items="${profesori }" var="p">
			<tr>
				<td>${p.ime }</td>
				<td>${p.prezime }</td>
				<td>${p.oib }</td>
				<td>${p.brojTelefona }</td>
				<td><a href="/ednevnik/servleti/admin/urediProfesora?mail=${p.mail }">Uredi</a></td>
			</tr>
		</c:forEach>
	</table>
		
</body>
</html>