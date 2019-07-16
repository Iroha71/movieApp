<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/movieCreateFinish.css">
<link rel="stylesheet" href="lib/bootstrap.min.css">
<title>完了</title>
</head>
<body>
<div class="row">
<div class="col-3"></div>
<div class="col-9">
	<p>映画情報を更新しました</p>
	<form method="get">
		<input type="submit" value="一覧へ戻る" class="button btn btn-success" formaction="top">
		<input type ="submit" value="映画更新を続ける" class="button btn btn-success" formaction="movieUpdateStart">
	</form>
</div>
</div>
</body>
</html>