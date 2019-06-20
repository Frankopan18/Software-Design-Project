<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>E-Dnevnik - Dodaj predmet razredu</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
<link rel="stylesheet" href="/ednevnik/style1.css">
</head>
<body>

<a href="javascript:history.back()" class="icon-block">Nazad</a>
<c:out value = "${sessionScope[\"current.user.mail\"]}"></c:out>
<a class="logout" href="/ednevnik/servleti/logout" style="float:right">Logout</a><br>
<p>&nbsp;</p>
<form action="/ednevnik/servleti/admin/predmetRazred" method="post">
	<h1>${razred.oznaka }</h1>
	<h2>${razred.godina }</h2>
		Predmet: <select name="predmet">
 		    <c:forEach items="${predmeti }" var="p"><option value="${p.id }">${p.naziv }</option></c:forEach>
		</select><br>
		<input type="hidden" name="idRazred" value="${razred.id }">
		<br></br>
		<a class="btn btn-lg btn-danger half-width" type="Submit" href="/ednevnik/servleti/admin/ispisRazreda">Odustani</a>
				&nbsp;
				<button class="btn btn-lg btn-primary half-width" type="Submit">Dodaj</button>
	</form>
	
	<h3>Predmeti koje razred trenutno sluša</h3>
	<ul>
		<c:forEach items="${razred.predmeti }" var="p">
			<li>${p.naziv }</li>
		</c:forEach>
		<c:if test="${empty razred.predmeti }"><li>Razred ne sluša niti jedan predmet</li></c:if>
	</ul>
</body>
</html>