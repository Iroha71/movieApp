<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/style.css">
<%@ page import="movie.beans.UserInfoBeans" %>
<%@ page import="movie.model.UserModel" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.*" %>
<% UserInfoBeans info=(UserInfoBeans)session.getAttribute("loginInfo");%>
<link rel="stylesheet" href="css/header.css">
<%if(info==null){ %>
<header class="header">
		<img src="img/logo.png" alt="ロゴ">
			<div><a href="userLogin" class="login"><img src="img/user.png" alt="user">ログイン</a></div>
		<nav class="global-nav">
		  <ul class="global-nav__list">
			<li class="global-nav__item"><a href="top">トップ</a></li>
			<li class="global-nav__item"><a href="userLogin">ログイン</a></li>
			<li class="global-nav__item"><a href="subscribestartServlet">新規会員登録</a></li>
			<li class="global-nav__item"><a href="">退会手続き</a></li>
		  </ul>
		</nav>
		<div class="hamburger" id="js-hamburger">
		  <span class="hamburger__line hamburger__line--1"></span>
		  <span class="hamburger__line hamburger__line--2"></span>
		  <span class="hamburger__line hamburger__line--3"></span>
		</div>
		<div class="black-bg" id="js-black-bg"></div>
	  </header>
<%}else{%>
<header class="header">
		<img src="img/logo.png" alt="ロゴ">
			<div class="info">ようこそ、<%=info.getMemberName()%>さん</div>
		<nav class="global-nav">
		  <ul class="global-nav__list">
			<li class="global-nav__item"><a href="top">トップ</a></li>
			<li class="global-nav__item"><a href="show">映画予約取り消し</a></li>
			<li class="global-nav__item"><a href="">会員情報変更</a></li>
			<li class="global-nav__item"><a href="">退会手続き</a></li>
		  </ul>
		</nav>
		<div class="hamburger" id="js-hamburger">
		  <span class="hamburger__line hamburger__line--1"></span>
		  <span class="hamburger__line hamburger__line--2"></span>
		  <span class="hamburger__line hamburger__line--3"></span>
		</div>
		<div class="black-bg" id="js-black-bg"></div>
	  </header>
<%} %>
<script type="text/javascript">
function toggleNav() {
  var body = document.body;
  var hamburger = document.getElementById('js-hamburger');
  var blackBg = document.getElementById('js-black-bg');

  hamburger.addEventListener('click', function() {
    body.classList.toggle('nav-open');
  });
  blackBg.addEventListener('click', function() {
    body.classList.remove('nav-open');
  });
}
toggleNav();
</script>