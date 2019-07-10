<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0">
<title>会員登録画面</title>
<link rel="stylesheet" href="css/subscribe.css">
<link rel="stylesheet" href="lib/bootstrap.min.css">
</head>
<body>
<form action="subscribe" method = "get">
	<div id="center"><table border="0" class="mx-auto">

		<p class="">会員登録</p>

		 <tr>
		 	<div class="form-group">
		 	<td>メールアドレス </td> <td><input type="text" name="mail" size="50" class="form-control"></td>
		 	</div>
		 </tr>

		 <tr>
		 	<div class="form-group">
		 	<td>パスワード </td><td><input type="password" name="pass" size="50" class="form-control"></td>
		 	</div>
		 </tr>

		 <tr>
		 	<div class="from-group">
		 	<td>氏名 </td><td><input type="text" name="name" size="50" class="form-control"></td>
		 	</div>
		 </tr>

		 <tr>
		 	<div class="from-group">
		 	<td>誕生日 </td><td><input type="text" name="birthday" size="50" class="form-control" placeholder="例：yy-mm-dd"></td>
		 	</div>
		 </tr>

		 <tr>
		 	<div class="from-group">
		 	<td>性別 </td><td><input type="radio" name="jender" value="男" size="50" class="radio-inline">男性<input type="radio" name="jender" value="女" class="radio-inline">女性<input type="radio" name="jender" value="その他" class="radio-inline">その他</td>
		 	</div>
		 </tr>

		 <tr>
		 	<div class="from-group">
		 	<td>電話番号 </td><td><input type="text" name="phone" size="50" class="form-control" placeholder="例：000-000-0000-0000"></td>
		 	</div>
		 </tr>


	</table>
	</div>
	<div class="form-group">
	<input type="submit" name="button" value="会員登録" class="btn d-block">
	</div>
</form>
<section class="modal-area col-sm-12 center" v-if="isModal">
	<div class="check-modal center">
		この会員情報で確定しますか？
		<table>
			<tr>
				<th>メールアドレス</th>
				<th>パスワード</th>
				<th>名前</th>
				<th>誕生日</th>
				<th>性別</th>
				<th>電話番号</th>
			</tr>


		</table>
		<button type="button" @click="submitDelete">登録情報を確認しました。</button>
		<button type="button" @click="showModal(false)">キャンセル</button>
	</div>
</section>

</body>
</html>