<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	if("${exception}".length > 0)
	{
		alert("${exception}");
		location.href ="/board/list";
	}
	else
	{
		alert("${fileError}");
		location.href ="javascript:history.back()";
	}
</script>
</head>

