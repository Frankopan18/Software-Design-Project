<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>E-Dnevnik - Dodaj razrednika</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
<link rel="stylesheet" href="/ednevnik/style1.css">
</head>
<body>
<a href="/ednevnik/servleti/admin" class="icon-block">Početna</a>
<c:out value = "${sessionScope[\"current.user.mail\"]}"></c:out>
<a class="logout" href="/ednevnik/servleti/logout" style="float:right">Logout</a><br>
<p>&nbsp;</p>
<form action="/ednevnik/servleti/admin/razrednikRazred" method="post" >
<h3>Dodaj razrednika/zamjenika razrednika razredu (${razred.oznaka })</h3>
	E-Mail profesora: <input type="email" name="mailProfesor" value="${mailProfesor }" required><br>
	<select name="razrednik">
			<option value="1">Razrednik</option>
  			<option value="0">Zamjenik razrednika</option>
		</select><br>
	<input type="hidden" name="idRazred" value="${razred.id }">
	<br></br><a class="btn btn-lg btn-danger half-width" type="Submit" href="/ednevnik/servleti/admin/ispisRazreda">Odustani</a>
				&nbsp;
				<button class="btn btn-lg btn-primary half-width" type="Submit">Spremi</button>
</form>
</body>
</html>