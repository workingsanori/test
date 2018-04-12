<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
  
	<title>title</title>
	
	<!-- 부트스트랩 -->
	<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
	<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
		
	<script type="text/javascript">
		$(function() {
			$(".pagination li a").on("click",function(){
				var frm = document.forms.frm_search;
				frm.currentPage.value =$(this).data("page"); 
				frm.submit();
			});
			//선택자에 2개를 쓰면 #board는 #board 안에있는  select[name=listSize]를 검색한다
			$("select[name=listSize]",$("#board")).on("change",function(){
				var frm = document.forms.frm_search;
				frm.listSize.value =$(this).val(); 
				frm.submit();				
			});
			
/* 			var init=function(){
			//스크립트는 구문이라던가 오류가 생기면 페이지 전체에 영향을 주기 때문에 사용하기 불편하다....
				$("[name=currentPage]").val(${search.currentPage});
				$("[name=listSize]").val(${search.listSize});
				
			}
			
			init();	 */		
		});
	
	
	</script>	
</head>
<body>
	<select name ="listSize" class="frm_listSize form-control" >
		<!-- eq 와 ==에 차이가 있다네여...캬캬캬캬캬 무슨차이??ㅋㅋㅋ  -->
		<option value="5"  ${search.listSize == 5  ? 'selected="selected"': ''}>5</option>
		<option value="10" ${search.listSize == 10 ? 'selected="selected"': ''}>10</option>
		<option value="20" ${search.listSize == 20 ? 'selected="selected"': ''}>20</option>
		<option value="30" ${search.listSize == 30 ? 'selected="selected"': ''}>30</option>
	</select>		
	<div class="container" id="board">
		<div class="page-header">
			<h1 style="margin-top:50px;"> 게시판 리스트</h1>
		</div>
		
		<div class="row">
		<!--검색-->
			<form name="frm_search" action="boardList.do" method="post" class="form-inline">
	 			<div class="form-group">
	 					<input type="hidden" name="currentPage" value="${search.currentPage}" /><!--서브밋할때마다 기존 설정을 유지하기 위해서-->
	 					<input type="hidden" name="listSize" value="${search.listSize}" /><!--서브밋할때마다 기존 설정을 유지하기 위해서-->
						<label>검색구분</label>
						<select name ="searchType" class="form-control">
							<!-- eq 와 ==에 차이가 있다네여...캬캬캬캬캬 무슨차이??ㅋㅋㅋ  -->
							<option value="all" ${search.searchType == 'all' ? 'selected="selected"': ''}>전체</option><!--보안상의 문제가 있을지 몰라서 가급적이면 컬럼이름을 쓰지 않음 ex:all, p1등등-->
							<option value="bo_title" ${search.searchType eq 'bo_title' ? 'selected="selected"': ''}>제목</option>
							<option value="bo_writer" ${search.searchType eq 'bo_writer' ? 'selected="selected"': ''}>작성자</option>
							<option value="bo_content" ${search.searchType eq 'bo_content' ? 'selected="selected"': ''}>내용</option>
						</select>
						<input name="searchWord" value="${search.searchWord}" class="form-control">
						<button type="submit" class="btn btn-default">검색</button><!--submit으로 꼭 해줘야지 form의 action이 실행됨ㅋㅋㅋㅋㅋㅋ 헷갈리지마!!!!-->
				</div>
			</form>
		</div>		
		
		<div class="row">
		<br>
			<div class="form-inline">
			전체 게시물 : ${search.totalRowCount} / 페이지수 : ${search.totalPageCount} / 
				<label>리스트</label>
				<select name ="listSize" class="frm_listSize form-control" >
					<!-- eq 와 ==에 차이가 있다네여...캬캬캬캬캬 무슨차이??ㅋㅋㅋ  -->
					<option value="5"  ${search.listSize == 5  ? 'selected="selected"': ''}>5</option>
					<option value="10" ${search.listSize == 10 ? 'selected="selected"': ''}>10</option>
					<option value="20" ${search.listSize == 20 ? 'selected="selected"': ''}>20</option>
					<option value="30" ${search.listSize == 30 ? 'selected="selected"': ''}>30</option>
				</select>	
			</div>		
		</div>
		<div class="row">
			<table class="table table-striped">
				<colgroup>
					<col width="5%" >	<!--넘버-->
					<col width="auto">	<!--제목-->
					<col width="10%">	<!--작성자-->
					<col width="20%">	<!--만든날짜-->
					<col width="8%">	<!--조회수-->
				</colgroup>
				<thead>
					<tr>
						<th class="text-center">번호</th>
						<th>제목</th>
						<th class="text-center">작성자</th>
						<th class="text-center">작성일</th>
						<th class="text-center">조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty boardList}">
						<c:forEach var="list" items="${boardList}" varStatus="st">
						<tr>
							<td class="text-center">${list.bo_no}</td>				
							<td><a href="boardView.do?bo_no=${list.bo_no}">${list.bo_title}</a></td>				
							<td class="text-center">${list.bo_writer}</td>				
							<td class="text-center">${list.bo_reg_date}</td>				
							<td class="text-center">${list.bo_read_cnt}</td>				
						</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty boardList}">
						<tr><td>데이터가 없습니다</td></tr>
					</c:if>
				
				
				</tbody>
			
			</table>
		</div>
	
		
		<div class="row">
			<div>
			<ul class="pagination">
					<c:if test="${search.startPage > 1}">
					    <li><a href="boardList.do?currentPage=${search.startPage -1 }" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>						
					</c:if>

			    		
				<c:forEach var="i" begin="${search.startPage}" end="${search.endPage }" varStatus="st">
					<c:if test="${i eq search.currentPage}">
						<li class="active"><a >${i}</a></li>
					</c:if>
					<c:if test="${i ne search.currentPage}">
						<li ><a data-page="${i}">${i}</a></li>
					</c:if>
				</c:forEach>
			
					<c:if test="${search.endPage < search.totalPageCount}">
					    <li><a href="boardList.do?currentPage=${search.endPage + 1 }" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>						
					</c:if>					
			</ul>
			</div>
			<a class="btn btn-default pull-right" href="boardForm.do" role="button">글쓰기</a>
		</div>		
	</div>
</body>
</html>