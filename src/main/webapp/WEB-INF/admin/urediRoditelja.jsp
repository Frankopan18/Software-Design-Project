<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>E-Dnevnik - Uređivanje roditelja</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
<link rel="stylesheet" href="/ednevnik/style1.css">
</head>
<body>
<a href="/ednevnik/servleti/admin/ispisRoditelja">Nazad</a>
	<c:out value="${sessionScope[\"current.user.mail\"]}"></c:out>
	<a class="logout" href="/ednevnik/servleti/logout" style="float:right">Logout</a><br>
	<p>&nbsp;</p>
<div class="container">
<div class="wrapper">
<form action="/ednevnik/servleti/admin/urediRoditelja" method="post" class="form">
	<h3 class="form-heading">Uredi roditelja</h3>
	<hr class="colorgraph">
		Ime:<input type="text" name="ime" value="${roditelj.ime }" required class="form-control"><br>
		${roditelj.imeGreska }
		Prezime:<input type="text" name="prezime" value="${roditelj.prezime }" required class="form-control"><br>
		${roditelj.prezimeGreska }
		Lozinka:<input type="password" name="lozinka" class="form-control"><br>
		Potvrdi lozinku:<input type="password" name="lozinkaPotvrda" class="form-control"><br>
		${roditelj.lozinkaGreska }
		Broj telefona:<input type="text" name="brojTelefona" value="${roditelj.brojTelefona }" required class="form-control"><br>
		${roditelj.brojTelefonaGreska }
		<input type="hidden" name="mail" value="${roditelj.mail }" class="form-control">
		<a class="btn btn-lg btn-danger half-width" type="Submit" href="/ednevnik/servleti/admin/ispisRoditelja">Odustani</a>
				&nbsp;
				<button class="btn btn-lg btn-primary half-width" type="Submit">Spremi</button>
	</form>
</div>
</div>
</body>
</html>