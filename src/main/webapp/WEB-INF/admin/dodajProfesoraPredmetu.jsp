<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>E-Dnevnik - Dodavanje profesora</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
<link rel="stylesheet" href="/ednevnik/style1.css">
</head>
<body>

<a href="/ednevnik/servleti/admin/ispisPredmeta" class="icon-block">Nazad</a>
<c:out value = "${sessionScope[\"current.user.mail\"]}"></c:out>
<a class="logout" href="/ednevnik/servleti/logout" style="float:right">Logout</a><br>
<p>&nbsp;</p>

<form action="/ednevnik/servleti/admin/predmetProfesor" method="post">
	<h1>${predmet.naziv }</h1>
	<h2>${predmet.skolskaGodina }</h2>
		e-mail profesora:<input type="email" name="mailProfesor" value="${mailProfesor }" required><br>
		${greska }<br>
		<input type="hidden" name="idPredmet" value="${predmet.id }">
		<a class="btn btn-lg btn-danger half-width" type="Submit" href="/ednevnik/servleti/admin/ispisPredmeta">Odustani</a>
				&nbsp;
				<button class="btn btn-lg btn-primary half-width" type="Submit">Dodaj</button>
	</form>

</body>
</html>