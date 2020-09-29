<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form action="/auth/loginProc"  method="post">
		<div class="form-group">
			<label for="email">User Name:</label>
			<input type="text" name="username" class="form-control" placeholder="Enter Username" id="username">
		</div>
		<div class="form-group">
			<label for="pwd">Password:</label>
			<input type="password" name="password" class="form-control" placeholder="Enter password" id="pwd">
		</div>
		<button id="button_login" class="btn btn-primary">로그인</button>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=bf1c33b6c7145ec9481e129ec6114ffa&redirect_uri=http://localhost:8080/auth/kakao/callback&response_type=code"><img height="38px" src="/image/kakao_login_medium.png"></a>
	</form>

</div>

<%@ include file="../layout/fotter.jsp"%>
