<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>E-Dnevnik - Dodavanje učenika razredu</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
<link rel="stylesheet" href="/ednevnik/style1.css">
</head>
<body>
<a href="/ednevnik/servleti/admin" class="icon-block">Početna</a>
<c:out value = "${sessionScope[\"current.user.mail\"]}"></c:out>
<a class="logout" href="/ednevnik/servleti/logout" style="float:right">Logout</a><br>
<p>&nbsp;</p>

<form action = "/ednevnik/servleti/admin/ucenikRazred" method="post" >
<h3>Dodaj učenika u razred (${razred.oznaka })</h3>
	E-Mail učenika: <input type="email" name="mailUcenik" value="${mailUcenik }" required><br>
	${greska }<br>
	<input type="hidden" name="idRazred" value="${razred.id }">
	<a class="btn btn-lg btn-danger half-width" type="Submit" href="/ednevnik/servleti/admin/ispisRazreda">Odustani</a>
				&nbsp;
				<button class="btn btn-lg btn-primary half-width" type="Submit">Spremi</button>
</form>

<h3>Učenici koji su trenutno u razredu</h3>
<ul>
	<c:forEach items="${razred.ucenici }" var="u">
		<li>${u.ime } ${u.prezime } (${u.mail })</li>
	</c:forEach>
	<c:if test="${empty razred.ucenici }"><li>U razredu trenutno nema učenika</li></c:if>
</ul>
</body>
</html>