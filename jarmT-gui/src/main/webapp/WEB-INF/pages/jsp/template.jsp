<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>         
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!doctype html>
<html lang="de">
  <head>
    <!-- Required meta tags -->
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/0be993e115.js" crossorigin="anonymous"></script>
    <title>Jarm</title>
    <link rel="STYLESHEET" type="text/css" href="<c:url value="/include/stylesheet.css"/>">
  </head>
       <body> 
       <header style="position: fixed; top: 0; left: 0; right: 0; background-color: #c0c0c0; font-weight: bold; z-index: 99;">
            <nav class="navbar navbar-expand-lg navbar-light">
            <h1>Jarm <i class="fas fa-hippo blue"></i></h1>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="nav nav-tabs">
                    <li class="nav-item active"><a class="nav-link"
                        href="<c:url value='/secured/projektList.do'/>"><font color="white">Home</font><span class="sr-only">(current)</span></a>
                    </li>
                    <c:if test="${empty user}">
                        <li class="nav-item"><a class="nav-link"
                            href="<c:url value='/bv/login.do'/>"><font color="white">Login</font></a></li>
                        <li class="nav-item"><a class="nav-link"
                            href="<c:url value='/bv/register.do'/>"><font color="white">Registrieren</font></a></li>
                    </c:if>
                    <c:if test="${!empty user}">
                        <li class="nav-item"><a class="nav-link"
                            href="<c:url value='/bv/logout.do'/>"><font color="white">Logout</font></a></li>
                    </c:if>
                </ul>
            </div>
        	</nav>
       </header>       
	   <main style="margin-bottom: 50px; margin-top: 75px;">
            <div id="message">
                <c:out value="${meldung}"/>
            </div>
            
            <div id="inhalt">
                <c:import url="${url}"></c:import>
            </div>
		</main>
    <footer style = "position: fixed; padding: 10px; bottom: 0; left: 0; right: 0; background-color: #c0c0c0; text-align: center">
                  Impressum und Kontakt
    </footer>
            
    <script
	  src="https://code.jquery.com/jquery-3.4.1.min.js"
	  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	  crossorigin="anonymous"></script>

    <script
        src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
    <script
        src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>   
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/notify/0.4.2/notify.js"></script>

	<script type='text/javascript'>$(document).ready(function(){ <c:out value="${notifications}" escapeXml="false" /> });</script>       
        
    <script>$(document).ready(function() { <c:out escapeXml="false" value="${script}"/> });</script>     
    </body>
</html>