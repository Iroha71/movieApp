<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="lib/bootstrap.min.css">
<script src="lib/vue.min.js"></script>
<title>ログイン画面</title>
</head>
<body>
<%String error=(String)request.getParameter("error"); %>
	<div class="container-fluid" id="app">
		<div class="row">
			<div class="col-8">
				<div v-if="isLoad">
					<jsp:include page="loading/loading.jsp" />
				</div>
				<%if(error!=null && error.equals("mistake")){ %>
					<p>メールアドレスまたはパスワードが間違っています</p>
				<%} %>
				<form action="auth" method="POST">
					<div class="form-group">
						<label>メールアドレス</label>
						<input type="text" name="mail" class="form-control">
					</div>
					<div class="form-group">
						<label>パスワード</label>
						<input type="password" name="password" class="form-control">
					</div>
					<div class="form-group">
						<button type="submit" class="form-control" @click="login">ログイン</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>

<script>
let app=new Vue({
	el: '#app',
	data:function(){
		return{
			isLoad: false
		}
	},
	methods:{
		login:function(){
			this.isLoad=true;
		}
	}
})
</script>
</html>