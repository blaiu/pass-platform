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
<%-- 							<form role="form" id="templatemo-preferences-form"  id="expansionForm" action="${ctx}/application/expansion" method="post"> --%>
							<form role="form"   id="expansionForm" action="${ctx}/application/expansion" method="post">
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
								
								<c:if test="${not empty projectPackage.memo  }">
								<div class="row">
									<div class="col-md-12 margin-bottom-15">
										<label for="memo">镜像备注</label><br>  
							<!--  			<div class="form-control">   -->
										<textarea rows="2" cols="152">${projectPackage.memo }</textarea>
							<!--			</div>   -->
									</div>
								</div>
								</c:if>
								
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
								
								<div class="row">
									<div class="col-md-12 margin-bottom-15">
										<label for="containerconfig">容器配置</label> 
										<div    class="form-control"   >${containerConfig.memory}M,${containerConfig.cpu}核</div>
										<input type="hidden" name="cpuMax" id="cpuMax" value="${containerConfig.cpu}"> 
										<input type="hidden" name="memoryMax" id="memoryMax" value="${containerConfig.memory}">
									</div>
								</div>
								
								<c:if test="${not empty volume.volumeName  }">
								<div class="row">
									<div class="col-md-12 margin-bottom-15">
										<label for="volumeName">挂载磁盘名称</label> 
										<div    class="form-control"> ${volume.volumeName}</div>
									</div>
								</div>
								</c:if>
								
								<div class="row ">
									<div class="col-md-12 margin-bottom-15">
										<button type="button" id="runButton" class="btn btn-primary" ${app.appStatus eq '1' ? 'disabled' :'' } >${app.appStatus eq '1'?'运行中...':'运行' }</button>
										<button type="button" id="stopButton" class="btn btn-primary"  ${app.appStatus eq '2' ? 'disabled' :'' } >${app.appStatus eq '2'?'已停止':'停止' }</button>
<!-- 										<button type="button" id="deleRc" class="btn btn-primary">删除实例</button> -->
									</div>
								</div>
								<div class="row">
			<div class="col-md-12 margin-bottom-15">
					 <!-- Nav tabs -->
                <ul class="nav nav-tabs" role="tablist" id="templatemo-tabs">
                 <li class="active"><a href="#settings" role="tab" data-toggle="tab"  >监控</a></li>
                  <li ><a href="#continaer" role="tab" data-toggle="tab" >容器实例</a></li>
                 <li ><a href="#home" role="tab" data-toggle="tab" id="logtab">日志</a></li>
                  <li><a href="#messages" role="tab" data-toggle="tab"  >扩容</a></li>
                  <c:if test="${app.expansionFlag eq 1  }">
                  <li><a href="#expansionPlan" role="tab" data-toggle="tab"  >扩容计划</a></li>
                  </c:if>
                </ul>
        <!--    <input type="hidden" id="cid" value="0"/>  -->
                <!-- Tab panes -->
                <div class="tab-content">
                  <div class="tab-pane fade " id="continaer">
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
                       <pre id="log">
                       
                       </pre>
                  </div>
                 
                  <div class="tab-pane fade" id="messages">
                 
                 <div class="row">
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
										<input type="hidden" name="allContainer" id="allContainer"/>
				                        
									</div>
								</div>
								<div class="row ">
									<div class="col-md-12 margin-bottom-15">
										<button type="button" id="expansionButton" class="btn btn-primary">确定扩容</button>
									</div>
								</div>
                  </div>
                  <div class="tab-pane  in active " id="settings" >
                  			<iframe src="http://10.58.56.36:3000/dashboard/solo/db/cloud-paas?panelId=1&fullscreen&from=now-30m&to=now&var-id=${nameSpaces}%2Fname%3A${rcLabel}&var-container_name=${contianerName}" width="700" height="500" frameborder="0"></iframe>
                  			<iframe src="http://10.58.56.36:3000/dashboard/solo/db/cloud-paas?panelId=2&fullscreen&from=now-30m&to=now&var-id=${nameSpaces}%2Fname%3A${rcLabel}&var-container_name=${contianerName}" width="700" height="500" frameborder="0"></iframe>
 					</div>
 					
 				
 				<div class="tab-pane fade " id="expansionPlan">
                   <div class="row">
								<div class="col-md-12 margin-bottom-15">
				   		    <form action="${ctx}/application/modify/expansion/${app.id}"  id="expansionPlanForm" method="post">    
									<!-- Nav tabs -->
									<ul class="nav nav-tabs" role="tablist" id="templatemo-tabs">
										<li class="active"><a href="#memory" role="tab" data-toggle="tab">内存指标</a></li>
										<li><a href="#CPU" role="tab" data-toggle="tab">CPU指标</a></li>
									</ul>
									
									<input type="hidden" class="form-control" name="appId" id="appId" value="${app.id}"> 
									
									<div class="tab-content">
										<div class="tab-pane fade in active" id="memory">
											<div class="row">
												<div class="col-md-6 margin-bottom-15">
													<label for="indicatorName" class="control-label">指标名称</label>
													<input type="text" class="form-control" readonly name="indicatorName" value="内存利用率"> 
										     		<input type="hidden" id="memoryExpansionIndex" readonly name="memoryExpansionIndex" value="2">    	
														
												</div>
												<div class="col-md-6 margin-bottom-15">
													<label for="singleSelect" class="control-label">指标取值</label>
									  				<select class="form-control" id="memoryGetValue" name="memoryGetValue">
														<option value="2">平均值</option>
													</select>    
													
												</div>
											</div>
											<div class="row">
												<div class="col-md-6 margin-bottom-15">
													<label for="memoryMaxValue" class="control-label">上限</label> 
									 				<input type="text" class="form-control" id="memoryMaxValue" name="memoryMaxValue" value="${memory.maxValue}" onkeyup="check(this)">   
														
												</div>
												<div class="col-md-6 margin-bottom-15">
													<label for="memoryMinValue" class="control-label">下限</label> 
													<input type="text" class="form-control" id="memoryMinValue" name="memoryMinValue" value="${memory.minValue}" onkeyup="check(this)">   
												</div>
											</div>
											
										</div>
										<div class="tab-pane fade in " id="CPU">
											<div class="row">
												<div class="col-md-6 margin-bottom-15">
													<label for="indicatorName" class="control-label">指标名称</label>
													<input type="text" class="form-control" readonly name="indicatorName" value="CPU利用率"> 
													<input type="hidden" id="cpuExpansionIndex" readonly name="cpuExpansionIndex" value="1">   
												</div>
												<div class="col-md-6 margin-bottom-15">
													<label for="singleSelect" class="control-label">指标取值</label>
													<select class="form-control" id="cpuGetValue" name="cpuGetValue">
														<option value="2">平均值</option>
													</select>   
												</div>
											</div>
											<div class="row">
												<div class="col-md-6 margin-bottom-15">
													<label for="cpuMaxValue" class="control-label">上限</label> 
													<input type="text" class="form-control" id="cpuMaxValue" name="cpuMaxValue"value="${cpu.maxValue}" onkeyup="check(this)">    
														
												</div>
												<div class="col-md-6 margin-bottom-15">
													<label for="cpuMinValue" class="control-label">下限</label> 
													<input type="text" class="form-control" id="cpuMinValue" name="cpuMinValue" value="${cpu.minValue}" onkeyup="check(this)">   
														
												</div>
											</div>

										</div>

									</div>

									<div class="row">
										<div class="col-md-6 margin-bottom-15">
											<label for="maxInstance" class="control-label">实例数量最大值</label>
											<input type="text" class="form-control" id="maxInstance" name="maxInstance" value="${memory.maxInstance}" onkeyup="check(this)">   
											<input type="hidden" class="form-control" id="expasions[1].maxInstance" name="expasions[1].maxInstance">
												
										</div>
										<div class="col-md-6 margin-bottom-15">
											<label for="minInstance" class="control-label">实例数量最小值</label>
											<input type="text" class="form-control" id="minInstance" name="minInstance" value="${memory.minInstance}" onkeyup="check(this)">   
											<input type="hidden" class="form-control" id="expasions[1].minInstance" name="expasions[1].minInstance">
												
										</div>

										<div class="col-md-6 margin-bottom-15">
											<label for="reduceCount" class="control-label">减少量</label> 
											<input type="text" class="form-control" id="reduceCount" name="reduceCount" value="${memory.reduceCount}" onkeyup="check(this)">   
											<input type="hidden" class="form-control" id="expasions[1].reduceCount" name="expasions[1].reduceCount">
												
										</div>
										<div class="col-md-6 margin-bottom-15">
											<label for="addCount" class="control-label">增加量</label> 
											<input type="text" class="form-control" id="addCount" name="addCount" value="${memory.addCount}" onkeyup="check(this)">   
											<input type="hidden" class="form-control" id="expasions[1].addCount" name="expasions[1].addCount">
										</div>
									</div>
									
									
									<div class="row ">
								        <div class="col-md-12 margin-bottom-15">
									         <button type="button" id="expansionSave" class="btn btn-primary">修改指标</button>
								        </div>
							       </div>
							       
					  			</form>    

								</div>

							</div>
							
							
                  </div>
 				
 				
				</div>
				</div>
							</form>
						
					
					
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
		
		
		$("#logtab").click(function(){
			if($("#allContainer").val()!=''){
			jQuery.ajax({
				type : "GET",
				url : "${ctx}/log/"+$("#allContainer").val(),
				data : "",
				dataType : "json",
				success : function(data) {
					$("#log").empty();
					$.each(data,function(i) {
						   // var ind = parseInt(i)+1;
							var codeStr = data[i].time+"  "+data[i].log+"\n";
							$("#log").append(codeStr);
							
					});
					
				} 
			});
				
			}else{
				alert("无日志信息！");
			}
		});
		
		
		$("#expansionSave").click(function(){
			
			//alert("aaaaa");
			
			var memoryExpansionIndex=document.getElementById("memoryExpansionIndex").value;
			var memoryGetValue=document.getElementById("memoryGetValue").value;
			var memoryMaxValue=document.getElementById("memoryMaxValue").value;
		    var memoryMinValue=document.getElementById("memoryMinValue").value;
			
			var cpuExpansionIndex=document.getElementById("cpuExpansionIndex").value;
			var cpuGetValue=document.getElementById("cpuGetValue").value;
			var cpuMaxValue=document.getElementById("cpuMaxValue").value;
			var cpuMinValue=document.getElementById("cpuMinValue").value;

			var maxInstance=document.getElementById("maxInstance").value;
			var minInstance=document.getElementById("minInstance").value;
			var reduceCount=document.getElementById("reduceCount").value;
			var addCount=document.getElementById("addCount").value;
			
			var flag=true;
			
			if(parseFloat(cpuMaxValue)>parseFloat($("#cpuMax").val()))
			{
			    alert("Cpu上限请输入小于"+$("#cpuMax").val()+"的数!");
			    flag=false;
			}
			
			if(parseFloat(cpuMinValue)>parseFloat($("#cpuMax").val()))
			{ alert("Cpu下限请输入小于"+$("#cpuMax").val()+"的数!");
			  flag=false;
			}
			
			var maxMemory=document.getElementById("memoryMaxValue").value;
			if(parseFloat(memoryMaxValue)>parseFloat($("#memoryMax").val()))
			    {alert("Memory上限请输入小于"+$("#memoryMax").val()+"的数!");
			    flag=false;
			    }
			var minMemory=document.getElementById("memoryMinValue").value;
			if(parseFloat(memoryMinValue)>parseFloat($("#memoryMax").val()))
			{ alert("Memory下限请输入小于"+$("#memoryMax").val()+"的数!");
			  flag=false;
			}
			
			
			var expansionPlan = {"memoryExpansionIndex":memoryExpansionIndex,"memoryGetValue":memoryGetValue,"memoryMaxValue":memoryMaxValue,"memoryMinValue":memoryMinValue,"cpuExpansionIndex":cpuExpansionIndex,"cpuGetValue":cpuGetValue,"cpuMaxValue":cpuMaxValue,"cpuMinValue":cpuMinValue,"maxInstance":maxInstance,"minInstance":minInstance,"reduceCount":reduceCount,"addCount":addCount};
			
			if(flag)
			{
			jQuery.ajax({
				type : "POST",
				url : "${ctx}/application/modify/expansion/${app.id}",
				data : expansionPlan,
				dataType : "json",
				success : function(data) {
					$('#templatemo-tabs a[href="#home"]').tab('show'); 
					$('#logs').prepend(data.msg);
					
				} 
			});
			
			}
			
			//$("#expansionPlanForm").submit();
		});
	 
	 
	});
	
	 
// 	 function abc(){ 
// 		 $('#cpu').attr('src', $('#cpu').attr('src'));
// 		 $('#memory').attr('src', $('#memory').attr('src'));
//  	} 
// 	 setInterval("abc()",3000);
	 
	var timeTask ;
	function initContainerTask(){
		timeTask = setInterval("initContainer()", 3000); 
	}
	
	function initContainer(){
		var runingFlag = true;
		var containerStr="";
		jQuery.ajax({
			type : "GET",
			url : "${ctx}/application/${app.projectSpell}/${app.appSpell}",
			data : "",
			dataType : "json",
			
			success : function(data) {
				//alert(data);
				
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
// 						 monitorStr = " <iframe src=\"http://10.58.56.36:3000/dashboard/solo/db/cloud-paas-pod?panelId=1&fullscreen&from=now-30m&to=now&var-id="+data[i].nameSpaces+"%2Fname%3A"+data[i].rcLabel+"&var-pod_name="+data[i].podName+"\" width=\"450\" height=\"200\" frameborder=\"0\"></iframe>";
// 						 monitorStr += " <iframe src=\"http://10.58.56.36:3000/dashboard/solo/db/cloud-paas-pod?panelId=2&fullscreen&from=now-30m&to=now&var-id="+data[i].nameSpaces+"%2Fname%3A"+data[i].rcLabel+"&var-pod_name="+data[i].podName+"\" width=\"450\" height=\"200\" frameborder=\"0\"></iframe>";
// 	                    $("#settings").append(monitorStr);
						containerStr+=(data[i].continerName).substring(9)+"#";
				});
				$("#allContainer").val(containerStr);
				if(runingFlag){
					clearInterval(timeTask);
				}
				$('.table tbody tr:odd').addClass('success');
			
               
			} 
		});
	}
	
	
	/*function checkMessage()
	{
		
		var maxCpu=document.getElementById("cpuMaxValue").value;
		if(parseFloat(maxCpu)>parseFloat($("#cpuMax").val()))
			{
		    alert("Cpu上限请输入小于"+$("#cpuMax").val()+"的数!");
		    return false;
		    }
		var minCpu=document.getElementById("cpuMinValue").value;
		if(parseFloat(minCpu)>parseFloat($("#cpuMax").val()))
		{ alert("Cpu下限请输入小于"+$("#cpuMax").val()+"的数!");
		  return false;
		}
		
		var maxMemory=document.getElementById("memoryMaxValue").value;
		if(parseFloat(maxMemory)>parseFloat($("#memoryMax").val()))
		    {alert("Memory上限请输入小于"+$("#memoryMax").val()+"的数!");
		    return false;
		    }
		var minMemory=document.getElementById("memoryMinValue").value;
		if(parseFloat(minMemory)>parseFloat($("#memoryMax").val()))
		{ alert("Memory下限请输入小于"+$("#memoryMax").val()+"的数!");
		  return false;
		}
		
	}*/
	
	function check(obj)
	{
	 var number = obj.value;
	 var number2 = parseInt(number);
	 if((/^[.0-9]+$/).test(number))
	  return;
	 else
	  obj.value = number.substring(0,number.length-1);
	}
	
	</script>
</body>
</html>