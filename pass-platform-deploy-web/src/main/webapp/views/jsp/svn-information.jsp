<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/commons/taglibs.jsp"%>  
<%@ include file="header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SVN信息</title>
  <meta name="keywords" content="" />
  <meta name="description" content="" />
  <meta name="viewport" content="width=device-width">        
  <link rel="stylesheet" href="${ctx }/css/templatemo_main.css">
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
            <li class="active">svn信息</li>
          </ol>
           
          
          <div class="row">
						
            <div class="col-md-12">
              
              
              <div class="table-responsive">
                
                <table class="table table-striped table-hover table-bordered">
                  <thead>
                    <tr bgcolor="#D1E9E9">
                      <th colspan="6" class="text-left"><span>主干信息</span></th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td class="text-right">主干路径</td>
                      <td class="text-left"><input type="text" id="trunkPath" name="trunkPath" class="form-control" required ></td>
                      <td class="text-right">SVN用户名</td>
                      <td class="text-left"><input type="text" id="username" name="username" class="form-control" required ></td>
                      <td class="text-right">SVN密码</td>
                      <td class="text-left"><input type="text" id="password" name="password" class="form-control" required ></td>                    
                    </tr>
                    <tr bgcolor="#D1E9E9">
                      <td colspan="6" class="text-left"><span style="font-size: 14px; font-weight:bold">分支信息</span></td>
                    </tr>
                    <tr>
                      <td class="text-right">分支路径</td>
                      <td class="text-left" colspan="5"><input type="text" id="branchPath" name="branchPath" class="form-control" required ></td>                    
                    </tr>
                    <tr bgcolor="#D1E9E9">
                      <td colspan="6" class="text-left"><span style="font-size: 14px; font-weight:bold">代码评审信息</span></td>
                    </tr>
                    <tr>
                      <td class="text-right">评审仓库SVN</td>
                      <td class="text-left" colspan="5"><input type="text" id="approval" name="approval" class="form-control" required ></td>                    
                    </tr>
                    
                    <tr>
	       		 	  <td colspan="6" align="center">
						<a href="application-modify.jsp"><button type="button" class="btn btn-primary">上一步</button></a>
                        <button type="submit" class="btn btn-default">提交</button>
					  </td>
	       		 	</tr>
                                 
                  </tbody>
                </table>
              </div>
              
               
            </div>
          </div>
          
      </div>
    </div>
    
    
  </div>
      
</div>
  <script src="${ctx }/js/jquery.min.js"></script>
  <script src="${ctx }/js/bootstrap.min.js"></script>
  <script src="${ctx }/js/templatemo_script.js"></script>
  
</body>
</html>