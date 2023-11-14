<%@ page language="java" contentType="text/html; charset=UTF-8"%>
    <%@ page pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sports World</title>

	<link rel="stylesheet" type="text/css" href=" /css/bootstrap.min.css"/>
		<script src= "https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" ></script>
	
</head>
<body>
<a  href="logout"  class="btn btn-primary">Déconnecter </a>


 
<div  class="container-fluid mt-5"  >
<h1>Liste des disciplines de l'abonnement</h1>
				 <% 
			            if( session.getAttribute("abonnementDisciplineSupprimé")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-success my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("abonnementDisciplineSupprimé") );
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
			           
                <div class="panel-body" align = "center" >
  <table class="table table-striped">
  <thead class="table-dark">
    <tr>
      		<th class="col-sm-3" >Nom de discipline</th>
      		<th class="col-sm-3">Action</th>
            
   	</tr>
  </thead>
    
                
  <tbody>
   <c:forEach  items="${listOfAbonnementdisciplines}" var="Discipline">
      <tr >
      	<td> <c:out value="${Discipline.getDiscipline().nomDiscipline}"/></td>
		<td><a href="SupprimerAbonnementDiscipline/${Discipline.getDiscipline().id_discipline}">Supprimer</a></td>
	</tr> 
   </c:forEach> 
  </tbody>
</table>
                    
  </div>
  </div> 
  <div  class="container-fluid mt-5"  >
  <h1>Liste de toutes les disciplines</h1>
                <div class="panel-body" align = "center" >
  <table class="table table-striped">
  <thead class="table-dark">
    <tr>
      		<th  class="col-sm-3">Nom de discipline</th>
      		<th   class="col-sm-3">Action</th>
            
   	</tr>
  </thead>
    
                
  <tbody>
   <c:forEach  items="${listOfDisciplines}" var="Discipline">
      <tr >
      	<td> <c:out value="${Discipline.nomDiscipline}"/></td>
		<td><a href="AddAbonnementDiscipline/${Discipline.id_discipline}">ajouter</a></td>
	</tr> 
   </c:forEach> 
  </tbody>
</table>
                    
  </div>
  </div> 
<div align="center">
			 <a href="gereAbonnements">Terminer</a>
			</div>
 <a href="espacegerant">Back....</a>
 
<% session.removeAttribute("DisciplineAjouter"); %>
<% session.removeAttribute("abonnementDisciplineSupprimé"); %>
<% session.removeAttribute("DisciplineExiste"); %>
 
	

	
</body>
</html>