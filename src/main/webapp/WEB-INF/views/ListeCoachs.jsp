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
<link rel="stylesheet" href="css/listecoach.css">
		<script src= "https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" ></script>
</head>
<body>

   <% session.removeAttribute("DisciplineAjouter"); %>
   <% session.removeAttribute("DisciplineExiste"); %>
   <% session.removeAttribute("SameEmailCoach"); %>
     <%session.removeAttribute("GerantModifier");%>
    <% session.removeAttribute("PasswordGerantChanged"); %>
    <div class="nav_bar">
        <ul>
       <img  height="44" width="194" src="images/logo.PNG" alt="SPORTS WORLD" /> 
       <li><a  href="logout"  >Déconnecter </a></li>
       <li><a href="espacegerant">ACCUEIL</a></li>

     </ul>
   </div>
  
    

			            
			          
			            
			                    
			  		
			            
<h1 class="text-danger mt-5"  align="center" >La liste des coachs</h1><hr>
<div  class="container-fluid mt-5"  >
					
					
                <div class="panel-body" align = "center" >
                    <% 
			            if( session.getAttribute("CoachDeleted")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-success my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("CoachDeleted") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
			                 <% 
			            if( session.getAttribute("CoachModifier")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-success my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("CoachModifier") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
							
			      
			            
                 <div align = "left" >		
                			<form:form align="right" action="/AfficheCoachs" method="get" modelAttribute="Recherche" >
					         <form:input  path="Keyword"  type="text" id="textSearch" name="Keyword"/>      
					         <form:input path=""  type="submit" value="Rechercher"/>
					         </form:form>
		     <h3><a  href="ajouterCoach">Ajouter un coach</a></h3>     
	    </div>
  <table class="table table-striped">
  <thead class="table-dark">
    <tr>
      		<th >Nom</th>
            <th >Prenom</th>
            <th >Email</th>
            <th >Age</th>
            <th >Genre</th>
            <th >salaire</th>
            <th >Experience</th>
            <th >Disciplines</th>
             <th>Modifier</th>
            <th>Supprimer</th>
            
   	</tr>
  </thead>
  <strong><b></b>
   <c:forEach  items="${listOfCoachs}" var="Coach">
      <tr >
		<td> <c:out value="${Coach.lastname}"/> </td>
		<td> <c:out value="${Coach.firstname}"/> </td>
		<td> <c:out value="${Coach.email}"/> </td>
		<td> <c:out value="${Coach.age}"/> ans</td>
		<td> <c:out value="${Coach.genre}"/> </td>
		<td> <c:out value="${Coach.salaire}"/> DA </td>
		<td> <c:out value="${Coach.experience}"/> ans</td>
		<td>
		 <c:forEach  items="${listCoachD}" var="CD">
		
		 	<c:if test="${Coach.id_coach eq CD.coach.id_coach}">
			 		<c:forEach  items="${listOfDisciplines}" var="Disciplines">
			 		
					       <c:if test="${CD.discipline.id_discipline eq Disciplines.id_discipline }">
					       			<c:out value="${Disciplines.nomDiscipline }"/> <p></p>
					 		</c:if>
				
				 	  </c:forEach>
			 </c:if>
			
		</c:forEach>
	 	</td>
	 	<td>
			<a href="editCoach/${Coach.id_coach}">Modifier</a>
		</td>							    
		<td>
			<a href="deleteCoach/${Coach.id_coach}">Supprimer</a>
		</td>	
	</tr> 
   </c:forEach> 
  </tbody>
</table>
                    
  </div>
  </div> 
  
  	<% session.removeAttribute("CoachDeleted"); %>
        <% session.removeAttribute("CoachAjouter"); %>
         <% session.removeAttribute("CoachModifier"); %>
         
   
</body>
</html>