<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sports World</title>
<link rel="stylesheet" type="text/css" href=" /css/listabonnement.css"/>
<link rel="stylesheet" type="text/css" href=" /css/bootstrap.min.css"/>

		<script src= "https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" ></script>
	
</head>
<body>

<c:set var="counter" value="1"  />
<%session.removeAttribute("GerantModifier");%>
 <% session.removeAttribute("PasswordGerantChanged"); %>
 <% session.removeAttribute("AbonnementDisciplineAdded"); %>
   <% session.removeAttribute("AbonnementDisciplineExiste"); %>
   <% session.removeAttribute("abonnementDisciplineSupprimé"); %>
      <% session.removeAttribute("DisciplineExiste"); %>
		   <% session.removeAttribute("DisciplineAjouter"); %>

<div class="nav_bar">
        <ul>
       <img  height="44" width="194" src="/images/logo.PNG" alt="SPORTS WORLD" /> 
       <li><a href="logout">Déconnecter</a></li>
       <li><a href="espacegerant">ACCUEIL</a></li>

     </ul>
   </div>
<div  class="container-fluid mt-5"  >
                <div class="panel-body" align = "center" >
                <h1 class="text-danger mt-5"  align="center" >La liste des Abonnements</h1>
                  		
   				 <% 
			            if( session.getAttribute("AbonnementSupprimer")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-success my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("AbonnementSupprimer") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
			            
			            <% 
			            if( session.getAttribute("AbonnementModifier")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-success my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("AbonnementModifier") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
			            
			                 <% 
			            if( session.getAttribute("AbonnementAjouter")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-success my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("AbonnementAjouter") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
			               <% 
			            if( session.getAttribute("AbonnementExiste")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-danger my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("AbonnementExiste") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
			            
			               
                  <div align = "left" >		   
		     <h3><a  href="ajouterAbonnement">Ajouter un abonnement</a></h3>  
	    </div> 
                     
  <table class="table table-striped">
  <thead class="table-dark">
    <tr>
    		<th>Abonnement</th>
      		<th >Disciplines</th>
            <th >tarif</th>
            <th >Durée</th>
            <th >Modifier</th>
            <th >Supprimier</th>
           
            
   	</tr>
  </thead>
   
			    
	  
  <tbody>
  <c:forEach  items="${listAbonnements4}" var="Abonnement">
    <tr >
       <td> Abonnement <c:out value="${counter}"  /> </td>
      		<td> 
	        <c:forEach  items="${Abonnement.getAbonnementDisciplines()}" var="Disciplines">
	        
	        	<c:out value="${Disciplines.getDiscipline().getNomDiscipline()}"/><br>
	        
	        </c:forEach>
	        </td>
	       	<td> <c:out value="${Abonnement.tarif}"/> DA</td>
			<td> <c:out value="${Abonnement.duree}"/> mois</td>
			<td>
			<a href="editAbonnement/${Abonnement.id_abonnement}">Modifier</a>
			</td>							    
			<td>
				<a href="deleteAbonnement/${Abonnement.id_abonnement}">Supprimer</a>
			</td>	
	    	 
			<c:set var="counter" value="${counter+1}"  />
	</tr> 
	</c:forEach>    

  </tbody>
</table>
                    
  </div>
  </div> 
 
<%session.removeAttribute("AbonnementAjouter");%>
		<%session.removeAttribute("AbonnementExiste");%>
		
	<%session.removeAttribute("AbonnementSupprimer");%>
		<%session.removeAttribute("AbonnementModifier");%>
</body>
</html>