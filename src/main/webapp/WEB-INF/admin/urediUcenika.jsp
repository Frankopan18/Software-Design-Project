<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>E-Dnevnik - Uređivanje učenika</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
<link rel="stylesheet" href="/ednevnik/style1.css">
</head>
<body>
<a href="/ednevnik/servleti/admin/ispisUcenika"  class="icon-block">Nazad</a>
<c:out value = "${sessionScope[\"current.user.mail\"]}"></c:out>
	<a class="logout" href="/ednevnik/servleti/logout" style="float:right">Logout</a><br>
	<div class="container">
		<div class="wrapper">
<form action="/ednevnik/servleti/admin/urediUcenika" method="post" enctype="multipart/form-data" class="form">
		<h3 class="form-heading">Uredi ucenika</h3>
		<hr class="colorgraph">
		Ime:<input type="text" name="ime" value="${ucenik.ime }" required class="form-control"><br>
		${ucenik.imeGreska }
		Prezime:<input type="text" name="prezime" value="${ucenik.prezime }" required class="form-control"><br>
		${ucenik.prezimeGreska }
		Lozinka:<input type="password" name="lozinka" class="form-control"><br>
		Potvrdi lozinku:<input type="password" name="lozinkaPotvrda" class="form-control"><br>
		${ucenik.lozinkaGreska }
		OIB:<input type="text" name="oib" value="${ucenik.oib }" required class="form-control"><br>
		${ucenik.oibGreska }
		Datum rođenja:<input type="text" name="datumRodenja" value="${ucenik.datumRodenja }" required class="form-control"><br>
		${ucenik.datumRodenjaGreska }
		Mjesto rođenja:<input type="text" name="mjestoRodenja" value="${ucenik.mjestoRodenja }" required class="form-control"><br>
		${ucenik.mjestoRodenjaGreska }
		Spol:
		<select name="spol" required>
			<option value="n">Nepoznat</option>
  			<c:choose>
  				<c:when test="${ucenik.spol == 'MUSKI' }">
  					<option value="m" selected>Muško</option>
  				</c:when>
  				<c:otherwise>
  					<option value="m">Muško</option>
  				</c:otherwise>
  			</c:choose>
 		    <c:choose>
  				<c:when test="${ucenik.spol == 'ZENSKI' }">
  					<option value="z" selected>Žensko</option>
  				</c:when>
  				<c:otherwise>
  					<option value="z">Žensko</option>
  				</c:otherwise>
  			</c:choose>
		</select><br>
		${ucenik.spolGreska }
		Slika: <input type="file" name="slika"><br>
		<input type="hidden" name="mail" value="${ucenik.mail }" class="form-control"><br>
		<a class="btn btn-lg btn-danger half-width" type="Submit" href="/ednevnik/servleti/admin/ispisUcenika">Odustani</a>
				&nbsp;
				<button class="btn btn-lg btn-primary half-width" type="Submit">Dodaj</button>
	</form>
</div>
</div>
</body>
</html>