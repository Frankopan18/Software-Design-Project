<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>E-Dnevnik - Dodaj roditelja učeniku</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
<link rel="stylesheet" href="/ednevnik/style1.css">
</head>
<body>
<a href="/ednevnik/servleti/admin" class="icon-block">Početna</a>
<c:out value = "${sessionScope[\"current.user.mail\"]}"></c:out>
<a class="logout" href="/ednevnik/servleti/logout" style="float:right">Logout</a><br>
<p>&nbsp;</p>
<form action="/ednevnik/servleti/admin/ucenikRoditelj" method="post">
		e-mail učenika:<input type="text" name="mailUcenik" value="${mailUcenik }" required><br>
		${mailUcenikGreska }<br>
		e-mail roditelja:<input type="text" name="mailRoditelj" value="${mailRoditelj }" required><br>
		${mailRoditeljGreska }<br>
		<c:if test="${not empty triRoditelja }">${triRoditelja }<br></c:if>
		<a class="btn btn-lg btn-danger half-width" type="Submit" href="/ednevnik/servleti/admin">Odustani</a>
				&nbsp;
				<button class="btn btn-lg btn-primary half-width" type="Submit">Spremi</button>
	</form>

</body>
</html>