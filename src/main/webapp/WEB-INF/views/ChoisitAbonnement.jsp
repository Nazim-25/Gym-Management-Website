<%@ page language="java" contentType="text/html; charset=UTF-8"%>
    <%@ page pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sports World</title>

	<link rel="stylesheet" type="text/css" href=" /css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href=" /css/abonnementabn.css"/>
		<script src= "https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" ></script>
	
</head>
<body>
<c:set var="counter" value="1"  />
<% session.removeAttribute("SoldeInsuffisant"); %>
<% session.removeAttribute("InformationsInvalides"); %>
<% session.removeAttribute("PasswordChanged"); %>

<div class="nav_bar">
        <ul>
       <img  height="44" width="194" src="images/logo.PNG" alt="SPORTS WORLD" /> 
       <li><a href="logout">Déconnecter</a></li>
       <li><a href="EspaceAbonne">ACCUEIL</a></li>

     </ul>
   </div>
			            
<div  class="container-fluid mt-5"  >
                <div class="panel-body" align = "center" >
                <h1 class="text-danger mt-5"  align="center" >La liste des abonnements</h1>
                
                <% 
			            if( session.getAttribute("good")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-success my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("good") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
			            
			            <% 
			            if( session.getAttribute("abonnementexiste")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-danger my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("abonnementexiste") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
  <table class="table table-striped">
  <thead class="table-dark">
    <tr>	
    
    		<th >Abonnement</th>
      		<th >Disciplines</th>
            <th >tarif</th>
            <th >Durée</th>
             <th>Action</th>
           
            
   	</tr>
  </thead>
   
			    
	  
  <tbody>
   <c:forEach  items="${listAbonnements2}" var="Abonnement">
    <tr >
       <td> Abonnement <c:out value="${counter}"  /> </td>
      		<td> 
	        <c:forEach  items="${Abonnement.getAbonnementDisciplines()}" var="Disciplines">
	        
	        	<c:out value="${Disciplines.getDiscipline().getNomDiscipline()}"/><br>
	        
	        </c:forEach>
	        </td>
	       	<td> <c:out value="${Abonnement.tarif}"/> DA</td>
			<td> <c:out value="${Abonnement.duree}"/> mois</td>
			
		<c:set var="counter" value="${counter+1}"  />
		<td>  <a href="abonner/${Abonnement.id_abonnement}"> S'abonner</a> </td>
     
	</tr> 
   </c:forEach> 
  </tbody>
</table>
                    
  </div>
  </div> 
 

<% session.removeAttribute("abonnementexiste"); %>
<% session.removeAttribute("good"); %>
	
</body>
</html>