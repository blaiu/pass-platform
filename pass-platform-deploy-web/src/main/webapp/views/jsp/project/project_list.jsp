<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/commons/taglibs.jsp"%> 
<%@ include file="../header.jsp"%>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>应用申请</title>
  <meta name="keywords" content="" />
  <meta name="description" content="" />
  <meta name="viewport" content="width=device-width">     
  
  <link rel="stylesheet" href="${ctx}/css/bootstrap.min.css">
  <link rel="stylesheet" href="${ctx}/css/templatemo_main.css" />
</head>
<body>
<!--  
  <div id="main-wrapper">
    <div class="navbar navbar-inverse" role="navigation">
      <div class="navbar-header">
        <div class="logo"><h1>GomePaas</h1></div>
         
      </div>   
    </div>
   --> 
    
      <div class="template-page-wrapper">
       
       <%@ include file="../menu.jsp"%>

      <div class="templatemo-content-wrapper">
        <div class="templatemo-content">
          <ol class="breadcrumb">
            <li>项目管理</li>
            <li class="active">项目列表</li>
          </ol>

          
           <div class="row">
              <div class="col-md-12 col-sm-12">
                   <div class="panel panel-primary">
                  <div class="panel-heading">项目列表</div>
                  <div class="panel-body">
                    <table class="table table-striped"  >
                      <thead>
                        <tr>
                          <th>#</th>
                          <th>项目名称</th>
<!--                           <th>镜像数量</th> -->
                          <th>项目简拼</th>
                          <th>最近修改</th>
                          <th>项目维护</th>
                          <th>创建镜像</th>
                          <th>删除</th>
                        </tr>
                      </thead>
                      <tbody >
                      <c:forEach items="${projects}" var="project" varStatus="status">
                       <tr>
                          <td>${ status.index + 1}</td>
                          <td>${project.projectName }</td>
                          <td>${project.projectSpell }</td>
                          <td><fmt:formatDate   pattern="yyyy-MM-dd" value="${project.updateTime }" /></td>
                          <td><a href="${ctx}/projects/${project.projectId }" >项目维护</a></td>
                          <c:if test="${project.codeSource eq 0}">
                          <td><a href="${ctx}/mirror/create/${project.projectId }" >创建镜像</a></td>
                          </c:if>
                          <c:if test="${project.codeSource eq 1}">
						  <td>----------</td>
						  </c:if>
                          <td><a href="#" onclick="deleteButton('${project.projectId }')" data-toggle="modal" data-target="#myModal">删除</a></td>
                        </tr>
                      </c:forEach>
                      
                      </tbody>
                    </table>
                  </div>
              </div> 
            </div>
          </div>
          				<div class="row ">
									<div class="col-md-12 margin-bottom-15">
										<button type="button" onclick="createProject()" class="btn btn-primary">创建项目</button>
									</div>
								</div>
          
          
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
         <!--    <h6 class="modal-title" id="myModalLabel">message</h6>  --> 
         </div>
         <div class="modal-body">
                                              是否删除项目？
             <input type="hidden" name="deleteId" id="deleteId">
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="deleteProject()">删除</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
         </div>
      </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
          
          
           

      </div>
    </div>
  </div>
</div>
  <script src="${ctx}/js/jquery.min.js"></script>
  <script src="${ctx}/js/bootstrap.min.js"></script>
  <script src="${ctx}/js/templatemo_script.js"></script>
    <script type="text/javascript">
		$('.table tbody tr:odd').addClass('success');
		function createProject(){
			window.location.href=ctx+"/views/jsp/project/project_edit.jsp";
		}
		
		function deleteButton(projId)
		{
			$("#deleteId").val(projId);
		}
		
		function deleteProject(){
			jQuery.ajax({
				type : "DELETE",
				url : "${ctx}/projects/"+$("#deleteId").val(),
				data : "",
				dataType : "json",
				success : function(data) {
					if(data.msg == "success"){
						location.href=ctx+"/projects";
					}else{
						alert("删除失败，请重试");
					}
				} 
			});
		}
		
		
		$(document).ready(function() {
			
		});
		
	</script>
</body>
</html>