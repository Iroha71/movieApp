<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="movie.beans.UserInfoBeans" %>
    <%@ page import="movie.beans.ReservationBeans" %>
    <%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="lib/bootstrap.min.css">
<script src="lib/vue.min.js"></script>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/movieDelete.css">
<title>映画予約取り消し</title>
</head>
<body class="container-fluid">
<%
 List<ReservationBeans>list = (List<ReservationBeans>)request.getAttribute("list");
%>
<article class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
		<form action="delete" method="GET">
			<p>取り消す予約を選択してください</p>
			<table border="1">
				<tr>
					<th></th>
					<th>タイトル</th>
					<th>チケット料金</th>
					<th>座席番号</th>
					<th>上映時間</th>
				</tr>
				<% for(ReservationBeans beans:list){ %>
				<tr class="reserve-list">
					<td>
						<input type="checkbox" name="reservation" value="<% beans.getReservationNumber();%>">
					</td>
					<td>
						<input type="hidden" name = "moviename" value=<%=beans.getMovieName() %> >
					 	<%=beans.getMovieName()%></td>
					<td>
						<input type="hidden" name = "feebase" value =<%=beans.getFeeBase() %>>
					 	￥<%=beans.getFeeBase() %></td>
					<td>
						<input type="hidden" name = "sheetnumber" value=<%= beans.getSheetNumber()%>>
					 	<%=beans.getSheetNumber()%></td>
					<td>
						<input type="hidden" name = "termstart" value=<%=beans.getTermStart()%>>
					 	<%=beans.getTermStart() %>
					 	-
					 	<input type="hidden" name = "termfinish" value=<%=beans.getTermFinish()%>>
					 	<%=beans.getTermFinish() %>
					 	<input type="hidden" name ="membernumber" value=<%=beans.getMemberNumber() %>>
			 			<input type="hidden" name ="reservationnumber" value=<%=beans.getReservationNumber() %>>
					</td>
				</tr>
				<%} %>
			</table>
			<input type="submit" value="予約取り消し" class="btn btn-danger d-block">
		</form>
	</div>
</article>
</body>
</html>