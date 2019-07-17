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
<form action="subscribe" method = "get" id="form">
	<div id="center"><table class="mx-auto">

		<p class="">会員登録</p>

		 <tr>
		 	<div class="form-group">
		 	<td>メールアドレス </td> <td><input type="text" name="mail" size="50" class="form-control"></td>
		 	</div>
		 </tr>

		 <tr>
		 	<div class="form-group">
		 	<td>パスワード </td><td><input type="password"  name="pass" size="50" class="form-control"></td>
		 	</div>
		 </tr>

		 <tr>
		 	<div class="from-group">
		 	<td>氏名 </td><td><input type="text" name="name" size="50" class="form-control"></td>
		 	</div>
		 </tr>

		 <tr>
		 	<div class="from-group">
		 	<td>誕生日 </td><td><input type="date" name="birthday" size="50" class="form-control" placeholder="例：yy-mm-dd"></td>
		 	</div>

		 </tr>

		 <tr>
		 	<div class="from-group">
		 	<td>性別 </td><td>
		 	<input type="radio"  name="jender" value="男" size="50" class="radio-inline">男性
		 	<input type="radio" name="jender" value="女" class="radio-inline">女性
		 	<input type="radio" name="jender" value="その他" class="radio-inline">その他</td>
		 	</div>
		 </tr>

		 <tr>
		 	<div class="from-group">
		 	<td>電話番号 </td><td><input type="text" name="phone" size="50" class="form-control" placeholder="例：000-000-0000-0000"></td>
		 	</div>
		 </tr>

	</table>
	</div>
	<div id="center" class="form-group">
	   <button id ="center" class="js-modal-open form-group btn btn-primary btn-lg" type="button">登録確認</a>
	</div>

	<div class="modal js-modal">
	  	<div class="modal_bg js-modal-close"></div>
		<div class="modal_content">
			<table>
				<tr>
				<td>メールアドレス</td>
				<td class="m-mail"></td>

				</tr>
				<tr>
				<td>氏名</td>
				<td class="m-name"></td>
				</tr>

				<tr>
				<td>誕生日</td>
				<td class="m-birthday"></td>
				</tr>

				<tr>
				<td>性別</td>
				<td class="m-jender"></td>
				</tr>

				<tr>
				<td>電話番号</td>
				<td class="m-phone"></td>
				</tr>
			</table>
			<button class= "js-modal-close" type="button">閉じる</button>
			<input type="submit" value="登録完了">
		</div>
	</div>



</form>


</body>
<script
  src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script>

<script type="text/javascript" language="javascript">
			$(function(){
				$('.js-modal-open').on('click',function(){
          // 入力項目をダイアログ内に表示するようにする
					// <td></td>の中に書きたい場合はtext(), 入力された値が欲しい場合はval()

          $('.m-mail').text($('input[name=mail]').val());
          $('.m-name').text($('input[name=name]').val());
          $('.m-birthday').text($('input[name=birthday]').val());
          $('.m-jender').text($('input[name=jender]:checked').val());
          $('.m-phone').text($('input[name=phone]').val());



          // モーダルを表示する
					$('.js-modal').fadeIn();
					return false;
				});
				$('.js-modal-close').on('click',function(){
					$('.js-modal').fadeOut();
					return false;
				});
			});


		</script>

</html>