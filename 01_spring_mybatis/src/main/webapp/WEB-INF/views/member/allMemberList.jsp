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
	<h1>전체 회원 조회</h1>
	
	<hr>
 	<button id="selMemberList">조회</button>
 	<table border="1">
 		<tr>
 			<th>아이디</th>
 			<th>이름</th>
 			<th>전화번호</th>
 			<th>나이</th>
 			<th>성별</th>
 			<th>가입일</th>
 		</tr>
 	</table>
 	<h4><a href="/">메인으로</a></h4>
	
	
	<script>
		$('#selMemberList').on('click',function(){
			$('.add').remove();
			$.ajax({
				url : '/member/allMemberList.kh',
				type : 'get',
				success : function(res){
					for(let i =0; i<res.length; i++){
						let html = "<tr class='add'>";
						html += "<td>" +res[i].memberId + "</td>";
						html += "<td>" +res[i].memberName + "</td>";
						html += "<td>" +res[i].memberPhone + "</td>";
						html += "<td>" +res[i].memberAge + "</td>";
						html += "<td>" +res[i].memberGender + "</td>";
						html += "<td>" +res[i].enrollDate + "</td>";
						html += "</tr>"
						
						$('table').append(html);
					}
				}
			,error : function() {
				console.log('ajax 통신 오류');
			}
			});
		});
	</script>
</body>
</html>