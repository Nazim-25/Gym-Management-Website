<%@ page language="java" contentType="text/html; charset=UTF-8"%>
    <%@ page pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page import="java.time.LocalDate"%>
     <%@ page import="java.time.temporal.ChronoUnit"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sports World</title>

	<link rel="stylesheet" type="text/css" href=" /css/bootstrap.min.css"/>
		<script src= "https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" ></script>
	<link rel="stylesheet" type="text/css" href=" /css/listabonnement.css"/>
<link rel="stylesheet" type="text/css" href=" /css/bootstrap.min.css"/>
</head>
<body>


<c:set var="counter" value="1"  />
<div class="nav_bar">
        <ul>
       <img  height="44" width="194" src="/images/logo.PNG" alt="SPORTS WORLD" /> 
       <li><a href="logout">Déconnecter</a></li>
       <li><a href="EspaceAbonne">ACCUEIL</a></li>

     </ul>
   </div>
<div  class="container-fluid mt-5"  >
                <div class="panel-body" align = "center" >
                <h1 class="text-danger mt-5"  align="center" >La liste des Abonnements</h1>
  <table class="table table-striped">
  <thead class="table-dark">
    <tr>	<th>Abonnement</th>
      		<th >Disciplines</th>
            <th >DateFin</th>
           
            
   	</tr>
  </thead>
   				<div  class="text-white bg-dark" >Aujourd'hui :<c:out value="${today}"/></div>
  <tbody>
   <c:forEach  items="${ListIAB}" var="Abonnement">
       <tr >
       <td> Abonnement <c:out value="${counter}"  /> </td>
      		<td> 
	        <c:forEach  items="${Abonnement.getAbonnement().getAbonnementDisciplines()}" var="Disciplines">
	        
	        	<c:out value="${Disciplines.getDiscipline().getNomDiscipline()}"/><br>
	       
	        </c:forEach>
	        </td>
	       
			<td> 
			<c:out value="${Abonnement.dateFin}"/>
			</td>
			
	    	 
			<c:set var="counter" value="${counter+1}"  />
	</tr> 
	</c:forEach>    
  </tbody>
</table>
                    
  </div>
  </div> 
 
</body>
</html>