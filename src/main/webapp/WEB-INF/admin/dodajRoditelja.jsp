<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>E-Dnevnik - Dodaj roditelja</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
<link rel="stylesheet" href="/ednevnik/style2.css">
<body class="site">
	<a href="/ednevnik/servleti/admin">Početna</a>
	<c:out value="${sessionScope[\"current.user.mail\"]}"></c:out>
	<a class="logout" href="/ednevnik/servleti/logout" style="float:right">Logout</a><br>
	<p>&nbsp;</p>
	<div class="title">Dodaj Roditelja</div> 	
	<div class="menu">
			<form action="/ednevnik/servleti/admin/dodajRoditelja" method="post"
				class="form">
				
				
				Ime:<input type="text" name="ime" value="${roditelj.ime }" required class="form-control" autofocus>
				<c:if test="${not empty roditelj.imeGreska }">${roditelj.imeGreska }<br></c:if>
				Prezime:<input type="text" name="prezime" value="${roditelj.prezime }" required class="form-control">
				<c:if test="${not empty roditelj.prezimeGreska }">${roditelj.prezimeGreska }<br></c:if>
				E-mail:<input type="email" name="mail" value="${roditelj.mail }" required class="form-control">
				<c:if test="${not empty roditelj.mailGreska }">${roditelj.mailGreska }<br></c:if>
				Lozinka:<input type="password" name="lozinka" class="form-control">
				Potvrdi lozinku:<input type="password" name="lozinkaPotvrda" class="form-control">
				<c:if test="${not empty roditelj.lozinkaGreska }">${roditelj.lozinkaGreska }<br></c:if>
				Broj telefona:<input type="text" name="brojTelefona" value="${roditelj.brojTelefona }" required class="form-control">
				<c:if test="${not empty roditelj.brojTelefonaGreska }">${roditelj.brojTelefonaGreska }<br></c:if>
				<input type="hidden" name="mail" value="${roditelj.mail }"> 
				<p>&nbsp;</p>
				<div class="row">
				<a class="btn btn-lg btn-danger half-width" type="Submit" href="/ednevnik/servleti/admin">Odustani</a>
				&nbsp;
				<button class="btn btn-lg btn-primary half-width" type="Submit">Spremi</button>
				</div>
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