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
<title>게시글 보기</title>
</head>
<body>
	<table class="table">
		<form action="reply.do" method="post">
			<input type="hidden" name="bId" value="${replyView.bId}">
			<input type="hidden" name="bGroup" value="${replyView.bGroup}">
			<input type="hidden" name="bStep" value="${replyView.bStep}">
			<input type="hidden" name="bIndent" value="${replyView.bIndent}">
			<tr>
				<td>번호</td>
				<td>${replyView.bId}</td>
			</tr>
			<tr>
				<td>조회수</td>
				<td>${replyView.bHit}</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><input class="form-control" type="text" name="bName" value="${replyView.bName}"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input class="form-control" type="text" name="bTitle" value="${replyView.bTitle}"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea class="form-control" cols="20" rows="10" name="bContent">${replyView.bContent}</textarea></td>
			</tr>
		
			<tr>
				<td colspan="2">
					<button class="btn btn-primary btn-sm" type="submit">답변</button>&nbsp;&nbsp;&nbsp;
					<a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath }/list.do">목록보기</a>
				</td>
			</tr>
		</form>
	</table>
</body>
</html>