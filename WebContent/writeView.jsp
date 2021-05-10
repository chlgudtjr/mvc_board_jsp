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
	<form action="write.do" method="post">
			<tr>
				<td>작성자</td>
				<td><input type="text" name="bName" ></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="bTitle"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="10" name="bContent"></textarea></td>
			</tr>
		
			<tr>
				<td colspan="2">
					<button type="submit">글작성</button>&nbsp;&nbsp;&nbsp;
					<a href="${pageContext.request.contextPath }/list.do">목록보기</a>
				</td>
			</tr>
		</form>
	</table>
</body>
</html>