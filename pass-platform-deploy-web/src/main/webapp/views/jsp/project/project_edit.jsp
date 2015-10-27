<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="../header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>项目管理</title>
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
						<li>项目管理</li>
						<li class="active">创建项目</li>
					</ol>
					<div class="row">
						<div class="col-md-12">
							<form role="form" action="${ctx}/projects" method="post" id="projectForm">
							    <input type="hidden" class="form-control" id="projectId" name="projectId" value="${project.projectId}">
								<div class="row">
									<div class="col-md-12 margin-bottom-15">
									<label for="codeSource">代码来源</label> 
										<select class="form-control margin-bottom-15" id="codeSource" name="codeSource" >
											<option value="0">无</option>
											<option value="1">SVN</option>
										</select>
									</div>
								</div>
								<div id="svnProject" style="display:none">
									<div class="row">
										<div class="col-md-12 margin-bottom-15">
											<label for="svnPath" class="control-label">svn地址</label>  
											<input type="text" class="form-control required"  name="svnPath"   id="svnPath"  value="${svn.svnAddressTrunk}" />
										</div>
									</div>
									<div class="row">
										<div class="col-md-12 margin-bottom-15">
											<label for="svnUserName" class="control-label">svn用户名</label>  
											<input type="text" class="form-control required"  name="svnUserName"   id="svnUserTrunk"  value="${svn.svnUserTrunk}" />
										</div>
									</div>
									<div class="row">
										<div class="col-md-12 margin-bottom-15">
											<label for="svnPassword" class="control-label">svn密码</label>  
											<input type="password" class="form-control required"  name="svnPassword"   id="svnPasswordTrunk"  value="${svn.svnPasswordTrunk}" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12 margin-bottom-15">
										<label for="projectName" class="control-label">项目名称</label>  
										<input type="text" class="form-control required rangelength:[2,10]"  name="projectName"   id="projectName"  value="${project.projectName}" >
									</div>
								</div>
								<div class="row">
									<div class="col-md-12 margin-bottom-15">
										<label for="projectName" class="control-label">项目简拼</label>  
										<input type="text" class="form-control required" name="projectSpell"   id="projectSpell"  value="${project.projectSpell}" >
									</div>
								</div>
								<div class="row">
									<div class="col-md-12 margin-bottom-15">
										<label for="firstName" class="control-label">项目说明</label>
										<textarea class="form-control " id="projectComment" name="projectComment" rows="3"  >${project.projectComment}</textarea>
									</div>
								</div>
								
								<div class="row ">
									<div class="col-md-12 margin-bottom-15">
									
								<c:if test="${empty project.projectId  }">
											<button type="button" id="saveButton"   class="btn btn-primary">创建项目</button>
										</c:if> 
										<c:if test="${not empty project.projectId }">
											<button type="button" id="modify"   class="btn btn-primary">修改项目</button>
										</c:if> 

										<button type="reset" id="resetButton" class="btn btn-default">清空</button>

									</div>
								</div>
								<div class="row"><font color="red" id="errorMsg" >${msg}</font></div>
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
	<script src="${ctx}/js/jQuery.Hz2Py-min.js"></script>
	
	<script>
	
	$(document).ready(function() {
		
		var val = document.getElementById("svnPath").value;
		if(val=='')
			{
			document.getElementById("codeSource").value = 0;
		    document.getElementById('svnProject').style.display = "none";
			}
		else
			{
			document.getElementById("codeSource").value = 1;
		    document.getElementById('svnProject').style.display = "block";
		    document.getElementById("svnPasswordTrunk").value = '';
			}
		
		
		$("#projectForm").validate({
			errorPlacement:function(error,element) {  	
				error.appendTo(element.parent().children());
			}, 
			errorElement: "em"  
		});
		$('#projectName').blur(function(){
			$('#projectSpell').val(($('#projectName').toPinyin()));
		});
		$('#saveButton').click(function(){
			$("#errorMsg").empty();
			if($('#projectSpell').val() == ""){
				$('#projectSpell').val(($('#projectName').toPinyin()));
			}
			if($("#codeSource").val() == "1"){
				checkSVN();
			}else{
				projectFormSubmit();
			}
			
		});
		
		$('#modify').click(function(){
			document.forms.projectForm.action="${ctx}/projects/modify/${project.projectId}";
			projectFormSubmit();
		});
		$('#codeSource').change(function(){
			if($("#codeSource").val() == 0){
				$("#svnProject").hide();
			}else{
				$("#svnProject").show();
			}
			
		});
		$("#resetButton").click(function(){
			$("#svnProject").hide();
		});
	});
	function projectFormSubmit(){
		if($('#codeSource').val() == "1"){
			checkSVN();
		}else{
			if($("#projectForm").valid()){
				$("#projectForm").submit();
			}
		}
	}
	function checkSVN(){
		if($("#projectForm").valid()){
			var svnPath = $("#svnPath").val();
			var svnName = $("#svnUserTrunk").val();
			var svnPwd=$("#svnPasswordTrunk").val();
			jQuery.ajax({
				type : "POST",
				url : "${ctx}/projects/svn",
				data : "svnPath="+svnPath+"&svnUserName="+svnName+"&svnPassword="+svnPwd,
				dataType : "json",
				success : function(data) {
				 	 if(data.success == "false"){
				 		 alert(data.msg);
				 		 return false;
				 	 }else{
				 		$("#projectForm").submit();
				 	 }
				} 
			});
		}

	}

</script>

</body>
</html>