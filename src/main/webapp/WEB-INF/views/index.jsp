<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!-- 包含常用的标签库 -->
<%@include file="common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<!-- 静态包含，把代码合并到一起，编译形成一个servlet -->
<%@include file="common/header.jsp"%>
<!-- Custom styles for this template -->
<link href="/css/mycss/signin.css" rel="stylesheet">
<title>ceeker springboot 登录页面</title>
</head>
<body>
	<div class="container">
		<h1  class="text-center">Welcome to ceeker springboot</h1>
      <form class="form-signin">
        <h2 class="form-signin-heading"></h2>
        <label for="inputEmail" class="sr-only">邮箱</label>
        <input type="email" id="inputEmail" class="form-control" placeholder="账号/邮箱/手机号" required autofocus>
        <label for="inputPassword" class="sr-only">密码</label>
        <input type="password" id="inputPassword" class="form-control" placeholder="密码" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> 记住我
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
      </form>
    </div> <!-- /container -->
	<script type="text/javascript" src="/js/jquery-2.2.4.js"></script>
	<script type="text/javascript" src="/js/bootstrap.min.js"></script>
</body>
</html>