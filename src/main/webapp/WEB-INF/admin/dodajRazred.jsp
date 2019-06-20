<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>E-Dnevnik - Dodaj razred</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
	<link rel="stylesheet" href="/ednevnik/style2.css">
</head>
<body class="site">
<a href="/ednevnik/servleti/admin" class="icon-block">Početna</a>
<c:out value = "${sessionScope[\"current.user.mail\"]}"></c:out>
<a class="logout" href="/ednevnik/servleti/logout" style="float:right">Logout</a><br>
<p>&nbsp;</p>
	<div class="title">Dodaj Razred</div> 	
	<div class="menu">
		
		<form action="/ednevnik/servleti/admin/dodajRazred" method="post" class="form">
		
			Oznaka:<input type="text" name="oznaka" value="${razred.oznaka }" required class="form-control" autofocus>
			<c:if test="${not empty razred.oznakaGreska }">${razred.oznakaGreska }<br></c:if>
			Školska godina:<input type="text" name="godina" value="${razred.godina }" required class="form-control">
			<c:if test="${not empty razred.godinaGreska }">${razred.godinaGreska }<br></c:if>
			<p>&nbsp;</p>
			<a class="btn btn-lg btn-danger half-width" type="Submit" href="/ednevnik/servleti/admin/ispisRazreda">Odustani</a>
				&nbsp;
				<button class="btn btn-lg btn-primary half-width" type="Submit">Spremi</button>
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