<%@ page language="java" contentType="text/html; charset=UTF-8"%>
    <%@ page pageEncoding="UTF-8" %>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sports World</title>

	<link rel="stylesheet" type="text/css" href=" /css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href=" /css/ajouterabonne.css"/>
		<script src= "https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" ></script>

</head>

<body>
	
	 <% session.removeAttribute("CoachModifier"); %>
	 <% session.removeAttribute("PasswordCoachWrong"); %>
	<% session.removeAttribute("NewCoachMdpWrong"); %>
	<% session.removeAttribute("PasswordCoachChanged"); %>
	
	  <div class="nav_bar">
            <ul>
            <img  height="44" width="194" src="/images/logo.PNG" alt="SPORTS WORLD" /></a> 
           <li><a href="logout">Déconnecter</a></li>
           <li><a href="espacecoach">ACCUEIL</a></li>
           
         </ul>
       </div>
    <div class="container">
  
  <form:form action="/SaveCoachInfo" method="post" modelAttribute="coach" class="firm">
    <h1>Formulaire de modification de coach</h1>
   
    		<% 
			            if( session.getAttribute("EmailCoachExiste")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-danger my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("EmailCoachExiste") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
			            
    
    <form:label class="form-label" path="firstname">Prénom</form:label>
     <form:input class="form-control" type="text" path="firstname" required="required" />
    
     
     <form:label class="form-label" path="lastname">Nom</form:label>
     <form:input class="form-control" type="text" path="lastname" required="required" />
    
    <form:label class="form-label" path="username">Nom d'Utilisateur</form:label></td>
    <form:input class="form-control" type="text" path="username" required="required" />
    
     <form:label class="form-label" path="email">Email</form:label></td>
     <form:input class="form-control" type="email" path="email" required="required" />
   
     <form:label class="form-label" path="age">age</form:label></td>
     <form:input class="form-control" type="numero" path="age" required="required" />
    
	
    <form:label class="form-label" path="num_tele">Num-Téléphone</form:label>
    <form:input class="form-control" type="number" path="num_tele" maxlength="10" minlength="10" size="10" required="required" />
    
     <td><form:label class="form-label" path="date_nais">Date-Naissance</form:label>
     <td><form:input class="form-control" type="date" path="date_nais" min="1950-01-01" max="2002-01-01" required="required"/>
   
    <form:label class="form-check-label" path="" >Genre :</form:label><br>
	  <form:label  path="genre" for="homme">Homme</form:label>
	     <form:radiobutton  path="genre" id="homme"  value="homme" required="required"/><br>
	      
	      
	   	<form:label path="genre" for="femme">Femme</form:label>
	     <form:radiobutton   path="genre" id="femme"  value="femme" required="required"/><br>
	    
   
   
   <form:input class="btn btn-info" path="" type="submit" value="Modifier" /><br>
   <a href="ChangePasswordCoach"  class="link-primary">changer le mot de passe</a>
  </form:form>
   
 </div>
  <% session.removeAttribute("EmailCoachExiste"); %>
 

 <script src= "https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" ></script>

</body>
</html>