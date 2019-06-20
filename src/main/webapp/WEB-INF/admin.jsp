
<!DOCTYPE html>
<html>
<head>
<title>E-Dnevnik - Admin</title>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="/ednevnik/css/home.css" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
</head>
<body class="site">
	

	<div class="navbar">
	  <a href="/ednevnik/servleti/admin">Pocetna</a>
	  
	  <div class="current" >
			<c:out value = "${sessionScope[\"current.user.mail\"]}"></c:out>
		  </div>
	  
	</div>
	<a class="logout" href="/ednevnik/servleti/logout">Logout</a>
	  
	<table>
	  <tr class="hover">
		<th href="" style="cursor:pointer" class="btn" data-toggle="myCollapse" data-target="#collapse2">Predmet</th>
	  </tr>
	
	  <tr id="collapse2" class="myCollapse">
	  	<td></td>
	  	<td></td>
	  	<td></td>
		<td><a href="/ednevnik/servleti/admin/dodajPredmet" style="cursor:pointer" class="btn">Dodavanje Predmeta</a></td>
		<td><a href="/ednevnik/servleti/admin/ispisPredmeta" style="cursor:pointer" class="btn">Ispis i Uređivanje Predmeta</a></td>
	  </tr>
	
	  <td></td>
	  <tr class="hover">
		<th href="" style="cursor:pointer" class="btn" data-toggle="myCollapse" data-target="#collapse3">Profesor</th>
	  </tr>
	  
	  <tr id="collapse3" class="myCollapse">
	  <td></td><td></td><td></td>
		  <td><a href="/ednevnik/servleti/admin/dodajProfesora" style="cursor:pointer" class="btn">Dodavanje Profesora</a></td>
		  <td><a href="/ednevnik/servleti/admin/ispisProfesora" style="cursor:pointer" class="btn">Ispis i Uređivanje Profesora</a></td>
	  </tr>

	  
	  <td></td>
	  <tr class="hover">
		<th href="" style="cursor:pointer" class="btn" data-toggle="myCollapse" data-target="#collapse4">Razred</th>
	  </tr>
	  
	  <tr id="collapse4" class="myCollapse">
	  <td></td><td></td><td></td>
		  <td><a href="/ednevnik/servleti/admin/dodajRazred" style="cursor:pointer" class="btn">Dodavanje Razreda</td>
		  <td><a href="/ednevnik/servleti/admin/ispisRazreda" style="cursor:pointer" class="btn">Ispis i Uređivanje Razreda</td>
	  </tr>
	  
	  <td></td>
	  <tr class="hover">
		<th href="" style="cursor:pointer" class="btn" data-toggle="myCollapse" data-target="#collapse5">Učenik</th>
	  </tr>
	  
		<tr id="collapse5" class="myCollapse">
		<td></td><td></td><td></td>
			  <td><a href="/ednevnik/servleti/admin/dodajUcenika" style="cursor:pointer" class="btn">Dodavanje Učenika </a></td>
			  <td><a href="/ednevnik/servleti/admin/ispisUcenika" style="cursor:pointer" class="btn">Ispis i Uređivanje Učenika</a></td>
		 </tr>
		 <tr class="hover">
				<th href="" style="cursor:pointer" class="btn" data-toggle="myCollapse" data-target="#collapse6">Roditelj</th>
			  </tr>
		 <tr id="collapse6" class="myCollapse">
		 <td></td>
				<td><a href="/ednevnik/servleti/admin/dodajRoditelja" style="cursor:pointer" class="btn">Dodavanje Roditelja</a></td>
				<td><a href="/ednevnik/servleti/admin/ispisRoditelja" style="cursor:pointer" class="btn">Ispis i Uređivanje Roditelja</a></td>
				<td><a href="/ednevnik/servleti/admin/ucenikRoditelj" style="cursor:pointer">Spoji ucenika i roditelja</a></td>
			</tr>
			
	  <td></td>
	  <td></td>
	  <tr class="hover">
		<th href="" style="cursor:pointer" class="btn" data-toggle="myCollapse" data-target="#collapse7">Dodaj Obavijest</th>
	  </tr>
	  
	  <tr id="collapse7" class="myCollapse">
		  <td href="" style="cursor:pointer" class="btn"><form action="/ednevnik/servleti/admin/dodajObavijest" method="post" enctype="multipart/form-data">
			Naslov:<input type="text" name="naslov" required><br> 
			Tekst:<textarea rows="10" cols="100" name="tekst" required></textarea><br> 
			Slika: <input type="file" name="slika"><br>
			<input type="submit">
		</form></td>
	  </tr>
	  
	  <tr class="hover">
			<th href="" style="cursor:pointer" class="btn" data-toggle="myCollapse" data-target="#collapse8">Pregled Obavijesti</th>
		  </tr>
		  
		  <tr id="collapse8" class="myCollapse">
			  <td href="" style="cursor:pointer" class="btn"> <c:forEach items="${obavijesti}" var="o">
		<p>
			<h2>${o.naslov }</h2>
			<br>${o.tekst }<br>
			<c:if test="${not empty o.slika }"><img src="/ednevnik/servleti/obavijestSlikaServlet?id=${o.id }" height="500" width="500"></img></c:if>
		</p>
		<p align="right">Stvorena: ${o.datumStvaranja }</p>
	</c:forEach> </td>
		  </tr>
	  <td></td>
	  <tr class="hover">
		<th><a href="/ednevnik/servleti/admin/dnevnikDogadaja" style="cursor:pointer">Pregled Dnevnika Događaja</a></th>
	  </tr>
	  <tr id="collapse2" class="myCollapse"><td><div>Should be collapsed</div></td></tr>
	  <td></td>
	
	</table>
	
	
	<button onclick="topFunction()" class="top">Top</button> 
	</body>
	
	<script>
	/* top button script */
	function topFunction() {
		document.body.scrollTop = 0;
		document.documentElement.scrollTop = 0;
	} 
	</script>

	<script>
	$("[data-toggle='myCollapse']").click(function( ev ) {
		ev.preventDefault();
		var target;
		if (this.hasAttribute('data-target')) {
			target = $(this.getAttribute('data-target'));
		} else {
			target = $(this.getAttribute('href'));
		};
		target.toggleClass("in");
		console.log(target.hasClass('in'));
	});
	</script>

<!--
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css"></head>
<body>

<c:out value = "${sessionScope[\"current.user.mail\"]}"></c:out>
<a href="/ednevnik/servleti/logout">Logout</a>

<h2>Dodaj obavijest</h2>
	<form action="/ednevnik/servleti/admin/dodajObavijest" method="post" enctype="multipart/form-data">
		Naslov:<input type="text" name="naslov" required><br> 
		Tekst:<textarea rows="5" cols="100" name="tekst" required></textarea><br>
		Slika: <input type="file" name="slika"><br>
		<input type="submit">
	</form>
	
	<h3>Učenik</h3>
	<a href="/ednevnik/servleti/admin/ispisUcenika">Ispis ucenika</a> | 
	<a href="/ednevnik/servleti/admin/dodajUcenika">Dodaj ucenika</a><br>
	<hr>
	<a href="/ednevnik/servleti/admin/ucenikRoditelj">Spoji ucenika i roditelja</a><br>
	<hr>
	<h3>Roditelj</h3>
	<a href="/ednevnik/servleti/admin/ispisRoditelja">Ispis roditelja</a>
	<a href="/ednevnik/servleti/admin/dodajRoditelja">Dodaj roditelja</a><br>
	<hr>
	<h3>Profesor</h3>
	<a href="/ednevnik/servleti/admin/ispisProfesora">Ispis profesora</a>
	<a href="/ednevnik/servleti/admin/dodajProfesora">Dodavanje profesora</a><br>
	<hr>
	<h3>Predmet</h3>
	<a href="/ednevnik/servleti/admin/dodajPredmet">Dodavanje predmeta</a>
	<a href="/ednevnik/servleti/admin/ispisPredmeta">Ispis predmeta</a><br>
	<hr>
	<h3>Razred</h3>
	<a href="/ednevnik/servleti/admin/dodajRazred">Dodaj razred</a>
	<a href="/ednevnik/servleti/admin/ispisRazreda">Ispis razreda</a><br>
	<hr>
	<a href="/ednevnik/servleti/admin/dnevnikDogadaja">Dnevnik događaja</a><br>

	<c:forEach items="${obavijesti}" var="o">
		<p>
			<h2>${o.naslov }</h2>
			<br>${o.tekst }<br>
			<c:if test="${not empty o.slika }"><img src="/ednevnik/servleti/obavijestSlikaServlet?id=${o.id }" height="70" width="70"></img></c:if>
		</p>
		<p align="right">Stvorena: ${o.datumStvaranja }</p>
		<p align="right"><a href="/ednevnik/servleti/obrisi?tip=obavijest&id=${o.id }">Obriši</a></p>
	</c:forEach>
</body>
</html>

-->	
</html>
