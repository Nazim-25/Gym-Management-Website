
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
    <%@ page pageEncoding="UTF-8" %>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
<title>Sports World</title>
	<link rel="stylesheet" type="text/css" href=" /css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href=" /css/ajoutercoach.css"/>
		<script src= "https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" ></script>
</head>
<body>
 

  <% session.removeAttribute("DisciplineAjouter"); %>
   <% session.removeAttribute("DisciplineExiste"); %>
 	<% session.removeAttribute("CoachDeleted"); %>
 	 <% session.removeAttribute("CoachModifier"); %>
 	 
 	  <div class="nav_bar">
            <ul>
            <img  height="44" width="194" src="images/logo.PNG" alt="SPORTS WORLD" />
           <li><a href="index">Déconnecter</a></li>
           <li><a href="espacegerant">ACCUEIL</a></li>
           
         </ul>
       </div>
    <div class="container">
  
  
  <form:form action="/AddCoach" method="post" modelAttribute="Coach" class="firm">
   <h1>Ajoutez un nouveau Coach</h1>
    <% 
			            if( session.getAttribute("SameEmailCoach")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-danger my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("SameEmailCoach") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
   
    
    <form:label class="form-label" path="firstname">Prenom</form:label>
     <form:input class="form-control" type="text" path="firstname" required="required" />
    
     
     <form:label class="form-label" path="lastname">Nom</form:label>
     <form:input class="form-control" type="text" path="lastname" required="required" />
   
    <form:label class="form-label" path="username">Nom d'utilisateur</form:label>
    <form:input class="form-control" type="text" path="username" required="required" />
    
     <form:label class="form-label" path="password">Mot de passe</form:label>
     <form:input class="form-control" type="password" path="password"  required="required" />
    
     <form:label class="form-label" path="email">Email</form:label></td>
     <form:input class="form-control" type="email" path="email" required="required" />
   
     <form:label class="form-label" path="salaire">salaire</form:label></td>
     <form:input class="form-control" type="numero" path="salaire" required="required" />
   
     <form:label class="form-label" path="age">age</form:label></td>
     <form:input class="form-control" type="numero" path="age" required="required" />
   
     <form:label class="form-label" path="experience">Experience</form:label>
     <form:input class="form-control" type="numero" path="experience" required="required" />
    
	      
    
    <form:label class="form-label" path="num_tele">Num-Téléphone</form:label>
     <form:input class="form-control" type="number" path="num_tele" maxlength="10" minlength="10" size="10" required="required" />
    
    
     <form:label class="form-label" path="date_nais">Date-Naissance</form:label></td>
     <form:input class="form-control" type="date" path="date_nais" min="1950-01-01" max="2002-01-01" required="required"  />
     
      <form:label  path="genre">SEXE:</form:label><br>
       
       
	     <form:label  path="genre" for="homme">Homme</form:label>
	     <form:radiobutton  path="genre" id="homme"  value="homme" required="required"/><br>
	      
	      
	   	<form:label path="genre" for="femme">Femme</form:label>
	     <form:radiobutton   path="genre" id="femme"  value="femme" required="required"/><br>
   
   <form:input class="btn btn-info" path="" type="submit" value="Ajouter" />
   
  </form:form>
  
 </div>
 
    <% session.removeAttribute("SameEmailCoach"); %>
 
 
</body>
</html>