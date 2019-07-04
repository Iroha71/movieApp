<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="lib/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/reservation.css">
</head>
<body class="container-fluid">
<%
	String[] sheets=new String[100];
	sheets = (String[])request.getAttribute("sheets");
	int sheetCount=0;
%>
	<article class="row progress-status hidden">
		<form action = "MovieReservationController" method = "get">
		<input type="hidden"  name="member"value=1>
		<input type="hidden" name="term" value = 1>
		<input type="hidden" name="theater" value=1>
		<input type="hidden" name ="screen" value=1>
		<input type="hidden" name = "sheet" value=1>
		<input type="hidden" name = "sheet" value=2>
		<input type = "text" name ="sheet" value = 3><%=sheets[3] %>
		<input type="hidden" name = "fee" value=1>
		<input type="hidden" name = "fee" value=1>
		<input type="hidden" name = "fee" value=1>
		<input type ="submit" name="button" value="予約する">
		</form>
	</article>
	<article class="row">
		<div class="col-sm-2"></div>
		<div class="col-sm-8">
			<table>
			<%for(int i=0;i<10;i++){ %>
				<tr>
				<%for(int j=(i*10)+1;j<=(i+1)*10;j++){ %>
					<%if(Integer.parseInt(sheets[sheetCount])==j-1){ %>
					<td><button class="sheet-div active-sheet"><%=j %></button></td>
					<%sheetCount++; %>
					<%}else{ %>
					<td>
						<button class="sheet-div disable-sheet" disabled>a</button>
					</td>
					<%} %>
				<%} %>
				</tr>
			<%} %>
			</table>
		</div>
	</article>
</body>
</html>