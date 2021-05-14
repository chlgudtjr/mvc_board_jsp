<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>       
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>게시글 내용보기</title>
</head>
<body>
	<table class="table">
	<form action="modify.do" method="post">
		<input type="hidden" name="bId" value="${contentView.bId }">
			<tr>
				<td><strong>번호</strong></td>
				<td>${contentView.bId }</td>
			</tr>
			<tr>
				<td><strong>조회수</strong></td>
				<td>${contentView.bHit }</td>
			</tr>
			<tr>
				<td><strong>작성자</strong></td>
				<td><input class="form-control" type="text" name="bName" value="${contentView.bName }"></td>
			</tr>
			<tr>
				<td><strong>제목</strong></td>
				<td><input class="form-control" type="text" name="bTitle" value="${contentView.bTitle}"></td>
			</tr>
			<tr>
				<td><strong>작성일자</strong></td>
				<td>${contentView.bDate }</td>
			</tr>
			<tr>
				<td><strong>내용</strong></td>
				<td><textarea class="form-control" rows="10">${contentView.bContent }</textarea></td>
			</tr>
		
			<tr>
				<td colspan="2">
					<button class="btn btn-primary btn-sm" type="submit">수정</button>&nbsp;&nbsp;&nbsp;
					<a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath }/delete.do?bId=${contentView.bId }">삭제</a>&nbsp;&nbsp;&nbsp;
					<a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath }/replyView.do?bId=${contentView.bId}">답변</a>&nbsp;&nbsp;&nbsp;
					<a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath }/list.do">목록보기</a>
				</td>
			</tr>
		</form>
	</table>
</body>
</html>