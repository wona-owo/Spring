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
	<h1>AJAX 테스트 페이지</h1>
	
	<hr>
	
	<button id="ajaxTest1">Ajax Test 1</button>
	<button id="ajaxTest2">Ajax Test 2</button>
	<button id="ajaxTest3">Ajax Test 3</button>
	<button id="ajaxTest4">Ajax Test 4</button>
	<button id="ajaxTest5">Ajax Test 5</button>
	<button id="ajaxTest6">Ajax Test 6</button>
	
	<script>
	$("#ajaxTest1").on('click', function(){
		$.ajax({
			url : '/ajax/ajaxTest1.kh',
			data : {str : '스프링', num : 10},
			type : 'get',
			success : function(res){
				console.log(res);
			}
			,error : function(){
				console.log('ajax 통신 오류 !');
			}
		})
	});
	
	$("#ajaxTest2").on('click', function(){
		$.ajax({
			url : '/ajax/ajaxTest2.kh',
			data : {str : '스프링', num : 10},
			type : 'get',
			success : function(res){
				console.log(res)
			}
			,error : function (){
				console.log('ajax 통신 오류 !');
			}
		})
	});
	
	$("#ajaxTest3").on('click', function(){
		$.ajax({
			url : '/ajax/ajaxTest3.kh',
			data : {str : '스프링', num : 10},
			type : 'get',
			success : function(res){
				//Controller에서 JSONArray에 요소들을 추가하고, 응답하면 응답된 데이터 자료형이 string임.
				console.log(typeof res);
				
				console.log(res);
				res = JSON.parse(res); //JSON.parse : JSON 형식의 "문자열"을 JSON으로 변환해주는 메소드
				
				//parse 메소드를 통해, Javascript Object로 변환하여 배열이 출력됨.
				console.log(res);
				console.log(typeof res);
			}
			,error : function (){
				console.log('ajax 통신 오류 !');
			}
		})
	});
	
	$("#ajaxTest4").on('click', function(){
		$.ajax({
			url : '/ajax/ajaxTest4.kh',
			data : {str : '스프링', num : 10},
			//dataType : 'json',  //응답 데이터 형식을 json으로 지정(Controller에서 json 문자열로 응답 시 자동으로 JSON 오브젝트로 변환)
			type : 'get',
			success : function(res){
				console.log(res)
			    console.log(typeof res);
				
				res = JSON.stringify(res);
				console.log(typeof res);
			}
			,error : function (){
				console.log('ajax 통신 오류 !');
			}
		})
	});
		
	
	$("#ajaxTest5").on('click', function(){
		$.ajax({
			url : '/ajax/ajaxTest5.kh',
			type : 'get',
			success : function(res){
				console.log(res);
				console.log(typeof res);
				
			}
			,error : function (){
				console.log('ajax 통신 오류 !');
			}
		})
	});
	
	$("#ajaxTest6").on('click', function(){
		$.ajax({
			url : '/ajax/ajaxTest6.kh',
			type : 'get',
			success : function(res){
				console.log(res);
				console.log(typeof res);
				
				for(i : res.length){
					console.log(res[i].memberId);
					console.log(res[i].memberName);
				}
				
			}
			,error : function (){
				console.log('ajax 통신 오류 !');
			}
		})
	});
	</script>
</body>
</html>