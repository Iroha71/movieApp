<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="lib/bootstrap.min.css">
<title>映画情報登録</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-8">
				<form action="movieCreate" method="GET">
					<div class="form-group">
						<span>映画名</span>
						<input type="text" name="movieName" class="form-control">
					</div>
					<div class="form-group">
						<span>公開日</span>
						<input type="date" name="releaseStartDate" class="form-control">
						<input type="date" name="releaseFinishDate" class="form-control">
					</div>
					<div class="form-group">
						<span>監督</span>
						<input type="text" name="directed" class="form-control">
					</div>
					<div class="form-group">
						<span>キャスト※増やせるようにする予定</span>
						<input type="text" name="cast" class="form-control">
					</div>
					<div class="form-group">
						<span>チケット値段</span>
						<input type="number" name="feeType" class="form-control">
					</div>
					<div class="form-group">
						<span>解説</span>
						<textarea name="movieDetail" class="form-control"></textarea>
					</div>
					<div class="form-group">
						<button type="submit" class="form-control">√登録</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>