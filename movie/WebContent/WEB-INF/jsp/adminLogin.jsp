<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="lib/bootstrap.min.css">
<title>管理者ログイン</title>
</head>
<body>
<%String error=(String)request.getParameter("error"); %>
	<div class="container-fluid">
		<div class="row">
			<form class="col-8" action="adminAuth" method="POST">
				<%if(error!=null&&error.equals("mistake")){ %>
					<p>メールアドレスまたはパスワードが間違っています</p>
				<%} %>
				<div class="form-group">
					<label>メールアドレス</label>
					<input type="text" name="mail" class="form-control">
				</div>
				<div class="form-group">
					<label>パスワード</label>
					<input type="password" name="password" class="form-control">
				</div>
				<div class="form-group">
					<button type="submit" class="form-control">ログイン</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>