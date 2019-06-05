<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="lib/bootstrap.min.css">
<title>削除完了</title>
</head>
<body>
<%String movieName=(String)session.getAttribute("movieName"); %>
	<div class="container-fluid">
		<div class="row">
			<div class="col-12">
				<h1>映画の削除が完了しました。</h1>
				<p>映画名:<%=movieName %></p>
				<a href="top">映画一覧へ</a>
			</div>
		</div>
	</div>
</body>
</html>