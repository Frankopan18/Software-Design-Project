<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>E-Dnevnik - Dodaj predmet</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
<link rel="stylesheet" href="/ednevnik/style2.css">


</head>
<body class="site">
	<a href="javascript:history.back()">Početna</a>
	<c:out value="${sessionScope[\"current.user.mail\"]}"></c:out>
	<a class="logout" href="/ednevnik/servleti/logout" style="float:right">Logout</a><br>
	<p>&nbsp;</p>
	<div class="title">Dodaj Predmet</div> 	
	<div class="menu">
			<form action="/ednevnik/servleti/admin/dodajPredmet" method="post"
				enctype="multipart/form-data" class="form">
				
				Naziv:<input type="text" name="naziv"
					value="${predmet.naziv }" required class="form-control" autofocus>
				<c:if test="${not empty predmet.nazivGreska }">${predmet.nazivGreska }<br></c:if> 
				Školska godina:<input type="text" name="skolskaGodina" value="${predmet.skolskaGodina }" required class="form-control">
				<c:if test="${not empty predmet.skolskaGodinaGreska }">${predmet.skolskaGodinaGreska }<br></c:if> 
				Opis:<input type="text" name="opis" value="${predmet.opis }" required class="form-control">
				<c:if test="${not empty predmet.opisGreska }">${predmet.opisGreska }<br></c:if>
				Nastavni plan:<input type="file" name="nastavniPlan" class="form-control"> 
				Nastavno pismo:<input type="file" name="nastavnoPismo" class="form-control">  
				Kategorije:<input type="text" name="kategorije" value="${predmet.kategorije }" required class="form-control"> 
				<c:if test="${not empty predmet.kategorijeGreska }">${predmet.kategorijeGreska }<br></c:if>
				<p>&nbsp;</p>
				<div class="row">
				<a class="btn btn-lg btn-danger half-width" type="Submit" href="/ednevnik/servleti/admin/ispisPredmeta">Odustani</a>
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