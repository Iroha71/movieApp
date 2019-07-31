<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="movie.beans.UserInfoBeans" %>
    <%@ page import="movie.beans.ReservationBeans" %>
    <%@ page import="java.util.List" %>
    <%@ page import="java.text.SimpleDateFormat" %>
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
<jsp:include page="./header/header.jsp" />
<div id='app'>
<%
List<ReservationBeans>list = (List<ReservationBeans>)request.getAttribute("list");
SimpleDateFormat sdf=new SimpleDateFormat("MM月dd日 HH:mm:ss");
SimpleDateFormat sdfFinishDate=new SimpleDateFormat("HH:mm:ss");
%>
<article class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
		<form action="delete" method="GET" id="deleteForm">
			<p>取り消す予約を選択してください</p>
			<table border="1">
				<tr>
					<th></th>
					<th>タイトル</th>
				</tr>
				<% for(ReservationBeans beans:list){ %>
				<tr class="reserve-list"@click="toggleCheckBox(<%=beans.getReservationNumber() %>)">
					<td><input type="checkbox" name="reservation" value="<%=beans.getReservationNumber() %>" v-model="reserveId" id="check<%=beans.getReservationNumber() %>"></td>
					<td><%=beans.getMovieName() %></td>
				</tr>
				<%} %>
			</table>
			<input type="button" value="予約取り消し" class="btn btn-danger d-block" @click="submitDelete">
		</form>
	</div>
</article>
</div>
</body>
<script>
toggleCheckBox=function(idx){
	const checkBox=document.getElementById('check'+idx);
	checkBox.checked=!checkBox.checked;
}
var app=new Vue({
	el: '#app',
	data:{
		isModal: false,
		reserveId: []
	},
	methods:{
		showModal:function(showModal){
			this.isModal=showModal
			console.log(this.reserveId)
		},
		submitDelete:function(){
			const form=document.getElementById('deleteForm');
			form.submit();
		}
	}
})
</script>
</html>