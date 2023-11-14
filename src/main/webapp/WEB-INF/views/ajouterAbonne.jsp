<%@ page language="java" contentType="text/html; charset=UTF-8"%>
    <%@ page pageEncoding="UTF-8" %>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Sports World</title>

	<link rel="stylesheet" type="text/css" href=" /css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href=" /css/ajouterabonne.css"/>
		<script src= "https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" ></script>
</head>
<body>
 <div class="nav_bar">
            <ul>
            <img  height="44" width="194" src="images/logo.PNG" alt="SPORTS WORLD" /></a> 
           <li><a href="logout">Déconnecter</a></li>
           <li><a href="espacegerant">ACCUEIL</a></li>
   
         </ul>
       </div>
 
<div class="container">
  <form:form action="/AddAbonne" method="post" modelAttribute="Abonne" class="firm">
  <h1>Ajoutez un nouveau abonne</h1>
   
   			 <% 
			            if( session.getAttribute("SameEmail")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-danger my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("SameEmail") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
    
    <form:label class="form-label" path="firstname">Prénom</form:label>
    <form:input class="form-control" type="text" path="firstname" required="required" />
   
     <form:label class="form-label" path="lastname">Nom</form:label>
     <form:input class="form-control" type="text" path="lastname" required="required" />
    
   <form:label class="form-label" path="username">Nom d'utilisateur</form:label>
     <form:input class="form-control" type="text" path="username" required="required" />
    
   
     <form:label class="form-label" path="password">Mot de Passe</form:label>
     <form:input class="form-control" type="password" path="password"  required="required" />
    
     <form:label class="form-label" path="email">Email</form:label>
     <form:input class="form-control" type="email" path="email" required="required" />
   
    <form:label class="form-label" path="num_tele">Num-Téléphone</form:label>
     <form:input class="form-control" type="number" path="num_tele" maxlength="10" minlength="10" size="10" required="required" />
    
    
     <form:label class="form-label" path="date_nais">Date-Naissance</form:label></td>
     <form:input class="form-control" type="date" path="date_nais" min="1950-01-01" max="2002-01-01" required="required"  />
     
     <form:label  path="">SEXE:</form:label><br>
       
       
	     <form:label  path="" for="homme">Homme</form:label>
	     <form:radiobutton  path="genre" id="homme"  value="homme" required="required"/><br>
	      
	      
	   	<form:label path="" for="femme">Femme</form:label>
	     <form:radiobutton   path="genre" id="femme"  value="femme" required="required"/><br>
	    
    
 	 <form:input class="btn btn-info" path="" type="submit" value="Ajouter" />
   
  
  </form:form>
 </div>
 
</body>
</html>