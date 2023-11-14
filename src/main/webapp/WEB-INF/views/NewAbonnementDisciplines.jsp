<%@ page language="java" contentType="text/html; charset=UTF-8"%>
    <%@ page pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sports World</title>
<link rel="stylesheet" type="text/css" href=" /css/disiplineger.css"/>
	<link rel="stylesheet" type="text/css" href=" /css/bootstrap.min.css"/>
		<script src= "https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" ></script>
	
</head>
<body>
<div class="nav_bar">
        <ul>
       <img  height="44" width="194" src="/images/logo.PNG" alt="SPORTS WORLD" /> 
       <li><a href="logout">Déconnecter</a></li>
       <li><a href="espacegerant">ACCUEIL</a></li>

     </ul>
   </div><hr>
   
		
			            
			            
<div  class="container-fluid mt-5"  >
                <div class="panel-body" align = "center" >
                <h1 class="text-danger mt-5"  align="center" >Liste de toutes les disciplines</h1>
                
					<% 
			            if( session.getAttribute("AbonnementDisciplineAdded")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-success my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("AbonnementDisciplineAdded") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
			            
			            
					<% 
			            if( session.getAttribute("AbonnementDisciplineExiste")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-danger my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("AbonnementDisciplineExiste") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
			            
  <table class="table table-striped">
  <thead class="table-dark">
    <tr>
      		<th >Nom de discipline</th>
      		<th  >Action</th>
            
   	</tr>
  </thead>
    
                
  <tbody>
   <c:forEach  items="${listDisciplines5}" var="Discipline">
      <tr >
      	<td> <c:out value="${Discipline.nomDiscipline}"/></td>
		<td><a href="AddAbonnementDiscipline/${Discipline.id_discipline}">ajouter</a></td>
	</tr> 
   </c:forEach> 
  </tbody>
</table>
                    
  </div>
  </div> 
 
			
			 <h3><a href="gereAbonnements">Terminer</a></h3>
			
	
	
 <% session.removeAttribute("AbonnementDisciplineAdded"); %>
  <% session.removeAttribute("AbonnementDisciplineExiste"); %>

	
</body>
</html>