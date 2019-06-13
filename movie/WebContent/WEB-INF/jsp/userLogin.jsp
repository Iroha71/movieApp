<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="lib/bootstrap.min.css">
<title>ログイン画面</title>
</head>
<body>
<%String error=(String)request.getParameter("error"); %>
	<div class="container-fluid">
		<div class="row">
			<div class="col-8">
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
						<button type="submit" class="form-control">ログイン</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>