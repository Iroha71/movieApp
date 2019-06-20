<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="movie.beans.MovieListBeans" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>予約確認</title>
</head>
<body>
<%
 List<MovieListBeans> list = (List<MovieListBeans>)request.getAttribute("list");

    if( list != null ){
	   for(MovieListBeans beans : list){
%>
<table border="0">
	<tr>
		<td><%=beans.getMovieName()%></td>
		<td><%=beans.getCount()%></td>
	</tr>

</table>
<form action="top" method="GET">
<button type="submit" name="top">映画一覧画面</button>
</form>


<%}} %>


</body>
</html>