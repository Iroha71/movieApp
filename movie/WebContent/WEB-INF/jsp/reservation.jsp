<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action = "MovieReservation" method = "get">
	<input type="hidden"  name="member"value=1>
	<input type="hidden" name="term" value = 1>
	<input type="hidden" name="theater" value="1">
	<input type="hidden" name ="screen" value=1>
	<input type="hidden" name = "sheet" value=1>
	<input type="hidden" name = "fee" value="1">
	<input type ="submit" name="button" value="予約する">
</form>
</body>
</html>