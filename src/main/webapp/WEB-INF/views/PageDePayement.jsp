<%@ page language="java" contentType="text/html; charset=UTF-8"%>
    <%@ page pageEncoding="UTF-8" %>
     <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>

 <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Sports World</title>
<link rel="stylesheet" type="text/css" href=" /css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href=" /css/ajouterabonne.css"/>
		<script src= "https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" ></script>
    </head>
<body>
<% session.removeAttribute("good"); %>
<div class="nav_bar">
            <ul>
            <img  height="44" width="194" src="/images/logo.PNG" alt="SPORTS WORLD" /></a> 
            <li><a href="logout">Déconnecter</a></li>
           <li><a href="EspaceAbonne">ACCUEIL</a></li>
   
         </ul>
       </div>
		
	 
                
                
                
                
                <div class="container">
                
                    <form:form action="/Payer/${id}" method="POST" modelAttribute="CompteBancaire" class="firm" >
                    <h1 >Payer</h1>
                  
                 <% 
			            if( session.getAttribute("SoldeInsuffisant")!= null ){
                 out.println("<div class='container'>");
                 	   out.println("  <div  class='alert alert-danger my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("SoldeInsuffisant") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
			            
			                
	             <% 
			            if( session.getAttribute("InformationsInvalides")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-danger my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("InformationsInvalides") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
                           
                                <form:input path="No_Carte" type="text"  placeholder="Numero de la carte" class="form-control my-3 p-4" required="required"/>
                                
                           
                      
                                <form:input path="pin" type="password"  placeholder="****" class="form-control my-3 p-4" required="required"/>
                       
                                <form:input path="" type="submit" value="Verifier" class="btn1 mt-3 mb-5"/>
                          
                   
                    </form:form>
              
	</div>


<% session.removeAttribute("SoldeInsuffisant"); %>
<% session.removeAttribute("InformationsInvalides"); %>
	



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
</body>
</html>