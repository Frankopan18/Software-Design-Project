<!DOCTYPE html>
<html>
<head>
	
	<%@ page contentType="text/html; charset=UTF-8" %>
	<link rel="stylesheet" href="register.css" type="text/css" />
</head>
<body>
	<div class="site">
		<div class="header">
			<div class="ime">e-Dnevnik</div>
		</div>
		<div class="box">
			<form action="/ednevnik/register.jsp" method="post">
			<li>
		    	<input class="input" type="email" name="email" placeholder="E-Mail"/>
			</li>
		   	
			<li>
		    	<input class="pwinput" type="password" name="pass" placeholder="Password"/>
			</li>
			
			<button type="submit" id="login-button">Register</button>
		</form>
		</div>
		<div class="img">
		 <br><br>  
		</div>
		<div class="footer">
			&copy; 2017/18 Finalium, OPP
		</div>
		<!--<img class="img" src="https://im2.ezgif.com/tmp/ezgif-2-970389b8ef.gif" alt="This will display an animated GIF" />-->
	</div>
	
</body>
</html>
