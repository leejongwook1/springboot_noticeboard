<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/js/modal.js"></script>
	<title>board</title>
</head>
<body>
	<div class="container">
		<h2>게시판</h2>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>no</th>
					<th>제목</th>
					<th>글</th>
					<th>이름</th>
					<th>작성 날짜</th>
					<th>수정</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="board" items="${boardList}">
					<tr>
						<td>${board.no}</td>
						<td>${board.title}</td>
						<td>${board.comment}</td>
						<td>${board.name}</td>
						<td>${board.date}</td>
						<td><button name="update" value="${board.no}" class="btn btn-xs btn-warning">수정</button></td>
						<td><button name="delete" value="${board.no}" class="btn btn-xs btn-danger">삭제</button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<jsp:include page="../board/modal.jsp"/>
		<button id="createBtn" type="button" class="btn btn-info btn-sm" data-toggle="modal">글 작성</button>
	</div>
</body>
</html> 