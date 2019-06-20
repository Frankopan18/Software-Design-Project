<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head><title>E-Dnevnik - Ispis predmeta djeteta</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
<link rel="stylesheet" href="/ednevnik/style1.css">
</head>
<body>
	
	<a href="/ednevnik/servleti/roditelj" class="icon-block">Nazad</a>
	<a class="logout" href="/ednevnik/servleti/logout" style="float:right">Logout</a>
	<c:out value="${sessionScope[\"current.user.mail\"]}"></c:out>
	<p>&nbsp;</p>
	<h2>Popis pohađanih predmeta</h2>
	<p>&nbsp;</p>
	<table class="table-bordered flex table">
		<tr>
			<th>Predmet</th>
		</tr>
		<c:forEach items="${dijete.razred.predmeti }" var="p">
			<tr>
				<td><a href="/ednevnik/servleti/ocjene?idPredmet=${p.id }&ucenik=${dijete.mail }">${p.naziv }</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>