<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>E-Dnevnik - Dodaj učenika</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
<link rel="stylesheet" href="/ednevnik/style2.css">
</head>
<body class="site">
<a href="/ednevnik/servleti/admin"  class="icon-block">Početna</a>
<c:out value = "${sessionScope[\"current.user.mail\"]}"></c:out>
	<a class="logout" href="/ednevnik/servleti/logout" style="float:right">Logout</a><br>
	<p>&nbsp;</p>
	<div class="title">Dodaj Učenika</div> 	
	<div class="menu">
<form action="/ednevnik/servleti/admin/dodajUcenika" method="post" enctype="multipart/form-data" class="form">
	
		Ime:<input type="text" name="ime" value="${ucenik.ime }" required class="form-control">
		<c:if test="${not empty ucenik.imeGreska }">${ucenik.imeGreska }<br></c:if>
		Prezime:<input type="text" name="prezime" value="${ucenik.prezime }" required class="form-control">
		<c:if test="${not empty ucenik.prezimeGreska }">${ucenik.prezimeGreska }<br></c:if>
		E-mail:<input type="email" name="mail" value="${ucenik.mail }" required class="form-control">
		<c:if test="${not empty ucenik.mailGreska }">${ucenik.mailGreska }<br></c:if>
		Lozinka:<input type="password" name="lozinka" class="form-control">
		Potvrdi lozinku:<input type="password" name="lozinkaPotvrda" class="form-control">
		<c:if test="${not empty ucenik.lozinkaGreska }">${ucenik.lozinkaGreska }<br></c:if>
		OIB:<input type="text" name="oib" value="${ucenik.oib }" required class="form-control">
		<c:if test="${not empty ucenik.oibGreska }">${ucenik.oibGreska }<br></c:if>
		Datum rođenja:<input type="date" name="datumRodenja" value="${ucenik.datumRodenja }" required class="form-control">
		<c:if test="${not empty ucenik.datumRodenjaGreska }">${ucenik.datumRodenjaGreska }<br></c:if>
		Mjesto rođenja:<input type="text" name="mjestoRodenja" value="${ucenik.mjestoRodenja }" required class="form-control">
		<c:if test="${not empty ucenik.mjestoRodenjaGreska }">${ucenik.mjestoRodenjaGreska }<br></c:if>
		Spol:
		<select name="spol">
			<option value="n">Nepoznat</option>
  			<option value="m">Muško</option>
 		    <option value="z">Žensko</option>
		</select>
		<c:if test="${not empty ucenik.spolGreska }">${ucenik.spolGreska }<br></c:if>
		<p>&nbsp;</p>
		Slika: <input type="file" name="slika">
		<p>&nbsp;</p>
		<a class="btn btn-lg btn-danger half-width" type="Submit" href="/ednevnik/servleti/admin">Odustani</a>
				&nbsp;
				<button class="btn btn-lg btn-primary half-width" type="Submit">Dodaj</button>
	</form>
	</div>
<button onclick="topFunction()" class="top">Top</button> 
</body>
<script>
/* top button script */
function topFunction() {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
} 
</script>
</html>