<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>E-dnevnik - Osobni podaci</title>
<a class="logout" href="/ednevnik/servleti/logout" style="float:right">Logout</a><br>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
<link rel="stylesheet" href="/ednevnik/style1.css">
</head>
<body>
	<a href="javascript:history.back()">Nazad</a>
	<c:out value="${sessionScope[\"current.user.mail\"]}"></c:out>
	<hr>
	<c:choose>
		<c:when test="${empty ucenik.slika }"> <td><img src="/ednevnik/Default-avatar.jpg" height="150" width="150"></img></td></c:when>
		<c:otherwise><td><img src="/ednevnik/servleti/ucenik/slika?mail=${ucenik.mail }" height="150" width="150"></img></td></c:otherwise>
	</c:choose>	<br>
	Ime: ${ucenik.ime }<br>
	Prezime: ${ucenik.prezime }<br>
	OIB: ${ucenik.oib }<br>
	Datum rođenja: ${ucenik.datumRodenja }<br>
	Mjesto rođenja: ${ucenik.mjestoRodenja }<br>
	Spol: ${spol }<br>
	Razred: ${ucenik.razred }<br>
	<br>
	<h2>Roditelji:</h2>
	<c:forEach items="${roditelji }" var="r">
		Ime: ${r.ime }<br>
		Prezime: ${r.prezime }<br>
	</c:forEach>
</body>
</html>