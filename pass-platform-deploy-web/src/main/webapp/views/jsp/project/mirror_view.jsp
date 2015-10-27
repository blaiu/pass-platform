<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="../header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>应用管理</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="viewport" content="width=device-width">

<link rel="stylesheet" href="${ctx}/css/templatemo_main.css">
</head>
<body>
	<div id="main-wrapper">
		<div class="navbar navbar-inverse" role="navigation">
			<div class="navbar-header">
				<div class="logo">
					<h1>GomePaas</h1>
				</div>

			</div>
		</div>
		<div class="template-page-wrapper">
			<%@ include file="../menu.jsp"%>
			<div class="templatemo-content-wrapper">
				<div class="templatemo-content">
					<ol class="breadcrumb">
						<li>应用管理</li>
						<li class="active">镜像查看</li>
					</ol>
					<div class="row">
						<div class="col-md-12">
							<form role="form" id="templatemo-preferences-form">
								<div class="row">
									<div class="col-md-12 margin-bottom-15">
										<label for="firstName" class="control-label">项目名称</label> 
											<div class="form-control">
												${project.projectName }
											</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12 margin-bottom-15">
										<label for="singleSelect">基础镜像</label> 											
										<div class="form-control">
												${projectPackage.basicImage }
											</div>

									</div>
								</div>
								
									<div class="row">
									<div class="col-md-12 margin-bottom-15">
										<label for="singleSelect">项目包文件</label> 
										<div class="form-control">
												${projectPackage.packageName }
											</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12 margin-bottom-15">
										<label for="singleSelect">镜像名称</label>  
										<div class="form-control">
												${projectPackage.imageName }
											</div>
									</div>
								</div>
								
								<c:if test="${not empty projectPackage.memo  }">
								<div class="row">
									<div class="col-md-12 margin-bottom-15">
										<label for="memo">备注</label>  
										<div class="form-control">
												${projectPackage.memo }
										</div>
									</div>
								</div>
								</c:if>
							</form>
						</div>
					</div>

					<div class="row">

						<!-- Nav tabs -->
						<ul class="nav nav-tabs" role="tablist" id="templatemo-tabs">
							<li class="active"><a href="#home" role="tab"	data-toggle="tab">日志</a></li>
						</ul>

						<!-- Tab panes -->
						<div class="tab-content">
							<div class="tab-pane fade in active" id="home">
								<pre>
                    		显示构建日志
                    			</pre>
							</div>
						 
							 
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${ctx}/js/jquery.min.js"></script>
	<script src="${ctx}/js/bootstrap.min.js"></script>
	<script src="${ctx}/js/templatemo_script.js"></script>
</body>
</html>