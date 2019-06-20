<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>E-Dnevnik - Dodaj termin</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
<link rel="stylesheet" href="/ednevnik/style1.css">
</head>
<body>

	<a href="/ednevnik/servleti/admin"  class="icon-block">Početna</a>
	<a href="/ednevnik/servleti/admin/ispisPredmeta" class="icon-block">Nazad</a>
	<c:out value="${sessionScope[\"current.user.mail\"]}"></c:out>
	<a class="logout" href="/ednevnik/servleti/logout" style="float:right">Logout</a><br>
	<p>&nbsp;</p>

	<h3>${predmet.naziv }</h3>
	<p>&nbsp;</p>
	<form method="post">
		Dan: <select name="dan">
			<option value="ponedjeljak">ponedjeljak</option>
			<option value="utorak">utorak</option>
			<option value="srijeda">srijeda</option>
			<option value="četvrtak">četvrtak</option>
			<option value="petak">petak</option>
		</select> 
		<br></br>
		Sat: <select name="sat">
			<option value="0">0</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
		</select> 
		<br></br>
	Dvorana/učionica:		<input type="text" name="mjesto" required> 
	<br></br>
		<input type="hidden"
			name="idPredmet" value="${predmet.id }"> <a class="btn btn-lg btn-danger half-width" type="Submit" href="/ednevnik/servleti/admin/ispisPredmeta">Odustani</a>
				&nbsp; <button class="btn btn-lg btn-primary half-width" type="Submit">Dodaj</button>
	</form>
	<p>&nbsp;</p>
	<h3>Trenutni termini</h3>
	<ul>
		<c:forEach items="${predmet.termini }" var="t">
			<li>${t } <a href="/ednevnik/servleti/obrisi?tip=termin&id=${t.id }&zavisniId=${predmet.id}">Obriši</a></li>
		</c:forEach>
	</ul>
</body>

</html>
