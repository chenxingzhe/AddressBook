<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>班级通讯录系统-修改密码</title>
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
					<h1>修改密码</h1>
					<form action="modifyPassword.useraction" method="post">
						<table cellpadding="0" cellspacing="0" border="0"
							class="form_table">
							<tr>
								<td valign="middle" align="right">原密码:</td>
								<td valign="middle" align="left">
									<input type="password" class="inputgri" name="old_password" />
								</td>
								<td>
								<%
								  String message1 = (String)request.getAttribute("old_password_wrong_error");
								 %>
								 <span style="color:red"><%=(message1==null?"":message1) %></span>
								 </td>
							</tr>
							<tr>
								<td valign="middle" align="right">新密码:</td>
								<td valign="middle" align="left">
									<input type="password" class="inputgri" name="new_password" />
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">确认密码:</td>
								<td valign="middle" align="left">
									<input type="password" class="inputgri" name="affirm_password" />
								</td>
								<td>
								<%
								  String message2 = (String)request.getAttribute("password_wrong_error");
								 %>
								 <span style="color:red"><%=(message2==null?"":message2) %></span>
								 </td>
							</tr>
						</table>
						<p>
							<input type="submit" class="button" value="确认修改" />
							<span style="color:red;"></span>
						</p>
					</form>
				</div>
			</div>
			<jsp:include page="foot.jsp"></jsp:include>
		</div>
	</body>
</html>
