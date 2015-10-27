<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	 <form name="form1" enctype="multipart/form-data" method="post" action="${ctx}/mirror/upload/delete" onclick="return submitFile()">
	 	<div> <span>文件名:</span>${packageName } <input type="hidden" name="filePath" value="${filePath}"></div>
	 	<div> <span>路径:</span>${filePath} <input type="hidden" name="packageId" value="${packageId}"></div>
	 	<div> <input type="submit" value="删除"></div> 
    </form>
</body>
</html>