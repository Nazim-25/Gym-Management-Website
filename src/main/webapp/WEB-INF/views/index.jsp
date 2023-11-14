<%@ page language="java" contentType="text/html; charset=UTF-8"%>
    <%@ page pageEncoding="UTF-8" %>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewreport" content="width=device-width, initial-scale=1.0">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>
		Sports World
	</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" type="text/css" href=" /css/bootstrap.min.css"/>
    <link href="css/animate.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Raleway&display=swap" rel="stylesheet" />
</head>
<body>
    <% session.removeAttribute("success"); %>
     <% session.removeAttribute("messageErreur"); %>
     <div class="container-fluid pl-0 pr-0 bg-img clearfix parallax-window2" data-parallax="scroll" data-image-src="images/banner2.jpg">
        <nav class="navbar navbar-expand-md navbar-dark">
          <div class="container"> 
            <a class="navbar-brand mr-auto" href="#"><img src="images/logo.PNG" alt="SPORTS WORLD" /></a>   
            <div class="collapse navbar-collapse" id="collapsibleNavbar">
              <ul class="navbar-nav ml-auto">
                <li class="nav-item"> <a class="nav-link" href="#">Accuiel</a> </li>
                <li class="nav-item"> <a class="nav-link" href="ConsulterPlanning">planning</a> </li>
                <li class="nav-item"> <a class="nav-link" href="ConsulterDisciplines"> disciplines</a></li>
                <li class="nav-item"> <a class="nav-link" href="ConsulterCoachs"> coachs</a> </li>
                <li class="nav-item"> <a class="nav-link" href="ConsulterAbonnements"> Abonnement</a> </li>
                 <li class="nav-item"> <a class="nav-link" href="#contact">contact</a> </li>
                 <li class="nav-item">
                </ul>
              </div>
            </div>
          </nav>
          <% 
			            if( session.getAttribute("abonneDeleted")!= null ){
                 out.println("<div class='container'>");
                 	   out.println(" <div  class='alert alert-success my-2 text-center' role='alert' >");
			            out.println( session.getAttribute("abonneDeleted") );
				       out.println("</div>");
				out.println("</div>");
			            } %>
          <div class="container">
            <div class="fh5co-banner-text-box">
              <div class="quote-box pl-5 pr-5 wow bounceInRight">
                <h2> SPORTS WORLD  </h2>
                 <a href="SignUp" class="btn text-uppercase btn-outline-danger btn-lg mr-3 mb-3 wow bounceInUp">s'inscrire</a>
                 <a href="Login" class="btn text-uppercase btn-outline-danger btn-lg mr-3 mb-3 wow bounceInUp"> se connecter</a>
              </div><br>
            </div>
        </div>
    </div>
    <div class="container-fluid fh5co-network">
      <div class="container">
        <div class="row">
          <div class="col-md-6">
            <h4 class="wow bounceInUp">L'ALIMENTATION CHANGE VOTRE POIDS</h4>
            <h2 class="wow bounceInRight">L'ENTRAINEMENTS CHANGE VOTRE CORPS </h2>
            <hr />
          </div>
        </div>
      </div>
    </div><br><br>
    <div id="about-us" class="container-fluid fh5co-about-us pl-0 pr-0 parallax-window" data-parallax="scroll" data-image-src="images/about-us-bg.jpg">
        <div class="container-fluid fh5co-content-box">
          <div class="container">
            <div class="row">
              <div class="col-md-5 pr-0"><img src="images/gym2 (2).jpg" alt="gym" class="img-fluid wow bounceInLeft" /> </div>
              <div class="col-md-7 pl-0">
                <div class="wow bounceInRight" data-wow-delay=".25s">
                  <img src="images/coucou.jpg" alt="girls in gym" class="img-fluid" /> </div>
              </div>
            </div>
            <div class="row trainers pl-0 pr-0">
            </div>
            <div class="row gallery">
              <div class="col-md-4">
                <div class="card">
                  <div class="card-body mb-4 wow bounceInLeft" data-wow-delay=".25s">
                    <h4 class="card-title">TANIA</h4>
                    <p class="card-text">Je voulais juste vous remercier sinc�rement pour l'opportunit� de repr�senter Precision Nutrition pour un r�sultat aussi incroyable. </p>
                  </div>
                  <img class="card-img-top img-fluid wow bounceInRight" data-wow-delay=".25s" src="images/J1.jpg" alt="Card image"> </div>
              </div>
              <div class="col-md-4">
                <div class="card"> <img class="card-img-top img-fluid wow bounceInUp" data-wow-delay=".25s" src="images/J2.jpg" alt="Card image">
                  <div class="card-body mt-4 wow bounceInDown" data-wow-delay=".25s">
                    <h4 class="card-title">SABRINA</h4>
                    <p class="card-text">Inscrit depuis peu je trouve l endroit sympa on est tr�s bien encadr� les coachs son cool �a donne envie d y retourner r�guli�rement m�me les cours collectifs sont tr�s sympa </p>
                  </div>
                </div>
              </div>
              <div class="col-md-4">
                <div class="card">
                  <div class="card-body mb-4 wow bounceInRight" data-wow-delay=".25s">
                    <h4 class="card-title">SOFIANE</h4>
                    <p class="card-text">Je n'ai jamais os� m'inscrire dans une salle de sport, mais �a c'�tait avant ! J'ai �t� super bien accueillie par les coachs de  SPORTS WORLD qui m'ont fait un super programme pour atteindre mon objectif : </p>
                  </div>
                  <img class="card-img-top img-fluid wow bounceInLeft" data-wow-delay=".25s" src="images/J3.jpg" alt="Card image"> </div>
              </div>
            </div>
          </div>
        </div>
        <footer class="container-fluid">
            <div class="container">
              <div class="row">
               
                <div class="col-md-6 footer2 wow bounceInUp" data-wow-delay=".25s" id="contact">
                  <div class="form-box">
                    <h4>CONTACTEZ NOUS </h4>
                    <table class="table table-responsive d-table">
                      <tr>
                        <td><input type="text" class="form-control pl-0" placeholder="NOM" /></td>
                        <td><input type="email" class="form-control pl-0" placeholder="EMAIL" /></td>
                      </tr>
                      <tr>
                        <td colspan="2"></td>
                      </tr>
                      <tr>
                        <td colspan="2"><input type="text" class="form-control pl-0" placeholder="ADRESSE" /></td>
                      </tr>
                      <tr>
                        <td colspan="2"></td>
                      </tr>
                      <tr>
                        <td colspan="2"><input type="text" class="form-control pl-0" placeholder="MESSAGES" /></td>
                      </tr>
                      <tr>
                        <td colspan="2"></td>
                      </tr>
                      <tr>
                        <td colspan="2" class="text-center pl-0"><button type="submit" class="btn btn-dark">ENVOYER</button></td>
                      </tr>
                    </table>
                  </div>
                </div>
                <div class="col-md-3 footer3 wow bounceInRight" data-wow-delay=".25s">
                    <h5>ADRESSE</h5>
                    <p>rue aban ramdane num 35.</p>
                    <h5>NUM PHONE</h5>
                    <p>1111111</p>
                    <h5>EMAIL</h5>
                    <p>SPORTSWORLD2021GL@GMAIL.COM </p>
                  </div>
                </div>
              </div>
            </footer>
            <% session.removeAttribute("message"); %>
            <script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/parallax.js"></script>
<script src="js/wow.js"></script>
<script src="js/main.js"></script>

</body>
</html>
            
              




