<%@ page language="java" contentType="text/html; charset=UTF-8"%>
    <%@ page pageEncoding="UTF-8" %>
   
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Sports World</title>
<link rel="stylesheet" type="text/css" href=" /css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href=" /css/connecterabn.css"/>
		<script src= "https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" ></script>
</head>
<body>
	<% session.removeAttribute("abonneDeleted"); %>
	   <div class="nav_bar">
            <ul>
            <a class="navbar-brand mr-auto" href="#"><img  height="44" width="194" src="images/logo.PNG" alt="SPORTS WORLD" /></a> 
           
           <li><a href="index">ACCUEIL</a></li>
   
         </ul>
       </div>
		
	                    
	                 
	            
			            
               <div class="container">
                
                    <form:form action="/authentification" method="POST" modelAttribute="Abonne" class="firm" >
                    <h4>Se connecter a votre compte</h4> 
		                     <% 
					            if( session.getAttribute("success")!= null ){
		                 out.println("<div class='container'>");
		                 	   out.println(" <div  class='alert alert-success my-2 text-center' role='alert' >");
					            out.println( session.getAttribute("success") );
						       out.println("</div>");
						out.println("</div>");
					            } %>
					            
					                
			             <% 
					            if( session.getAttribute("messageAbonne")!= null ){
		                 out.println("<div class='container'>");
		                 	   out.println(" <div  class='alert alert-danger my-2 text-center' role='alert' >");
					            out.println( session.getAttribute("messageAbonne") );
						       out.println("</div>");
						out.println("</div>");
					            } %>
                       
                                <form:input path="email" type="email"  placeholder="Email-adresse" class="form-control my-3 p-4" required="required"/>
                                
                          
                                <form:input path="password" type="password"  placeholder="**********" class="form-control my-3 p-4" required="required"/>
                          
                                <form:input path="" type="submit" value="Se connecter" /><br>
                         

                        <a href="mdpOublier">Mot de passe oublié</a>
                        <p>Vous n'avez pas de compte ?   <a href="/SignUp">Inscrivez-vous ici</a> </p>
                    </form:form>
                    </div>
             

  <% session.removeAttribute("messageAbonne"); %>

	



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
</body>
</html>