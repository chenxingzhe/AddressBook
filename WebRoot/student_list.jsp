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
					<form action="search.studentaction" method="post">
						<div calss="search">
							按&nbsp;&nbsp;
							<select name="classify" class="inputgri">
								<option value="id">学号</option>
								<option value="name">姓名</option>
								<option value="tel">联系方式</option>
							</select>
							&nbsp;&nbsp;查询&nbsp;&nbsp;<input type="text" class="inputgri" name="keywords" placeholder="输入关键字">
							<input type="checkbox" name="fuzzyquery" value="fuzzyquery">模糊查询
							<input type="submit" value="查询">
						</div>
					</form>	
					<br>				
					<table class="table">
						<tr class="table_header">
							<td>学号</td>
							<td>姓名</td>
							<td>性别</td>
							<td>地址</td>
							<td>生日</td>
							<td>联系方式</td>
							<td>操作</td>
						</tr>
						<%
							List<Student> studentdList = (List<Student>)request.getAttribute("studentList");
							if(studentdList!=null){
								for(int i = 0 ; i <studentdList.size();i++){
									Student student = studentdList.get(i);
						 %>
						<tr class="row1">
							<td><%=student.getId() %></td>
							<td><%=student.getName() %></td>
							<td><%=student.getGender() %></td>
							<td><%=student.getAddress() %></td>
							<td><%=new SimpleDateFormat("yyyy-MM-dd").format(student.getBirthday()) %></td>
							<td><%=student.getTel() %></td>
							<td>
								<%
									boolean isMe = ((User)request.getSession().getAttribute("currentUser")).getUsername().equals(student.getId());
									if(isMe){ 
								%>
								<a href="student_modify.jsp?id=<%=student.getId()%>">更改</a>&nbsp;&nbsp;
								<a href="delete.studentaction?id=<%=student.getId()%>">删除</a>
								<%} %>
							</td>
						</tr>
						<%}} %>
					</table>
					<div align="center">
						<a>[上一页]</a>&nbsp;&nbsp;
						<a>[下一页]</a>&nbsp;&nbsp;
						共&nbsp;X&nbsp;页
					</div>
					<br>
				</div>
			</div>
			<jsp:include page="foot.jsp"></jsp:include>
		</div>
	</body>
</html>
