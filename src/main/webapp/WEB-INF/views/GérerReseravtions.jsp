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
		<script src= "https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" ></script>
</head>
<body>

<% session.removeAttribute("ReservationDeleted"); %>
<c:set var="counter" value="1"  />

         <a href="logout">Déconnecter</a>
        <a href="espacegerant">ACCUEIL</a>

        
    

  <div  class="container-fluid mt-5"  >
  
                <div class="panel-body" align = "center" >
                <h3 class="text-danger mt-5"  align="center" >Les reservations des abonnées du cette semaine</h3>
                <div align = "left" >		
                			<form:form align="right" action="/GérerReservations" method="get" modelAttribute="Recherche" >
					         <form:input  path="date"  type="date" id="textSearch" name="date"/>      
					         <form:input path=""  type="submit" value="Rechercher"/>
					         </form:form>
					 </div>
  <table class="table table-striped">
  <thead class="table-dark">
  
    <tr>	
    		<th>Réservation</th>
    	    <th>Nom Discipline</th>
      		<th >Nom de coach</th>
      		<th >Nom de l'abonné</th>
            <th >Date</th>
            <th >Heure_debut</th>
            <th >Heure_fin</th>
           <th >Action</th>
            
   	</tr>
  </thead>
   
			    
	   
  <tbody>
   <c:forEach  items="${ListSeancesReserver}" var="seance">
       <tr >
       <td> Reservation <c:out value="${counter}"  /> </td>
  
	       	<td> <c:out value="${seance.getSeance().getDiscipline().getNomDiscipline()}"/> </td>
			<td> <c:out value="${seance.getSeance().getCoach().getLastname()}"/>  <c:out value="${seance.getSeance().getCoach().getFirstname()}"/> </td>
			<td> <c:out value="${seance.getAbonne().getLastname()}"/>  <c:out value="${seance.getAbonne().getFirstname()}"/> </td>
			<td> <c:out value="${seance.getSeance().getDate()}"/> </td>
			<td> <c:out value="${seance.getSeance().getHeureDebut()}"/> </td>
			<td> <c:out value="${seance.getSeance().getHeureFin()}"/> </td>
			<td><a href="supprimerReservation/${seance.getSeance().getId_seance()}/delete/${seance.getId_inscriptionSéance()}" >Supprimer</a></td>
		<c:set var="counter" value="${counter+1}"  />
	</tr> 
	</c:forEach>    
  </tbody>
</table>
                    
  </div>
  </div> 
 
   <% session.removeAttribute("SeanceDeleted"); %>
	<% session.removeAttribute("SeanceAdded"); %>
<a href="espacegerant">Back...</a>
</body>
</html>