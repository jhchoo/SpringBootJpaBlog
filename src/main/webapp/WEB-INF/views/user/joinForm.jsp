<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form>
		<div class="form-group">
			<label for="email">User Name:</label> <input type="text" class="form-control" placeholder="Enter Username" id="username">
		</div>
		<div class="form-group">
			<label for="pwd">Password:</label> <input type="password" class="form-control" placeholder="Enter password" id="pwd">
		</div>
		<div class="form-group">
			<label for="email">Email:</label> <input type="email" class="form-control" placeholder="Enter email" id="email">
		</div>
	</form>
	<button id="button_save" class="btn btn-primary">회원가입 신청</button>

</div>

<script src="/blog/js/user.js" type="text/javascript"></script>
<%@ include file="../layout/fotter.jsp"%>
