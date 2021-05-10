<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>       
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 보기</title>
</head>
<body>
	<table border="1" width="500px">
	<form action="modify.do" method="post">
		<input type="hidden" name="bId" value="${contentView.bId }">
		<tbody >
			<tr>
				<td>번호</td>
				<td>${contentView.bId }</td>
			</tr>
			<tr>
				<td>조회수</td>
				<td>${contentView.bHit }</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><input type="text" name="bName" value="${contentView.bName }"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="bTitle" value="${contentView.bTitle}"></td>
			</tr>
			<tr>
				<td>작성일자</td>
				<td>${contentView.bDate }</td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="10">${contentView.bContent }</textarea></td>
			</tr>
		
			<tr>
				<td colspan="2">
					<button type="submit">수정</button>&nbsp;&nbsp;&nbsp;
					<a href="${pageContext.request.contextPath }/delete.do?bId=${contentView.bId }">삭제</a>&nbsp;&nbsp;&nbsp;
					<a href="${pageContext.request.contextPath }/replyView.do?bId=${contentView.bId}">답변</a>&nbsp;&nbsp;&nbsp;
					<a href="${pageContext.request.contextPath }/list.do">목록보기</a>
				</td>
			</tr>
		</tbody>
		</form>
	</table>
</body>
</html>