<%@page import="java.util.Set"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>E-Dnevnik - Učenik</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
	<link rel="stylesheet" href="/ednevnik/style1.css">
</head>
<body>
	<c:out value="${sessionScope[\"current.user.mail\"]}"></c:out>
	<a class="logout" href="/ednevnik/servleti/logout" style="float:right">Logout</a><br>
	<p>&nbsp;</p>
	<hr>

	<a href="/ednevnik/servleti/ucenik/ispisIzostanka">Ispis izostanka</a>
	|
	<a href="/ednevnik/servleti/ucenik/ispisOsobnihPodataka">Ispis
		osobnih podataka</a> |
	<a href="/ednevnik/servleti/ucenik/ispisKontaktnihPodatakaProfesora">Ispis
		kontaktnih podataka razrednika i zamjenika razrednika</a>
	
	<p align="center"></p> <form method="get">
	 Godina:
		<select id="godinaSelect" name="godina">
			<c:forEach items="${godine }" var="g">
				<c:choose>
					<c:when test="${godina == g }">
						<option selected>${g }</option>
					</c:when>
					<c:otherwise>
						<option>${g }</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select> <input type="submit">
	</form>

	<h1>${ucenik.ime} ${ucenik.prezime}</h1>

	<h1>Obavijesti</h1>
	<c:forEach items="${obavijesti}" var="o">
		<p>
		<h3>${o.naslov }</h3>
		<br>${o.tekst }
		<br>
		<p align="center">
			<c:if test="${not empty o.slika }"><img src="/ednevnik/servleti/obavijestSlikaServlet?id=${o.id }" height="70" width="70"></img></c:if>
		</p>	
		</p>
		<p align="right">Stvorena: ${o.datumStvaranja }</p>
	</c:forEach>
	<c:if test="${empty obavijesti }">
	<h3>Nema obavijesti</h3>
	</c:if>
	<hr>

	<table class="table-bordered flex table">
		<tr>
			<th>Sat</th>
			<th>Ponedjeljak</th>
			<th>Utorak</th>
			<th>Srijeda</th>
			<th>Četvrtak</th>
			<th>Petak</th>
		</tr>
		<c:forEach begin="0" end="8" varStatus="outerLoop">
			<tr>
				<td>${outerLoop.index }</td>
				<c:forEach begin="1" end="5" varStatus="innerLoop">
					<td><c:forEach items="${predmeti }" var="p">
							<c:forEach items="${p.termini }" var="t">
								<c:if
									test="${t.danInt == innerLoop.index && t.sat == outerLoop.index }">
							${p } [${t.mjesto }]
						</c:if>
							</c:forEach>
						</c:forEach></td>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
	
	<h2>Predmeti</h2>
	<c:forEach items="${predmeti }" var="p">
		<a
			href="/ednevnik/servleti/ocjene?idPredmet=${p.id }&ucenik=${ucenik.mail }">${p }</a>
			<br>
	</c:forEach>
	<p>&nbsp;</p>
	
</body>
</html>