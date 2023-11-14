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
	<link rel="stylesheet" type="text/css" href=" /css/abonnemindex.css"/>
		<script src= "https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" ></script>
	
</head>
<body>


<c:set var="counter" value="1"  />

<div class="nav_bar">
        <ul>
       <img  height="44" width="194" src="images/logo.PNG" alt="SPORTS WORLD" /> 
       <li><a  href="logout"  >Déconnecter </a></li>
       <li><a href="espacegerant">ACCUEIL</a></li>

     </ul>
   </div>
<div  class="container-fluid mt-5"  >


  
                <div class="panel-body" align = "center" >
                <h1 class="text-danger mt-5"  align="center" >La liste des plannings</h1>
                
 				<div align = "left" >		
                			<form:form align="right" action="/AncienPlannings" method="get" modelAttribute="Recherche" >
					         <form:input  path="date"  type="date" id="textSearch" name="date"/>      
					         <form:input path=""  type="submit" value="Rechercher"/>
					         </form:form>
					 </div>
                <% 
			            if( session.getAttribute("NoSeances")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-danger my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("NoSeances") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
  <table class="table table-striped">
  <thead class="table-dark">
    <tr>	<th>Planning</th>
      		<th >Nombre de séances</th>
            <th >Date Début</th>
            <th >Date Fin</th>
           <th>Action</th>
            
   	</tr>
  </thead>
   
			    
	   
  <tbody>
   <c:forEach  items="${listPlannings}" var="Planning">
       <tr >
       <td> Planning <c:out value="${counter}"  /> </td>
      		<td> 
	      
	        
	        	<c:out value="${Planning.nbrSeance}"/> Séances<br>
	        
	      
	        </td>
	       	<td> <c:out value="${Planning.dateDebut}"/> </td>
			<td> <c:out value="${Planning.dateFin}"/></td>
			
	    	 <td>
				<a href="showPlanning/${Planning.id_planning}">Afficher</a>
			</td>
			<c:set var="counter" value="${counter+1}"  />
	</tr> 
	</c:forEach>    
  </tbody>
</table>
                    
  </div>
  </div> 
 
	<% session.removeAttribute("NoSeances"); %>

	
</body>
</html>