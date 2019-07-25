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
        <img src="img/a.png" class="movie-img">
            </div>
                <table border="0" class="info">
                <tr>
                <td class="title"><%=beans.getMovieName()%>
                <input type="hidden" name = "term" value=<%=beans.getTermNumber() %> >
                <input type="hidden" name = "theater" value=<%=beans.getTheaterId() %> >
                <input type="hidden" name = "screen" value=<%=beans.getScreenNumber() %> >
                <input type="hidden" name = "member" value=<%=info.getMemberNumber() %> ></td>
                </tr>
                <form action="MovieReservationStartContorller" method="get">
                <tr>
                <td class="ticket"><button type="submit" name="ticket" class="tbtn">
                            <table border="0" class="status">
                                <tr><td><%=sdf.format(beans.getTermStart())%>～<%=sdf.format(beans.getTermFinish())%></td></tr>
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
</body>
</html>