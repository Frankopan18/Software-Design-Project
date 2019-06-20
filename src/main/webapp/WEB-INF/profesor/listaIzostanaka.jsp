<%@page import="hr.finalium.ednevnik.util.Util"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
<link rel="stylesheet" href="/ednevnik/style1.css">

</head>
<body>
	<a href="/ednevnik/servleti/profesor?godina=<% out.print(Util.getCurrentYear());%>">Nazad</a>
	<c:out value="${sessionScope[\"current.user.mail\"]}"></c:out>
	<a class="logout" href="/ednevnik/servleti/logout" style="float:right">Logout</a><br>
	<p>&nbsp;</p>
	<h2>
		<c:out value="${razred.oznaka }"></c:out>
	</h2>
	<table class="table-bordered flex table">
		<tr>
			<th>Predmet</th>
			<th>Ime Učenika</th>
			<th>Komentar profesora</th>
			<th>Vrijeme</th>
			<th>Komentar razrednika</th>
			<th>Stanje</th>
			<th>Uredi</th>
		</tr>
		<c:forEach items="${izostanci }" var="i">
			<tr>
				<td>${i.predmet }</td>
				<td>${i.ucenik }</td>
				<td>${i.komentarProfesora }</td>
				<td>${i.vrijeme }</td>
				<td>${i.komentarRazrednika }</td>
				<td>${i.stanje }</td>
				<td><a href="/ednevnik/servleti/opravdaj?izostanakId=${i.id }">Uredi</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>