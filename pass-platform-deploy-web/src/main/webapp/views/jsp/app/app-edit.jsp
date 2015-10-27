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
					<li class="active">应用创建</li>
				</ol>
				<div class="row">
					<div class="col-md-12">
						<form action="${ctx}/application/add" name="appForm" id="appForm" method="post">
							<div class="row">
								<div class="col-md-12 margin-bottom-15">
									<label for="appName" class="control-label">应用名称</label> <input
										type="text" class="form-control margin-bottom-15 required"
										id="appName" name="appName" value="${app.appName}">
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 margin-bottom-15">
									<label for="appName" class="control-label">应用简拼</label> <input
										type="text" class="form-control margin-bottom-15 required"
										id="appSpell" name="appSpell" value="${app.appSpell}">
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 margin-bottom-15">
									<label for="projectId" class="control-label">所属项目</label> <select
										class="form-control margin-bottom-15 required" id="projectId"
										name="projectId">
										<option value="">--请选择项目--</option>

										<c:forEach items="${project}" var="p">
											<option value="${p.projectId}"
												${p.projectId eq curproject.projectId ? 'selected':'' }>${p.projectName}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 margin-bottom-15">
									<label for="packageId" class="control-label">选择镜像</label> <select
										class="form-control margin-bottom-15 required" id="packageId"
										name="packageId" value="">
										<c:forEach items="${projectPackges}" var="projectPackage">
											<option value="${projectPackage.packageId}"
												${projectPackage.packageId eq curPackage.packageId ? 'selected':'' }>${projectPackage.imageName}-${projectPackage.versionNo}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 margin-bottom-15">
									<label for="hostPort" class="control-label">端口号</label> <input
										type="text" class="form-control margin-bottom-15 required"
										id="hostPort" name="hostPort" value="${app.hostPort}">
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 margin-bottom-15">
									<label for="containerCount" class="control-label">容器个数</label>
									<input type="text"
										class="form-control margin-bottom-15 required"
										id="containerCount" name="containerCount" value="1">
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 margin-bottom-15">
									<label for="configId" class="control-label">容器配置</label>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 margin-bottom-15">
									<c:forEach items="${containerconfig}" var="c">
										<label class="radio-inline"> <input type="radio"
											name="configId" id="configId${c.id}" value="${c.id}" checked
											onclick="config(${c.id})">${c.cpu}核 , ${c.memory}M <input
											type="hidden" name="configCpu" id="configCpu${c.id}"
											value="${c.cpu}"> <input type="hidden"
											name="configMemory" id="configMemory${c.id}"
											value="${c.memory}">
										</label>
									</c:forEach>
								</div>
								<input type="hidden" name="cpuMax" id="cpuMax"> <input
									type="hidden" name="memoryMax" id="memoryMax">
							</div>



							<div class="row">
								<div class="col-md-12 margin-bottom-15">
									<label for="mountVolume" class="control-label">挂载磁盘</label>

								</div>
							</div>
							<div class="row">
								<div class="col-md-12 margin-bottom-15">
									<label class="radio-inline"> <input type="radio"
										name="mountVolume" id="mountVolume1" value="0" checked
										onclick="mount(1)"> 否
									</label> <label class="radio-inline"> <input type="radio"
										name="mountVolume" id="mountVolume2" value="1"
										onclick="mount(2)"> 是
									</label>
								</div>
							</div>
							<div class="row" id="mountTab">
								<div class="col-md-6 margin-bottom-15">
									<select class="form-control margin-bottom-15 required" class="selector" id="volumeId" name="volumeId">
										<option value="">--请选择磁盘--</option>
										<c:forEach items="${volume}" var="v">
											<option value="${v.id}">${v.volumeName}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-md-6 margin-bottom-15">
									<button type="button" id="createVolume" class="btn btn-primary"
										data-toggle="modal" data-target="#myModal">创建磁盘</button>
								</div>
								<div class="col-md-7 margin-bottom-15">
									<input type="text" class="form-control margin-bottom-15"
										id="customPath" name="customPath" placeholder="请输入用户路径"
										onkeyup="checkPath(this)">
								</div>

							</div>

							<div class="row">
								<div class="col-md-12 margin-bottom-15">
									<label for="configId" class="control-label">扩容方案</label>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 margin-bottom-15">
									<label class="radio-inline"> <input type="radio"
										name="expansionFlag" id="expansionFlag1" value="0" checked
										onclick="expansion(1)"> 手动扩容
									</label> <label class="radio-inline"> <input type="radio"
										name="expansionFlag" id="expansionFlag2" value="1"
										onclick="expansion(2)"> 动态扩容
									</label>

								</div>
							</div>
							<div class="row" id="expansionTab">
								<div class="col-md-12 margin-bottom-15">
									<!-- Nav tabs -->
									<ul class="nav nav-tabs" role="tablist" id="templatemo-tabs">
										<li class="active"><a href="#memory" role="tab"
											data-toggle="tab">内存指标</a></li>
										<li><a href="#CPU" role="tab" data-toggle="tab">CPU指标</a></li>
									</ul>
									<div class="tab-content">
										<div class="tab-pane fade in active" id="memory">
											<div class="row">
												<div class="col-md-6 margin-bottom-15">
													<label for="indicatorName" class="control-label">指标名称</label>
													<input type="text" class="form-control" readonly
														name="indicatorName" value="内存利用率"> <input
														type="hidden" id="expasions[0].expansionIndex" readonly
														name="expasions[0].expansionIndex" value="2">
												</div>
												<div class="col-md-6 margin-bottom-15">
													<label for="singleSelect" class="control-label">指标取值</label>
													<select class="form-control" id="expasions[0].getValue"
														name="expasions[0].getValue">
														<option value="2">平均值</option>
													</select>
												</div>
											</div>
											<div class="row">
												<div class="col-md-6 margin-bottom-15">
													<label for="maxValue" class="control-label">上限</label> <input
														type="text" class="form-control"
														id="expasions[0].maxValue" name="expasions[0].maxValue"
														value="0" onkeyup="check(this)">
												</div>
												<div class="col-md-6 margin-bottom-15">
													<label for="minValue" class="control-label">下限</label> <input
														type="text" class="form-control"
														id="expasions[0].minValue" name="expasions[0].minValue"
														value="0" onkeyup="check(this)">
												</div>
											</div>



										</div>
										<div class="tab-pane fade in " id="CPU">
											<div class="row">
												<div class="col-md-6 margin-bottom-15">
													<label for="indicatorName" class="control-label">指标名称</label>
													<input type="text" class="form-control" readonly
														name="indicatorName" value="CPU利用率"> <input
														type="hidden" id="expasions[1].expansionIndex" readonly
														name="expasions[1].expansionIndex" value="1">
												</div>
												<div class="col-md-6 margin-bottom-15">
													<label for="singleSelect" class="control-label">指标取值</label>
													<select class="form-control" id="expasions[1].getValue"
														name="expasions[1].getValue">
														<option value="2">平均值</option>
													</select>
												</div>
											</div>
											<div class="row">
												<div class="col-md-6 margin-bottom-15">
													<label for="maxValue" class="control-label">上限</label> <input
														type="text" class="form-control"
														id="expasions[1].maxValue" name="expasions[1].maxValue"
														value="0" onkeyup="check(this)">
												</div>
												<div class="col-md-6 margin-bottom-15">
													<label for="minValue" class="control-label">下限</label> <input
														type="text" class="form-control"
														id="expasions[1].minValue" name="expasions[1].minValue"
														value="0" onkeyup="check(this)">
												</div>
											</div>

										</div>

									</div>

									<div class="row">
										<div class="col-md-6 margin-bottom-15">
											<label for="maxInstance" class="control-label">实例数量最大值</label>
											<input type="text" class="form-control"
												id="expasions[0].maxInstance"
												name="expasions[0].maxInstance" value="0"
												onkeyup="check(this)"> <input type="hidden"
												class="form-control" id="expasions[1].maxInstance"
												name="expasions[1].maxInstance">
										</div>
										<div class="col-md-6 margin-bottom-15">
											<label for="minInstance" class="control-label">实例数量最小值</label>
											<input type="text" class="form-control"
												id="expasions[0].minInstance"
												name="expasions[0].minInstance" value="0"
												onkeyup="check(this)"> <input type="hidden"
												class="form-control" id="expasions[1].minInstance"
												name="expasions[1].minInstance">
										</div>

										<div class="col-md-6 margin-bottom-15">
											<label for="reduceCount" class="control-label">减少量</label> <input
												type="text" class="form-control"
												id="expasions[0].reduceCount"
												name="expasions[0].reduceCount" value="0"
												onkeyup="check(this)"> <input type="hidden"
												class="form-control" id="expasions[1].reduceCount"
												name="expasions[1].reduceCount">
										</div>
										<div class="col-md-6 margin-bottom-15">
											<label for="addCount" class="control-label">增加量</label> <input
												type="text" class="form-control" id="expasions[0].addCount"
												name="expasions[0].addCount" value="0" onkeyup="check(this)">
											<input type="hidden" class="form-control"
												id="expasions[1].addCount" name="expasions[1].addCount">
										</div>
									</div>

								</div>

							</div>

							<input type="hidden" class="form-control" id="imageName"
								name="imageName" value=""> <input type="hidden"
								class="form-control" id="projectName" name="projectName"
								value="${p.projectName}">

							<div class="row ">
								<div class="col-md-12 margin-bottom-15">
									<button type="button" id="save" class="btn btn-primary">部署应用</button>
								</div>
							</div>

						</form>
					</div>
				</div>
				<div>
					<font color="red">${errorMsg}</font>
				</div>
				<div class="row">

					<!-- Nav tabs -->
					<ul class="nav nav-tabs" role="tablist" id="templatemo-tabs">
						<li class="active"><a href="#home" role="tab"
							data-toggle="tab">日志</a></li>
					</ul>

					<!-- Tab panes -->
					<div class="tab-content">
						<div class="tab-pane fade in active" id="home">
							<pre id="showLog">
									<c:if test="${errorMsg ne '' }">
									${errorMsg}  - ${logMsg }
									</c:if>
                    	</pre>
						</div>

					</div>
				</div>




				<!-- Modal -->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">

							<form action="#" method="post" id="volumeForm">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h6 class="modal-title" id="myModalLabel">磁盘创建</h6>
								</div>


								<div class="modal-body">

									<label for="volumeName" class="control-label">磁盘名称</label> 
									<input type="text" class="form-control" id="volumeName" name="volumeName" required> 
										<label for="volumeSize" class="control-label">磁盘大小(MB)</label> 
										<input type="text" class="form-control" id="volumeSize" name="volumeSize" required> 
										<label for="readOnlyFlag" class="control-label">读写性</label> 
										<label class="radio-inline">
										<input type="radio" name="readOnlyFlag" id="readOnlyFlag1" value="0" checked> 可读可写</label> 
										<label class="radio-inline"> 
										<input type="radio" name="readOnlyFlag" id="readOnlyFlag2" value="1"> 只读</label>

								</div>

								<div class="modal-footer">
									<button type="button" class="btn btn-primary" id="addVolume">创建</button>
									<button type="button" class="btn btn-default" data-dismiss="modal" id="closeButton">关闭</button>
								</div>

								<div class="row">
									<font color="red" id="errorMsg">${msg}</font>
								</div>
							</form>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- /.modal -->





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
	<script src="${ctx}/js/jQuery.Hz2Py-min.js"></script>

	<script type="text/javascript">
  function config(id){
	  var cpuflag="configCpu"+id;
	  var memoryflag="configMemory"+id;
	  $("#cpuMax").val($("#"+cpuflag).val());
	  $("#memoryMax").val($("#"+memoryflag).val());
  }
  
  </script>


	<script type="text/javascript">
 $("#expansionTab").hide();
   function expansion(flag){
	   if(flag == "1"){
		   $("#expansionTab").hide();
	   }else{
		   $("#expansionTab").show();
	   }
   }
   
  </script>

	<script type="text/javascript">
 $("#mountTab").hide();
   function mount(flag){
	   if(flag == "1"){
		   $("#mountTab").hide();
	   }else{
		   $("#mountTab").show();
	   }
   } 
  </script>

	<script type="text/javascript">
  
  $(document).ready(function(){ 
	  $('#projectId').change(function(){ 
			  if($('#projectId').val() == ""){
				  $("#packageId").empty();
			  }else{
				  jQuery.ajax({
						type : "GET",
						url : "${ctx}/mirror/projectId/"+ $('#projectId').val(),
						data :"",
						dataType : "json",
						success : function(data) {
							$("#packageId").empty();
							$.each(data,function(i) {
									var codeStr = "<option value=\""+data[i].packageId+"\">"+data[i].imageName+"-"+data[i].versionNo+"</option>";
									if(i == 1){
										$("#imageName").val(data[i].imageName);
									}
									$("#packageId").append(codeStr);
							});
						}
					});
			  }
	  });
		  $('#packageId').click(function(){
			 var imgName =  $("#packageId").find("option:selected").text().split("-")[0];
			 $("#imageName").val(imgName);
		  });
		$("#appForm").validate({
			errorPlacement:function(error,element) {  	
				error.appendTo(element.parent().children());
			}, 
			errorElement: "em"  
		});
	  	$('#appName').blur(function(){
			$('#appSpell').val(($('#appName').toPinyin()));
		});
		$('#save').click(function(){
			if($('#appSpell').val() == ""){
				$('#appSpell').val(($('#appName').toPinyin()));
			}
			
			var maxCpu=document.getElementById("expasions[1].maxValue").value;
			if(parseFloat(maxCpu)>parseFloat($("#cpuMax").val()))
				{
			    alert("Cpu上限请输入小于"+$("#cpuMax").val()+"的数!");
			    return false;}
			var minCpu=document.getElementById("expasions[1].minValue").value;
			if(parseFloat(minCpu)>parseFloat($("#cpuMax").val()))
			{ alert("Cpu下限请输入小于"+$("#cpuMax").val()+"的数!");
			  return false;
			}
			
			var maxMemory=document.getElementById("expasions[0].maxValue").value;
			if(parseFloat(maxMemory)>parseFloat($("#memoryMax").val()))
			    {alert("Memory上限请输入小于"+$("#memoryMax").val()+"的数!");
			    return false;
			    }
			var minMemory=document.getElementById("expasions[0].minValue").value;
			if(parseFloat(minMemory)>parseFloat($("#memoryMax").val()))
			{ alert("Memory下限请输入小于"+$("#memoryMax").val()+"的数!");
			  return false;
			}
			
			
			document.getElementById("expasions[1].maxInstance").value=document.getElementById("expasions[0].maxInstance").value;
			document.getElementById("expasions[1].minInstance").value=document.getElementById("expasions[0].minInstance").value;
			document.getElementById("expasions[1].reduceCount").value=document.getElementById("expasions[0].reduceCount").value;
			document.getElementById("expasions[1].addCount").value=document.getElementById("expasions[0].addCount").value;

			$('#appForm').submit();
		});
		
		
		
		$('#addVolume').click(function(){
			     
			var volumeName=$("#volumeName").val();
			var volumeSize=$("#volumeSize").val();
			var readFlag=document.getElementsByName("readOnlyFlag");
			var readOnlyFlag=0;
			for(var   i=0; i<readFlag.length; i++){  
		       if(readFlag[i].checked)
		       {   
		           readOnlyFlag=readFlag[i].value; 
		       } 
		    }

				  jQuery.ajax({
						type : "POST",
						url : "${ctx}/volume/add/to",
						data :"volumeName="+volumeName+"&volumeSize="+volumeSize+"&readOnlyFlag="+readOnlyFlag,
						dataType : "json",
						success : function(data) {
									var codeStr = "<option value=\""+data.id+"\" selected>"+data.volumeName+"</option>";
									$("#volumeId").append(codeStr);
						}
					});
				 
				  
				  $("#volumeName").val("");
				  $("#volumeSize").val("");
				  
				  $("#myModal").modal('hide');
				  
	  });
 
		 
  });
	

function check(obj)
{
 var number = obj.value;
 var number2 = parseInt(number);
 if((/^[.0-9]+$/).test(number))
  return;
 else
  obj.value = number.substring(0,number.length-1);
}


function checkPath(obj)
{
 var path = obj.value;
 if((/^[a-zA-Z/]*$/).test(path))
  return;
 else
  obj.value = path.substring(0,path.length-1);
}
</script>

</body>
</html>