<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Dodaj Izostanak</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css"></head>
<link rel="stylesheet" href="/ednevnik/style1.css">

<body>
	<a href="/ednevnik/servleti/predmetRazred?idPredmet=${predmet.id }&idRazred=${razred.id }">Nazad</a>
	<c:out value="${sessionScope[\"current.user.mail\"]}"></c:out>
	<a class="logout" href="/ednevnik/servleti/logout" style="float:right">Logout</a><br>
	<p>&nbsp;</p>
	<h3>${predmet.naziv }</h3>
	<p>&nbsp;</p>
	<form method="post">
		<select name="ucenik">
		<c:forEach items="${ucenici }" var="u">
			<option value="${u.mail }">${u }</option>
		</c:forEach>
		</select><br>
		Komentar: <input type="text" name="komentarProfesora" value="${izostanak.komentarProfesora }">
		<input type="hidden" name="idPredmet" value="${predmet.id }">
		<a class="btn btn-lg btn-danger half-width" type="Submit" href="/ednevnik/servleti/predmetRazred?idPredmet=${predmet.id }&idRazred=${razred.id }">Odustani</a>
				&nbsp;
		<button class="btn btn-lg btn-primary half-width" type="Submit">Dodaj</button>
	</form>
</body>
</html>
