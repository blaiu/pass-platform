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
						<li>应用管理</li>
						<li class="active">应用维护</li>
					</ol>
					<div class="row">
						<div class="col-md-12">
							<form role="form" id="templatemo-preferences-form">
								<div class="row">
									<div class="col-md-12 margin-bottom-15">
										<label for="firstName" class="control-label">应用名称</label> 
										<select class="form-control margin-bottom-15" id="singleSelect">
											<option>应用1</option>
											<option>应用2</option>
										</select>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12 margin-bottom-15">
										<label for="firstName" class="control-label">JDK版本</label> 
										<select class="form-control margin-bottom-15" id="singleSelect">
											<option>1.6</option>
											<option>1.7</option>
										</select>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12 margin-bottom-15">
										<label for="singleSelect">Tomcat版本</label> 
										<select class="form-control margin-bottom-15" id="singleSelect">
											<option>6.0</option>
											<option>7.0</option>
										</select>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12 margin-bottom-15">
										<label for="singleSelect">镜像名称</label> 
											<input type="text"  class="form-control"  name="" />
									</div>
								</div>
								
								
								
								<div class="row">
									<div class="col-md-12 margin-bottom-15">
										<label for="singleSelect">IP:端口</label> 
										<input type="text"  class="form-control"  name="" />
									</div>
								</div>
								<div class="row ">
									<div class="col-md-12 margin-bottom-15">
										<button type="submit" class="btn btn-primary">运行</button>
										<button type="submit" class="btn btn-primary">停止</button>
									</div>
								</div>
							</form>
						</div>
					</div>
					
					<div class="row">
					
					 <!-- Nav tabs -->
                <ul class="nav nav-tabs" role="tablist" id="templatemo-tabs">
                  <li class="active"><a href="#continaer" role="tab" data-toggle="tab">容器实例</a></li>
                 <li ><a href="#home" role="tab" data-toggle="tab">日志</a></li>
                  <li><a href="#messages" role="tab" data-toggle="tab">扩容</a></li>
                  <li><a href="#settings" role="tab" data-toggle="tab">监控</a></li>
                </ul>

                <!-- Tab panes -->
                <div class="tab-content">
                  <div class="tab-pane fade in active" id="continaer">
                   <table class="table table-striped">
                      <thead>
                        <tr>
                          <th>#</th>
                          <th>项目包</th>
                          <th>镜像名称</th>
                          <th>运行状态</th>
                          <th>启动，停止</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr>
                          <td>1</td>
                          <td>John</td>
                          <td>v1.2</td>
                          <td>运行中</td>
                          <td><a href="${ctx}/views/jsp/project/mirror-view.jsp" >启动，停止</a></td>
                        </tr>
                        <tr class="success">
                          <td>2</td>
                          <td>Bill</td>
                           <td>v1.2</td>
                          <td>在用</td>
                          <td><a href="${ctx}/views/jsp/project/mirror-view.jsp" >启动，停止</a></td>
                        </tr>
                        </tbody>
                        </table>
                  </div>
                   <div class="tab-pane fade  " id="home">
                    	<pre>
                    		显示构建日志
                    	</pre>
                  </div>
                 
                  <div class="tab-pane fade" id="messages">
                 <form role="form" id="templatemo-preferences-form">
                 <div class="row">
									<div class="col-md-6 margin-bottom-15">
										<label for="firstName" class="control-label">CPU使用率%</label> <input
											type="text" class="form-control" id="firstName" value="90">
									</div>
							 
									<div class="col-md-6 margin-bottom-15">
										<label for="firstName" class="control-label">内存使用率%</label> <input
											type="text" class="form-control" id="firstName" value="80">
									</div>
								</div>
									<div class="row">
									<div class="col-md-6 margin-bottom-15">
										<label for="firstName" class="control-label">已有实例数</label> <input
											type="text" class="form-control" id="firstName" value="3">
									</div>
								 
									<div class="col-md-6 margin-bottom-15">
										<label for="firstName" class="control-label">容器个数</label> <input
											type="text" class="form-control" id="firstName" value="5">
									</div>
								</div>
								<div class="row ">
									<div class="col-md-12 margin-bottom-15">
										<button type="submit" class="btn btn-primary">确定扩容</button>
									</div>
								</div>
                  </div>
                  <div class="tab-pane fade" id="settings">
                     <pre>监控，CPU，内存</pre>
                     
                  </div>
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