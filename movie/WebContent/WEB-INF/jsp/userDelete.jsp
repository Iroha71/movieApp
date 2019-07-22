<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="movie.beans.UserInfoBeans" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="lib/bootstrap.min.css">
<script src="lib/vue.min.js"></script>
<title>退会確認</title>
</head>
<body class="container-fluid">
<jsp:include page="./header/header.jsp"></jsp:include>
<%UserInfoBeans userInfo=(UserInfoBeans)session.getAttribute("loginInfo"); %>
<div class="row" id="app">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
		<h1>退会しますか?</h1>
		<button type="button" class="btn btn-danger" @click="submitDelete">はい</button>
		<button type="button" class="btn btn-primary" @click="deleteCancel">いいえ</button>
	</div>
</div>
</body>
<script>
let app=new Vue({
	el: '#app',
	methods:{
		submitDelete:function(){
			location.href="userDelete"
		},
		deleteCancel:function(){
			location.href="top"
		}
	}
})
</script>
</html>