<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/commons/taglibs.jsp"%>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>实例配置</title>
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
            <li class="active">实例配置</li>
          </ol>
          
          
          <div class="row margin-bottom-30">
            <div class="col-md-12">
                <a href="javascript:;" data-toggle="modal" data-target="#confirmModal"><button type="button" class="btn btn-primary">创建VM</button></a><br/>    
            </div>
          </div> 
          
          <div class="row">
            
              <form role="form" id="templatemo-preferences-form">
                <div class="table-responsive">
                  
                  <table class="table table-striped table-hover table-bordered" id="addTable">
                    <thead>
                    <tr>
                      <th class="text-center">部署环境</th>
                      <th class="text-center">分组</th>
                      <th class="text-center">IP地址</th>
                      <th class="text-center">实例个数</th>
                      <th class="text-center">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                      <td>
	       		 		<select id="deployEnvironment" name="deployEnvironment" style="width:100%; height: 35px; font-size: 14px; vertical-align: middle;" disabled="disabled">
						   <option value="">--- 请选择 ---</option>
						   <option value="formal">线上正式环境</option>
						   <option value="pre-release">预发布环境</option>
              			</select>
	       		 	  </td>
	       		      <td>
	       		 		<select id="group" name="group" style="width:100%; height: 35px; font-size: 14px; vertical-align: middle;" disabled="disabled">
						   <option value="">--- 请选择 ---</option>
						   <option value="formal">线上组</option>
						   <option value="pre-release">预发布环境</option>
              			</select>
	       		 	  </td>
                      <td>
	       		 		<input type="text" name="ipAddress" id="ipAddress" class="form-control" disabled="disabled"/>
	       		 	  </td>
	       		 	  <td>
	       		 		<input type="text" name="number" id="number" class="form-control" disabled="disabled"/>
	       		 	  </td>
                      <td class="text-center">
                        <input type="button" value="删除" disabled="disabled" class="btn btn-default" onclick="deleteRow(this)"/>
                      </td>   
                    </tr>
                  </tbody>
                    <tbody id="templeteTBody"  style="display: none;">
                    <tr>
                      <td>
	       		 		  <select id="deployEnvironment" name="deployEnvironment" style="width:100%; height: 35px; font-size: 14px; vertical-align: middle;" >
							  <option value="">--- 请选择 ---</option>
							  <option value="formal">线上正式环境</option>
							  <option value="pre-release">预发布环境</option>
              			  </select>
	       		 	  </td>
	       		      <td>
	       		 		  <select id="group" name="group" style="width:100%; height: 35px; font-size: 14px; vertical-align: middle;" >
							  <option value="">--- 请选择 ---</option>
							  <option value="formal">线上组</option>
							  <option value="pre-release">预发布环境</option>
              			   </select>
	       		 	  </td>
                      <td>
	       		 		  <input type="text" name="ipAddress" id="ipAddress" class="form-control" required/>
	       		 	  </td>
	       		 	  <td>
	       		 		  <input type="text" name="number" id="number" class="form-control" required/>
	       		 	  </td>
                      <td class="text-center">
                          <input type="button" value="删除" class="btn btn-primary" onclick="deleteRow(this)"/>
                      </td>   
                    </tr>
                    </tbody>
    
                    <tbody id="footTbody">
                    <tr>
                      <td colspan="5" align="center">
						  <input type="button" value="增加" class="btn btn-primary" onclick="setValue()">
					  </td>
					</tr>
                    <tr>
	       		 	  <td colspan="5" align="center">
						<a href="application-modify.jsp"><button type="button" class="btn btn-primary">上一步</button></a>
                        <button type="submit" class="btn btn-default">提交</button>
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
  
  <!-- Modal -->
      <div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
              <h4 class="modal-title" id="myModalLabel">创建VM</h4>
            </div>
            <div class="modal-content">
              <table class="table table-striped table-hover table-bordered">
                    <tbody>
										<tr>
											<td style="width:; font-size: 14px; text-align: right;">
												<label>VM标准：</label>
											</td>
	       		 							<td style="width:;">
	       		 								<select id="VM" name="VM" style="width:100%; height: 35px; font-size: 14px; vertical-align: middle;" >
							                		<option value="">--- 请选择 ---</option>
						                			<option value="test">VM测试环境标准</option>
						                			<option value="online">VM线上环境标准</option>
						                			<option value="pre-release">VM预发布环境标准</option>
              									</select>
	       		 							</td>
	       		 						</tr>
										<tr>
											<td style="font-size: 14px; text-align: right;">
												<label>创建结果：</label>
											</td>
	       		 							<td>
	       		 							</td>
	       		 						</tr>
	       		 						</tbody>
                  </table>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-default">保存</button>
            </div>
          </div>
        </div>
      </div>
      
</div>
  <script src="../js/jquery.min.js"></script>
  <script src="../js/bootstrap.min.js"></script>
  <script src="../js/templatemo_script.js"></script>
  
  <script type="text/javascript">
	 /*
   *增加模板行
   */
   
   function addRow() {
    var table = document.getElementById("addTable");
    var tbody = document.getElementById("templeteTBody");
    var newTBody = tbody.cloneNode(true);
    newTBody.style.display="";
    var footTBody = document.getElementById("footTbody");
    return table.insertBefore(newTBody,footTBody);
   }
   /*
   *删除模板行
   */
   
   function deleteRow(obj) {
    var tbody = obj.parentNode.parentNode.parentNode;
    var table = document.getElementById("addTable"); 
    table.removeChild(tbody);
   }

/**
   *向模板中填充值
   */
    function setValue(){

            var tbody=addRow();

    }
	
	</script>
</body>
</html>