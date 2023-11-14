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
<%session.removeAttribute("GerantModifier");%>
 <% session.removeAttribute("PasswordGerantChanged"); %>
     <div class="nav_bar">
        <ul>
       <img  height="44" width="194" src="images/logo.PNG" alt="SPORTS WORLD" /> 
       <li><a  href="logout"  >Déconnecter </a></li>
       <li><a href="espacegerant">ACCUEIL</a></li>

     </ul>
   </div>
 
<h1 class="text-danger mt-5"  align="center" >La liste des disciplines</h1><hr>
	
				  <% 
			            if( session.getAttribute("discplineSupprimer")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-success my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("discplineSupprimer") );
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
			            if( session.getAttribute("DisciplineModifier")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-success my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("DisciplineModifier") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
			            
			            
			            
  
			            <%if( session.getAttribute("DisciplineExiste")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-danger my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("DisciplineExiste") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
<div  class="container-fluid mt-5"  >
                <div class="panel-body" align = "center" >
                  <div align = "left" >		   
		     <h3><a  href="AjouterDiscipline">Ajouter une discipline</a></h3>     
	    			</div>
  <table class="table table-striped">
  <thead class="table-dark">
    <tr>
      		<th class="col-sm-2">Nom de discipline</th>
            <th class="col-sm-12" >Description</th>
            <th class="col-sm-2">Modifier</th>
            <th class="col-sm-2">Supprimer</th>
            
   	</tr>
  </thead>
    
                
  <tbody>
   <c:forEach  items="${listDisciplines2}" var="Discipline">
      <tr >
		<td> <c:out value="${Discipline.nomDiscipline}"/></td>
		<td> <c:out value="${Discipline.description}"/> </td>
		<td>
			<a href="editDiscipline/${Discipline.id_discipline}">Modifier</a>
		</td>							    
		<td>
			<a href="deleteDiscipline/${Discipline.id_discipline}">Supprimer</a>
		</td>	
			
	</tr> 
   </c:forEach> 
  </tbody>
</table>
                    
  </div>
  </div> 
  
	<% session.removeAttribute("discplineSupprimer"); %>
	<% session.removeAttribute("DisciplineAjouter"); %>
	<% session.removeAttribute("DisciplineModifier"); %>
		<% session.removeAttribute("DisciplineExiste"); %>
	
	
	

	
</body>
</html>