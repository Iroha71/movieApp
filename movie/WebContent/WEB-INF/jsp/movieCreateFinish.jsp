<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/movieCreateFinish.css">
<link rel="stylesheet" href="lib/bootstrap.min.css">
<meta charset="UTF-8">
<title>映画情報登録完了</title>
</head>
<body>
<jsp:include page="./header/header.jsp" />
<div class="row">
<div class="col-3"></div>
<div class="col-9">
	<p>映画情報を登録しました</p>
	<form method="get">
		<input type="submit" value="一覧へ戻る" class="button btn btn-success" formaction="top">
		<input type ="submit" value="映画登録を続ける" class="button btn btn-success" formaction="movieCreateStart">
	</form>
</div>
</div>
</body>
</html>