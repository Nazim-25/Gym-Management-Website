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
<% session.removeAttribute("EmailAbonneExiste"); %>
<div class="nav_bar">
            <ul>
            <img  height="44" width="194" src="/images/logo.PNG" alt="SPORTS WORLD" />
           <li><a href="logout">Déconnecter</a></li>
           <li><a href="EspaceAbonne">ACCUEIL</a></li>
           
   
         </ul>
       </div>
 
  
 			
<div class="container">
			<h1>Formulaire de modification de mot de passe  </h1>
			 <% 
			            if( session.getAttribute("NewMdpWrong")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-danger my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("NewMdpWrong") );
				       out.println("</div>");
				out.println("</div>");}
 			 %>
			            
			    	 <% 
			            if( session.getAttribute("PasswordWrong")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-danger my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("PasswordWrong") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
  <form:form action="/SaveNewPassword" method="post" modelAttribute="Mdp" class="firm">
   
   
    <form:label class="form-label" path="MdpActuel"> Mot de passe actuel </form:label>
    <form:input  class="form-control" type="password" path="mdpActuel" required="required" />
   
     
     <form:label class="form-label"  path="Mdp1">Nouveau mot de passe </form:label>
     <form:input class="form-control" type="password" path="Mdp1" required="required" />
   
    <form:label class="form-label" path="Mdp2">Confirmer mot de passe</form:label>
    <form:input class="form-control" type="password" path="Mdp2" required="required" />
   
   <form:input   class="btn btn-info" path="" type="submit" value="Modifier" />
   
   					
  </form:form>
  <a href="mdpOublierAbonne">Mot de passe oublée</a>
 </div>

 <% session.removeAttribute("PasswordWrong"); %>
	<% session.removeAttribute("NewMdpWrong"); %>

</body>
</html>