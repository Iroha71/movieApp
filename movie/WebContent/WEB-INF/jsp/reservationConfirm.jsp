<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="movie.beans.MovieListBeans" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/reservationConfirm.css">
<link rel="stylesheet" href="lib/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>予約確認</title>
</head>
<body>
<div class="container-fuild">
<div class="row tables">
	<div class ="col-1"></div>
	<div class="col-10">
		<table border="1">
		<tr>
		<td colspan="2" class="moviename">movie name</td>
		</tr>
<%
 List<MovieListBeans> list = (List<MovieListBeans>)request.getAttribute("list");

    if( list != null ){
	   for(MovieListBeans beans : list){
%>
		<tr>
			<td class="detail"><%=beans.getMovieName()%></td>
			<td class="detail"><%=beans.getCount()%></td>
		</tr>

<%}} %>
	</table>
	<div class="row form">
	<form action="top" method="GET">
		<button type="submit" name="top" class="btn btn-dark button">映画一覧画面</button>
	</form>
	</div>
	</div>
	<div class="col-1"></div>

</div>

</body>
</html>