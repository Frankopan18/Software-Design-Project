<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
<link rel="stylesheet" href="/ednevnik/style1.css">
</head>
<body>
	<a href="/ednevnik/servleti/predmet?id=${predmet.id }">Nazad</a>
	<c:out value="${sessionScope[\"current.user.mail\"]}"></c:out>
	<a class="logout" href="/ednevnik/servleti/logout" style="float:right">Logout</a><br>
	<p>&nbsp;</p>
	<h2>
		<c:out value="${razred.oznaka }"></c:out>
	</h2>
	
	<a
		href="/ednevnik/servleti/predmet/dodajIzostanak?idPredmet=${predmet.id }&idRazred=${razred.id }">Dodaj
		izostanak</a> |
	<a
		href="/ednevnik/servleti/predmet/dodajUnos?idPredmet=${predmet.id }&idRazred=${razred.id }">Dodaj
		ocjenu/komentar</a> |
	<a
		href="/ednevnik/servleti/predmet/zakljucnaOcjena?idPredmet=${predmet.id }&idRazred=${razred.id }">Zaključi
		ocjenu</a>
	<p>&nbsp;</p>
	<h2>Učenici</h2>
	<table class="table">
		<c:forEach items="${razred.ucenici }" var="u">
			<tr>
				<td><a href="/ednevnik/servleti/ocjene?ucenik=${u.mail }&idPredmet=${predmet.id }">${u }</a></td>
				<c:choose>
					<c:when test="${empty u.slika }"> <td><img src="/ednevnik/Default-avatar.jpg" height="70" width="70"></img></td></c:when>
					<c:otherwise><td><img src="/ednevnik/servleti/ucenik/slika?mail=${u.mail }" height="70" width="70"></img></td></c:otherwise>
				</c:choose>	
			<tr>
		</c:forEach>
	</table>
</body>
</html>