<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>       
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 리스트</title>
</head>
<body>
	<table border="1">
		<thead >
			<tr >
				<th>번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>조회수</th>
				<th>작성일자</th>
			</tr>
		</thead>
		
		<tbody >
			<c:forEach items="${list }" var="list">
			<tr>
				<td>${list.bId }</td>
				<td>${list.bName }</td>
				<td>
					<c:forEach begin="1" end="${list.bIndent }">-</c:forEach>
					<a href="${pageContext.request.contextPath }/contentView.do?bId=${list.bId }">${list.bTitle }</a>
				</td>
				<td>${list.bHit }</td>
				<td>${list.bDate }</td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="2">
					<a href="${pageContext.request.contextPath }/writeView.do">글작성</a>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>