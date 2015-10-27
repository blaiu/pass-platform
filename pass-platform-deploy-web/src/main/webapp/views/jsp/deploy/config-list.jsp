<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="../header.jsp"%>   
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>配置列表</title>
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
        <div class="logo"><h1>GomePaas</h1></div>
         
      </div>   
    </div>
    -->
    
      <div class="template-page-wrapper">
       
       <%@ include file="../menu.jsp"%>

      <div class="templatemo-content-wrapper">
        <div class="templatemo-content">
          <ol class="breadcrumb">
            <li>容器配置</li>
            <li class="active">配置列表</li>
          </ol>
       
           <div class="row">
              <div class="col-md-12 col-sm-12">
                   <div class="panel panel-primary">
                  <div class="panel-heading">配置列表</div>
                  <div class="panel-body">
                    <table class="table table-striped">
                      <thead>
                        <tr>
                          <th>#</th>
                          <th>配置名称</th>
                          <th>内存（M）</th>
                          <th>CPU（核）</th>
                          <th>修改</th>
                          <th>删除</th>
                        </tr>
                      </thead>
                      <tbody>
                        <c:forEach var="p" items="${list}" varStatus="status">
                        <tr>
                          <td>${ status.index + 1}</td>
                          <td>${p.configName }</td>
                          <td>${p.memory }</td>
                          <td>${p.cpu }</td>
                          <td><a href="${ctx}/deploy/view/${p.id}"><button type="button" class="btn btn-primary">修改</button></a></td>
                          <td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" onclick="deleteConfig(${p.id})">删除</button></td>
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
										<a href="${ctx}/views/jsp/deploy/container-config.jsp"><button type="button" class="btn btn-primary">容器配置</button></a>
									</div>
		   </div>
		   
		   
		   
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
         </div>
         <div class="modal-body">
                                              是否删除容器配置？
             <input type="hidden" name="deleteId" id="deleteId">
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="deleteButton()">删除</button>
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
		
		function deleteConfig(id)
		{
			$("#deleteId").val(id);
		}
		
		function deleteButton()
		{
			window.location.href="${ctx}/deploy/delete/"+$("#deleteId").val();
		}
		
	</script> 
</body>
</html>