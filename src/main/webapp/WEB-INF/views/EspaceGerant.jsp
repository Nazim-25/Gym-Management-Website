<%@ page language="java" contentType="text/html; charset=UTF-8"%>
    <%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Sports world</title>
<link rel="stylesheet" type="text/css" href=" /css/bootstrap.min.css"/>
<link rel="stylesheet" href="css/espacege.css">
		<script src= "https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" ></script>
</head>
<body>
  <% session.removeAttribute("messageGerant"); %>
  <% session.removeAttribute("DisciplineAjouter"); %>
   <% session.removeAttribute("DisciplineExiste"); %>
   <% session.removeAttribute("SameEmailCoach"); %>
    <% session.removeAttribute("EmailCoachExiste"); %>
          <% session.removeAttribute("CoachAjouter"); %>
          	<% session.removeAttribute("CoachDeleted"); %>
          	 <% session.removeAttribute("CoachModifier"); %>
    <% session.removeAttribute("CoachDisciplineDelected"); %>
    <% session.removeAttribute("discplineSupprimer"); %>
     <% session.removeAttribute("DisciplineExiste"); %>
     	<% session.removeAttribute("DisciplineModifier"); %>
		<% session.removeAttribute("DisciplineAjouter"); %>
		<%session.removeAttribute("AbonnementSupprimer");%>
		<%session.removeAttribute("AbonnementModifier");%>
		<%session.removeAttribute("AbonnementAjouter");%>
		<%session.removeAttribute("AbonnementExiste");%>
		 <% session.removeAttribute("EmailGerantExiste"); %>
		 <%session.removeAttribute("AbonneModifier2");%>
		<%session.removeAttribute("AbonneSupprimé");%>
		 <% session.removeAttribute("AbonnementDisciplineAdded"); %>
		   <% session.removeAttribute("AbonnementDisciplineExiste"); %>
		   <% session.removeAttribute("DisciplineExiste"); %>
		   <% session.removeAttribute("DisciplineAjouter"); %>
		   <% session.removeAttribute("ReservationDeleted"); %>
		    <% session.removeAttribute("SeanceDeleted"); %>
		    <% session.removeAttribute("seanecAjouté"); %>
		    <% session.removeAttribute("seanecNotAdded"); %>
		    
          <% session.removeAttribute("NoSeances"); %>
            
               
                            <ul>
                                <img height="44" width="194" src="images/logo.PNG" alt="SPORTS WORLD" />
                                <li><a href="logout">Deconnecter</a></li>  
                     
                              </ul>
                              <div class="sidebar">
                                <img src="images/gerant.jpg" alt="Avatar" class="avatar"> <br><br><hr><br>
                                <a href="AfficheAbonnes" > Liste des abonnés </a>
                                <a href="AfficheCoachs"  > Liste des Coachs </a>
                                <a href="gereDisciplines">Gérer les disciplines </a>
                                <a href="gereAbonnements">Gérer les abonnements </a>
                                <a href="AffichePlanningGerant" >Consulter le planning </a>
                                <a href="ModifierInfoGerant/${id_gerant}">Personaliser</a>
                              </div>
                              <h2>Bienvenue,Qu'est-ce que voulez vous faire Monsieur? </h2>
                              
                                <% 
                            if( session.getAttribute("GerantModifier")!= null ){
                     out.println("<div class='container'>");
                            out.println(" <div  class='alert alert-success my-2 text-center' role='alert' >");
                            out.println( session.getAttribute("GerantModifier") );
                           out.println("</div>");
                    out.println("</div>");
                            } %>
                            
                            <% 
                            if( session.getAttribute("PasswordGerantChanged")!= null ){
                     out.println("<div class='container'>");
                            out.println(" <div  class='alert alert-success my-2 text-center' role='alert' >");
                            out.println( session.getAttribute("PasswordGerantChanged") );
                           out.println("</div>");
                    out.println("</div>");
                            } %>
                              <%session.removeAttribute("GerantModifier");%>
                            <% session.removeAttribute("PasswordGerantChanged"); %>
</body>
</html>
