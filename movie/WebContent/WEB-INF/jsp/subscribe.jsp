<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員登録画面</title>
<link rel="stylesheet" href="css/subscribe.css">
<link rel="stylesheet" href="lib/bootstrap.min.css">
</head>
<body>
<form action="subscribe" method = "get">
	<table border="0" class="mx-auto">
		<p>会員登録</p>
		 <tr>
		 	<div class="form-group">
		 	<td>メールアドレス </td> <td><input type="text" name="mail" size="50" class="form-control"></td>
		 	</div>
		 </tr>

		 <tr>
		 	<div class="form-group">
		 	<td>パスワード </td><td><input type="text" name="pass" size="50" class="form-control"></td>
		 	</div>
		 </tr>

		 <tr>
		 	<div class="from-group">
		 	<td>氏名 </td><td><input type="text" name="name" size="50" class="form-control"></td>
		 	</div>
		 </tr>

		 <tr>
		 	<div class="from-group">
		 	<td>誕生日 </td><td><input type="text" name="birthday" size="50" class="form-control"></td>
		 	</div>
		 </tr>

		 <tr>
		 	<div class="from-group">
		 	<td>性別 </td><td><input type="radio" name="jender" value="男" size="50" class="radio-inline">男性<input type="radio" name="jender" value="女" class="radio-inline">女性</td>
		 	</div>
		 </tr>

		 <tr>
		 	<div class="from-group">
		 	<td>電話番号 </td><td><input type="text" name="phone" size="50" class="form-control"></td>
		 	</div>
		 </tr>


	</table>
	<div class="form-group">
	<input type="submit" name="button" value="会員登録" class="btn-primary">
	</div>
</form>
</body>
</html>