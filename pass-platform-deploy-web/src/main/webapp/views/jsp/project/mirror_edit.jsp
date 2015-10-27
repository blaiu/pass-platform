<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			
			   
				<div class="templatemo-content" id="loadingData">
					<ol class="breadcrumb">
						<li>项目管理</li>
						<li class="active">镜像维护</li>
					</ol>
					
			  		<div class="row" >     
			            <div class="col-md-12">
					<div class="row">
						<div class="col-md-12">
							<form role="form" id="templatemo-preferences-form">
								<div class="row">
									<div class="col-md-12 margin-bottom-15">
										<label for="firstName" class="control-label">项目名称:</label> 
											<br/>
											<div class="form-control">
												${proj.projectName }
											</div>
											
									</div>
								</div>
								<div class="row">
									<div class="col-md-12 margin-bottom-15">
										<label for="singleSelect">基础镜像</label> 
										<select class="form-control margin-bottom-15" id="basicImage" name="basicImage">
											<c:forEach items="${mirrorTypes}" var="mt">
											<option value="${mt.value}">${mt.key}</option>
											</c:forEach>
										</select>
									</div>
								</div>

								<div class="row">
									<div class="col-md-12 margin-bottom-15">
									 <label for="memo" class="control-label">备注</label>  
										<input type="text" class="form-control" name="memo"  id="memo" maxlength="100">
									</div>
								</div>
								
								<div class="row">
									<div class="col-md-12 margin-bottom-15">
									 <label for="packageName" class="control-label">项目包文件</label>  
										<input type="text" class="form-control required" readonly="true" name="packageName"   id="packageName"   >
									</div>
								</div>
								<div class="row">
									<div class="col-md-12 margin-bottom-15">
										<input type="file" id="file1" name="file" /></p>
  									   <input type="button" onclick="ajaxFileUpload()" value="上传" />
  									   <input type="hidden" name="versionNo" id="versionNo" value=""/>
									</div>
								</div>
								<div class="row ">
									<div class="col-md-12 margin-bottom-15">
										<button type="button" class="btn btn-primary" onclick="createImage()">生成镜像</button>
										<button type="button" class="btn btn-primary" onclick="clickButton()">一键部署</button>
										<a href="${ctx}/projects/${proj.projectId }"><button type="button" class="btn btn-primary">返回列表</button></a>
                                         
									</div>
									<div id="up">   
                                    <img src="${ctx}/images/loading.gif" height="30px" width="30px"/><span class="spa">生成镜像中,请稍等...</span>        
                                    </div> 
									
								</div>
								
							</form>
						</div>
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
                    		显示构建日志
                    	</pre>
							</div>
						</div>
					</div>
					
					   </div>
				  	</div>   
                    
				</div>
			</div>
		</div>
<!--  	</div>   -->
	<script src="${ctx}/js/jquery.min.js"></script>
	<script src="${ctx}/js/bootstrap.min.js"></script>
	<script src="${ctx}/js/templatemo_script.js"></script>
	<script src="${ctx}/js/ajaxfileupload.js"></script>
	<script src="${ctx}/js/jquery-loading.js"></script>
	<script type="text/javascript">
	   
	document.getElementById("up").style.display="none"; 
	
  		function createImage() {
  			
  			$("#loadingData").toggleLoading();
            document.getElementById("up").style.display="inline";
            
            
  			var projectId = "${proj.projectId}";
  			var projectSpell = "${proj.projectSpell }";
 			var basicImage = $("#basicImage").val();
 			var versionNo = $("#versionNo").val();
 			var packageName = $("#packageName").val();
 			var memo=$("#memo").val();
 			  	$.post(ctx + "/mirror/createimage/"+projectId, 
  				{basicImage:basicImage,packageName:packageName,projectSpell:projectSpell,versionNo:versionNo,memo:memo}, 
  				
  				function(data) {
  					$("#showLog").html(data);
  					document.getElementById("up").style.display="none";
  					$("#loadingData").toggleLoading();
  					
  				}
  				
  				
  	            
  			)
            
  		}
  		

  		 function ajaxFileUpload() {
  	        
  			$("#loadingData").toggleLoading();
             $.ajaxFileUpload(
                 {
                     url: '${ctx}/mirror/uploadWar', //用于文件上传的服务器端请求地址
                     type:'post',
                     secureuri: false, //是否需要安全协议，一般设置为false
                     fileElementId: 'file1', //文件上传域的ID
                     dataType: 'json', //返回值类型 一般设置为json
                     success: function(data)  //服务器成功响应处理函数
                     {
                    	 if(data.success){
                    		 $("#packageName").val(data.fileName);
                    		 $("#versionNo").val(data.versionNo);
                    		 //alert(data.versionNo);
                    	 }
                     },
                     error: function (data)//服务器响应失败处理函数
                     {
                         alert("上传失败");
                     }
                 }
             );
             
             $("#loadingData").toggleLoading();
             
         }
  		 
  		function clickButton(){
			window.location.href='${ctx}/application/versionNo/${proj.projectId }/'+$("#versionNo").val();
  		}
  		
  		
  	</script>
  	
  	
</body>
</html>