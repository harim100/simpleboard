<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="../../resources/js/jquery-3.6.0.min.js"></script>
<title>게시판 목록</title>
<link rel="stylesheet" href="../../resources/css/boardList.css">
</head>
<body>
	<div class="container">
		<div class="navBar">
			<span> ${customerName} | </span> 
			<a href="javascript:doLogout()"><span>로그아웃</span></a>
		</div>
		<form>
			<div class="btnGroup">
				<button type="button" class="deleteBtn btn" id="deleteBtn">삭제</button>
				<button type="button" class="insertBtn btn" id="insertBtn" onClick="insert()">등록</button>
			</div>
			<table id="bListTable" class="bListTable">
				<colgroup>
					<col width="5%" />
					<col width="10%" />
					<col width="40%" />
					<col width="25%" />
					<col width="20%" />
				</colgroup>
				<tr>
					<th><input type="checkbox" id="selectAll"/></th>
					<th>이미지</th>
					<th>제목</th>
					<th>등록일</th>
					<th>수정/삭제</th>
				</tr>
				<c:forEach var="bList" items="${bList}" varStatus="status">
					<tr>
						<td>
							<input type="checkbox" name="cb" value="${bList.customerNum}"/> 
							<input type="hidden" value="${bList.brdIdx}"/>
						</td>
						<td>
							<img src="${bList.imagePath}" width="50" height="50">
						</td>
						<td class="tdTitle">
							<c:choose>
									<c:when test="${fn:length(bList.title) gt 11}">
										<c:out value="${fn:substring(bList.title, 0, 10)}" />...
			        				</c:when>
									<c:otherwise>
										<c:out value="${bList.title}" />
									</c:otherwise>
							</c:choose>
						</td>
						<td>${bList.insDate}</td>
						<td>
							<div name="tableBtns">
								<button class="modifyOneBtn tableBtn modify" onclick="modify(this)" type="button" value="${bList.brdIdx}">수정</button>
								<button class="deleteOneBtn tableBtn" onclick="deleteOne(this)" type="button" value="${bList.brdIdx}">삭제</button>
								<input type="hidden" value="${bList.customerNum}" name="customerNum"/>
							</div>
						</td>
					</tr>
				</c:forEach>
			</table>
			<table class="pagination">
				<tr>
					<c:if test="${pagination.isBefore}">
						<td>
							<a href="${pageContext.request.contextPath}/board/list?page=${pagination.startPage}">
							이전
							</a>
						</td>
					</c:if>
					<c:forEach var="curPage" items="${pagination.pages}">
						<td>
							<a href="${pageContext.request.contextPath}/board/list?page=${curPage+1}">
							${curPage+1}
							</a>
						</td>
					</c:forEach>
					<c:if test="${pagination.isNext}">
						<td>
							<a href="${pageContext.request.contextPath}/board/list?page=${pagination.endPage+1}">
							다음
							</a>
						</td>
					</c:if>
				</tr>
			</table>
		</form>
	</div>
	
<script type="text/javascript">
$(document).ready(function()
{
	$("#deleteBtn").click(function ()
	{
		var cbArr = [];
		$("input[name='cb']:checked").each(function(i) 
		{
			 cbArr.push($(this));
		});
		
		if(cbArr.length>0)
		{
			if (confirm("정말 삭제하시겠습니까?") == true) 
			{
				var idxArr = [];
		
				for (var i=0 ; i < cbArr.length ; i++)
				{
					idxArr.push(cbArr[i].next().val());
				}
				
				var obj = {"idxArr": idxArr};
				
				$.ajax({
					url: '/board/delete/group'
					, data: obj
					, method: 'get'
					, success: function(data) {
						data = 1 ? alert("삭제 성공") : alert("삭제 실패");
						location.href = "javascript:location.reload()";
					}, error: function(e) {
						alert("error" + e);
					}
				});
			} 
			else 
			{
				return;
			}
		} 
		else
		{
			alert("삭제할 글을 선택해 주세요");
		}
	});
});

	function modify(obj) 
	{
		location.href = '/board/view?brdIdx=' + obj.value;
	}
	
	function insert() 
	{
		location.href = '/board/write';
	}

	const cbSelectAll = $("#selectAll");
	const cbList = $("[name=cb]");
	
	cbList.each(function (){
		$(this).change(function(){
			cbValidation(this);
		});
	});
	
	cbSelectAll.change(function()
	{
		if(this.checked) 
		{
			for (var cbs of cbList) 
			{
				cbs.checked = true;
				cbValidation(cbs);
			} 
		} 
		else 
		{
			for (var cbs of cbList) 
			{
				cbs.checked = false;
			}
		}
	});
	
	function init()
	{
		var customerNum = "${customerNumber}";
		if (customerNum.length <= 0) 
		{
			alert("로그인이 필요합니다"),
			location.href ="/login"
		}
		hideButtons();
	}
	
	function cbValidation(cb)
	{
		var author = cb.value;
			if(cb.value != "${customerNumber}")
			{
				cb.checked = false;
			}
	}
	
	function deleteOne(btn) 
	{
		if (confirm("정말 삭제하시겠습니까?") == true) 
		{
			$.get('/board/delete?brdIdx='+btn.value, function(result) 
			{
				result = 1 ? alert("삭제 성공") : alert("삭제 실패");
				location.href = `${pageContext.request.contextPath}/board/list`;
			});
		} else {
			return;
		}
	}
	
	function doLogout()
	{
		$.post('/logout', function() 
		{
			location.href= "/login";
		});
	}
	
	function isChecked(cb) 
	{
		return cb.checked === true;
	}
	
	function hideButtons()
	{
		const tableBtns = $("[name='tableBtns']");
		
		 $(tableBtns).each(function(i)
		{
			if($(this).children("[name='customerNum']").val() != "${customerNumber}")
			{
				$(this).css("display", "none");			 
			}
		});
	}
	
	init();
</script>
</body>
</html>