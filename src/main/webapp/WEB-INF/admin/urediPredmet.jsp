<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>E-Dnevnik - Uređivanje predmeta</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
<link rel="stylesheet" href="/ednevnik/style1.css">
</head>
<body>
<a href="/ednevnik/servleti/admin/ispisPredmeta" class="icon-block">Nazad</a>
<c:out value = "${sessionScope[\"current.user.mail\"]}"></c:out>
<a class="logout" href="/ednevnik/servleti/logout" style="float:right">Logout</a><br>
<p>&nbsp;</p>
<div class="container">
		<div class="wrapper">
<form action="/ednevnik/servleti/admin/urediPredmet" class="form" method="post" enctype="multipart/form-data">
	
	<h3 class="form-heading">Uredi predmet</h3>
	<hr class="colorgraph">
		Naziv:<input type="text" name="naziv" value="${predmet.naziv }" class="form-control" required><br>
		${predmet.nazivGreska }<br>
		Školska godina:<input type="text" name="skolskaGodina" value="${predmet.skolskaGodina }" class="form-control" required><br>
		${predmet.skolskaGodinaGreska }<br>
		Opis:<input type="text" name="opis" value="${predmet.opis }" class="form-control" required><br>
		${predmet.opisGreska }
		Nastavni plan:<input type="file" name="nastavniPlan" class="form-control"><br>
		Nastavno pismo:<input type="file" name="nastavnoPismo" class="form-control"><br>
		<input type="hidden" name="id" value="${predmet.id }" class="form-control">
		<a class="btn btn-lg btn-danger half-width" type="Submit" href="/ednevnik/servleti/admin/ispisPredmeta">Odustani</a>
				&nbsp;
				<button class="btn btn-lg btn-primary half-width" type="Submit">Spremi</button>
	</form>
</div>
</div>
</body>
</html>