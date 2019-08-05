<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "movie.beans.FeeBeans" %>
<%@ page import = "movie.beans.MovieListBeans" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.*" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href = "css/movieCreate.css">
<link rel="stylesheet" href="lib/bootstrap.min.css">
<title>映画情報登録</title>
</head>
<body>
<jsp:include page="./header/header.jsp" />
<%
	List<FeeBeans>feeType = (List<FeeBeans>)request.getAttribute("feeType");
	List<MovieListBeans>ScreenList = (List<MovieListBeans>)request.getAttribute("screenList");
	List<MovieListBeans>TermList = (List<MovieListBeans>)request.getAttribute("termList");
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
%>
	<div class="container-fluid">
		<div class="row">
			<div class = "col-4">
				<div class ="detail-group">
					<span>映画名</span>
				</div>
				<div class ="detail-group">
					<span>映画画像</span>
				</div>
				<div class ="detail-group">
					<span>公開開始日</span>
				</div>
				<div class ="detail-group">
					<span>公開終了日</span>
				</div>
				<div class = "detail-group">
					<span>公開時間</span>
				</div>
				<div class = "detail-group">
					<span>シアター</span>
				</div>
				<div class = "detail-group">
					<span>スクリーン</span>
				</div>
				<div class ="detail-group">
					<span>監督</span>
				</div>
				<div class ="detail-group">
					<span>キャスト</span>
				</div>
				<div class ="detail-group">
					<span>チケット値段</span>
				</div>
				<div class ="detail-group">
					<span>解説</span>
				</div>
			</div>
			<div class="col-8">
				<form action="movieCreate" method="POST" enctype="multipart/form-data">
					<div class="form-group">
						<input type="text" name="movieName" class="form-control">
					</div>
					<div class="form-group pass">
						<input type="file" class="form-control" name="thumbnail">
					</div>
					<div class="form-group date">
						<input type="date" name="releaseStartDate" class="form-control">
						<br>
						<input type="date" name="releaseFinishDate" class="form-control">
					</div>
					<div class = "form-group">
						<select name = "term" class="Select">
							<%for(MovieListBeans termBeans : TermList){ %>
								<option value="<%=termBeans.getTermType()%>"><%=termBeans.getTermType()%>:<%=sdf.format(termBeans.getTermStart()) %>～<%=sdf.format(termBeans.getTermFinish()) %></option>
							<%} %>
						</select>
					</div>
					<div class = "form-group">
						<select name = "theater" class="Select">
							<%for(MovieListBeans ScreenBeans : ScreenList){ %>
								<option value="<%=ScreenBeans.getTheaterId()%>"><%=ScreenBeans.getTheaterId()%>:<%=ScreenBeans.getTheaterName()%></option>
							<%} %>
						</select>
					</div>
					<div class = "form-group">
						<select name = "screen" class="Select">
							<%for(MovieListBeans ScreenBeans : ScreenList){ %>
								<option value="<%=ScreenBeans.getScreenNumber()%>"><%=ScreenBeans.getScreenNumber()%></option>
							<%} %>
						</select>
					</div>
					<div class="form-group">
						<input type="text" name="directed" class="form-control">
					</div>
					<div class="form-group">
						<input type="text" name="cast" class="form-control">
					</div>
					<div class="form-group">
						<select name = "fee" class="Select">
							<%for(FeeBeans feeBeans : feeType){ %>
								<option value="<%=feeBeans.getFeeType()%>"><%=feeBeans.getFeeType()%>:<%=feeBeans.getFee() %>円</option>
							<%} %>
						</select>
					</div>
					<div class="form-group">
						<textarea name="movieDetail" class="form-control"></textarea>
					</div>
					<div class="form-group">
						<button type="submit" class="form-control btn btn-primary">√登録</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>