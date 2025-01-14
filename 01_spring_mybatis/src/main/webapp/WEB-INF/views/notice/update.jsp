<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<h1>게시글 - 게시글 수정</h1>
	
	<hr>
	
	<form action="/notice/update.kh"
		method="post"
		enctype="multiport/form-data">
			<input type="hidden" name="noticeNo" value="${notice.noticeNo}">
		
		
		<table border="1">
			<tr>
				<th>제목</th>
				<td>
				<input type="text"
					  name="noticeTitle"
					  value="${notice.noticeTitle}">
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
				<input type="text"
					name="noticeContent"
					value="${notice.noticeContent}">
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
				
				<%--
				신규 게시글 작성 시, 첨부파일 업로드를 하지 않은 사용자는 수정 시,
				업로드 할 수 있도록 input type=file 태그 생성.
				
				 HTML5에서 기존 file 정보를 input type=file로 만들지 않는 이유는,
				 보안적인(로컬 파일 시스템 경로 조작) 이슈로 지원하지 않음.
				 --%>
				<c:if test="${not empty notice.fileList}">
					<c:forEach var="file" items="${notice.fileList}">
						<span>${file.fileName}</span>
					</c:forEach>
					<a href="javascript:void(0)"
					 onclick="fileReUpload(this)">
					 재업로드
					 </a>
					 </c:if>
					 <c:if test="${empty notice.fileList}">
					 	<input type="file" name="files" multiple>
					 </c:if>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="수정하기">
				</td>			
			</tr>
		</table>
		</form>
		<script>
		function fileUpload(obj){
			//obj : 클릭한 <a>태그
			
			let tdEl = $(obj).parent(); //상위 td 태그
			
			tdEl.find('span').remove(); //기존 파일 정보 출력 <span> 삭제
			
			let inputEl = $('<input>');  //재업로드 할 수 있도록  input type = file 생성
			inputEl.attr('type', 'file');
			inputEl.attr('name','files');
			inputEl.attr('multiple'. true);
			
			tdEl.append(inputEl);
			
			$(obj).remove();
			
		}
		</script>
</body>
</html>