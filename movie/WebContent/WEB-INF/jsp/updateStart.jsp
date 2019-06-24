<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="movie.beans.MovieListBeans" %>
    <%@ page import="java.util.List" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update</title>
</head>
<body>
<%
	List<MovieListBeans> list = (List<MovieListBeans>)request.getAttribute("list");
%>
<form action="movieUpdate" method="get">
<table border="0">
<% for(MovieListBeans beans : list){ %>
	<tr><td>映画名 :</td></tr>
	<tr><td><input type="text" name="movieName" value="<%=beans.getMovieName()%>"></td></tr>

	<tr><td>公開日</td></tr>
	<tr><td><input type="text" name= "startDate" value= "<%=beans.getStartDate() %>"></td></tr>

	<tr><td>公開終了日</td></tr>
	<tr><td><input type="text" name="finishDate" value= "<%=beans.getFinishDate() %>"></td></tr>

	<tr><td>キャスト</td></tr>
	<tr><td><input type="text" name = "cast" value= "<%=beans.getCast() %>"></td></tr>

	<tr><td>監督</td></tr>
	<tr><td><input type="text" name="directed" value="<%=beans.getDirected()%>"></td></tr>

	<tr><td>映画詳細</td></tr>
	<tr><td><input type="text" name="detail" value="<%=beans.getDetail() %>"></td></tr>

<%}%>
</table>
<button type="submit">更新しますか？</button>
</form>
</body>
</html>