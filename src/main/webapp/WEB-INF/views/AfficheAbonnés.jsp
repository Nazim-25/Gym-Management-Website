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
	<link rel="stylesheet" href="css/afficheabon.css">
	
		<script src= "https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" ></script>
	
</head>
<body>
<% session.removeAttribute("SameEmail"); %>
<% session.removeAttribute("abonneDeleted"); %>
<%session.removeAttribute("GerantModifier");%>
<%session.removeAttribute("EmailAbonneExiste2");%>
 <div class="nav_bar">
        <ul>
       <img  height="44" width="194" src="images/logo.PNG" alt="SPORTS WORLD" /> 
       <li><a  href="logout"  >Déconnecter </a></li>
       <li><a href="espacegerant">ACCUEIL</a></li>

     </ul>
   </div>

<div  class="container-fluid mt-5"  >
                <div class="panel-body" align = "center" >
                <h1 class="text-danger mt-5"  align="center" >La liste des abonnés</h1>
                
                
                	<% 
			            if( session.getAttribute("AbonneModifier2")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-success my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("AbonneModifier2") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
						<% 
						if( session.getAttribute("AbonneSupprimé")!= null ){
				 out.println("<div class='container'>");
						out.println(" <div  class='alert alert-success my-2 text-center' role='alert' >");
						out.println( session.getAttribute("AbonneSupprimé") );
					   out.println("</div>");
				out.println("</div>");
						} %>
			            
			            <% 
			            if( session.getAttribute("AbonneAdded")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-success my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("AbonneAdded") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
			            
			    	        <div align="right">
			           <form:form action="/AfficheAbonnes" method="get" modelAttribute="Recherche" >
			            <form:input path="Keyword"  type="text" id="textSearch" name="Keyword"/>
			            <form:input path="" type="submit" value="Rechercher"/>
			            </form:form>
			            </div>
			            
           <div align = "left" >		   
		     <h3><a  href="ajouterAbonne">Ajouter un abonne</a></h3>     
	    </div>
  <table class="table table-striped">
  <thead class="table-dark">
    <tr>
      		<th >Nom</th>
            <th >Prénom</th>
            <th >email</th>
            <th>Numero_Téléphone</th>
            <th>Nom d'utilisateur</th> 
            <th>date_naissance</th>
             <th>Modifier</th>
            <th>Supprimer</th>
           
            
   	</tr>
  </thead>
   
			    
	  
  <tbody>
   <c:forEach  items="${listAbonnés}" var="Abonne">
       <tr >
        <td> <c:out value="${Abonne.lastname}"/></td>
       	<td> <c:out value="${Abonne.firstname}"/> </td>
		<td> <c:out value="${Abonne.email}"/> </td>
		 <td> <c:out value="${Abonne.num_tele}"/></td>
		 <td> <c:out value="${Abonne.username}"/> </td>
       	<td> <c:out value="${Abonne.date_nais}"/> </td>
		
    	<td>
			<a href="edit/${Abonne.id_abonne}">Modifier</a>
		</td>							    
		<td>
			<a href="delete/${Abonne.id_abonne}">Supprimer</a>
		</td>		
     
	</tr> 
   </c:forEach> 
  </tbody>
</table>
                    
  </div>
  </div> 
	
	
  	<%session.removeAttribute("AbonneSupprimé");%>
	<%session.removeAttribute("AbonneModifier2");%>
	<%session.removeAttribute("AbonneAdded");%>
	
</body>
</html>