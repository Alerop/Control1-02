<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.HashMap" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ejercicio1</title>
</head>
<body>
<%
	request.getAttribute("Enlaces");

	HashMap<String,String> Enlaces = (HashMap<String, String>)session.getAttribute("Enlaces");
	
	if(Enlaces == null){
		Enlaces = new HashMap<String, String>(10);
	}
	
%>
<ul>
<c:forEach var="enl" items="${Enlaces}">
	<ol><a href="${enl.value}">${enl.key}</a></ol>
</c:forEach>

	
</ul>
</body>
</html>