<%@ page language="java" contentType="text/html; charset=UTF-8"%>
    <%@ page pageEncoding="UTF-8" %>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Sports World</title>
<link rel="stylesheet" type="text/css" href=" /css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href=" /css/ajouterabonnement.css"/>
		<script src= "https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" ></script>
    </head>
<body>
<%session.removeAttribute("AbonnementModifier");%>
<%session.removeAttribute("AbonnementAjouter");%>
<%session.removeAttribute("AbonnementExiste");%>

<div class="nav_bar">
        <ul>
       <img  height="44" width="194" src="/images/logo.PNG" alt="SPORTS WORLD" /> 
       <li><a href="logout">Déconnecter</a></li>
       <li><a href="espacegerant">ACCUEIL</a></li>

     </ul>
   </div>
   
  <div class="container">
  <form:form action="/SaveNewAbonnement" method="post" modelAttribute="abonnement" class="firm">
    <h1>Formulaire d'ajout un abonnement</h1>
    <form:label class="form-label" path="tarif">Le Tarif</form:label>
    <form:input class="form-control" type="number" path="tarif" required="required" />
    
     <form:label class="form-label" path="duree">La Durée</form:label>
     <form:input class="form-control" type="number" path="duree" required="required" />
   
   <form:input class="btn btn-info" path="" type="submit" value="Ajouter" />
   
  </form:form>
 </div>
 
 	<script src= "https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" ></script>

</body>
</html>