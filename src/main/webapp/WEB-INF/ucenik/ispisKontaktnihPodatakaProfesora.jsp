<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head><title>E-Dnevnik - Kontaktni podaci razrednika i zamjenika razrednika</title>

<a class="logout" href="/ednevnik/servleti/logout" style="float:right">Logout</a><br>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
<link rel="stylesheet" href="/ednevnik/style1.css">
</head>
<body>
	<a href="javascript:history.back()">Nazad</a>
	<c:out value="${sessionScope[\"current.user.mail\"]}"></c:out>
	
	<h2>Razrednik:</h2>
	Ime i prezime: ${razrednik.ime } ${razrednik.prezime }<br>	
	E-mail: ${razrednik.mail }<br>
	Broj telefona: ${razrednik.brojTelefona }<br>
	<br>
	<h2>Zamjenik razrednika:</h2>
	Ime i prezime: ${zamjenikRazrednika.ime } ${zamjenikRazrednika.prezime }<br>
	E-mail: ${zamjenikRazrednika.mail }<br>
	Broj telefona: ${zamjenikRazrednik.brojTelefona }
</body>
</html>