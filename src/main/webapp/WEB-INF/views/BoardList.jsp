<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="../../resources/js/jquery-3.6.0.min.js"></script>
<title>게시판 목록</title>
<link rel="stylesheet" href="../../resources/css/boardList.css">
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
			if (confirm("<spring:message code='Board.delete.confirm'/>") == true) 
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
					, success: function(data) 
					{
						data = 1 ? alert('<spring:message code="Board.delete.success"/>') : alert('<spring:message code="Board.delete.fail"/>');
						location.href = "javascript:location.reload()";
					}, error: function(e) 
					{
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
			alert('<spring:message code="Board.delete.choose"/>');
		}
	});
	
	function cbValidation(cb)
	{
		var author = cb.value;
			if(cb.value != "${customer_no}")
			{
				cb.checked = false;
			}
	}
	
	function isChecked(cb) 
	{
		return cb.checked === true;
	}
	
	const cbSelectAll = $("#selectAll");
	const cbList = $("[name=cb]");
	
	cbList.each(function ()
	{
		$(this).change(function()
		{
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
	
	const tableBtns = $("[name='tableBtns']");
	const tdTitle = $("#tdTitle");
	
	 $(tableBtns).each(function(i)
	{
		$(this).click(function (event)
		{
			if($(this).children("#customer_no").val() != "${customer_no}")
			{
				alert('<spring:message code="Board.auth.error"/>');
			}
			else if($(event.target).attr("id") == "modifyBtn")
			{
				location.href = '/board/view?brdIdx=' + $(this).children("#modifyBtn").val();
			}
			else if($(event.target).attr("id") == "deleteBtn")
			{
				deleteOne(event.target);
			}
		});
	});
	
	function init()
	{
		var customerNum = "${customer_no}";
		if (customerNum.length <= 0) 
		{
			alert('<spring:message code="Board.check.login"/>');
			location.href ="/login";
		}
		
	}
	
	init();
});

function deleteOne(btn) 
{
	if (confirm('<spring:message code="Board.delete.confirm"/>') == true) 
	{
		$.get('/board/delete?brdIdx='+btn.value, function(result) 
		{
			result = 1 ? alert('<spring:message code="Board.delete.success"/>') : alert('<spring:message code="Board.delete.fail"/>');
			location.href = `${pageContext.request.contextPath}/board/list`;
		});
	} 
	else 
	{
		return;
	}
}

function insert() 
{
	location.href = '/board/write';
}

function doLogout()
{
	$.post('/logout', function() 
	{
		location.href= "/login";
	});
}
</script>
</head>
<body>
	<div class="container">
		<div class="navBar">
			<span> ${customer_nm} | </span> 
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
							<input type="checkbox" name="cb" value="${bList.customer_no}"/> 
							<input type="hidden" value="${bList.brd_idx}"/>
						</td>
						<td>
							<img src="${bList.image_path}" width="50" height="50">
						</td>
						<td class="tdTitle" id="tdTitle">
							<c:choose>
								<c:when test="${fn:length(bList.title) gt 11}">
									<c:out value="${fn:substring(bList.title, 0, 10)}" />...
		        				</c:when>
								<c:otherwise>
									<c:out value="${bList.title}" />
								</c:otherwise>
							</c:choose>
						</td>
						<td>${bList.ins_date}</td>
						<td>
							<div name="tableBtns">
								<button class="modifyOneBtn tableBtn modify" id="modifyBtn" type="button" value="${bList.brd_idx}">수정</button>
								<button class="deleteOneBtn tableBtn" id="deleteBtn" type="button" value="${bList.brd_idx}">삭제</button>
								<input type="hidden" value="${bList.customer_no}" id="customer_no" name="customer_no"/>
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
</body>
</html>