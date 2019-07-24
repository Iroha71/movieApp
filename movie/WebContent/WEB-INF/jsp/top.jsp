<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="movie.beans.MovieListBeans" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.*" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/top.css">
<title>TOP</title>
</head>
<body>
<jsp:include page="./header/header.jsp" />
<%
 List<MovieListBeans> list = (List<MovieListBeans>)request.getAttribute("list");
 SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

%>
<%
    if( list != null ){
	   for(MovieListBeans beans : list){

	//座席の残りの割合
	double sa=beans.getSheet() - beans.getCount();
	double percent= sa / beans.getSheet();
%>
<div class="movie">
    <div>
        <img src="img/harry.png" class="movie-img">
            </div>
                <table border="0" class="info">
                <tr>
                <td class="title" algin="left"><%=beans.getMovieName()%>
                <input type="hidden" name = "term" value=<%=beans.getMovieName() %> >
                <input type="hidden" name = "theater" value=<%=beans.getMovieName() %> >
                <input type="hidden" name = "screen" value=<%=beans.getMovieName() %> >
                <input type="hidden" name = "member" value=<%=beans.getMovieName() %> ></td>
                </tr>
                <form action="MovieReservationStartContorller" method="get">
                <tr>
                <td class="ticket"><button type="submit" name="ticket" class="tbtn">
                            <table border="0" class="status">
                                <tr><td><%=beans.getTermStart()%>～<%=beans.getTermFinish()%></td></tr>
                                <tr><td><% if( percent >=0.9){ %>
	    		                          ◎
	    	                            <%}else if(percent >=0.3){%>
	    	                              〇
	    	                            <%}else if(percent >=0.01){ %>
	    	                              △
	    	                            <%}else if(percent ==0){ %>
	    	                              ×<%} %></td></tr>
                            </table>
                </button></td>
               </tr>
               </form>
            </table>
        </div>
        <% } }%>

<form action="MovieReservationStartContorller" method="get">
<button type="submit" name="ticket">
<table border="0">
	<tr>
		<td>タイトル : </td>
		<td><%=beans.getMovieName()%></td>
	</tr>
	<tr>
		<td>上映時間 : </td>
		<td><%=sdf.format(beans.getTermStart())%>～<%=sdf.format(beans.getTermFinish())%></td>

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