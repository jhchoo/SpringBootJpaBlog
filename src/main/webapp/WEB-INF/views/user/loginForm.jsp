<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form>
		<div class="form-group">
			<label for="email">User Name:</label> <input type="email" class="form-control" placeholder="Enter Username" id="username">
		</div>
		<div class="form-group">
			<label for="pwd">Password:</label> <input type="password" class="form-control" placeholder="Enter password" id="pwd">
		</div>
		<div class="form-group form-check">
			<label class="form-check-label"> <input class="form-check-input" type="checkbox"> Remember me
			</label>
		</div>
	</form>
	<button id=button_login class="btn btn-primary">로그인</button>

</div>

<script src="/blog/js/user.js" type="text/javascript"></script>
<%@ include file="../layout/fotter.jsp"%>
