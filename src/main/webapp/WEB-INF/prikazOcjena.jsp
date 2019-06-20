<%@page import="hr.finalium.ednevnik.model.nastava.Predmet"%>
<%@page import="hr.finalium.ednevnik.model.nastava.Kategorija"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page import="hr.finalium.ednevnik.model.nastava.Unos"%>
<%@page import="java.time.ZoneId"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
<link rel="stylesheet" href="/ednevnik/style1.css">
</head>
<body>
	
	<a href="javascript:history.back()">Nazad</a>
	<c:out value="${sessionScope[\"current.user.mail\"]}"></c:out>

	<h2>${ucenik }-${predmet }</h2>

	<table class="table-bordered container-fluid">
		<tr>
			<td>Kategorija</td>
			<c:forEach begin="9" end="12" varStatus="loop">
				<td>${loop.index }</td>
			</c:forEach>
			<c:forEach begin="1" end="6" varStatus="loop">
				<td>${loop.index }</td>
			</c:forEach>
		</tr>
		<%
			for (Kategorija k : (Set<Kategorija>) request.getAttribute("kategorije")) {
				out.append("<tr><td>" + k + "</td>");
				for (int i = 9; i <= 12; i++) {
					out.append("<td>");
					boolean jedina = true;
					for (Unos u : (List<Unos>) request.getAttribute("unosi")) {
						int mjesec = u.getVrijemeUnosa().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
								.getMonthValue();
						if (i == mjesec && u.getOcjena() != null && u.getKategorija().equals(k)) {
							if (!jedina) {
								out.append(", ");
							}
							out.append(u.getOcjena().toString());
							jedina = false;
						}
					}
					out.append("</td>");
				}
				for (int i = 1; i <= 6; i++) {
					out.append("<td>");
					boolean jedina = true;
					for (Unos u : (List<Unos>) request.getAttribute("unosi")) {
						int mjesec = u.getVrijemeUnosa().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
								.getMonthValue();
						if (i == mjesec && u.getOcjena() != null && u.getKategorija().equals(k)) {
							if (!jedina) {
								out.append(", ");
							}
							out.append(u.getOcjena().toString());
							jedina = false;
						}
					}
					out.append("</td>");
				}
				out.append("</tr>");
			}
		%>
	</table>

	<br>
	<h3>Detalji</h3>
	<table class="table-striped">
		<c:forEach items="${unosi }" var="u">
			<tr>
				<c:choose>
					<c:when test="${predmet == u.kategorija.predmet }">
						<td>${u.ocjena }</td>
						<td>${u.komentar }</td>
						<td>${u.vrijemeUnosa }</td>
						<td>${u.kategorija }</td>
						<c:if test="${not empty profesor }"><td><a href="/ednevnik/servleti/obrisi?tip=unos&id=${u.id }&ucenik=${ucenik.mail}&idPredmet=${predmet.id}">Obriši</a></td></c:if>
					</c:when>
				</c:choose>
			</tr>
		</c:forEach>
	</table>
</body>
</html>