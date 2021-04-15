<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Profile</title>
	<link rel="stylesheet" href="/webjars/bootstrap/4.5.0/css/bootstrap.min.css" />
    <script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
	<body>
		<div class="container">
	        <div class="card">
	            <div class="card-header bg-dark text-light">${user.firstName} ${user.lastName}</div>
	            <div class="card-body">
	                <p>Email: ${user.email}</p>
	                <p>Member since: ${user.createdAt}</p>
	                <p>Games uploaded: ${user.games.size()}</p>
	                <p>Reviews Left: ${user.reviews.size()}</p>
	                <h4>Games you haven't yet reviewed!</h4>
	                <ul>
	                    <c:forEach items="${gamesToReview}" var="game">
	                        <li><a href="/games/${game.id}">${game.title}</a></li>
	                    </c:forEach>
	                </ul>
	            </div>
	        </div>
	    </div>
	</body>
</html>