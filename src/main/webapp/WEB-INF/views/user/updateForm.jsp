<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form>
		<input type="hidden" id="id" value="${principal.user.id}">
		<div class="form-group">
			<label for="email">User Name:</label>
			<input type="text" value="${principal.user.username}" class="form-control" placeholder="Enter Username" id="username" readonly>
		</div>
		<div class="form-group">
			<label for="pwd">Password:</label>
			<input type="password" class="form-control" placeholder="Enter password" id="pwd">
		</div>
		<div class="form-group">
			<label for="email">Email:</label>
			<input type="email" value="${principal.user.email}" class="form-control" placeholder="Enter email" id="email">
		</div>
		
	</form>
	<button id="button_update" class="btn btn-primary">회원수정완료</button>

</div>

<script src="/js/user.js" type="text/javascript"></script>
<%@ include file="../layout/fotter.jsp"%>
