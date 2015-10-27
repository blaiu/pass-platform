<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="../header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>共享磁盘</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="viewport" content="width=device-width">

<link rel="stylesheet" href="${ctx}/css/templatemo_main.css">
</head>
<body>

		<div class="template-page-wrapper">
			<%@ include file="../menu.jsp"%>
			<div class="templatemo-content-wrapper">
				<div class="templatemo-content">
					<ol class="breadcrumb">
						<li>共享磁盘管理</li>
					</ol>
					<div class="row">
						<div class="col-md-6">
							<form action="${ctx}/volume/add" method="post" id="volumeForm">
							<input type="hidden" class="form-control" id="id" name="id" value="${volume.id}">
								<div class="row">
									<div class="col-md-12 margin-bottom-15">
										<label for="volumeName" class="control-label">磁盘名称</label>
										<c:if test="${empty volume.appId  }">
										 <c:if test="${empty volume.id  }">
										<input type="text" class="form-control" id="volumeName" name="volumeName" required value="${volume.volumeName}">
										</c:if>
										 <c:if test="${not empty volume.id  }">
										<input type="text" class="form-control" id="volumeName" name="volumeName" required value="${volume.volumeName}" readonly>
										</c:if> 
										</c:if> 
										<c:if test="${not empty volume.appId  }">
										<input type="text" class="form-control" id="volumeName" name="volumeName" required value="${volume.volumeName}" readonly>
										</c:if> 
									</div>
								</div>
								<div class="row">
									<div class="col-md-12 margin-bottom-15">
										<label for="volumeSize" class="control-label">磁盘大小(MB)</label>
										<c:if test="${empty volume.appId  }"> 
										<input type="text" class="form-control" id="volumeSize" name="volumeSize" required value="${volume.volumeSize}" onkeyup="check(this)">
										</c:if> 
										<c:if test="${not empty volume.appId  }"> 
										<input type="text" class="form-control" id="volumeSize" name="volumeSize" required value="${volume.volumeSize}" readonly>
										</c:if>
									</div>
								</div>
								
					  			<c:if test="${empty volume.id  }">  
								<div class="row">
									<div class="col-md-12 margin-bottom-15">
										<label for="readOnlyFlag" class="control-label">读写性</label>
										<label class="radio-inline"> 
										<input type="radio" name="readOnlyFlag" id="readOnlyFlag1" value="0" checked>
											可读可写
										</label> 
										<label class="radio-inline"> 
										<input type="radio" name="readOnlyFlag" id="readOnlyFlag2" value="1">
											只读
										</label>
									</div>
								</div>
								</c:if>
								
								<c:if test="${not empty volume.appId  }">
								<div class="row">
									<div class="col-md-12 margin-bottom-15">
										<label for="appName" class="control-label">挂载应用名称</label> 
										<input type="text" class="form-control" id="appName" name="appName" required value="${app.appName}" readonly>
									</div>
								</div>
								</c:if> 

									<div class="row ">
										<div class="col-md-12 margin-bottom-15">
											<c:if test="${empty volume.id  }">
											<button type="button" id="saveVolume"   class="btn btn-primary">创建</button>
										</c:if> 
										<c:if test="${not empty volume.id }">
										    <c:if test="${empty volume.appId  }">
											<button type="button" id="modifyVolume"   class="btn btn-primary">修改</button>
											</c:if>
										    <c:if test="${not empty volume.appId  }">
											<button type="button" id="modifyVolume1"   class="btn btn-primary" disabled>修改</button>
											</c:if>
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
	<script>
	$(document).ready(function() {
		
		$('#saveVolume').click(function(){
			volumeFormSubmit();
			
		});
		
		$('#modifyVolume').click(function(){
			document.forms.volumeForm.action="${ctx}/volume/modify/${volume.id}";
			volumeFormSubmit();
		});
		
		$("#volumeForm").validate({
			errorPlacement:function(error,element) {  	
				error.appendTo(element.parent().children());
			}, 
			errorElement: "em"  
		});
		
		
		
	});
	
	function volumeFormSubmit(){
			if($("#volumeForm").valid()){
				$("#volumeForm").submit();
			}
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
	
	function check(obj)
	{
	 var number = obj.value;
	 var number2 = parseInt(number);
	 if((/^[0-9]*[1-9][0-9]*$/).test(number))
	  return;
	 else
	  obj.value = number.substring(0,number.length-1);
	}
	
</script>
</body>
</html>