<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="movie.beans.MovieListBeans" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理者映画一覧</title>
</head>
<body>
<%
 List<MovieListBeans> list = (List<MovieListBeans>)request.getAttribute("list");

    if( list != null ){
	   for(MovieListBeans beans : list){
%>

<form action="update" method="GET">
<table border="0">
	<tr>
		<td>タイトル : </td>
		<td><%=beans.getMovieName()%></td>
	</tr>
	<tr>
	 <td><button type="submit" name="detail">詳細</button></td>
	</tr>
</table>
</form>

<% } }%>



</body>
</html>