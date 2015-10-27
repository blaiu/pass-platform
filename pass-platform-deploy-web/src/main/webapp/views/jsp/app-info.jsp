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
  <div id="main-wrapper">
  <!--  
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
            <li>工作台</li>
            <li class="active">各应用运行情况</li>
          </ol>
          
<!--           ===================================== -->
          
           <div class="templatemo-charts">
            <div class="row">
              <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="panel panel-info">
                  <div class="panel-heading">Pie Chart &amp; Doughnut Chart</div>
                </div>
                <div class="row templatemo-chart-row">

                  <div class="templatemo-chart-box col-lg-4 col-md-3 col-sm-4 col-xs-12">
                    <canvas id="templatemo-pie-chart"></canvas>
                    <h4>Pie Chart</h4>
                    <span class="text-muted">应用管理</span>  
                  </div>
                  
                  <div class="templatemo-chart-box col-lg-4 col-md-3 col-sm-4 col-xs-12">           
                    <canvas id="templatemo-doughnut-chart"></canvas>
                    <h4>Doughnut Chart</h4>
                    <span class="text-muted">镜像管理</span> 
                  </div>
                  
                  <div class="templatemo-chart-box col-lg-4 col-md-3 col-sm-4 col-xs-12">           
                    <canvas id="templatemo-radar-chart"></canvas>
                    <h4>Radar Chart</h4>
                    <span class="text-muted">服务管理</span> 
                  </div>

                

                </div>                  
              </div>
            </div>
           <div class="row">
              <div class="col-md-12 col-sm-12">
                   <div class="panel panel-primary">
                  <div class="panel-heading">应用列表</div>
                  <div class="panel-body">
                    <table class="table table-striped">
                      <thead>
                        <tr>
                          <th>#</th>
                          <th>应用名称</th>
                          <th>容器数量</th>
                          <th>运行情况</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr>
                          <td>1</td>
                          <td>John</td>
                          <td>Smith</td>
                          <td>@js</td>
                        </tr>
                        <tr class="success">
                          <td>2</td>
                          <td>Bill</td>
                          <td>Jones</td>
                          <td>@bj</td>
                        </tr>
                        <tr>
                          <td>3</td>
                          <td>Marry</td>
                          <td>James</td>
                          <td>@mj</td>
                        </tr>
                                               <tr class="success">
                          <td>2</td>
                          <td>Bill</td>
                          <td>Jones</td>
                          <td>@bj</td>
                        </tr>
                        <tr>
                          <td>3</td>
                          <td>Marry</td>
                          <td>James</td>
                          <td>@mj</td>
                        </tr>
                                               <tr class="success">
                          <td>2</td>
                          <td>Bill</td>
                          <td>Jones</td>
                          <td>@bj</td>
                        </tr>
                        <tr>
                          <td>3</td>
                          <td>Marry</td>
                          <td>James</td>
                          <td>@mj</td>
                        </tr>
                                               <tr class="success">
                          <td>2</td>
                          <td>Bill</td>
                          <td>Jones</td>
                          <td>@bj</td>
                        </tr>
                        <tr>
                          <td>3</td>
                          <td>Marry</td>
                          <td>James</td>
                          <td>@mj</td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
              </div> 
            </div>
          
          
          
           
<!--           ===================================== -->
      </div>
    </div>

  </div>
</div>
  <script src="${ctx}/js/jquery.min.js"></script>
  <script src="${ctx}/js/bootstrap.min.js"></script>
  <script src="${ctx}/js/templatemo_script.js"></script>
   <script type="text/javascript">
      // Line chart
      var randomScalingFactor = function(){ return Math.round(Math.random()*100)};
 

      var pieChartData = [
      {
        value: 300,
        color:"#F7464A",
        highlight: "#FF5A5E",
        label: "Red"
      },
      {
        value: 50,
        color: "#46BFBD",
        highlight: "#5AD3D1",
        label: "Green"
      },
      {
        value: 100,
        color: "#FDB45C",
        highlight: "#FFC870",
        label: "Yellow"
      }
      ] // pie chart data

      // radar chart
      var radarChartData = {
        labels: ["Eating", "Drinking", "Sleeping", "Designing", "Coding", "Cycling", "Running"],
        datasets: [
        {
          label: "My First dataset",
          fillColor: "rgba(220,220,220,0.2)",
          strokeColor: "rgba(220,220,220,1)",
          pointColor: "rgba(220,220,220,1)",
          pointStrokeColor: "#fff",
          pointHighlightFill: "#fff",
          pointHighlightStroke: "rgba(220,220,220,1)",
          data: [65, 59, 90, 81, 56, 55, 40]
        },
        {
          label: "My Second dataset",
          fillColor: "rgba(151,187,205,0.2)",
          strokeColor: "rgba(151,187,205,1)",
          pointColor: "rgba(151,187,205,1)",
          pointStrokeColor: "#fff",
          pointHighlightFill: "#fff",
          pointHighlightStroke: "rgba(151,187,205,1)",
          data: [28, 48, 40, 19, 96, 27, 100]
        }
        ]
      }; // radar chart

      // polar area chart
      var polarAreaChartData = [
      {
        value: 300,
        color:"#F7464A",
        highlight: "#FF5A5E",
        label: "Red"
      },
      {
        value: 50,
        color: "#46BFBD",
        highlight: "#5AD3D1",
        label: "Green"
      },
      {
        value: 100,
        color: "#FDB45C",
        highlight: "#FFC870",
        label: "Yellow"
      },
      {
        value: 40,
        color: "#949FB1",
        highlight: "#A8B3C5",
        label: "Grey"
      },
      {
        value: 120,
        color: "#4D5360",
        highlight: "#616774",
        label: "Dark Grey"
      }

      ];

      window.onload = function(){
        var ctx_pie = document.getElementById("templatemo-pie-chart").getContext("2d");
        var ctx_doughnut = document.getElementById("templatemo-doughnut-chart").getContext("2d");
        var ctxRadar = document.getElementById("templatemo-radar-chart").getContext("2d");

    
        window.myPieChart = new Chart(ctx_pie).Pie(pieChartData,{
          responsive: true
        });
        window.myDoughnutChart = new Chart(ctx_doughnut).Doughnut(pieChartData,{
          responsive: true
        });
        var myRadarChart = new Chart(ctxRadar).Radar(radarChartData, {
          responsive: true
        });
        
      }
    </script>
</body>
</html>