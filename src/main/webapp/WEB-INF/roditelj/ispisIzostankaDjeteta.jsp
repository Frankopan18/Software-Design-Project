<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head><title>E-Dnevnik - Ispis izostanka djeteta</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
<link rel="stylesheet" href="/ednevnik/style1.css">
</head>
<body>

	<a href="/ednevnik/servleti/roditelj" class="icon-block">Nazad</a>
	<c:out value="${sessionScope[\"current.user.mail\"]}"></c:out>
	<a class="logout" href="/ednevnik/servleti/logout" style="float:right">Logout</a>
	<br>
	<p>&nbsp;</p>
	<h2>Popis izostanaka</h2><br>
	<p>&nbsp;</p>

	<table class="table-bordered flex table"> 
		<tr>
			<th>Predmet</th>
			<th>Vrijeme</th>
			<th>Komentar profesora</th>
			<th>Komentar razrednika</th>
			<th>Stanje</th>
		</tr>
		<c:forEach items="${izostanci }" var="i">
			<tr>
				<td>${i.predmet }</td>
				<td>${i.vrijeme }</td>
				<td>${i.komentarProfesora }</td>
				<td>${i.komentarRazrednika }</td>
				<td>${i.stanje }</td>
			</tr>
		</c:forEach>
	</table>
	<p>&nbsp;</p>
	<h2>Broj izostanka koji čekaju na odluku razrednika: ${brojNaCekanju }</h2>
	<h2>Broj neopravdanih izostanaka: ${brojNeopravdanih }</h2>
	<h2>Broj opravdanih izostanka: ${brojOpravdanih }</h2>
	<h2>Broj izostanka: ${brojIzostanka }</h2>
</body>
</html>