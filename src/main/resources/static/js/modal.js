let action = '';
let url = '';
let type = '';
let no = 0;

document.addEventListener("DOMContentLoaded", function(){
	//게시판 출력
	$.ajax({
		url: '/boardJson',
		type: 'GET',
		datatype: 'json',
		contentType: 'application/json',
		success:function(data){
			let json = JSON.parse(data);
			
			if(json.length != 0){
				alert("게시판 데이터 받아오기 성공!!");
				console.log(data);
				console.log(json);
				
				let option = "";
				let tr = "";
			
				$("#select_board").empty();
				$("#board tbody").empty();
			
				//select option출력
				option += `<option value="total">전체</option>`;
				for(key in json){
					option += `<option value="${json[key].no}">${json[key].no}</option>`;
				}
				$("#select_board").append(option);
			
				//게시판 출력
				for(key in json){
					tr = printBoard(json, tr, key);
				}
				$("#board tbody").append(tr);
			} else{
				alert("데이터가 없습니다!!");
				console.log(data);
			}
		},
		error:function(request, error){
			alert(`code: ${request.status} \n message: ${request.responseText} \n error: ${error}`);
		}
	})
	
	$("#select_board").on('change', function(e){
		no = $(e.target).val()
		if($(e.target).val() != "total"){
			$.ajax({
				url: '/board/'+no,
				type: 'GET',
				success:function(data){
					let tr="";
				
					if(data){
						alert("글 번호 불러오기 성공");
						console.log(data);
						
						$("#board tbody").empty();
						
						console.log(no + " 클릭")
						
						tr += `<tr>`;
						tr += `<td>${data.no}</td>`;
						tr += `<td>${data.title}</td>`;
						tr += `<td>${data.comment}</td>`;
						tr += `<td>${data.name}</td>`;
						tr += `<td>${data.date}</td>`;
						tr += `<td><button name="update" value="${data.no}" class="btn btn-xs btn-warning">수정</button></td>`;
						tr += `<td><button name="delete" value="${data.no}" class="btn btn-xs btn-danger">삭제</button></td>`;
						tr += `</tr>`;
						
						$("#board tbody").append(tr);
					} else{
						alert("글 번호 불러오기 실패");
						console.log(data);
					}
				},
				error:function(request, status, error){
					console.log(request.responseText)
					alert(`code: ${request.status} \n message: ${request.responseText} \n error: ${error}`);
				}
			})
		} else{
			console.log("전체 클릭");
			location.reload();
		}
	});
	
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
	$(document).on('click', "button[name='update']", function(){
		console.log("update");
		action = 'update';
		type = 'PUT';
		no = this.value;
		
		let row = $(this).parent().parent();
		let tr = row.children();
		
		let title = tr.eq(1).text();
		let comment = tr.eq(2).text();
		let name = tr.eq(3).text();
		
		$("#modal-title").text('수정하기');
		
		$("#modalSubmit").text('수정');
		
		$("#title").val(title);
		$("#comment").val(comment);
		$("#name").val(name);
		
		$("#name").attr("disabled", true);
		
		$("#myModal").modal();
	});
	
	//삭제버튼
	$(document).on('click', "button[name='delete']", function(){
		no = this.value;
		
		$.ajax({
			url:'/board/'+no,
			type: 'DELETE',
			success:function(data){
				if(data==1){
					alert("삭제 성공");
					console.log(data);
					location.reload();
				} else{
					alert("삭제 실패");
					console.log(data);
				}
			},
			error:function(request, status, error){
				alert(`code: ${request.status} \n message: ${request.responseText} \n error: ${error}`);
			}
		});
	});
	
	//modal submit 버튼
	$("#modalSubmit").click(function(){
		url = '/board';
		
		let data;
		
		let date = (new Date()).toISOString().slice(0,10)
		
		if($("#modalSubmit").text() == "작성"){
			data = {
				'title': $("#title").val(),
				'comment': $("#comment").val(),
				'name': $("#name").val(),
				'date': date
			};
		} else if($("#modalSubmit").text() == "수정"){
			data = {
				'no': no,
				'title': $("#title").val(),
				'comment': $("#comment").val(),
				'date': date
			};
		}
		
		console.log(data);
		console.log(type);
		
		$.ajax({
			url: url,
			type: type,
			data: JSON.stringify(data),
			dataType: 'json',
			contentType: 'application/json',
			success:function(data){
				if(data==1){
					alert(`글 ${$("#modalSubmit").text()} 성공`);
					console.log(data);
					location.reload();
				} else{
					alert(`글 ${$("#modalSubmit").text()} 실패`);
					console.log(data);
				}
			},
			error:function(request, status, error){
				alert(`code: ${request.status} \n message: ${request.responseText} \n error: ${error}`);
			}
		});
	});
	
	//tbody tr 생성함수
	function printBoard(data, tr){
		tr += `<tr>`;
		tr += `<td>${data[key].no}</td>`;
		tr += `<td>${data[key].title}</td>`;
		tr += `<td>${data[key].comment}</td>`;
		tr += `<td>${data[key].name}</td>`;
		tr += `<td>${data[key].date}</td>`;
		tr += `<td><button name="update" value="${data[key].no}" class="btn btn-xs btn-warning">수정</button></td>`;
		tr += `<td><button name="delete" value="${data[key].no}" class="btn btn-xs btn-danger">삭제</button></td>`;
		tr += `</tr>`;
		
		return tr;
	}
	
});