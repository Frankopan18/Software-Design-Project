<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head><title>E-Dnevnik - Kontaktni podaci razrednika i zamjenika razrednika</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
<link rel="stylesheet" href="/ednevnik/style1.css">
</head>
<body>
		<a href="/ednevnik/servleti/roditelj" class="icon-block">Nazad</a>
	<c:out value="${sessionScope[\"current.user.mail\"]}"></c:out>
	<a class="logout" href="/ednevnik/servleti/logout" style="float:right">Logout</a>
	<p>&nbsp;</p>
<form class="form">
	<h2>Razrednik:</h2><br>
	Ime i prezime: ${razrednik.ime } ${razrednik.prezime }<br>
	E-mail: ${razrednik.mail }<br>
	Broj telefona: ${razrednik.brojTelefona }<br>
	<hr color="00000000">
	<h3>Zamjenik razrednika:</h3>
	Ime: ${zamjenikRazrednika.ime } ${zamjenikRazrednika.prezime }<br>
	E-mail: ${zamjenikRazrednika.mail }<br>
	Broj telefona: ${zamjenikRazrednik.brojTelefona }<br>
</form>
</body>
</html>