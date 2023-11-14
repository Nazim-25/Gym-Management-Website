<%@ page language="java" contentType="text/html; charset=UTF-8"%>
    <%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Sports World</title>
<link rel="stylesheet" type="text/css" href=" /css/bootstrap.min.css"/>
<link rel="stylesheet" href="css/espaceco.css"/>

<body>

  <% session.removeAttribute("EmailCoachExiste"); %>
  <% session.removeAttribute("PasswordCoachWrong"); %>
  <% session.removeAttribute("NewCoachMdpWrong"); %>
  <% session.removeAttribute("SeanceAdded"); %>
  <% session.removeAttribute("ReservationDeleted"); %>
<% session.removeAttribute("a"); %>
		 
                        <ul>
                            <img height="44" width="194" src="images/logo.PNG" alt="SPORTS WORLD" />
                            <li><a href="logout">Deconnecter</a></li>
                           
                            
                          </ul>
                          <div class="sidebar">
                            <img src="images/coach.jpg" alt="Avatar" class="avatar"><br><br><hr><br>
                            <a class="active" href="ModifierInfoCoach/${id_coach}">Personaliser</a>
                            <a href="AffichePlanningCoach">planning</a>
                          </div>
                         <h2>Bienvenue Coach 
                         </h2>
                         	<% 
			            if( session.getAttribute("CoachModifier")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-success my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("CoachModifier") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
			            
			                    <% 
			            if( session.getAttribute("PasswordCoachChanged")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-success my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("PasswordCoachChanged") );
				       out.println("</div>");
				out.println("</div>");
			            } %>

                  <% 
			            if( session.getAttribute("success")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-success my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("success") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
                         

                         <% session.removeAttribute("CoachModifier"); %>
                         <% session.removeAttribute("PasswordCoachChanged"); %>
                         <% session.removeAttribute("success"); %>       
</body>
</html>
