<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<h1>회원관리 - 마이페이지</h1>
	
	<hr>
	
	<form action="/member/update.kh" method="post">
		<input type="hidden" name="memberId" value="${loginMember.memberId}">
		<table border="1">
			<tr>
				<th>아이디</th>
				<td>${loginMember.memberId}</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="memberPw" value="${loginMember.memberPw}"></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="memberName" value="${loginMember.memberName}"></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" name="memberPhone" value="${loginMember.memberPhone}"></td>
			</tr>
			<tr>
				<th>나이</th>
				<td>${loginMember.memberAge}</td>
			<tr>
			<tr>
				<th>성별</th>
				<td><input type="radio" name="memberGender" value="M">남
				<input type="radio" name="memberGender" value="W">여</td>
			<tr>
			<tr>
				<th>가입일</th>
				<td>${loginMember.enrollDate}</td>
			<tr>
					<td colspan="2">
						<input type="submit" value="수정하기">
					</td>
				</tr>
		</table>
	</form>
	
	<script>
	$(function() {
	    // JSP 데이터를 JavaScript 변수로 전달
	    let memberGender = '${loginMember.memberGender}';

	    // input 태그 name 속성이 memberGender이고, value가 로그인한 회원의 성별과 일치하는 요소를 체크
	    $('input[name="memberGender"][value="' + memberGender + '"]').prop('checked', true);
	});

	
	</script>
	<h4><a href="/">메인으로</a></h4>
</body>
</html>