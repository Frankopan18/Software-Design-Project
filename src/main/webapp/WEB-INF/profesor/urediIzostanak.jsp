<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Dodaj Izostanak</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
<link rel="stylesheet" href="/ednevnik/style1.css">
</head>
<body>
	<a href="/ednevnik/servleti/prikaziIzostanke?razredId=${razred.id }">Nazad</a>
	<c:out value="${sessionScope[\"current.user.mail\"]}"></c:out>
	<a class="logout" href="/ednevnik/servleti/logout" style="float:right">Logout</a><br>
	<p>&nbsp;</p>
	<h3>${predmet.naziv }</h3>

	<form method="post">
		Učenik: ${izostanak.ucenik }<br>
		Predmet: ${izostanak.predmet }<br>
		Komentar profesora: ${izostanak.komentarProfesora }<br>
		Vrijeme: ${izostanak.vrijeme }<br>
		Komentar razrednika: <input type="text" value="${izostanak.komentarRazrednika }" name="komentarRazrednika"> <br>
		<select name="stanje">
		<option value="c">Na čekanju</option>
		<option value="o">Opravdano</option>
		<option value="n">Neopravdano</option>
		</select>
		<input type="hidden" name="idIzostanak" value="${izostanak.id }">
		<a class="btn btn-lg btn-danger half-width" type="Submit" href="/ednevnik/servleti/prikaziIzostanke?razredId=${razred.id }">Odustani</a>
				&nbsp;
				<button class="btn btn-lg btn-primary half-width" type="Submit">Spremi</button>
	</form>
</body>
</html>
