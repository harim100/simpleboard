<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
리스트
<tr>
<ul>
	<c:forEach var = "title" items="${bTitle}">
		<li>
		<c:choose>
       	 	<c:when test="${fn:length(title.BoardTitle) gt 11}">
        		<c:out value="${fn:substring(title.BoardTitle, 0, 10)}"/>...
        	</c:when>
        	<c:otherwise>
        		<c:out value="${title.BoardTitle}"/>
        	</c:otherwise>
		</c:choose>
		</li>
	</c:forEach>
</ul>

</body>
</html>