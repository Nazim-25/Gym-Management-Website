<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Sports World</title>
<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href="/css/forgotmail.css"/>
		
    </head>
<body>
 <div class="nav_bar">
            <ul>
            <img  height="44" width="194" src="/images/logo.PNG" alt="SPORTS WORLD" /></a> 
            <li><a href="logout">Déconnecter</a></li>
           <li><a href="espacecoach">ACCUEIL</a></li>
   
         </ul>
       </div>

    <div class="continer">
        
                
                
                
			            
			          
                    <form:form action="/send-otp-Coach" method="POST" modelAttribute="Abonne" class="firm" >
                       <h3 >VOTRE EMAIL </h3> 
                       
                        <% 
			            if( session.getAttribute("message")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-danger my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("message") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
                            <div class="form-group">
                            
                                <form:input path="email" type="email"  placeholder="Email-adresse" class="form-control " required="required"/>
                          
                        	</div>
                      
                        
                            
                                <form:input path="" type="submit" value="Send OTP" class="btn btn-warning mt-5"/>
                            
                       
                        
                    </form:form>
                </div>
	            
	      


	

<% session.removeAttribute("message"); %>

<script src= "https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" ></script>
</body>
</html>