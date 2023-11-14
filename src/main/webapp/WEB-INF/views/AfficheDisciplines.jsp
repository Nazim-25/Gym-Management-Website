<%@ page language="java" contentType="text/html; charset=UTF-8"%>
    <%@ page pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
<title>Sports World</title>

	<link rel="stylesheet" type="text/css" href=" /css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href=" /css/displineindex.css"/>
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
<h1 class="text-danger mt-5"  align="center" >La liste des disciplines</h1><hr>
<div  class="container-fluid mt-5"  >
                <div class="panel-body" align = "center" >
  <table class="table table-striped">
  <thead class="table-dark">
    <tr>
      		<th class="col-sm-2">Nom de discipline</th>
            <th class="col-sm-12" >Description</th>
            
   	</tr>
  </thead>
    
                
  <tbody>
   <c:forEach  items="${listDisciplines}" var="Discipline">
      <tr >
		<td> <c:out value="${Discipline.nomDiscipline}"/></td>
		<td> <c:out value="${Discipline.description}"/> </td>
		
			
	</tr> 
   </c:forEach> 
  </tbody>
</table>
                    
  </div>
  </div> 
 
	

</body>
</html>