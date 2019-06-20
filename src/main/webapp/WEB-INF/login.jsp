<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>E-Dnevnik</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
<link rel="stylesheet" href="/ednevnik/login.css">
</head>
<body>
<div class="site">
			<div class="header">
				<div class="ime">e-Dnevnik</div>
			</div>

			<form action="" method="post" class="box" action="/ednevnik/index.jsp">       
			   
				  
				  <li>
				  <input type="text" class="form-control" name="mail" placeholder="E-Mail" required autofocus="" value="${mail }"/>
				  <c:if test="${not empty poruka }"><span>${poruka }</span></c:if>
				    </li>
	
				  <li>
				  <input type="password" class="form-control" name="lozinka" placeholder="Lozinka" required/>   
				  </li> 

				  <button name="Submit" value="Login" type="Submit">Login</button>  
				 		
			</form>	
			
			<div class="footer">
				&copy; 2017/18 Finalium, OPP
			</div>		

</div>
</body>
</html>

