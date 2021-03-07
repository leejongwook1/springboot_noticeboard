let action = '';
let url = '';
let type = '';
let no = 0;

document.addEventListener("DOMContentLoaded", function(){
	//글 작성
	$("#createBtn").click(function(){
		action = "create";
		type = "POST";
		
		$("#modal-title").text("새 글 작성");
		
		$("#modalSubmit").text('작성')
		
		$("#title").val('');
		$("#comment").val('');
		$("#name").val('');
		
		if($("#name").is(":disabled")){
			$("#name").removeAttr("disabled");
		}
		
		$("#myModal").modal();
	});
	
	//수정버튼
	$("button[name='update']").click(function(){
		action = 'update';
		type = 'PUT';
		no = this.value;
		
		let row = $(this).parent().parent();
		let tr = row.children();
		
		let title = tr.eq(1).text();
		let comment = tr.eq(2).text();
		let name = tr.eq(3).text();
		
		$("#modal-title").text('수정하기');
		
		$("#modalSubmit").text('수정')
		
		$("#title").val(title);
		$("#comment").val(comment);
		$("#name").val(name);
		
		$("#name").attr("disabled", true);
		
		$("#myModal").modal();
	});
	
	//삭제버튼
	$("button[name='delete']").click(function(){
		no = this.value;
		
		$.ajax({
			url:'/board/'+no,
			type: 'DELETE',
			complete: function(data){ location.reload(); }
		})
	});
	
	//modal submit 버튼
	$("#modalSubmit").click(function(){
		url = '/board';
		
		let data;
		
		let date = (new Date()).toISOString().slice(0,10)
		
		if($("#modalSubmit").text() == "작성"){
			data = {
				title: $("#title").val(),
				comment: $("#comment").val(),
				name: $("#name").val(),
				date: date
			};
		} else if($("#modalSubmit").text() == "수정"){
			data = {
				no: no,
				title: $("#title").val(),
				comment: $("#comment").val(),
				date: date
			};
		}
		
		console.log(data);
		
		$.ajax({
			url: url,
			type: type,
			data: data,
			complete: function(data){ location.reload(); }
		})
		
		
	});
});