<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="entity.*,java.text.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>班级通讯录系统-主界面</title>
		<meta http-equiv="content-type" content="text/html;charset=utf-8"/>
		<link rel="stylesheet" type="text/css" href="css/style.css" />
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
					<h1>通讯录</h1>
					<form action="search.infoaction" method="post">
						<div calss="search">
							按&nbsp;&nbsp;
							<select name="classify" class="inputgri">
								<%User currentUser = (User)request.getSession().getAttribute("currentUser"); %>
								<%if(currentUser.getRole().equals("admin")){ %>
								<option value=classid>班级号</option>
								<%} %>
								<option value="id">学号</option>
								<option value="name">姓名</option>
								<option value="tel">联系方式</option>
							</select>
							&nbsp;&nbsp;查询&nbsp;&nbsp;
							<input type="text" class="inputgri" name="keywords" placeholder="输入关键字">&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" id="fuzzyquery" name="fuzzyquery" value="fuzzyquery">
							<label for="fuzzyquery">模糊查询</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="submit" value="查询">
						</div>
					</form>	
					<br>				
					<table class="table"  style="font-size:10px;">
						<tr class="table_header">
							<td>学号</td>
							<td>班级</td>
							<td>姓名</td>
							<td>性别</td>
							<td>籍贯</td>
							<td>地址</td>
							<td>生日</td>
							<td>联系方式</td>
							<td>操作</td>
						</tr>
						<%
							List<Info> infoList = (List<Info>)request.getAttribute("infoList");
							if(infoList!=null){
								for(int i = 0 ; i <infoList.size();i++){
									Info info = infoList.get(i);
						%>
						<tr class="row<%=i%2+1%>">
							<td><%=info.getId() %></td>
							<td><%=info.getClassid() %></td>
							<td><%=info.getName() %></td>
							<td><%=info.getGender() %></td>
							<td><%=info.getProvince() %></td>
							<td><%=info.getAddress() %></td>
							<td><%=new SimpleDateFormat("yyyy-MM-dd").format(info.getBirthday()) %></td>
							<td><%=info.getTel() %></td>
							<td id="operation">
								<%
									boolean isAdmin = currentUser.getRole().equals("admin");
									boolean isMe = currentUser.getId().equals(info.getId());
									if(isAdmin || isMe){ 
								%>
									<a href="info_modify.jsp?id=<%=info.getId()%>">更改</a>&nbsp;&nbsp;
									<a href="delete.infoaction?id=<%=info.getId()%>">删除</a>
								<%} %>
							</td>
						</tr>
						
						<%}} %>
					</table>
					<br>
				</div>
			</div>
			<jsp:include page="foot.jsp"></jsp:include>
		</div>
	</body>
</html>
