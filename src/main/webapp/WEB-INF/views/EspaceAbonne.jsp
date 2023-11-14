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
<link rel="stylesheet" href="css/espaceabo.css">
</head>
<body>
		
<% session.removeAttribute("messageAbonne"); %>
<% session.removeAttribute("PasswordWrong"); %>
<% session.removeAttribute("NewMdpWrong"); %>
<% session.removeAttribute("messageErreur"); %>
<% session.removeAttribute("EmailAbonneExiste"); %>
<% session.removeAttribute("abonnementexiste"); %>
<% session.removeAttribute("ReservationEchec"); %>
<% session.removeAttribute("ReservationAdded"); %>
<% session.removeAttribute("ReservationAlreadyAdded"); %>
<% session.removeAttribute("AbonnementDied"); %>
<% session.removeAttribute("ReservationDeleted"); %>
<% session.removeAttribute("AbEX"); %>

 
                        <ul>
                            <img height="44" width="194" src="images/logo.PNG" alt="SPORTS WORLD" />
                            <li><a href="logout">Déconnecter</a></li>
                            
                            
                          </ul>
                         

                          <div class="sidebar">
                            <img src="images/abonné.jpg" alt="Avatar" class="avatar">
                          
                            <br><br><hr><br>
                        
                            <a href="AfficheAbonnements">Affiche les Abonnement</a>
                            <a href="AnnulerInscription">annuler l'inscription</a>
                            <a href="AffichePlanning" >Consulter le planning </a>
                            <a href="AfficheMesAbonnements" >Mes Abonnements </a>
                             <a href="ModifierInfo/${id_abonne}">Personaliser</a>
                          </div>
                          <h2>Bienvenue dans votre salle </h2>
                          <% 
			            if( session.getAttribute("AbonneModifier")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-success my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("AbonneModifier") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
						
					<% 
			            if( session.getAttribute("PasswordChanged")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-success my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("PasswordChanged") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
<% 
			            if( session.getAttribute("UserAdded")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-success my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("UserAdded") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
                         
                          <% session.removeAttribute("UserAdded"); %>
                          <% session.removeAttribute("PasswordChanged"); %>
                           <% session.removeAttribute("AbonneModifier"); %>
 
 </body>
</html>

               