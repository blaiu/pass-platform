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

<link rel="stylesheet" href="${ctx}/css/bootstrap.min.css">
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
						<li>项目管理</li>
						<li class="active">项目查看</li>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
					</ol>
					<div class="row">
						<div class="col-md-12">
							<form role="form" id="templatemo-preferences-form">
								<div class="row">
									<div class="col-md-12 margin-bottom-15">
										<label for="projectName" class="control-label">项目名称</label> 
										<div class="form-control">
											${project.projectName }
										</div>
										
									</div>
								</div>
								<div class="row">
									<div class="col-md-12 margin-bottom-15">
										<label for="projectSpell" class="control-label">项目简拼</label> 
										<div class="form-control">
											${project.projectSpell }
										</div>
										
									</div>
								</div>

								<div class="row">
									<div class="col-md-12 margin-bottom-15">
										<label for="firstName" class="control-label">项目说明</label>
										<div class="form-control">${project.projectComment }</div>
									</div>
								</div>
								
								<c:if test="${not empty svn.svnAddressTrunk  }">
								<div class="row">
									<div class="col-md-12 margin-bottom-15">
										<label for="firstName" class="control-label">svn地址</label>
										<div class="form-control">${svn.svnAddressTrunk }</div>
									</div>
								</div>
								</c:if>
								
								<div class="row ">
									<div class="col-md-12 margin-bottom-15">
										<a href="${ctx}/projects/view/${project.projectId }" ><button type="button" id="modifyButton" class="btn btn-primary">修改项目</button></a>
										<c:if test="${project.codeSource eq '1' }">
												  <button type="button" id="buildImg" class="btn btn-primary">构建镜像</button> 
										</c:if>
										<c:if test="${project.codeSource ne '1' }">
												 	<a href="${ctx}/mirror/create/${project.projectId }" ><button type="button" class="btn btn-primary">创建镜像</button></a>
										</c:if>
										
										<button type="button" id="deployApp" class="btn btn-primary">部署应用</button>
									</div>
								</div>
							</form>
						</div>
					</div>
						<div class="row">

						<!-- Nav tabs -->
						<ul class="nav nav-tabs" role="tablist" id="templatemo-tabs">
							<li class="active"><a href="#applist" role="tab"	data-toggle="tab">应用列表</a></li>
							<li ><a href="#imagelist" role="tab"	data-toggle="tab">镜像列表</a></li>
							<li ><a href="#buildlog" role="tab"	data-toggle="tab">构建日志</a></li>
						</ul>

						<!-- Tab panes -->
						<div class="tab-content">
							<div class="tab-pane fade in active" id="applist">
								 <table class="table table-striped">
                      <thead>
                        <tr>
                          <th>#</th>
                          <th>应用名称</th>
                          <th>镜像版本</th>
                          <th>运行状态</th>
                          <th>管理</th>
                        </tr>
                      </thead>
                      <tbody id="appLists">
                        </tbody>
                        </table>
							</div>
						 <div class="tab-pane fade  " id="imagelist">
					 <table class="table table-striped" style="table-layout:fixed">
                      <thead>
                        <tr>
                          <th>#</th>
                          <th>镜像名称</th>
                          <th>镜像版本</th>
                          <th>镜像状态</th>
                          <th width="10%">备注</th>
                          <th>查看</th>
                          <th>一键部署</th>
                          <th>删除</th>
                        </tr>
                      </thead>
                      <tbody id="imageLists">
                        </tbody>
                        </table>
							</div>
				<div class="tab-pane fade " id="buildlog">
                       <pre id="log">
                       
                       </pre>
                  </div>
					</div>
				</div>
					
				</div>
			</div>
		</div>
	</div>
	<!-- blogType -->
	<div   class="modal fade bs-example-modal-sm" id="baseImage" tabindex="-1" role="dialog" aria-labelledby="baseImage">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">请选择基础镜像</h4>
	      </div>
	      <div class="modal-body" >
				 <div class="form-group">
				 		<form id="typeForm">
					 	 <label for="codeInfo"><span class="labelinfowarn"></span>基础镜像</label>
					 	<select class="form-control margin-bottom-15" id="basicImage" name="basicImage">
								<c:forEach items="${mirrorTypes}" var="mt">
									<option value="${mt.value}">${mt.key}</option>
								</c:forEach>
						</select>
						<div class="row">
									<div class="col-md-12 margin-bottom-15">
									 <label for="memo" class="control-label">备注</label>  
										<input type="text" class="form-control" name="memo"  id="memo" maxlength="100">
									</div>
						</div>
						</form>
				 </div>
	      </div>
	       <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		        <button type="button" class="btn btn-primary" id="beginBuild">开始构建</button>
		      </div>
	    </div>
	  </div>
	</div>
	<!-- blogType end -->
	
	<script src="${ctx}/js/jquery.min.js"></script>
	<script src="${ctx}/js/bootstrap.min.js"></script>
	<script src="${ctx}/js/templatemo_script.js"></script>
	<script src="${ctx}/js/jquery-loading.js"></script>
	<script type="text/javascript">
	 
	$(document).ready(function() {
		
		
		initMirror();
		jQuery.ajax({
			type : "GET",
			url : "${ctx}/mirror/projectId/${project.projectId}/app",
			data : "",
			dataType : "json",
			success : function(data) {
				$.each(data,function(i) {
						var codeStr = " <tr>"+
	                         " <td>"+i+"</td>"+
	                         " <td>"+data[i].appName+"</td>"+
	                         " <td>"+data[i].imageName+"</td>"+
	                         " <td>"+data[i].appStatus+"</td>"+
	                         " <td><a href='${ctx}/application/"+data[i].id+ "'>管理</a></td>"
	                        "</tr>";
						$("#appLists").append(codeStr);
				});
				$('.table tbody tr:odd').addClass('success');
			}
		});
		
		$("#deployApp").click(function(){
			location.href="${ctx}/application/projectId/${project.projectId}"
		});
		$("#buildImg").click(function(){
			$('#baseImage').modal('show');
		});
		$("#beginBuild").click(function(){
			
			$("#baseImage").toggleLoading();
			var data = "";
			if($("#basicImage").val() != ""){
				data = $("#basicImage").val();
			}
			var memo= "";
			if($("#memo").val() != ""){
				memo = $("#memo").val();
			}
			$('#baseImage').modal('hide');
			jQuery.ajax({
				type : "POST",
				url : "${ctx}/projects/structure/${project.projectId}",
				data : {basicImage:data,memo:memo},
				dataType : "json",
				success : function(data) {
					$('#templatemo-tabs a[href="#buildlog"]').tab('show'); 
					$("#log").empty();
					if(data.success == "true"){
						$("#log").append(data.msg)
					}else{
						$("#log").append(data.msg)
					}
				}
			});
			
			$("#baseImage").toggleLoading();
		});
		
		
	});
	
	
	function removeImage(packageId){
		jQuery.ajax({
			type : "delete",
			url : "${ctx}/mirror/"+packageId,
			data : "",
			dataType : "json",
			success : function(data) {
			 	if(data.message){
// 			 		 $("tr[id="+packageId+"]").remove();
			 		initMirror();
			 	}else{
			 		alert("删除失败");
			 	}
			}
		});
	}
	function initMirror(){
		jQuery.ajax({
			type : "GET",
			url : "${ctx}/mirror/projectId/${project.projectId}",
			data : "",
			dataType : "json",
			success : function(data) {
				if(data.length > 0){
					$("#modifyButton").attr("disabled",true);
				}else{
					$("#modifyButton").removeAttr("disabled");
				}
				$("#imageLists").empty();
				$.each(data,function(i) {
					   if(data[i].memo!=null)
					   {
						var codeStr = " <tr id='"+data[i].packageId+"'>"+
	                         " <td>"+i+"</td>"+
	                         " <td>"+data[i].imageName+"</td>"+
	                         " <td>"+data[i].versionNo+"</td>"+
	                         " <td>"+data[i].status+"</td>"+
	                         " <td><h5 style='overflow:hidden;white-space:nowrap;text-overflow:ellipsis;' data-toggle='tooltip' data-placement='bottom' title='"+data[i].memo+"'>"+data[i].memo+"<h5></td>"+
	                         " <td><a href=\"${ctx}/mirror/"+data[i].packageId+"\"  >查看镜像</a></td>"+
	                         " <td><a href=\"${ctx}/application/projectId/${project.projectId}/"+data[i].packageId+"\" >一键部署</a></td>"+
	                         " <td><a href=\"#\" onclick=\"removeImage('"+data[i].packageId+"')\" >删除</a></td>"+
	                        "</tr>";
					   }else{
						   var codeStr = " <tr id='"+data[i].packageId+"'>"+
	                         " <td>"+i+"</td>"+
	                         " <td>"+data[i].imageName+"</td>"+
	                         " <td>"+data[i].versionNo+"</td>"+
	                         " <td>"+data[i].status+"</td>"+
	                         " <td>------</td>"+
	                         " <td><a href=\"${ctx}/mirror/"+data[i].packageId+"\"  >查看镜像</a></td>"+
	                         " <td><a href=\"${ctx}/application/projectId/${project.projectId}/"+data[i].packageId+"\" >一键部署</a></td>"+
	                         " <td><a href=\"#\" onclick=\"removeImage('"+data[i].packageId+"')\" >删除</a></td>"+
	                        "</tr>";
					   }
						$("#imageLists").append(codeStr);
				});
				$('.table tbody tr:odd').addClass('success');
				
				 $("[data-toggle='tooltip']").tooltip();
			}
		});
		
	}
	</script>
	
</body>
</html>