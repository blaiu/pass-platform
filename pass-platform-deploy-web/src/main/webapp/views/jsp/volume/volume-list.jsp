<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="../header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>共享磁盘列表</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="viewport" content="width=device-width">

<link rel="stylesheet" href="${ctx}/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx}/css/templatemo_main.css">

</head>
<body>

	<div class="template-page-wrapper">

		<%@ include file="../menu.jsp"%>

		<div class="templatemo-content-wrapper">
			<div class="templatemo-content">
				<ol class="breadcrumb">
					<li>共享磁盘</li>
					<li class="active">共享磁盘列表</li>
				</ol>

				<div class="row">
					<div class="col-md-12 col-sm-12">
						<div class="panel panel-primary">
							<div class="panel-heading">共享磁盘列表</div>
							<div class="panel-body">
								<table class="table table-striped">
									<thead>
										<tr>
											<th>#</th>
											<th>磁盘名称</th>
											<th>磁盘大小</th>
											<th>是否挂载</th>
											<th>挂载应用名称</th>
											<th>修改</th>
											<th>格式化</th>
											<th>删除</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="p" items="${list}" varStatus="status">
											<tr>
												<td>${ status.index + 1}</td>
												<td>${p.volumeName }</td>
												<td>${p.volumeSize }</td>
												<c:if test="${p.isUsed eq 1 }">
													<td>是</td>
												</c:if>
												<c:if test="${p.isUsed eq 0 }">
													<td>否</td>
												</c:if>
												<c:if test="${p.isUsed eq 1 }">

													<td><c:forEach var="app" items="${applist}">
													<c:if test="${p.appId eq app.id }">
													 ${app.appName }
													</c:if>
                         								
                        						  </c:forEach></td>
												</c:if>
												<c:if test="${p.isUsed eq 0 }">
													<td>------------</td>
												</c:if>
												<td>
												
												<c:if test="${p.isUsed eq 1 }">
												<button type="button" class="btn btn-primary" disabled>修改</button>
												</c:if>

												<c:if test="${p.isUsed eq 0 }">
												<a href="${ctx}/volume/view/${p.id}">
												<button type="button" class="btn btn-primary">修改</button>
												</a>
												</c:if>
												
												</td>
												
												<td>
												<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal1" onclick="formatVolume(${p.id})">格式化</button>
												</td>
												
												<td>
												<c:if test="${p.isUsed eq 1 }">
												<button type="button" class="btn btn-primary" disabled>删除</button>
												</c:if>
												<c:if test="${p.isUsed eq 0 }">
												<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" onclick="deleteVolume(${p.id})">删除</button>
												</c:if>
												
												</td>
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
						<a href="${ctx}/views/jsp/volume/volume-edit.jsp"><button
								type="button" class="btn btn-primary">创建</button></a>
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
                                              是否删除共享磁盘？
             <input type="hidden" name="deleteId" id="deleteId">
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="deleteButton()">删除</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
         </div>
      </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->



<!-- Modal -->
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
         </div>
         <div class="modal-body">
                                              是否格式化共享磁盘？
             <input type="hidden" name="formatId" id="formatId">
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="formatButton()">格式化</button>
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
		
		function deleteVolume(id)
		{
			$("#deleteId").val(id);
		}
		
		function deleteButton()
		{
			window.location.href="${ctx}/volume/delete/"+$("#deleteId").val();
		}
		
		function formatVolume(id)
		{
			$("#formatId").val(id);
		}
		
		function formatButton()
		{
			window.location.href="${ctx}/volume/format/"+$("#formatId").val();
		}
	</script>
</body>
</html>