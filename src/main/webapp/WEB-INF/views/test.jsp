<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page import="java.time.LocalDate"%>
     <%@ page import="java.time.temporal.ChronoUnit"%>
      <%@ page  import="java.time.LocalTime"%>
      <%@ page  import="java.util.List"%>
    <%@ page  import="com.example.demo.DAO.Seance"%>
     <%@ page  import="com.example.demo.Metier.Operations"%>
    <%@ page  import="com.example.demo.Services.VisiteurService"%>
 <%@ page  import="org.springframework.beans.factory.annotation.Autowired"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% LocalDate today=LocalDate.now();%>
<%LocalDate today1=LocalDate.now().plusDays(3);%>



<% long day2=ChronoUnit.DAYS.between(today1 , today);
    	long month2=ChronoUnit.MONTHS.between(today , today1);    	long year2=ChronoUnit.YEARS.between(today , today1);
    	
    	out.println(day2);
    	%>


</body>
</html>