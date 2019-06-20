<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>E-Dnevnik - Osobni podaci djeteta</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
<link rel="stylesheet" href="/ednevnik/style1.css">
</head>
<body>

	<a href="/ednevnik/servleti/roditelj" class="icon-block">Nazad</a>
	<c:out value="${sessionScope[\"current.user.mail\"]}"></c:out>
	<a class="logout" href="/ednevnik/servleti/logout" style="float:right">Logout</a>
	<p>&nbsp;</p>
	<h2>Ispis osobnih podataka djeteta</h2>
	<p>&nbsp;</p>
<form class="form">

	<hr>
	Ime: ${dijete.ime }<br>
	Prezime: ${dijete.prezime }<br>
	OIB: ${dijete.oib }<br>
	Datum rođenja: ${dijete.datumRodenja }<br>
	Mjesto rođenja: ${dijete.mjestoRodenja }<br>
	Razred: ${dijete.razred }<br>
	</form>
</body>
</html>