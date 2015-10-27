<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../commons/taglibs.jsp"%>
<%@ include file="jsp/header.jsp"%>
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
		<%@ include file="jsp/menu.jsp"%>
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
										<div  class="form-control"  >${app.appName }</div>
										  
									</div>
								</div>
								<div class="row">
									<div class="col-md-12 margin-bottom-15">
										<label for="imageName">镜像名称</label> 
											<div    class="form-control"   >${app.imageName}</div>
									</div>
								</div>
								
								<div class="row">
									<div class="col-md-12 margin-bottom-15">
										<label for="serverInfo">服务IP:端口</label> 
										<div    class="form-control"   >${app.serverIp}:${app.serverPort}</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12 margin-bottom-15">
										<label for="hostInfo">内网IP:端口</label> 
										<div    class="form-control"   >${app.hostIp}:${app.hostPort}</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12 margin-bottom-15">
										<label for="initInstance">初始实例数</label> 
										<div    class="form-control"   >${app.containerCount}</div>
									</div>
								</div>
								<div class="row ">
									<div class="col-md-12 margin-bottom-15">
										<button type="button" id="runButton" class="btn btn-primary" ${app.appStatus eq '1' ? 'disabled' :'' } >${app.appStatus eq '1'?'运行中...':'运行' }</button>
										<button type="button" id="stopButton" class="btn btn-primary"  ${app.appStatus eq '2' ? 'disabled' :'' } >${app.appStatus eq '2'?'已停止':'停止' }</button>
<!-- 										<button type="button" id="deleRc" class="btn btn-primary">删除实例</button> -->
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
                          <th>POD-IP</th>
                          <th>运行状态</th>
                        </tr>
                      </thead>
                      <tbody id="containerTask">
                       
                        </tbody>
                        </table>
                  </div>
                   <div class="tab-pane fade " id="home">
                    	<pre id="logs">
                    	</pre>
                  </div>
                 
                  <div class="tab-pane fade" id="messages">
                 <form role="form" id="expansionForm" action="${ctx}/application/expansion" method="post"  >
                 <div class="row">
									<div class="col-md-4 margin-bottom-15">
										<label for="firstName" class="control-label">CPU使用率%</label> 
										<div class="form-control" value="90"></div>
									</div>
							 
									<div class="col-md-4 margin-bottom-15">
										<label for="firstName" class="control-label">内存使用率%</label> 
										<div  class="form-control" value="80"></div>
									</div>
								</div>
									<div class="row">
									<div class="col-md-4 margin-bottom-15">
										<label for="firstName" class="control-label">已有实例数</label> <input
											type="text" class="form-control" id="instanceCount" name="instanceCount" value="">
									</div>
								 
									<div class="col-md-4 margin-bottom-15">
										<label for="expansionCount" class="control-label">容器个数</label> 
										<input	type="text" class="form-control" id="expansionCount" name="expansionCount"  >
										<input type="hidden" name="projectSpell" id="projectSpell" value="${app.projectSpell}" />
										<input type="hidden" name="appSpell" id="appSpell" value="${app.appSpell}" />
									</div>
								</div>
								<div class="row ">
									<div class="col-md-12 margin-bottom-15">
										<button type="button" id="expansionButton" class="btn btn-primary">确定扩容</button>
									</div>
								</div>
                  </div>
                  <div class="tab-pane fade" id="settings">
                   <iframe src="http://10.126.53.10:3000/dashboard/solo/db/heapster-pod?from=now-1h&to=now&var-podName=rc-name-234234234-pjiux&panelId=1&fullscreen" width="450" height="200" frameborder="0"></iframe>
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
		<script src="${ctx}/js/jquery.validate.js"></script>
	<script src="${ctx}/js/jquery.metadata.js"></script>
	<script src="${ctx}/js/messages_zh.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		initContainerTask();
		
		$("#expansionForm").validate({
			errorPlacement:function(error,element) {  	
				error.appendTo(element.parent().children());
			}, 
			errorElement: "em"  
		});
		$("#deleRc").click(function(){
			jQuery.ajax({
				type : "DELETE",
				url : "${ctx}/application/${app.projectSpell}/${app.appSpell}/${app.packageId}",
				data : "",
				dataType : "json",
				success : function(data) {
				 	$('#templatemo-tabs a[href="#home"]').tab('show'); 
				 	$('#logs').prepend(data.msg);
				 	initContainerTask();
				} 
			});
		});
		
		
		$('#expansionButton').click(function(){
			  if($("#expansionForm").valid()){
					jQuery.ajax({
						type : "POST",
						url : "${ctx}/application/expansion/",
						data : $('#expansionForm').serializeArray(),
						dataType : "json",
						success : function(data) {
							$('#templatemo-tabs a[href="#home"]').tab('show'); 
							$('#logs').prepend(data.msg);
							initContainerTask();
							$("#expansionCount").val("");
						} 
					});
			  }
		});
		$("#runButton").click(function(){
			location.href ="${ctx}/application/server/run/${app.id}";
		});
		$("#stopButton").click(function(){
			location.href ="${ctx}/application/server/stop/${app.id}";
		});
		
	});
	
	
	var timeTask ;
	function initContainerTask(){
		timeTask = setInterval("initContainer()", 3000); 
	}
	
	function initContainer(){
		var runingFlag = true;
		jQuery.ajax({
			type : "GET",
			url : "${ctx}/application/${app.projectSpell}/${app.appSpell}",
			data : "",
			dataType : "json",
			success : function(data) {
				$("#instanceCount").val(data.length);
				$("#containerTask").empty();
				$.each(data,function(i) {
					    var ind = parseInt(i)+1;
					    if(data[i].status == "Running"){
					    	runingFlag = runingFlag && true;
					    }else{
					    	runingFlag = runingFlag && false;
					    }
						var codeStr = " <tr>"+
	                         " <td>"+ind+"</td>"+
	                         " <td>"+data[i].podName+"</td>"+
	                         " <td>"+data[i].imagName+"</td>"+
	                         " <td>"+data[i].podIp+"</td>"+
	                         " <td>"+data[i].status+"</td>"+
	                        "</tr>";
						$("#containerTask").append(codeStr);
				});
				if(runingFlag){
					clearInterval(timeTask);
				}
				$('.table tbody tr:odd').addClass('success');
			} 
		});
	}
	</script>
</body>
</html>