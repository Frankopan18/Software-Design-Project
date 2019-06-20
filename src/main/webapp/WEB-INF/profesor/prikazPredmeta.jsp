<%@page import="hr.finalium.ednevnik.util.Util"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Pregled predmeta</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
<link rel="stylesheet" href="/ednevnik/style1.css">
</head>
<body>


	<a href="/ednevnik/servleti/profesor?godina=<% out.print(Util.getCurrentYear());%>" class="icon-block">Nazad</a>
	<c:out value="${sessionScope[\"current.user.mail\"]}"></c:out>
	<a class="logout" href="/ednevnik/servleti/logout" style="float:right">Logout</a><br>
	<p>&nbsp;</p>
	<form method="post">
	Naziv:
	<c:out value="${predmet.naziv }"></c:out>
	<br> Školska godina:
	<c:out value="${predmet.skolskaGodina }"></c:out>
	<br> Opis:
	<c:out value="${predmet.opis }"></c:out>
	<br>
	<a
		href="/ednevnik/servleti/predmet/getfile?id=${predmet.id }&file=plan">Nastavni
		plan</a>
	<br>
	<a
		href="/ednevnik/servleti/predmet/getfile?id=${predmet.id }&file=pismo">Nastavno
		pismo</a>
	</form>	

	<h3>Razredi</h3>
	<ul>
		<c:forEach items="${predmet.razredi }" var="razred">
			<li><a
				href="/ednevnik/servleti/predmetRazred?idPredmet=${predmet.id }&idRazred=${razred.id}">${razred }</a></li>
		</c:forEach>
		<c:if test="${empty predmet.razredi }"><li>Niti jedan razred ne sluša ovaj predmet</li></c:if>
	</ul>
</body>
</html>