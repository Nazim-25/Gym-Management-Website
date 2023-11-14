<%@ page language="java" contentType="text/html; charset=UTF-8"%>
    <%@ page pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sports World</title>

	<link rel="stylesheet" type="text/css" href=" /css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href=" /css/disiplineger.css"/>
		<script src= "https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" ></script>
	
</head>
<body>
<div class="nav_bar">
        <ul>
       <img  height="44" width="194" src="/images/logo.PNG" alt="SPORTS WORLD" /> 
       <li><a href="logout">Deconnecter</a></li>
       <li><a href="espacegerant">ACCUEIL</a></li>

     </ul>
   </div>

			            
			            <h1 class="text-danger mt-5"  align="center" >La liste des disciplines déja selectionné</h1>
<div  class="container-fluid mt-5"  >
                <div class="panel-body" align = "center" >
  <table class="table table-striped">
  <thead class="table-dark">
    <tr>
      		<th class="col-sm-3" >Nom de discipline</th>
      		<th class="col-sm-3">Action</th>
            
   	</tr>
  </thead>
    
                
  <tbody>
   <c:forEach  items="${listDisciplineCoachD2}" var="Discipline">
      <tr >
      	<td> <c:out value="${Discipline.nomDiscipline}"/></td>
		<td><a href="SupprimerDisciplineCoach/${Discipline.id_discipline}">Supprimer</a></td>
	</tr> 
   </c:forEach> 
  </tbody>
</table>
                    
  </div>
  </div> 
  
  <h1 class="text-danger mt-5"  align="center" >La liste de tous les disciplines </h1>
   <% 
			            if( session.getAttribute("CoachDisciplineDelected")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-success my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("CoachDisciplineDelected") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
			            
			             <% 
			            if( session.getAttribute("DisciplineAjouter")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-success my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("DisciplineAjouter") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
			            
			             <% 
			            if( session.getAttribute("DisciplineExiste")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-danger my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("DisciplineExiste") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
  <div  class="container-fluid mt-5"  >
                <div class="panel-body" align = "center" >
  <table class="table table-striped">
  <thead class="table-dark">
    <tr>
      		<th  class="col-sm-3">Nom de discipline</th>
      		<th   class="col-sm-3">Action</th>
            
   	</tr>
  </thead>
    
                
  <tbody>
   <c:forEach  items="${listDisciplines2}" var="Discipline">
      <tr >
      	<td> <c:out value="${Discipline.nomDiscipline}"/></td>
		<td><a href="AddDiscipline2/${Discipline.id_discipline}">ajouter</a></td>
	</tr> 
   </c:forEach> 
  </tbody>
</table>
                    
  </div>
  </div> 
<div align="center">
			 <a href="AfficheCoachs">Terminer</a>
			</div>
 
<% session.removeAttribute("DisciplineAjouter"); %>
   <% session.removeAttribute("DisciplineExiste"); %>
   <% session.removeAttribute("CoachDisciplineDelected"); %>
	

	
</body>
</html>