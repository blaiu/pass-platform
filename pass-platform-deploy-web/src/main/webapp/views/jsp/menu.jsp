<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
      <div class="navbar-collapse collapse templatemo-sidebar">
<!--         <ul class=templatemo-panels> -->
<!--           <li class="sub"> -->
<!--             <a href="#"><i class="fa fa-database"></i>应用管理<div class="pull-right"><span class="caret"></span></div></a> -->
<!--             <ul class="templatemo-submenu"> -->
<!--               <li><a href="application-apply.jsp">应用申请</a></li> -->
<!--               <li><a href="application-list.jsp">应用列表</a></li> -->
<!--               <li><a href="application-approval.jsp">应用审批</a></li> -->
<!--             </ul> -->
<!--           </li> -->
<!--         </ul> -->
<!--       </div> -->
   
   <ul class="templatemo-sidebar-menu">
<!--           <li><a href="index.html"><i class="fa fa-home"></i>Dashboard</a></li> -->
<!--           <li class="sub"> -->
<!--             <a href="javascript:;"> -->
<!--               <i class="fa fa-database"></i> Nested Menu <div class="pull-right"><span class="caret"></span></div> -->
<!--             </a> -->
<!--             <ul class="templatemo-submenu"> -->
<!--               <li><a href="#">Aenean</a></li> -->
<!--               <li><a href="#">Pellentesque</a></li> -->
<!--               <li><a href="#">Congue</a></li>              -->
<!--               <li><a href="#">Interdum</a></li> -->
<!--               <li><a href="#">Facilisi</a></li> -->
<!--             </ul> -->
<!--           </li> -->
          <li class="active"><a href="${ctx}/views/jsp/app-info.jsp"><i class="fa fa-cubes"></i>工作台</a></li>
         <li  ><a href="${ctx}/projects"><i class="fa fa-users"></i><span class="badge pull-right"></span>项目管理</a></li>
<%--           <li><a href="${ctx}/views/jsp/image/image-list.jsp"><i class="fa fa-map-marker"></i><span class="badge pull-right">42</span>镜像管理</a></li> --%>
          <li><a href="${ctx}/application/list"><i class="fa fa-database"></i><span class="badge pull-right"></span>应用管理</a></li>
         <li><a href="${ctx}/views/jsp/app/app-list.jsp"><i class="fa fa-users"></i><span class="badge pull-right"></span>待审核</a></li>
         <li><a href="${ctx}/views/jsp/app/app-list.jsp"><i class="fa fa-users"></i><span class="badge pull-right"></span>线上待发布</a></li>
         <li><a href="${ctx}/deploy/list"><i class="fa fa-users"></i><span class="badge pull-right"></span>容器配置</a></li>
         <li><a href="${ctx}/volume/list"><i class="fa fa-users"></i><span class="badge pull-right"></span>共享磁盘</a></li>
          
          <li><a href="${ctx}/logout"><i class="fa fa-sign-out"></i>退出</a></li>
        </ul>
  </div>
