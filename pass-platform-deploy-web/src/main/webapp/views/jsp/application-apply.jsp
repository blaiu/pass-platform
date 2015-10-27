<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/commons/taglibs.jsp"%> 
<%@ include file="header.jsp"%>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>应用申请</title>
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
        <div class="logo"><h1>GomeCloud</h1></div>
         
      </div>   
    </div>
     -->
    
      <div class="template-page-wrapper">
       
       <%@ include file="menu.jsp"%>

      <div class="templatemo-content-wrapper">
        <div class="templatemo-content">
          <ol class="breadcrumb">
            <li>应用管理</li>
            <li class="active">应用申请</li>
          </ol>
          
          <div class="row">
            
              <form role="form" id="templatemo-preferences-form">
                <div class="table-responsive">
                  <table class="table table-striped table-hover table-bordered">
                    <tbody>
										<tr>
											<td style="width: 300px; font-size: 14px; text-align: right;">
												<span style="color: red;">*</span> <label>应用名称：</label>
											</td>
	       		 							<td style="width: 800px;">
	       		 								<input type="text" name="name" id="appName" class="form-control" required placeholder="应用名称(英文,不超过50个字符)" />
	       		 							</td>
	       		 							<td style="width: 300px; font-size: 14px; text-align: right;">
	       		 								<span style="color: red;">*</span> <label>应用中文名：</label>
	       		 							</td>
	       		 							<td style="width: 800px;">
	       		 								<input type="text" name="chineseName" id="chineseName" class="form-control" required placeholder="应用名称(中文,不超过50个字符)" />
	       		 							</td>
	       		 						</tr>
										<tr>
											<td style="font-size: 14px; text-align: right;">
												<span style="color: red;">*</span> <label>应用域名：</label>
											</td>
	       		 							<td>
	       		 								<input type="text" id="domain" name="domain" class="form-control" required placeholder="应用域名"/>
	       		 							</td>
	       		 							<td style="font-size: 14px; text-align: right;">
	       		 								<span style="color: red;">*</span> <label>开发组：</label>
	       		 							</td>
	       		 							<td>
	       		 								<input type="text" id="developTeam" name="developTeam" class="form-control" required placeholder="开发组" />
	       		 							</td>
	       		 						</tr>
										<tr>
											<td style="font-size: 14px; text-align: right;">
												<span style="color: red;">*</span> <label>应用负责人：</label>
											</td>
	       		 							<td>
	       		 								<input type="text" id="head" name="head" class="form-control" required placeholder="应用负责人" />
	       		 							</td>
	       		 							<td style="font-size: 14px; text-align: right;">
	       		 								<span style="color: red;">*</span> <label>应用组成员：</label>
	       		 							</td>
	       		 							<td>
	       		 								<input type="text" id="members" name="members" class="form-control" required placeholder="应用域名" />
	       		 							</td>
	       		 						</tr>
										<tr>
										    
											<td style="font-size: 14px; text-align: right;">
												<span style="color: red;">*</span> <label>应用类型：</label>
											</td>
	       		 							<td>
	       		 								<select id="appType" name="appType" style="width:100%; height: 35px; font-size: 14px; vertical-align: middle;" >
							                		<option value="">--- 请选择 ---</option>
							                		<option value="web">java/web</option>
							                		<option value="jar">java/jar</option>
              									</select>
	       		 							</td>
	       		 							<td style="font-size: 14px; text-align: right;">
												<span style="color: red;">*</span> <label>部署类型：</label>
											</td>
	       		 							<td>
	       		 								<select id="domainType" name="domainType" style="width:100%; height: 35px; font-size: 14px; vertical-align: middle;" >
							                		<option value="">--- 请选择 ---</option>
						                			<option value="tomcat">Tomcat</option>
						                			<option value="worker">worker</option>
              									</select>
	       		 							</td>
	       		 							
	       		 						</tr>
										<tr>
											
	       		 							<td style="font-size: 14px; text-align: right;">
	       		 								<span style="color: red;">*</span> <label>部署应用编号：</label>
	       		 							</td>
	       		 							<td>
	       		 								<input type="text" id="appNumber" name="appNumber" class="form-control" required placeholder="部署应用编号" />
	       		 							</td>
	       		 							<td style="font-size: 14px; text-align: right;">
	       		 								<span style="color: red;">*</span> <label>部署应用路径：</label>
	       		 							</td>
	       		 							<td>
	       		 								<input type="text" id="deployPath" name="deployPath" class="form-control" required placeholder="部署应用路径" />
	       		 							</td>
	       		 						</tr>
	       		 						<tr>
	       		 							<td colspan="4" align="center">
												<button type="submit" class="btn btn-primary">提交审批</button>
                                                <button type="reset" class="btn btn-default">取消申请</button>
											</td>
	       		 						</tr>
									</tbody>
                  </table>
                
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
</body>
</html>