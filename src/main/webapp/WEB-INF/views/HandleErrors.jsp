<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	if(${movePage})
	{
		alert("${message}");
		location.href ="/board/list";
	}
	else
	{
		alert("${message}");
		location.href ="javascript:history.back()";
	}
</script>
</head>

