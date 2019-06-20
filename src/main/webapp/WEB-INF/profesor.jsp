<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>ednevnik</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
<link rel="stylesheet" href="/ednevnik/style1.css">
</head>
<body>

	<c:out value="${sessionScope[\"current.user.mail\"]}"></c:out>
	<a class="logout" href="/ednevnik/servleti/logout" style="float:right">Logout</a><br>
	<br>
	<p align="center"><form method="get">
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
		</select> <button class="btn btn-lg btn-primary half-width" type="Submit">Pošalji</button>
	</form>

	<h1>Obavijesti</h1>
	<c:forEach items="${obavijesti}" var="o">
		<p>
		<h3>${o.naslov }</h3>
		<br>${o.tekst }
		<br>
		<c:choose>
			<c:when test="${not empty u.slika }"><img src="/ednevnik/servleti/obavijestSlikaServlet?id=${o.id }" height="70" width="70"></img></c:when>
		</c:choose>	
		</p>
		<p align="right">Stvorena: ${o.datumStvaranja }</p>
	</c:forEach>
	<hr color="00000000">
	<h3>Predmeti</h3>
		<ol>
			<c:forEach items="${predmeti}" var="p">
				<li><a href="/ednevnik/servleti/predmet?id=${p.id }">${p.naziv }</a></li>
			</c:forEach>
		</ol>
	<hr color="00000000">
	<h3>Razred</h3>
		<c:choose>
		<c:when test="${not empty profesor.razred }">${profesor.razred }&nbsp;&nbsp <a href="/ednevnik/servleti/prikaziIzostanke?razredId=${profesor.razred.id }">Opravdaj izostanke</a></c:when>
		<c:otherwise>Niste razrednik niti jednom razredu</c:otherwise>
	</c:choose>
	<hr color="00000000">
	<h3>Konzultacije</h3>
		<a href="/ednevnik/servleti/profesor/dodajTerminKonzultacija">Dodaj termin konzultacija</a><br>
		<c:choose>
			<c:when test="${not empty profesor.razred }"><a href="/ednevnik/servleti/profesor/dodajTerminRoditeljskogSastanka">Dodaj termin roditeljskog sastanka</a></c:when>
		</c:choose>
</body>
</html>