<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="movie.beans.MovieListBeans" %>
<%@ page import="movie.beans.UserInfoBeans" %>
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
 UserInfoBeans info=(UserInfoBeans)session.getAttribute("loginInfo");

 SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

%>
<%
    if( list != null ){
	   for(MovieListBeans beans : list){

	//座席の残りの割合
	double sa=beans.getSheet() - beans.getCount();
	double percent= sa / beans.getSheet();
%>
<div class="movie">
<form action="MovieReservationStartContorller" method="get">
		        <%if(info==null){ %>
                <input type="hidden" name = "term" value=<%=beans.getTermNumber() %> >
                <input type="hidden" name = "theater" value=<%=beans.getTheaterId() %> >
                <input type="hidden" name = "screen" value=<%=beans.getScreenNumber() %> >
                <input type="hidden" name = "sheet" value=<%=beans.getSheet() %> >

                <%}else{ %>
                <input type="hidden" name = "term" value=<%=beans.getTermNumber() %> >
                <input type="hidden" name = "theater" value=<%=beans.getTheaterId() %> >
                <input type="hidden" name = "screen" value=<%=beans.getScreenNumber() %> >
                <input type="hidden" name = "sheet" value=<%=beans.getSheet() %> >
                <input type="hidden" name = "member" value=<%=info.getMemberNumber() %> >
                <%} %>
        <button type="submit" name="ticket" class="tbtn">
		<table>
		  <tr>
		    <td class="title"><%=beans.getMovieName()%></td>
		  </tr>
		  <tr>
		    <td><%=beans.getTermStart()%>～<%=beans.getTermFinish()%></td>
		  </tr>
		  <tr>
		    <td><% if( percent >=0.9){ %>
	    		                          ◎
	    	                            <%}else if(percent >=0.3){%>
	    	                              〇
	    	                            <%}else if(percent >=0.01){ %>
	    	                              △
	    	                            <%}else if(percent ==0){ %>
	    	                              ×<%} %></td>

		  </table>
		  </button>
		  </form>
<%}} %>
</body>
</html>