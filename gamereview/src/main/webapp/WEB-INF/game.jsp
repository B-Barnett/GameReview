<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Game Review</title>
	<link rel="stylesheet" href="/webjars/bootstrap/4.5.0/css/bootstrap.min.css" />
    <script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
	<body>
		<div class="container">    
	        <h1>${title}</h1>
	        <div class="row">
	           <div class="col-sm-4">
	               <form:form action="/games/new" method="post" modelAttribute="newGamePlus">
	                   <div class="form-group">
	                       <label>Title</label>
	                       <form:input path="title" class="form-control" />
	                       <form:errors path="title" class="text-danger" />
	                   </div>
	                   <div class="form-group">
	                       <label>Studio</label>
	                       <form:input path="studio" class="form-control" />
	                       <form:errors path="studio" class="text-danger" />
	                   </div>
	                   <div class="form-group">
	                       <label>Year</label>
	                       <form:input path="year" class="form-control" />
	                       <form:errors path="year" class="text-danger" />
	                   </div>
	                   <div class="form-group">
	                       <label>Genres</label>
	                       <form:input path="genresInput" class="form-control" />
	                       <form:errors path="genresInput" class="text-danger" />
	                   </div>
	                   <input type="submit" value="Add Game" class="btn btn-primary" />
	               </form:form>
	           </div>
	           <div class="col-sm-8">
	               <table class="table">
	                   <tr>
	                       <th>Title</th>
	                       <th>Studio</th>
	                       <th>Year</th>
	                       <th>Genres</th>
	                       <th>Average Rating</th>
	                   </tr>
	                   <c:forEach items="${allGames}" var="game">
	                      <tr>
	                          <td><a href="/games/${game.id}">${game.title}</a></td>
	                          <td>${game.studio}</td>
	                          <td>${game.year}</td>
	                          <td>${game.genreDescription()}</td>
	                          <td>${game.getAverageRating()}</td>
	                      </tr>
	                   </c:forEach>
	                </table>
	           </div>
	        </div>
	    </div>
	</body>
</html>