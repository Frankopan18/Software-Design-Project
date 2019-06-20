<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Dodaj Ocjenu/komentar</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
<link rel="stylesheet" href="/ednevnik/style1.css">
</head>
<body>
	<a href="/ednevnik/servleti/predmetRazred?idPredmet=${predmet.id }&idRazred=${razred.id }">Nazad</a>
	<c:out value="${sessionScope[\"current.user.mail\"]}"></c:out>
	<a class="logout" href="/ednevnik/servleti/logout" style="float:right">Logout</a><br>
	<p>&nbsp;</p>
	<h3>${predmet.naziv }</h3>
	<p>&nbsp;</p>
	<form method="post" action="zakljucnaOcjena">
	
		<select name="ucenik">
			<c:forEach items="${ucenici }" var="u">
				<option value="${u.mail }">${u }</option>	
			</c:forEach>
		</select>
		
		<input type="hidden" name="kategorija" value="Zaključna ocjena">
		
		<select name="ocjena">
			<option disabled selected></option>
  			<option value="1">1</option>
 		    <option value="2">2</option>
 		    <option value="3">3</option>
 		    <option value="4">4</option>
 		    <option value="5">5</option>
		</select>
		Komentar: <input type="text" name="komentar" required> <input
			type="hidden" name="idPredmet" value="${predmet.id }"> 
		<a class="btn btn-lg btn-danger half-width" type="Submit" href="/ednevnik/servleti/predmetRazred?idPredmet=${predmet.id }&idRazred=${razred.id }">Odustani</a>
				&nbsp;
				<button class="btn btn-lg btn-primary half-width" type="Submit">Spremi</button>
	</form>
	${greska }
</body>
</html>
