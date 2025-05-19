<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
		<script>
			$(function(){
				$('#searchBtn').click(function(){
					$.ajax({
						url : "../NaverSearchAPI.do",
						type : "get",
						data : {
							keyword : $('#keyword').val(),
							startNum : $('#startNum option:selected').val()
						},
						dataType : "json", 
						success : sucFuncJson,
						error : errFunc
					});
				});
			});
			
			function sucFuncJson(d){
				var str = "";
				$.each(d.items, function(index, item){
					str += "<ul>";
					str += "	<li>" + (index + 1) + "</li>";
					str += "	<li>" + item.title + "</li>";
					str += "	<li>" + item.description + "</li>";
					str += "	<li>" + item.bloggername + "</li>";
					str += "	<li>" + item.bloggerlink + "</li>";
					str += "	<li>" + item.postdate + "</li>";
					str += "	<li><a href='" + item.link + "' target='_blank'>바로가기</a></li>";
					str += "</ul>";
				});
				
				$('#searchResult').html(str);
			}
			
			function errFunc(e){
				alert("실패" + e.status);
			}
		</script>
		<style>
			ul{border: 2px #cccccc solid;}
		</style>
	</head>
	<body>
		<div>
		    <div>
		    	<!-- 검색을 위한 <form> 태그를 정의 -->
		        <form id="searchFrm">
		            한 페이지에 10개씩 출력됨 <br />
		            <!-- 검색 시작 위치를 페이지 단위로 선택 -->
		            <select id="startNum">
		                <option value="1">1페이지</option>
		                <option value="11">2페이지</option>
		                <option value="21">3페이지</option>
		                <option value="31">4페이지</option>
		                <option value="41">5페이지</option>
		            </select>
		            <!-- 검색어를 입력 -->
		            <input type="text" id="keyword" placeholder="검색어를 입력하세요." />
		            <button type="button" id="searchBtn">검색 요청</button> <!-- 검색 요청 버튼 -->
		        </form>
		    </div>
		    <!-- 검색 결과가 출력되는 영역 -->
		    <div class="row" id="searchResult">
		        여기에 검색 결과가 출력됩니다.
		    </div>
	    </div>
	</body>
</html>