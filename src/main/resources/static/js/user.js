/**
 * 
 */

let index = {
	init:function() {
		$("#button_save").on("click", ()=>{ // function(){} , ()=>{} 사용한 이유는 this를 바인딩 하기 위해서 이다. 
			this.save();
		});
		
		$("#button_update").on("click", ()=>{ 
			this.update();
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
			url:"/api/joinProc",
			data:JSON.stringify(data), // http body  데이터 
			contentType: "application/json; charset:utf-8",
			dataType:"json" // 요청을 서버로 해서 응답이 왔을 때, 응답이 json형태이면 javascript 파라미터로 변
		}).done(function(resp) {
			// alert(resp)
			// console.log(resp)
			
			if (resp.status == 500) {
				alert("회원가입에 실패 하였습니다.")
			} else {
				alert("회원가입이 완료 되었습니다.")
				location.href = "/";
			}
		}).fail(function() {
		}); 
	},
	
	update: function() {
		let data = {
			id: $("#id").val(),
			username: $("#username").val(),
			password: $("#pwd").val(),
			email: $("#email").val()
		}
		
		$.ajax({
			type: "PUT",
			url:"/api/user",
			data:JSON.stringify(data), // http body  데이터 
			contentType: "application/json; charset:utf-8",
			dataType:"json" // 요청을 서버로 해서 응답이 왔을 때, 응답이 json형태이면 javascript 파라미터로 변
		}).done(function(resp) {
			
			alert("회원수정이 완료되었습니다.")
			// alert(resp)
			// console.log(resp)
			location.href = "/";
		}).fail(function() {
			
			
		}); 
	}
	
}

index.init()