<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시글 - 게시글 상세 조회</h1>
	
	<c:if test="${param.error eq 'true'}">
        <script>
            alert('게시물 삭제에 실패했습니다. 다시 시도해 주세요.');
        </script>
    </c:if>
	
	<hr>
	
	<table border="1">
	
		<tr>
			<th>번호</th>
			<td>${notice.noticeNo}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${notice.noticeTitle}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${notice.noticeContent}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${notice.noticeWriter}</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<c:forEach var="file" items="${notice.fileList}">
				<%-- 서버로 요청해서 파일 다운 방식--%>
					<a href ="javascript:void(0)" 
					   onclick="fileDown('${file.fileName}', '${file.filePath}')">${file.fileName}</a>
				
				<%-- 바로 클라이언트로 요청(사용자에게 파일 경로가 노출이 된다)
				<a href="/resources/upload/notice/${file.filePath}" download>${file.fileName}</a>--%>
				</c:forEach>
			</td>
		</tr>
		<c:if test="${loginMember.memberId == notice.noticeWriter}">
		<tr>
			<td colspan="2">
					<a href="/notice/delete.kh?noticeNo=${notice.noticeNo}">
					삭제하기
					</a>
					<a href="/notice/updateFrm.kh?noticeNo=${notice.noticeNo}">
					수정하기
					</a>
			</td>
		</tr>
	</c:if>
	</table>
	<script>
		function fileDown(fileName, filePath){
			fileName = encodeURIComponent(fileName);
			filePath = encodeURIComponent(filePath);
			
			location.href ="/notice/fileDown.kh?fileName=" + fileName +"&filePath=" + filePath;
		}
		
	</script>
</body>
</html>