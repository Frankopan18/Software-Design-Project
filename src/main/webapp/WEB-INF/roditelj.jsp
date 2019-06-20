<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
	<title>E-Dnevnik - Roditelj</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
<link rel="stylesheet" href="/ednevnik/style1.css">
</head>
<body>
	<c:out value = "${sessionScope[\"current.user.mail\"]}"></c:out>
	<a class="logout" href="/ednevnik/servleti/logout" style="float:right">Logout</a>
<br>
	<p>&nbsp;</p>
	<h1>Obavijesti</h1>
	<c:forEach items="${obavijesti}" var="o">
		<p>
		<h3>${o.naslov }</h3>
		<br>${o.tekst }<br>
		<p align="center"><c:if test="${not empty o.slika }"><img src="/ednevnik/servleti/obavijestSlikaServlet?id=${o.id }" height="500" width="500"></img></c:if></p>
		<p align="right">Stvorena: ${o.datumStvaranja }</p>
		</p>
	</c:forEach>

	<p>&nbsp;</p>
	<table class="table-bordered flex table">
		<tr>
			<th>Ime djeteta</th>
			<th>Prezime djeteta</th>
			<th>Termin konzultacija</th>
			<th>Termin roditeljskog sastanka</th>
			<th>Avatar djeteta</th>
			<th>Ispis predmeta</th>
			<th>Ispis izostanka</th>
			<th>Ispis osobnih podataka</th>
			<th>Ispis kontakt. podataka profesora</th>
		</tr>
		<c:forEach items="${roditelj.djeca }" var="d">
			<tr>
				<td>${d.ime }</td>
				<td>${d.prezime }</td>
				<td>${d.razred.razrednik.konzultacije }</td>
				<td>${d.razred.roditeljskiSastanak }</td>
				<c:choose>
				<c:when test="${empty d.slika }"> <td><img src="/ednevnik/Default-avatar.jpg" height="70" width="70"></img></td></c:when>
				<c:otherwise><td><img src="/ednevnik/servleti/ucenik/slika?mail=${d.mail }" height="70" width="70"></img></td></c:otherwise>
				</c:choose>	
				<td><a href="/ednevnik/servleti/roditelj/ispisPredemetaDjeteta?mail=${d.mail }" style="text-decoration: none;">Ispis predmeta &nbsp;&nbsp</a></td>
				<td><a href="/ednevnik/servleti/roditelj/ispisIzostankaDjeteta?mail=${d.mail }" style="text-decoration: none;">Ispis izostanaka &nbsp;&nbsp</a></td>
				<td><a href="/ednevnik/servleti/roditelj/ispisOsobnihPodatakaDjeteta?mail=${d.mail }" style="text-decoration: none;">Ispis osobnih podataka &nbsp;&nbsp</a></td>
			    <td><a href="/ednevnik/servleti/roditelj/ispisKontaktnihPodatakaProfesoraRoditelj?mail=${d.mail }" style="text-decoration: none;">Ispis kontaktnih podataka profesora&nbsp;&nbsp</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>