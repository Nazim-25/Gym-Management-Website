<%@ page language="java" contentType="text/html; charset=UTF-8"%>
    <%@ page pageEncoding="UTF-8" %>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
<title>Sports World</title>
<link rel="stylesheet" type="text/css" href=" /css/bootstrap.min.css"/>
<link rel="stylesheet" href="css/indexcoach.css">
		<script src= "https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" ></script>
</head>
<body>
<% session.removeAttribute("abonneDeleted"); %>
<div class="nav_bar">
        <ul>
       <img  height="44" width="194" src="images/logo.PNG" alt="SPORTS WORLD" /> 
       
       <li><a href="index">ACCUEIL</a></li>

     </ul>
   </div>
<h1 class="text-danger mt-5"  align="center" >La liste des coachs</h1>
<div  class="container-fluid mt-5"  >
                <div class="panel-body" align = "center" >
  <table class="table table-striped">
  <thead class="table-dark">
    <tr>
      		<th >Nom</th>
            <th >Prénom</th>
            <th >Age</th>
            <th >Genre</th>
            <th >Experience</th>
            <th >Disciplines</th>
            
   	</tr>
  </thead>
  <tbody>
   <c:forEach  items="${listCoachs}" var="Coach">
      <tr >
		<td> <c:out value="${Coach.lastname}"/> </td>
		<td> <c:out value="${Coach.firstname}"/> </td>
		<td> <c:out value="${Coach.age}"/> ans</td>
		<td> <c:out value="${Coach.genre}"/> </td>
		<td> <c:out value="${Coach.experience}"/> ans</td>
		<td>
		 <c:forEach  items="${listCD2}" var="CD">
		
		 	<c:if test="${Coach.id_coach eq CD.coach.id_coach}">
			 		<c:forEach  items="${listDisciplines2}" var="Disciplines">
			 		
					       <c:if test="${CD.discipline.id_discipline eq Disciplines.id_discipline }">
					       			<c:out value="${Disciplines.nomDiscipline }"/> <p></p>
					 		</c:if>
				
				 	  </c:forEach>
			 </c:if>
			
		</c:forEach>
	 	</td>
	</tr> 
   </c:forEach> 
  </tbody>
</table>
                    
  </div>
  </div> 
  
  
</body>
</html>