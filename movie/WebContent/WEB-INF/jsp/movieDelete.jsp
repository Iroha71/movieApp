<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="movie.beans.UserInfoBeans" %>
    <%@ page import="movie.beans.MovieReservationBeans" %>
    <%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>映画予約取り消し</title>
</head>
<body>
<%
 List<MovieReservationBeans>list = (List<MovieReservationBeans>)request.getAttribute("list");
%>
予約取り消しますか。
<article>
<form action="delete" method="get">
	<table>
	<% for(MovieReservationBeans beans:list){ %>
	<tr>
	 <th><input type="checkbox" name="reservation" value="<% beans.getReservationNumber();%>"></th>
	 <td><input type="hidden" name = "moviename" value=<%=beans.getMovieName() %> >
	 	<%=beans.getMovieName()%></td>
	 <td><input type="hidden" name = "feebase" value =<%=beans.getFeeBase() %>>
	 	<%=beans.getFeeBase() %></td>
	 <td><input type="hidden" name = "sheetnumber" value=<%= beans.getSheetNumber()%>>
	 	<%=beans.getSheetNumber()%></td>
	 <td><input type="hidden" name = "termstart" value=<%=beans.getTermStart()%>>
	 	<%=beans.getTermStart() %></td>
	 <td><input type="hidden" name = "termfinish" value=<%=beans.getTermFinish()%>>
	 	<%=beans.getTermFinish() %></td>
	 <td><input type="hidden" name ="membernumber" value=<%=beans.getMemberNumber() %>>
	 	<%=beans.getMemberNumber()%></td>
	 <td><input type="hidden" name ="reservationnumber" value=<%=beans.getReservationNumber() %>>
	 	<%=beans.getReservationNumber() %></td>
	</tr>
	 <%} %>
	</table>
	<input type="submit" value="予約取り消し">
	</form>

</article>
</body>
</html>