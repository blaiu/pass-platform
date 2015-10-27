<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="../header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>容器配置</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="viewport" content="width=device-width">

<link rel="stylesheet" href="${ctx}/css/templatemo_main.css">
</head>
<body>
<!--
	<div id="main-wrapper">
		<div class="navbar navbar-inverse" role="navigation">
			<div class="navbar-header">
				<div class="logo">
					<h1>GomePaas</h1>
				</div>
			</div>
		</div>
		-->
		<div class="template-page-wrapper">
			<%@ include file="../menu.jsp"%>
			<div class="templatemo-content-wrapper">
				<div class="templatemo-content">
					<ol class="breadcrumb">
						<li>容器配置</li>
					</ol>
					<div class="row">
						<div class="col-md-12">
							<form action="${ctx}/deploy/config" method="post" id="projectForm">
								<div class="row">
									<div class="col-md-12 margin-bottom-15">
										<label for="configName" class="control-label">配置名称</label> 
										<input type="text" class="form-control" id="configName" name="configName" required maxlength=20>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12 margin-bottom-15">
										<label for="memory" class="control-label">内存(M)</label> 
										<input type="text" class="form-control" id="memory" name="memory" required min=0 max=8192>
									</div>
								</div>

								<div class="row">
									<div class="col-md-12 margin-bottom-15">
										<label for="cpu" class="control-label">CPU(核)</label> 
										<input type="text" class="form-control" id="cpu" name="cpu" required min=0 max=8192>
									</div>
								</div>
								

									<div class="row ">
										<div class="col-md-12 margin-bottom-15">
											<button type="submit" class="btn btn-primary">配置</button>
										</div>
									</div>
							</form>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	<script src="${ctx}/js/jquery.min.js"></script>
	<script src="${ctx}/js/bootstrap.min.js"></script>
	<script src="${ctx}/js/templatemo_script.js"></script>
	
	<script src="${ctx}/js/jquery.validate.js"></script>
	<script src="${ctx}/js/jquery.metadata.js"></script>
	<script src="${ctx}/js/messages_zh.js"></script>
	<script>
	$(document).ready(function() {
		$("#projectForm").validate({
			errorPlacement:function(error,element) {  	
				error.appendTo(element.parent().children());
			}, 
			errorElement: "em"  
		});
	});
</script>
</body>
</html>