/**
 * 
 */

let index = {
	init:function() {
		$("#button_save").on("click", ()=>{ // function(){} , ()=>{} 사용한 이유는 this를 바인딩 하기 위해서 이다. 
			this.save();
		});
		
		$("#button_delete").on("click", ()=>{ // 삭제 
			this.deleteById();
		});
		
		$("#button_update").on("click", ()=>{ // 수정
			this.update();
		});
		
		$("#btn_reply_save").on("click", ()=>{ // 댓글등록 
			this.replySave();
		});
		
	},
	
	save: function() {
		// alert('user 의 save  함수가 호출됨 ')
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		}
		
		// console.log(data)
		
		// 비동기 호
		// ajax 통신을 이용해서 3개의 데이터를 insert 요청 
		$.ajax({
			// 통신 수행 
			type: "POST",
			url:"/api/board",
			data:JSON.stringify(data), // http body  데이터 
			contentType: "application/json; charset:utf-8",
			dataType:"json" // 요청을 서버로 해서 응답이 왔을 때, 응답이 json형태이면 javascript 파라미터로 변
		}).done(function(resp) {
			
			alert("글쓰기가 완료 되었습니다.")
			// alert(resp)
			// console.log(resp)
			location.href = "/";
		}).fail(function() {
		}); 
	},
	
	deleteById: function() {
		let id = $("#id").text();
		
		$.ajax({
			// 통신 수행 
			type: "DELETE",
			url:"/api/board/"+id,
			contentType: "application/json; charset:utf-8",
			dataType:"json" // 요청을 서버로 해서 응답이 왔을 때, 응답이 json형태이면 javascript 파라미터로 변
		}).done(function(resp) {
			alert("삭제가 완료 되었습니다.")
			location.href = "/";
		}).fail(function() {
		}); 
	},
	
	update: function() {
		let id = $("#id").val();
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		}
		
		// console.log(data)
		
		// 비동기 호
		// ajax 통신을 이용해서 3개의 데이터를 insert 요청 
		$.ajax({
			// 통신 수행 
			type: "PUT",
			url:"/api/board/"+id,
			data:JSON.stringify(data), // http body  데이터 
			contentType: "application/json; charset:utf-8",
			dataType:"json" // 요청을 서버로 해서 응답이 왔을 때, 응답이 json형태이면 javascript 파라미터로 변
		}).done(function(resp) {
			
			alert("수정이  완료 되었습니다.")
			// alert(resp)
			// console.log(resp)
			location.href = "/";
		}).fail(function() {
		}); 
	},
	
	
	replySave: function() {
		// alert('user 의 save  함수가 호출됨 ')
		let data = {
			content: $("#reply_content").val()
		};
		
		let boardid = $("#boardid").val();
		//alert(data.content);
  //		alert(boardid);
		
		// 비동기 호
		// ajax 통신을 이용해서 3개의 데이터를 insert 요청 
		$.ajax({
			// 통신 수행 
			type: "POST",
			url:'/api/board/'+boardid+'/reply',
			data:JSON.stringify(data), // http body  데이터 
			contentType: "application/json; charset:utf-8",
			dataType:"json" // 요청을 서버로 해서 응답이 왔을 때, 응답이 json형태이면 javascript 파라미터로 변
		}).done(function(resp) {
			
			alert("댓글작성이 완료 되었습니다.")
			// alert(resp)
			// console.log(resp)
			location.href = "/board/"+boardid;
		}).fail(function() {

		});
	},
	
	replyDelete: function(boardid, replyid) {
		
		//	alert(boardid +"  sdf "+ replyid);
			
		$.ajax({
			// 통신 수행 
			type: "DELETE",
			url:'/api/board/'+boardid+'/reply/'+replyid,
			contentType: "application/json; charset:utf-8",
			dataType:"json" // 요청을 서버로 해서 응답이 왔을 때, 응답이 json형태이면 javascript 파라미터로 변
		}).done(function(resp) {
			alert("댓글삭제 성공 ")
			location.href = '/board/'+boardid;
		}).fail(function() {

		});
	},
}

index.init()