/**
 * 
 */

let index = {
	init:function() {
		$("#button_save").on("click", ()=>{ // function(){} , ()=>{} 사용한 이유는 this를 바인딩 하기 위해서 이다. 
			this.save();
		});
		$("#button_login").on("click", ()=>{ // 
			this.login();
		});
	},
	
	save: function() {
		// alert('user 의 save  함수가 호출됨 ')
		let data = {
			username: $("#username").val(),
			password: $("#pwd").val(),
			email: $("#email").val()
		}
		
		// console.log(data)
		
		// 비동기 호
		// ajax 통신을 이용해서 3개의 데이터를 insert 요청 
		$.ajax({
			// 통신 수행 
			type: "POST",
			url:"/blog/api/user",
			data:JSON.stringify(data), // http body  데이터 
			contentType: "application/json; charset:utf-8",
			dataType:"json" // 요청을 서버로 해서 응답이 왔을 때, 응답이 json형태이면 javascript 파라미터로 변
		}).done(function(resp) {
			
			alert("회원가입이 완료 되었습니다.")
			// alert(resp)
			// console.log(resp)
			location.href = "/blog";
		}).fail(function() {
			
			
		}); 
	},
	
	login: function() {
		// alert('user 의 save  함수가 호출됨 ')
		let data = {
			username: $("#username").val(),
			password: $("#pwd").val()
		}
		
		// console.log(data)
		
		// 비동기 호
		// ajax 통신을 이용해서 3개의 데이터를 insert 요청 
		$.ajax({
			// 통신 수행 
			type: "POST",
			url:"/blog/api/user/login",
			data:JSON.stringify(data), // http body  데이터 
			contentType: "application/json; charset:utf-8",
			dataType:"json" // 요청을 서버로 해서 응답이 왔을 때, 응답이 json형태이면 javascript 파라미터로 변
		}).done(function(resp) {
			
			alert("로그인이 완료 되었습니다.")
			// alert(resp)
			// console.log(resp)
			location.href = "/blog";
		}).fail(function() {
			
			
		}); 
	}
}

index.init()