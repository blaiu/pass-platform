<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/commons/taglibs.jsp"%>  
<%@ include file="header.jsp"%>
<html>
<head>
  <meta charset="utf-8">
  <title>应用列表</title>
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
            <li><a href="#">应用管理</a></li>
            <li class="active">应用审批</li>
          </ol>
          
          <div class="row margin-bottom-30">
            <div class="col-md-12">
               <table class="no-border">
                   <tbody class="no-border-x no-border-y">
                      <tr>
				         <td class="text-right" style="width:100px;"><h5>系统：</h5></td>
                                  <td style="width:300px;">
                                    <input type="text" class="form-control" placeholder="">
                                  </td>
								  <td class="text-right" style="width:100px;"><h5>关键字：</h5></td>
					              <td style="width:300px;">
                                    <input type="text" class="form-control" placeholder="">
                                  </td>
					              <td style="width:200px;">
                                    <input type='button' value="搜索">
                                  </td>
                  
                       </tr>	
  
                   </tbody>
                </table>         
            </div>
          </div> 
          
          <div class="row">
						
            <div class="col-md-12">
              
              
              <div class="table-responsive">
                
                <table class="table table-striped table-hover table-bordered">
                  <thead>
                    <tr>
                      <th class="text-center">应用名称</th>
                      <th class="text-center">应用中文名</th>
                      <th class="text-center">系统名称</th>
                      <th class="text-center">系统级别</th>
                      <th class="text-center">开发组</th>
                      <th class="text-center">应用负责人</th>
                      <th class="text-center">创建时间</th>
                      <th class="text-center">操作</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td class="text-center"></td>
                      <td class="text-center"></td>
                      <td class="text-center"></td>
                      <td class="text-center"></td>
                      <td class="text-center"></td>
                      <td class="text-center"></td>                    
                      <td class="text-center"></td>
                      <td class="text-center"><button type="button" class="btn btn-primary">通过</button>&nbsp;&nbsp;
                          <a href="javascript:;" data-toggle="modal" data-target="#confirmModal"><button type="button" class="btn btn-primary">不通过</button></a>
                      </td>
                    </tr>
                    <tr>
                      <td class="text-center"></td>
                      <td class="text-center"></td>
                      <td class="text-center"></td>
                      <td class="text-center"></td>
                      <td class="text-center"></td>
                      <td class="text-center"></td>                    
                      <td class="text-center"></td>
                      <td class="text-center"><button type="button" class="btn btn-primary">通过</button>&nbsp;&nbsp;
                          <a href="javascript:;" data-toggle="modal" data-target="#confirmModal"><button type="button" class="btn btn-primary">不通过</button></a>
                      </td>
                    </tr>
                                 
                  </tbody>
                </table>
              </div>
              <ul class="pagination pull-right">
                <li class="disabled"><a href="#">&laquo;</a></li>
                <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
                <li><a href="#">2 <span class="sr-only">(current)</span></a></li>
                <li><a href="#">3 <span class="sr-only">(current)</span></a></li>
                <li><a href="#">4 <span class="sr-only">(current)</span></a></li>
                <li><a href="#">5 <span class="sr-only">(current)</span></a></li>
                <li><a href="#">&raquo;</a></li>
              </ul>  
            </div>
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
              <h4 class="modal-title" id="myModalLabel">审批</h4>
            </div>
            <div class="modal-content">
              <table class="table table-striped table-hover table-bordered">
                    <tbody>
										<tr>
											<td style="width:30%; font-size: 14px; text-align: right;">
												<label>审批：</label>
											</td>
	       		 							<td style="width:;">
	       		 								<select id="nothough" name="nothough" style="width:100%; height: 35px; font-size: 14px; vertical-align: middle;" >
							                		<option value="">--- 请选择 ---</option>
						                			<option value="test">不通过</option>
              									</select>
	       		 							</td>
	       		 						</tr>
										<tr>
											<td style="font-size: 14px; text-align: right;">
												<label>理由：</label>
											</td>
	       		 							<td>
	       		 							  <textarea name="reason" id="reason" rows="5" cols="70"></textarea>
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
    <script src="${ctx }/js/jquery.min.js"></script>
    <script src="${ctx }/js/bootstrap.min.js"></script>
    <script src="${ctx }/js/templatemo_script.js"></script>
  </body>
</html>