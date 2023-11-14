<%@ page language="java" contentType="text/html; charset=UTF-8"%>
    <%@ page pageEncoding="UTF-8" %>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sports World</title>
<link rel="stylesheet" type="text/css" href=" /css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href=" /css/ajouterdiscipline.css"/>
		<script src= "https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" ></script>
</head>
<body>
  		<% session.removeAttribute("DisciplineExiste"); %>
     	<% session.removeAttribute("DisciplineModifier"); %>
		<% session.removeAttribute("DisciplineAjouter"); %>
		<% session.removeAttribute("discplineSupprimer"); %>
		
		<div class="nav_bar">
        <ul>
       <img  height="44" width="194" src="/images/logo.PNG" alt="SPORTS WORLD" /> 
       <li><a  href="logout"  >Déconnecter </a></li>
       <li><a href="espacegerant">ACCUEIL</a></li>

     </ul>
   </div>
<div class="">
  <form:form action="/SaveDiscipline" method="post" modelAttribute="discipline" class="firm">
   <h1>Modifiez la discipline</h1>
   
   <form:label class="form-label" path="nomDiscipline">Nom de discipline</form:label>
   <form:input class="form-control" type="text" path="nomDiscipline" required="required" />
    
   <form:label class="form-label" path="description">description</form:label>
    <form:textarea class="form-control z-depth-1" id="exampleFormControlTextarea6" path="description" name="text" rows = "15" cols = "45"></form:textarea>
   	
   <form:input class="btn btn-info" path="" type="submit" value="Modifier" />
   
  </form:form>
 </div>
   <% session.removeAttribute("DisciplineExiste"); %>
   
   
 	<script src= "https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" ></script>
 
</body>
</html>