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
         <li><a href="logout">Déconnecter</a></li>
        <li><a href="espacegerant">ACCUEIL</a></li>

        <img height="44" width="194" src="images/logo.PNG" alt="SPORTS WORLD" />
    </ul>
    </div>

   

	
			            
			            
<h1 class="text-danger mt-5"  align="center" >La liste des Disciplines</h1>

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
			            
			            
<h4>Pour ajouter une discipline cliqué sur ajouter</h4>
<div  class="container-fluid mt-5"  >
                <div class="panel-body" align = "center" >
  <table class="table table-striped">
  <thead class="table-dark">
    <tr>
      		<th >Nom de discipline</th>
      		<th  >Action</th>
            
   	</tr>
  </thead>
    
                
  <tbody>
   <c:forEach  items="${listDisciplines}" var="Discipline">
      <tr >
      	<td> <c:out value="${Discipline.nomDiscipline}"/></td>
		<td><a href="AddDiscipline/${Discipline.id_discipline}">ajouter</a></td>
	</tr> 
   </c:forEach> 
  </tbody>
</table>
                    
  </div>
  </div> 
 
	
 <% session.removeAttribute("DisciplineAjouter"); %>
   <% session.removeAttribute("DisciplineExiste"); %>
	
</body>
</html>