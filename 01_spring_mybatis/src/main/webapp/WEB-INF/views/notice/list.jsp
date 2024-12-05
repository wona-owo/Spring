<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시글 - 게시글 목록 조회</h1>
	
	<hr>
	
	<h4><a href="/notice/writeFrm.kh">작성하기</a></h4>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성일</th>
		</tr>
		<c:forEach var="notice" items="${list}">
			<tr>
				<td>${notice.noticeNo}</td>
				<td><a href="/notice/detailView.kh?noticeNo=${notice.noticeNo}">${notice.noticeTitle}</a></td>
				<td>${notice.noticeDate}</td>
			</tr>
		</c:forEach>
	</table>
	<h4>${pageNavi}</h4>
</body>
</html>