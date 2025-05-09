
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
     pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
 <!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewreport" content="width=device-width, initial-scale=1.0">
	<title>
		Sports World
	</title>
	<link rel="stylesheet" type="text/css" href="/css/index.css" />
	<link rel="stylesheet" type="text/css" href=" /css/bootstrap.min.css"/>
	<link href="https://fonts.googleapis.com/css?family=Raleway&display=swap" rel="stylesheet" />
	
</head>
<body>

<c:when test="${Discipline.nomDiscipline eq Discipline.description}">
			        hello
			    </c:when>
			    
<% session.removeAttribute("success"); %>
<div class="container-fluid banner">
		<div class="row">
			<div class="col-md-12">
				<nav class="navbar navbar-md">
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
					    <span class="navbar-toggler-icon"></span>
					</button>
					<div class="navbar-brand">SPORTSWORLD</div>
					<ul class="nav">
						<li class="nav-item">
							<a class="nav-link" href="#">HOME</a>
						</li>
						
						<li class="nav-item">
							<a class="nav-link" href="#">ABOUT</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="#">CONTACT</a>
						</li>
								<li class="nav-item dropdown">
							        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							          Services
							        </a>
							        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
							          <a class="dropdown-item" href="loginAbonne">Abonne</a>
							          <a class="dropdown-item" href="loginCoach">Coach</a>
							          <a class="dropdown-item" href="loginGerant">Gerant</a>
							        </div>
							      </li>
							      
							      
						<li class="nav-item dropdown">
							        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							          Login
							        </a>
							        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
							          <a class="dropdown-item" href="loginAbonne">Abonne</a>
							          <a class="dropdown-item" href="loginCoach">Coach</a>
							          <a class="dropdown-item" href="loginGerant">Gerant</a>
							        </div>
							      </li>
						<li class="nav-item">
							<a class="nav-link" href="SignUp">SIGN UP</a>
						</li>
					</ul>
				</nav>
			</div>
			<div class="col-md-8 offset-md-2 info">
				<h1 class="text-center">SPORTS HALL</h1>
				<p class="text-center">
					Lorem ipsum dolor sit amet, consectetur adipisicing
				</p>
				<a href="#" class="btn btn-md text-center">GET STARTED</a>
			</div>
		</div>
	</div>
	<% session.removeAttribute("message"); %>
</body>
</html>

