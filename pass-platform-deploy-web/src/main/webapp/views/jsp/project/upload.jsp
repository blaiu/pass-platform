<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${null != error && '' != error}">
	<div> 
		<span style="color: red;">* 
			${error}
		</span> 
	</div>
	</c:if> 
	 <form name="form1" enctype="multipart/form-data" method="post" action="" onclick="return submitFile()" target="_self">  
       <p><input id="fileId" type="file" name="file" size="20" maxlength="20">  <br></p>  
       <p><input type="submit" value="上 传" onclick="submitFile()"> <span style="font-size: 12px; color: red;"> 注：只支持tar.gz类型的文件</span></p>  
       
    </form>
	<script type="text/javascript">
		function submitFile() {
			var path = document.getElementById("fileId").value;
			var action = "${ctx}/mirror/upload";
			var actions = action + "?filePath=" + path + "&projectId=${projectId}&projectName=${projectName}";
			form1.action = actions;
			return true;
		}
	</script>
</body>
</html>