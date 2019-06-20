<%@page import="hr.finalium.ednevnik.util.Util"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Dodaj termin konzultacija</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css"></head>
<link rel="stylesheet" href="/ednevnik/style1.css">
<body>
	<a href="/ednevnik/servleti/profesor?godina=<% out.print(Util.getCurrentYear());%>">Nazad</a>
	<c:out value="${sessionScope[\"current.user.mail\"]}"></c:out>
	<a class="logout" href="/ednevnik/servleti/logout" style="float:right">Logout</a><br>
	<p>&nbsp;</p>
	<form action="/ednevnik/servleti/profesor/dodajTerminRoditeljskogSastanka" method="post">
		Termin i mjesto roditeljskog sastanka:
		<br>
		<input type="text" name="roditeljski" value="${profesor.razred.roditeljskiSastanak }" required><br>
		<a class="btn btn-lg btn-danger half-width" type="Submit" href="/ednevnik/servleti/profesor?godina=<% out.print(Util.getCurrentYear());%>">Odustani</a>
				&nbsp;
				<button class="btn btn-lg btn-primary half-width" type="Submit">Spremi</button>
	</form>
		
</body>
</html>