<%@ page language="java" contentType="text/html; charset=UTF-8"%>
    <%@ page pageEncoding="UTF-8" %>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sports World</title>
<link rel="stylesheet" type="text/css" href=" /css/bootstrap.min.css"/>

	<link rel="stylesheet" type="text/css" href=" /css/ajouterabonne.css"/>
		<script src= "https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" ></script>
	
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body>





<%session.removeAttribute("AbonnementModifier");%>
<%session.removeAttribute("AbonnementAjouter");%>
		<%session.removeAttribute("AbonnementExiste");%>
 <body>
        
        <div class="nav_bar">
            <ul>
            <img  height="44" width="194" src="/images/logo.PNG" alt="SPORTS WORLD" /></a> 
           <li><a href="logout">Déconnecter</a></li>
           <li><a href="espacegerant">ACCUEIL</a></li>
           <li><a href="ModifierAbonnementDisciplines">Modifier les disciplines</a><br></li>
   
         </ul>
       </div>
       
 
  <div class="container">
  <form:form action="/SaveAbonnement" method="post" modelAttribute="abonnement" class="firm">
   <h2>Modifiez l'abonnement</h2>
   
   
    <form:label class="form-label" path="tarif">Le Tarif</form:label>
    <form:input class="form-control" type="number" path="tarif" required="required" />
    
    <form:label class="form-label" path="duree">La Durée</form:label></td>
    <form:input class="form-control" type="number" path="duree" required="required" />
    
  
 
   <form:input class="btn btn-info" path="" type="submit" value="Modifier" />
   
  </form:form>
 </div>
 	<script src= "https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" ></script>

</body>
</html>