<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="movie.beans.MovieListBeans" %>
    <%@ page import ="movie.beans.FeeBeans" %>
	<%@ page import="java.util.List" %>
	<%@ page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href = "css/updateStart.css">
<link rel="stylesheet" href="lib/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>映画情報更新画面</title>
</head>
<body>
<jsp:include page="./header/header.jsp" />
<%
	List<MovieListBeans> list = (List<MovieListBeans>)request.getAttribute("list");
	List<FeeBeans>feeType = (List<FeeBeans>)request.getAttribute("feeType");
%>
<div class="container-fluid">
	<div class="row">
<% for(MovieListBeans beans : list){ %>

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
				<form action="movieUpdate" method="get" enctype="multipart/form-data">
			<div class="form-group">
				<input type="text" name="movieName" class="form-control" value=<%=beans.getMovieName()%>>
			</div>
			<div class="form-group pass">
				<input type="file" class="form-control" name="thumbnail">
			</div>
			<div class="form-group date">
				<input type="date" name="releaseStartDate" class="form-control" value=<%=beans.getStartDate() %>>
				<br>
				<input type="date" name="releaseFinishDate" class="form-control" value=<%=beans.getFinishDate() %>>
			</div>
			<div class="form-group">
				<input type="text" name="directed" class="form-control"value =<%=beans.getDirected() %>>
			</div>
			<div class="form-group">
				<input type="text" name="cast" class="form-control" value=<%=beans.getCast() %>>
			</div>
			<div class="form-group">
				<select name = "name" class="feeSelect">
					<%for(FeeBeans feeBeans : feeType){ %>
						<option value="<%=feeBeans.getFeeType()%>"><%=feeBeans.getFeeType()%>:<%=feeBeans.getFee() %>円</option>
					<%} %>
				</select>
			</div>
			<div class="form-group">
				<textarea name="movieDetail" class="form-control"><%=beans.getDetail() %></textarea>
			</div>
				</div>


		<%}%>
		<div class = "col-4"></div>
		<div class = "col-8">
			<input type="submit" value="更新する" class="btn btn-primary">
		</div>
		</form>
</div>
</div>
</body>
</html>