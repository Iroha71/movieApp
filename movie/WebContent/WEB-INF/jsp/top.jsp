<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="movie.beans.MovieListBeans" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TOP</title>
</head>
<body>
<%
 List<MovieListBeans> list = (List<MovieListBeans>)request.getAttribute("list");
%>
<%
    if( list != null ){
	   for(MovieListBeans beans : list){

	//座席の残りの割合
	double sa=beans.getSheet() - beans.getCount();
	double percent= sa / beans.getSheet();
%>
<div id="list">

<form action="MovieReservationStartContorller" method="get">
<button type="submit" name="ticket">
<table border="0">
	<tr>
		<td>タイトル : </td>
		<td><%=beans.getMovieName()%></td>
	</tr>
	<tr>
		<td>上映時間 : </td>
		<td><%=beans.getTermStart()%>～<%=beans.getTermFinish()%></td>

	</tr>

	<tr>
	    <td>残り状況 : </td>
	    <td><% if( percent >=0.9){ %>
	    		◎
	    	<%}else if(percent >=0.3){%>
	    	    〇
	    	<%}else if(percent >=0.01){ %>
	    	    △
	    	<%}else if(percent ==0){ %>
	    	    ×<%} %></td>
	</tr>
</table>
</button>
</form>
</div>
<% } }%>
</body>
</html>