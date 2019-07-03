<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/style.css">
<%@ page import="movie.beans.UserInfoBeans" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.*" %>
<% UserInfoBeans beans=(UserInfoBeans)request.getAttribute("loginInfo");%>
<header class="header">
    <div>
		<img src="img/logo.png" alt="ロゴ">
		<p><%=beans.getName() %></p>
		<nav class="global-nav">
		  <ul class="global-nav__list">
			<li class="global-nav__item"><a href="">メニュー1</a></li>
			<li class="global-nav__item"><a href="">メニュー2</a></li>
			<li class="global-nav__item"><a href="">メニュー3</a></li>
			<li class="global-nav__item"><a href="">メニュー4</a></li>
			<li class="global-nav__item"><a href="">メニュー5</a></li>
		  </ul>
		</nav>
		<div class="hamburger" id="js-hamburger">
		  <span class="hamburger__line hamburger__line--1"></span>
		  <span class="hamburger__line hamburger__line--2"></span>
		  <span class="hamburger__line hamburger__line--3"></span>
		</div>
		<div class="black-bg" id="js-black-bg"></div>
    </div>
</header>

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