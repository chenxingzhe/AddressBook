<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.*,entity.*,util.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>班级通讯录系统-数据统计</title>
		<meta http-equiv="content-type" content="text/html;charset=utf-8"/>
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<script type="text/javascript" src="js/prototype-1.6.0.3.js"></script>
		
	</head>
	<body>
		<div id="wrap">
			<div id="top_content">
				<div id="header">
					<div id="rightheader">
						<%String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); %>
						<p><%=date %><br/></p>
					</div>
					<div id="topheader">
						<h1 id="title">
							<a>班级通讯录系统</a>
						</h1>
					</div>
					<jsp:include page="menu.jsp"></jsp:include>
				</div>
				<div id="content">
					<p id="whereami"></p>
					<h1>数据统计</h1>
					<div id="main" style="height:400px;"></div>
					<div id="main2" style="height:400px"></div>
				</div>
				<br>
			</div>
			<jsp:include page="foot.jsp"></jsp:include>
		</div>
		<script src="js/echarts.js"></script>

		<script type="text/javascript">    
	    	var monthArray = new Array([12]); 
	    	for(var i = 0 ; i < 12;i++){
	    		monthArray[i] = 0;
	    	}     
	       <%
	        List<Info> infoList = (List<Info>)request.getSession().getAttribute("infoList");
	        for(int i=0;i<infoList.size();i++){
	            Info info = infoList.get(i);
	            int month = info.getBirthday().getMonth();
	     	%>
	          monthArray[<%=month%>]++;
	     	<%      
	        	}
	     	%>
	     	// 路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
        //使用
        require(
            [
                'echarts',
                'echarts/chart/bar',
                'echarts/chart/line'
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main')); 
                
                option = {
                	    title : {
                	        text: '出生月份分布'
                	    },
                	    tooltip : {
                	        trigger: 'axis'
                	    },
                	    legend: {
                	        data:['人数']
                	    },
                	    toolbox: {
                	        show : true,
                	        feature : {
                	            mark : {show: true},
                	            dataView : {show: true, readOnly: false},
                	            magicType : {show: true, type: ['line', 'bar']},
                	            restore : {show: true},
                	            saveAsImage : {show: true}
                	        }
                	    },
                	    calculable : true,
                	    xAxis : [
                	        {
                	            type : 'category',
                	            data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
                	        }
                	    ],
                	    yAxis : [
                	        {
                	            type : 'value'
                	        }
                	    ],
                	    series : [
                	        {
                	            name:'人数',
                	            type:'bar',
                	            data:monthArray,
                	            markPoint : {
                	                data : [
                	                    {type : 'max', name: '最大值'},
                	                    {type : 'min', name: '最小值'}
                	                ]
                	            }
                	        }
                	    ]
                	};
        
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
    	</script>
    	<script>
    		<%
    			infoList = (List<Info>)request.getSession().getAttribute("infoList");
    			int[] p = InfoDAO.getProvinceDistribution(infoList);
    			String[] provinces = {"北京","天津","上海","重庆","河北","河南","云南","辽宁","黑龙江","湖南","安徽","山东","新疆","江苏","浙江","江西","湖北","广西","甘肃","山西","内蒙古",
				"陕西","吉林","福建","贵州","广东","青海","西藏","四川","宁夏","海南","台湾","香港","澳门"};
    		%>
    		var array = new Array();
    		<%
				for(int i = 0 ; i < 34 ; i ++){
			%>
			array[<%=i%>] = <%=p[i]%>;
			<%
				}
			%>
	    	// 路径配置
	        require.config({
	            paths: {
	                echarts: 'http://echarts.baidu.com/build/dist'
	            }
	        });
	    	//使用
	        require(
	            [
	                'echarts',
	                'echarts/chart/map'
	            ],
	            function (ec) {
	                // 基于准备好的dom，初始化echarts图表
	                var myChart = ec.init(document.getElementById('main2')); 
	                option = {
					    title : {
					        text: '籍贯分布',
					        x:'center'
					    },
					    tooltip : {
					        trigger: 'item'
					    },
					    legend: {
					        orient: 'vertical',
					        x:'left',
					        data:['人数']
					    },
					    dataRange: {
					        min: 0,
					        max: 10,
					        x: 'left',
					        y: 'bottom',
					        text:['多','少'],           // 文本，默认为数值文本
					        calculable : true
					    },
					    toolbox: {
					        show: true,
					        orient : 'vertical',
					        x: 'right',
					        y: 'center',
					        feature : {
					            mark : {show: true},
					            dataView : {show: true, readOnly: false},
					            restore : {show: true},
					            saveAsImage : {show: true}
					        }
					    },
					    roamController: {
					        show: true,
					        x: 'right',
					        mapTypeControl: {
					            'china': true
					        }
					    },
					    series : [
					        {
					            name: '人数',
					            type: 'map',
					            mapType: 'china',
					            roam: false,
					            itemStyle:{
					                normal:{label:{show:true}},
					                emphasis:{label:{show:true}}
					            },
					            data:[
					            	<%
					            		for(int i = 0 ; i < 33 ; i ++){
					            	%>
					                {name: '<%=provinces[i]%>',value: array[<%=i%>]},
					                <%
					                	}
					                %>
					                {name: '澳门',value: array[33]}
					            ]
					        }
					    ]
					};
                                   
	                // 为echarts对象加载数据 
	                myChart.setOption(option); 
	            }
	        );
    	</script>
	</body>
</html>
