<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<body>
	<h1>환율 정보 조회 서비스</h1>
	
	<hr>
	
	<button id="searchFinancial">환율조회</button>
	
	<script>
	$("#searchFinancial").on('click',function(){
		$.ajax({
			url : "/api/financial",
			success : function(resData){
				console.log(resData);
			},
			error : function(){
				console.log("ajax 오류");
			}
		})
		
		
	});
	</script>
</body>
</html>