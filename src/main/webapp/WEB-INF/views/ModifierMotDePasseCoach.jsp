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
 <% session.removeAttribute("PasswordCoachChanged"); %>
 <% session.removeAttribute("CoachModifier"); %>
  <% session.removeAttribute("EmailGerantExiste"); %>
  
  <div class="nav_bar">
            <ul>
           <img  height="44" width="194" src="/images/logo.PNG" alt="SPORTS WORLD" /></a> 
           <li><a href="logout">Déconnecter</a></li>
           <li><a href="espacecoach">ACCUEIL</a></li>
   
         </ul>
       </div>
  
 
 <div class="container">
  
 			
  <form:form action="/SaveNewCoachPassword" method="post" modelAttribute="Mdp" class="firm">
   <h1>Modifiez votre mot de passe  </h1>
    <% 
			            if( session.getAttribute("NewCoachMdpWrong")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-danger my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("NewCoachMdpWrong") );
				       out.println("</div>");
				out.println("</div>");}
 			 %>
			            
			    	 <% 
			            if( session.getAttribute("PasswordCoachWrong")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-danger my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("PasswordCoachWrong") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
   
    <form:label class="form-label" path="MdpActuel"> Mot de passe actuel </form:label>
     <form:input  class="form-control" type="password" path="mdpActuel" required="required"/>
    
     <form:label class="form-label"  path="Mdp1">Nouveau mot de passe </form:label>
     <form:input class="form-control" type="password" path="Mdp1" required="required" />
   
  
    <form:label class="form-label" path="Mdp2">Confirmer mot de passe</form:label>
     <form:input class="form-control" type="password" path="Mdp2" required="required" />
    
  
 
   
   <form:input   class="btn btn-info" path="" type="submit" value="Modifier" />
   
   <a href="mdpOublierCoach">Mot de passe oublié</a>
  </form:form>
 </div>

 <% session.removeAttribute("PasswordCoachWrong"); %>
	<% session.removeAttribute("NewCoachMdpWrong"); %>
 
</body>
</html>