<%@ page language="java" contentType="text/html; charset=UTF-8"%>
    <%@ page pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ page import="java.time.LocalDate"%>
     <%@ page import="java.time.temporal.ChronoUnit"%>
      <%@ page  import="java.time.LocalTime"%>
      <%@ page  import="java.util.List"%>
    <%@ page  import="com.example.demo.DAO.Seance"%>
     <%@ page  import="com.example.demo.Metier.Operations"%>
    <%@ page  import="com.example.demo.Services.VisiteurService"%>
 <%@ page  import="org.springframework.beans.factory.annotation.Autowired"%>

    
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sports World</title>

	<link rel="stylesheet" type="text/css" href=" /css/bootstrap.min.css"/>
		<script src= "https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" ></script>
	<style>
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}
th, td {
  padding: 15px;
}
</style>
</head>
<body>

<% session.removeAttribute("NoSeances"); %>
<c:set var="counter" value="1"  />
<a  href="logout"  class="btn btn-primary">Déconnecter </a>
<a  href="GérerReservations"  class="btn btn-primary">Gérer les Reservations </a>
<a  href="AncienPlannings"  class="btn btn-primary">Ancien plannings </a>


<%LocalDate today=LocalDate.now();  LocalDate todayplus1=today.plusDays(1);  LocalDate todayplus2=today.plusDays(2);%>
<%LocalDate todayplus3=today.plusDays(3);  LocalDate todayplus4=today.plusDays(4); %>

<%LocalDate todayplus5=today.plusDays(5);  LocalDate todayplus6=today.plusDays(6); %>

<% java.time.DayOfWeek dayOfWeek1 = today.getDayOfWeek(); java.time.DayOfWeek dayOfWeek2 = todayplus1.getDayOfWeek();%>

<%java.time.DayOfWeek dayOfWeek3 = todayplus2.getDayOfWeek(); java.time.DayOfWeek dayOfWeek4 = todayplus3.getDayOfWeek();%>

<%java.time.DayOfWeek dayOfWeek5 = todayplus4.getDayOfWeek(); java.time.DayOfWeek dayOfWeek6 = todayplus5.getDayOfWeek(); %>
<%java.time.DayOfWeek dayOfWeek7 = todayplus6.getDayOfWeek(); %>

<%LocalTime a9=LocalTime.of(9,00);LocalTime a11=LocalTime.of(11,00);LocalTime a13=LocalTime.of(13,00); LocalTime a15=LocalTime.of(15,00); %>
<% LocalTime a17=LocalTime.of(17,00);%>



		

<% String AddSeance="AddSeance"; %>
<% boolean b=false; %>

<%	List<Seance> ListSeances=(List<Seance>)session.getAttribute("ListSeances"); %>



			            


<div class="panel-body" align = "center" >
                <h3 class="text-danger mt-5"  align="center" >Le planning hebdomadaire</h3>
</div>
 <c:set var="existe" scope = "session" value="${a}"  />
 <c:if test = "${a != null }">
         <   <form:form action="/SaveNewSeanceByGerant" method="post" modelAttribute="coach">
                 <table style="with: 80%">
                  		<tr>
                  		<select name="coach">
							    <c:forEach items="${coachs}" var="coach">
							       <option value="${coach.getId_coach()}" >
									  ${coach.getLastname()}   ${coach.getFirstname()}
									</option>
							    </c:forEach>
						</select>
							
					    <select name="discipline">
							    <c:forEach items="${disciplines}" var="Discipline">
							       <option value="${Discipline.getId_discipline()}" >
									    ${Discipline.getNomDiscipline()}
									</option>
							    </c:forEach>
							</select>
					    </tr>
					    <tr>
					     <form:input class="btn btn-info" path="" type="submit" value="ajouter" />
					     </tr>
					    
					    
					     </table>
	                 
	   
	 	 </form:form>
      </c:if>
      <% 
			            if( session.getAttribute("seanecNotAdded")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-danger my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("seanecNotAdded") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
 				<% 
			            if( session.getAttribute("SeanceAdded")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-success my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("SeanceAdded") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
			            
 				<% 
			            if( session.getAttribute("seanecAjouté")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-success my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("seanecAjouté") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
			            
			            <% 
			            if( session.getAttribute("SeanceDeleted")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-success my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("SeanceDeleted") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
			            
			            <% 
			            if( session.getAttribute("ReservationDeleted")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-success my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("ReservationDeleted") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
 

<table border="1" style="width:95%">
<tr>
	<td></td>
    <td><%=dayOfWeek1.toString()%> <br> <%=today%> </td>
    <td><%=dayOfWeek2.toString()%>  <br> <%=todayplus1%></td>
    <td><%=dayOfWeek3.toString()%>  <br> <%=todayplus2%></td>
    <td><%=dayOfWeek4.toString()%>  <br> <%=todayplus3%></td>
     <td><%=dayOfWeek5.toString()%>  <br> <%=todayplus4%></td>
    <td><%=dayOfWeek6.toString()%>  <br> <%=todayplus5%></td>
    <td><%=dayOfWeek7.toString()%>  <br> <%=todayplus6%></td>
</tr>

 <tr>

	 <td>09:00-11:00</td> 
	 <td> 
	 <%
	 b=false;
	 for (Seance ls:ListSeances){
		
    	LocalDate date1=ls.getDate();
    	long day=ChronoUnit.DAYS.between(date1 , today);
    	long month=ChronoUnit.MONTHS.between(date1 , today);
    	long year=ChronoUnit.YEARS.between(date1 , today);

			if(ls.getHeureDebut()==a9 && day==0 && month==0 && year==0){
				out.println(ls.getDiscipline().getNomDiscipline() +"<br> par "+ls.getCoach().getLastname() +" "+ls.getCoach().getFirstname());
				b=true;
				break;
			}
	 }
	 if(!b){
			out.println("<a href="+AddSeance+"/"+today+"/"+a9+">Ajouter une séance </a>");
		}
			%> 
 	</td> 
	 <td> 	
	 <%
	 b=false;
	 for (Seance ls:ListSeances){
			 
			LocalDate date1=ls.getDate();
		 	long day1=ChronoUnit.DAYS.between(date1 , todayplus1);
	 		long month1=ChronoUnit.MONTHS.between(date1 , todayplus1);
	 		long year1=ChronoUnit.YEARS.between(date1 , todayplus1);
	 		
			if(ls.getHeureDebut()==a9 && day1==0 && month1==0 && year1==0){
			out.println(ls.getDiscipline().getNomDiscipline() +"<br> par "+ls.getCoach().getLastname() +" "+ls.getCoach().getFirstname());
			b=true;
			break;
		
	 	}
	 }if(!b){
			out.println("<a href="+AddSeance+"/"+todayplus1+"/"+a9+">Ajouter une séance </a>");
		}
		 %>
	 
	</td>
	<td>
	<%
	 b=false;
	 	for (Seance ls:ListSeances){
	 
	 		LocalDate date1=ls.getDate();
	 		long day2=ChronoUnit.DAYS.between(date1 , todayplus2);
	    	long month2=ChronoUnit.MONTHS.between(date1 , todayplus2);
	    	long year2=ChronoUnit.YEARS.between(date1 , todayplus2);
		if(ls.getHeureDebut()==a9 && day2==0 && month2==0 && year2==0){
			out.println(ls.getDiscipline().getNomDiscipline() +"<br> par "+ls.getCoach().getLastname() +" "+ls.getCoach().getFirstname());
			b=true;
			break;
		}
		
	 }
	 if(!b){
			out.println("<a href="+AddSeance+"/"+todayplus2+"/"+a9+">Ajouter une séance </a>");
		}
		 %>
</td>  
 <td><%
 		b=false;
	 	for (Seance ls:ListSeances){
	 		
	 		LocalDate date1=ls.getDate();
	 		long day3=ChronoUnit.DAYS.between(date1 , todayplus3);
	    	long month3=ChronoUnit.MONTHS.between(date1 , todayplus3);
	    	long year3=ChronoUnit.YEARS.between(date1 , todayplus3);
		if(ls.getHeureDebut()==a9 && day3==0 && month3==0 && year3==0){
			out.println(ls.getDiscipline().getNomDiscipline() +"<br> par "+ls.getCoach().getLastname() +" "+ls.getCoach().getFirstname());
			b=true;
			break;
		}
	  }
		 if(!b){
				out.println("<a href="+AddSeance+"/"+todayplus3+"/"+a9+">Ajouter une séance </a>");
			}
		 
		 %>
</td>  

 <td>  
 <%
 		b=false;
	 	for (Seance ls:ListSeances){
	 	
	 		LocalDate date1=ls.getDate();
	 		long day4=ChronoUnit.DAYS.between(date1 , todayplus4);
	    	long month4=ChronoUnit.MONTHS.between(date1 , todayplus4);
	    	long year4=ChronoUnit.YEARS.between(date1 , todayplus4);
		if(ls.getHeureDebut()==a9 && day4==0 && month4==0 && year4==0){
				out.println(ls.getDiscipline().getNomDiscipline() +"<br> par "+ls.getCoach().getLastname() +" "+ls.getCoach().getFirstname());
				b=true;
				break;
			}
	 	 }
	 if(!b){
			out.println("<a href="+AddSeance+"/"+todayplus4+"/"+a9+">Ajouter une séance </a>");
		}
		 %>
 </td> 
 <td> 
  <%
  		b=false;
	 	for (Seance ls:ListSeances){
	 		
	 		LocalDate date1=ls.getDate();
	 		long day5=ChronoUnit.DAYS.between(date1 , todayplus5);
	    	long month5=ChronoUnit.MONTHS.between(date1 , todayplus5);
	    	long year5=ChronoUnit.YEARS.between(date1 , todayplus5);
			if(ls.getHeureDebut()==a9 && day5==0 && month5==0 && year5==0){
				out.println(ls.getDiscipline().getNomDiscipline() +"<br> par "+ls.getCoach().getLastname() +" "+ls.getCoach().getFirstname());
				b=true;
				break;
			}
		 }
		  if(!b){
				out.println("<a href="+AddSeance+"/"+todayplus5+"/"+a9+">Ajouter une séance </a>");
			}
		 %>
</td> 
 <td>  
 <%
		 b=false;
	 	for (Seance ls:ListSeances){
	 		LocalDate date1=ls.getDate();
	 		long day6=ChronoUnit.DAYS.between(date1 , todayplus6);
	    	long month6=ChronoUnit.MONTHS.between(date1 , todayplus6);
	    	long year6=ChronoUnit.YEARS.between(date1 , todayplus6);
			if(ls.getHeureDebut()==a9 && day6==0 && month6==0 && year6==0){
				out.println(ls.getDiscipline().getNomDiscipline() +"<br> par "+ls.getCoach().getLastname() +" "+ls.getCoach().getFirstname());
				b=true;
				break;
				} 	
				
			}
 if(!b){
		out.println("<a href="+AddSeance+"/"+todayplus6+"/"+a9+">Ajouter une séance </a>");
	}
		 %>

</td>  
 </tr>  
<tr> 
 <td>11:00-13:00</td> 
  <td> 
	 <% b=false;
	 for (Seance ls:ListSeances){
		  
    	LocalDate date1=ls.getDate();
    	long day=ChronoUnit.DAYS.between(date1 , today);
    	long month=ChronoUnit.MONTHS.between(date1 , today);
    	long year=ChronoUnit.YEARS.between(date1 , today);

			if(ls.getHeureDebut()==a11 && day==0 && month==0 && year==0){
				out.println(ls.getDiscipline().getNomDiscipline() +"<br> par "+ls.getCoach().getLastname() +" "+ls.getCoach().getFirstname());
				 b=true;
				break;
			}
	 }
	 if(!b){
			out.println("<a href="+AddSeance+"/"+today+"/"+a11+">Ajouter une séance </a>");
		}
			%> 
 	</td> 
	 <td> 	
	 <%
	 b=false;
	 for (Seance ls:ListSeances){
			LocalDate date1=ls.getDate();
		 	long day1=ChronoUnit.DAYS.between(date1 , todayplus1);
	 		long month1=ChronoUnit.MONTHS.between(date1 , todayplus1);
	 		long year1=ChronoUnit.YEARS.between(date1 , todayplus1);
	 		
			if(ls.getHeureDebut()==a11 && day1==0 && month1==0 && year1==0){
			out.println(ls.getDiscipline().getNomDiscipline() +"<br> par "+ls.getCoach().getLastname() +" "+ls.getCoach().getFirstname());
			 b=true;
			break;
		}
	 }if(!b){
			out.println("<a href="+AddSeance+"/"+todayplus1+"/"+a11+">Ajouter une séance </a>");
		}
		 %>
	 
	</td>
	<td>
	<%
	 b=false;
	 	for (Seance ls:ListSeances){
	 		LocalDate date1=ls.getDate();
	 		long day2=ChronoUnit.DAYS.between(date1 , todayplus2);
	    	long month2=ChronoUnit.MONTHS.between(date1 , todayplus2);
	    	long year2=ChronoUnit.YEARS.between(date1 , todayplus2);
		if(ls.getHeureDebut()==a11 && day2==0 && month2==0 && year2==0){
			out.println(ls.getDiscipline().getNomDiscipline() +"<br> par "+ls.getCoach().getLastname() +" "+ls.getCoach().getFirstname());
			 b=true;
			break;
		}
	 	 }
	 	if(!b){
			out.println("<a href="+AddSeance+"/"+todayplus2+"/"+a11+">Ajouter une séance </a>");
		}
		 %>
</td>  
 <td><%
 b=false;
	 	for (Seance ls:ListSeances){
	 		LocalDate date1=ls.getDate();
	 		long day3=ChronoUnit.DAYS.between(date1 , todayplus3);
	    	long month3=ChronoUnit.MONTHS.between(date1 , todayplus3);
	    	long year3=ChronoUnit.YEARS.between(date1 , todayplus3);
		if(ls.getHeureDebut()==a11 && day3==0 && month3==0 && year3==0){
			out.println(ls.getDiscipline().getNomDiscipline() +"<br> par "+ls.getCoach().getLastname() +" "+ls.getCoach().getFirstname());
			 b=true;
			break;
		}
	 	 }
	 	if(!b){
			out.println("<a href="+AddSeance+"/"+todayplus3+"/"+a11+">Ajouter une séance </a>");
		}
		 %>
</td>  

 <td>  
 <%
 		b=false;
	 	for (Seance ls:ListSeances){
	 		LocalDate date1=ls.getDate();
	 		long day4=ChronoUnit.DAYS.between(date1 , todayplus4);
	    	long month4=ChronoUnit.MONTHS.between(date1 , todayplus4);
	    	long year4=ChronoUnit.YEARS.between(date1 , todayplus4);
		if(ls.getHeureDebut()==a11 && day4==0 && month4==0 && year4==0){
				out.println(ls.getDiscipline().getNomDiscipline() +"<br> par "+ls.getCoach().getLastname() +" "+ls.getCoach().getFirstname());
				 b=true;
				break;
			}
	 	 }if(!b){
				out.println("<a href="+AddSeance+"/"+todayplus4+"/"+a11+">Ajouter une séance </a>");
			}
		 %>
 </td> 
 <td> 
  <%
 			 b=false;
		 	for (Seance ls:ListSeances){
	 		LocalDate date1=ls.getDate();
	 		long day5=ChronoUnit.DAYS.between(date1 , todayplus5);
	    	long month5=ChronoUnit.MONTHS.between(date1 , todayplus5);
	    	long year5=ChronoUnit.YEARS.between(date1 , todayplus5);
			if(ls.getHeureDebut()==a11 && day5==0 && month5==0 && year5==0){
				out.println(ls.getDiscipline().getNomDiscipline() +"<br> par "+ls.getCoach().getLastname() +" "+ls.getCoach().getFirstname());
				 b=true;
				break;
			}
				 	 }if(!b){
							out.println("<a href="+AddSeance+"/"+todayplus5+"/"+a11+">Ajouter une séance </a>");
						}
		 %>
</td> 
 <td>  
 <%
 		b=false;
	 	for (Seance ls:ListSeances){
	 		LocalDate date1=ls.getDate();
	 		long day6=ChronoUnit.DAYS.between(date1 , todayplus6);
	    	long month6=ChronoUnit.MONTHS.between(date1 , todayplus6);
	    	long year6=ChronoUnit.YEARS.between(date1 , todayplus6);
			if(ls.getHeureDebut()==a11 && day6==0 && month6==0 && year6==0){
				out.println(ls.getDiscipline().getNomDiscipline() +"<br> par "+ls.getCoach().getLastname() +" "+ls.getCoach().getFirstname());
				 b=true;
				break;
				}	 	
			}
	 	if(!b){
			out.println("<a href="+AddSeance+"/"+todayplus6+"/"+a11+">Ajouter une séance </a>");
		}
		 %>

</td>  
 </tr> 
<tr> 
 <td>13:00-15:00</td>  
  <td> 
	 <%
	 b=false;
	 for (Seance ls:ListSeances){
		  
    	LocalDate date1=ls.getDate();
    	long day=ChronoUnit.DAYS.between(date1 , today);
    	long month=ChronoUnit.MONTHS.between(date1 , today);
    	long year=ChronoUnit.YEARS.between(date1 , today);

			if(ls.getHeureDebut()==a13 && day==0 && month==0 && year==0){
				out.println(ls.getDiscipline().getNomDiscipline() +"<br> par "+ls.getCoach().getLastname() +" "+ls.getCoach().getFirstname());
				 b=true;
				break;
			}
	 }
	 if(!b){
			out.println("<a href="+AddSeance+"/"+today+"/"+a13+">Ajouter une séance </a>");
		}
			%> 
 	</td> 
	 <td> 	
	 <%
	 b=false;
	 for (Seance ls:ListSeances){
			LocalDate date1=ls.getDate();
		 	long day1=ChronoUnit.DAYS.between(date1 , todayplus1);
	 		long month1=ChronoUnit.MONTHS.between(date1 , todayplus1);
	 		long year1=ChronoUnit.YEARS.between(date1 , todayplus1);
	 		
			if(ls.getHeureDebut()==a13 && day1==0 && month1==0 && year1==0){
			out.println(ls.getDiscipline().getNomDiscipline() +"<br> par "+ls.getCoach().getLastname() +" "+ls.getCoach().getFirstname());
			 b=true;
			break;
		}
	 }
	 if(!b){
			out.println("<a href="+AddSeance+"/"+todayplus1+"/"+a13+">Ajouter une séance </a>");
		}
		 %>
	 
	</td>
	<td>
	<%
	 b=false;
	 	for (Seance ls:ListSeances){
	 		LocalDate date1=ls.getDate();
	 		long day2=ChronoUnit.DAYS.between(date1 , todayplus2);
	    	long month2=ChronoUnit.MONTHS.between(date1 , todayplus2);
	    	long year2=ChronoUnit.YEARS.between(date1 , todayplus2);
		if(ls.getHeureDebut()==a13 && day2==0 && month2==0 && year2==0){
			out.println(ls.getDiscipline().getNomDiscipline() +"<br> par "+ls.getCoach().getLastname() +" "+ls.getCoach().getFirstname());
			 b=true;
			break;
		}
	 	 }
	 	if(!b){
			out.println("<a href="+AddSeance+"/"+todayplus2+"/"+a13+">Ajouter une séance </a>");
		}
		 %>
</td>  
 <td><%
 		b=false;
	 	for (Seance ls:ListSeances){
	 		LocalDate date1=ls.getDate();
	 		long day3=ChronoUnit.DAYS.between(date1 , todayplus3);
	    	long month3=ChronoUnit.MONTHS.between(date1 , todayplus3);
	    	long year3=ChronoUnit.YEARS.between(date1 , todayplus3);
		if(ls.getHeureDebut()==a13 && day3==0 && month3==0 && year3==0){
			out.println(ls.getDiscipline().getNomDiscipline() +"<br> par "+ls.getCoach().getLastname() +" "+ls.getCoach().getFirstname());
			 b=true;
			break;
		}
	 	 }
	 	if(!b){
			out.println("<a href="+AddSeance+"/"+todayplus3+"/"+a13+">Ajouter une séance </a>");
		}
		 %>
</td>  

 <td>  
 <%
 b=false;
	 	for (Seance ls:ListSeances){
	 		LocalDate date1=ls.getDate();
	 		long day4=ChronoUnit.DAYS.between(date1 , todayplus4);
	    	long month4=ChronoUnit.MONTHS.between(date1 , todayplus4);
	    	long year4=ChronoUnit.YEARS.between(date1 , todayplus4);
		if(ls.getHeureDebut()==a13 && day4==0 && month4==0 && year4==0){
				out.println(ls.getDiscipline().getNomDiscipline() +"<br> par "+ls.getCoach().getLastname() +" "+ls.getCoach().getFirstname());
				 b=true;
				break;
			}
	 	 }
	 	if(!b){
			out.println("<a href="+AddSeance+"/"+todayplus4+"/"+a13+">Ajouter une séance </a>");
		}
		 %>
 </td> 
 <td> 
  <%
  		b=false;
	 	for (Seance ls:ListSeances){
	 		LocalDate date1=ls.getDate();
	 		long day5=ChronoUnit.DAYS.between(date1 , todayplus5);
	    	long month5=ChronoUnit.MONTHS.between(date1 , todayplus5);
	    	long year5=ChronoUnit.YEARS.between(date1 , todayplus5);
			if(ls.getHeureDebut()==a13 && day5==0 && month5==0 && year5==0){
				out.println(ls.getDiscipline().getNomDiscipline() +"<br> par "+ls.getCoach().getLastname() +" "+ls.getCoach().getFirstname());
				 b=true;
				break;
			}
				 	 }
	 	if(!b){
			out.println("<a href="+AddSeance+"/"+todayplus5+"/"+a13+">Ajouter une séance </a>");
		}
		 %>
</td> 
 <td>  
 <%
 		b=false;
	 	for (Seance ls:ListSeances){
	 		LocalDate date1=ls.getDate();
	 		long day6=ChronoUnit.DAYS.between(date1 , todayplus6);
	    	long month6=ChronoUnit.MONTHS.between(date1 , todayplus6);
	    	long year6=ChronoUnit.YEARS.between(date1 , todayplus6);
			if(ls.getHeureDebut()==a13 && day6==0 && month6==0 && year6==0){
				out.println(ls.getDiscipline().getNomDiscipline() +"<br> par "+ls.getCoach().getLastname() +" "+ls.getCoach().getFirstname());
				 b=true;
				break;
				}	 	
			}
	 	if(!b){
			out.println("<a href="+AddSeance+"/"+todayplus6+"/"+a13+">Ajouter une séance </a>");
		}
		 %>

</td>  
</tr>   
<tr>    
 <td>15:00-17:00 </td>   
 
 
  <td> 
   
	 <%
		b=false;
	 for (Seance ls:ListSeances){
		  
    	LocalDate date1=ls.getDate();
    	long day=ChronoUnit.DAYS.between(date1 , today);
    	long month=ChronoUnit.MONTHS.between(date1 , today);
    	long year=ChronoUnit.YEARS.between(date1 , today);

			if(ls.getHeureDebut()==a15 && day==0 && month==0 && year==0){
				out.println(ls.getDiscipline().getNomDiscipline() +"<br> par "+ls.getCoach().getLastname() +" "+ls.getCoach().getFirstname());
				b=true;
				break;
			}
	 }
	 if(!b){
			out.println("<a href="+AddSeance+"/"+today+"/"+a15+">Ajouter une séance </a>");
		}
			%> 
 	</td> 
	 <td> 	
	 <%
	 b=false;
	 for (Seance ls:ListSeances){
			LocalDate date1=ls.getDate();
		 	long day1=ChronoUnit.DAYS.between(date1 , todayplus1);
	 		long month1=ChronoUnit.MONTHS.between(date1 , todayplus1);
	 		long year1=ChronoUnit.YEARS.between(date1 , todayplus1);
	 		
			if(ls.getHeureDebut()==a15 && day1==0 && month1==0 && year1==0){
			out.println(ls.getDiscipline().getNomDiscipline() +"<br> par "+ls.getCoach().getLastname() +" "+ls.getCoach().getFirstname());
			 b=true;
			break;
		}
	 }
	 if(!b){
			out.println("<a href="+AddSeance+"/"+todayplus1+"/"+a15+">Ajouter une séance </a>");
		}
		 %>
	 
	</td>
	<td>
	<%
	 b=false;
	 	for (Seance ls:ListSeances){
	 		LocalDate date1=ls.getDate();
	 		long day2=ChronoUnit.DAYS.between(date1 , todayplus2);
	    	long month2=ChronoUnit.MONTHS.between(date1 , todayplus2);
	    	long year2=ChronoUnit.YEARS.between(date1 , todayplus2);
		if(ls.getHeureDebut()==a15 && day2==0 && month2==0 && year2==0){
			out.println(ls.getDiscipline().getNomDiscipline() +"<br> par "+ls.getCoach().getLastname() +" "+ls.getCoach().getFirstname());
			 b=true;
			break;
		}
	 	 }
	 	 if(!b){
				out.println("<a href="+AddSeance+"/"+todayplus2+"/"+a15+">Ajouter une séance </a>");
			}
		 %>
</td>  
 <td><%
 		b=false;
	 	for (Seance ls:ListSeances){
	 		LocalDate date1=ls.getDate();
	 		long day3=ChronoUnit.DAYS.between(date1 , todayplus3);
	    	long month3=ChronoUnit.MONTHS.between(date1 , todayplus3);
	    	long year3=ChronoUnit.YEARS.between(date1 , todayplus3);
		if(ls.getHeureDebut()==a15 && day3==0 && month3==0 && year3==0){
			out.println(ls.getDiscipline().getNomDiscipline() +"<br> par "+ls.getCoach().getLastname() +" "+ls.getCoach().getFirstname());
			 b=true;
			break;
		}
	 	 }
	 	 if(!b){
				out.println("<a href="+AddSeance+"/"+todayplus3+"/"+a15+">Ajouter une séance </a>");
			}
		 %>
</td>  

 <td>  
 <%
 b=false;
	 	for (Seance ls:ListSeances){
	 		LocalDate date1=ls.getDate();
	 		long day4=ChronoUnit.DAYS.between(date1 , todayplus4);
	    	long month4=ChronoUnit.MONTHS.between(date1 , todayplus4);
	    	long year4=ChronoUnit.YEARS.between(date1 , todayplus4);
		if(ls.getHeureDebut()==a15 && day4==0 && month4==0 && year4==0){
				out.println(ls.getDiscipline().getNomDiscipline() +"<br> par "+ls.getCoach().getLastname() +" "+ls.getCoach().getFirstname());
				 b=true;
				break;
			}
	 	 }
	 	 if(!b){
				out.println("<a href="+AddSeance+"/"+todayplus4+"/"+a15+">Ajouter une séance </a>");
			}
		 %>
 </td> 
 <td> 
  <%
  		b=false;
	 	for (Seance ls:ListSeances){
	 		LocalDate date1=ls.getDate();
	 		long day5=ChronoUnit.DAYS.between(date1 , todayplus5);
	    	long month5=ChronoUnit.MONTHS.between(date1 , todayplus5);
	    	long year5=ChronoUnit.YEARS.between(date1 , todayplus5);
			if(ls.getHeureDebut()==a15 && day5==0 && month5==0 && year5==0){
				out.println(ls.getDiscipline().getNomDiscipline() +"<br> par "+ls.getCoach().getLastname() +" "+ls.getCoach().getFirstname());
				 b=true;
				break;
			}
				 	 }
	 	 if(!b){
				out.println("<a href="+AddSeance+"/"+todayplus5+"/"+a15+">Ajouter une séance </a>");
			}
		 %>
</td> 
 <td>  
 <%
 		b=false;
	 	for (Seance ls:ListSeances){
	 		LocalDate date1=ls.getDate();
	 		long day6=ChronoUnit.DAYS.between(date1 , todayplus6);
	    	long month6=ChronoUnit.MONTHS.between(date1 , todayplus6);
	    	long year6=ChronoUnit.YEARS.between(date1 , todayplus6);
			if(ls.getHeureDebut()==a15 && day6==0 && month6==0 && year6==0){
				out.println(ls.getDiscipline().getNomDiscipline() +"<br> par "+ls.getCoach().getLastname() +" "+ls.getCoach().getFirstname());
				 b=true;
				break;
				}	 	
			}
	 	 if(!b){
				out.println("<a href="+AddSeance+"/"+todayplus6+"/"+a15+">Ajouter une séance </a>");
			}
		 %>

</td>  

</tr>
 </table>      
 
 
 
 
 <div  class="container-fluid mt-5"  >
                <div class="panel-body" align = "center" >
                <h3 class="text-danger mt-5"  align="center" >Les séances de cette semaine</h3>
  <table class="table table-striped">
  <thead class="table-dark">
  
    <tr>	
    		<th>Séance</th>
    	    <th>Nom Discipline</th>
      		<th >Nom de coach</th>
            <th >Date</th>
            <th >Heure_debut</th>
            <th >Heure_fin</th>
           <th >Action</th>
            
   	</tr>
  </thead>
   
			    
	   
  <tbody>
   <c:forEach  items="${WeekSeancesReservations}" var="seance">
       <tr >
       <td> Séance <c:out value="${counter}"  /> </td>
  
	       	<td> <c:out value="${seance.getDiscipline().getNomDiscipline()}"/> </td>
	       	<td> <c:out value="${seance.getCoach().getLastname()}"/>  <c:out value="${seance.getCoach().getFirstname()}"/> </td>
			<td> <c:out value="${seance.getDate()}"/> </td>
			<td> <c:out value="${seance.getHeureDebut()}"/> </td>
			<td> <c:out value="${seance.getHeureFin()}"/> </td>
			<td><a href="SupprimerReservationCoach/${seance.getId_seance()}" >Supprimer</a></td>
		<c:set var="counter" value="${counter+1}"  />
	</tr> 
	</c:forEach>    
  </tbody>
</table>
                    
  </div>
  </div> 
    
<% session.removeAttribute("seanecAjouté"); %>
<% session.removeAttribute("seanecNotAdded"); %>
<% session.removeAttribute("a"); %>
<% session.removeAttribute("ReservationDeleted"); %>
   <% session.removeAttribute("SeanceDeleted"); %>
	<% session.removeAttribute("SeanceAdded"); %>
<a href="espacegerant">Back...</a>
	
</body>
</html>