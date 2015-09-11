<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>班级通讯录系统-登录</title>
		<meta http-equiv="content-type" content="text/html;charset=utf-8" />
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
					<div id="navigation">
					</div>
				</div>
				<div id="content">
					<p id="whereami"></p>
					<h1>系统登录</h1>
					<form action="login.useraction" method="post">
						<table cellpadding="0" cellspacing="0" border="0"
							class="form_table">
							<tr>
								<td valign="middle" align="right">用户名:</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="username"/>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">密码:</td>
								<td valign="middle" align="left">
									<input type="password" class="inputgri" name="password"/>
								</td>
								<td>
								<%
								  String message = (String)request.getAttribute("user_or_password_wrong_error");
								 %>
								 <span style="color:red"><%=(message==null?"":message) %></span>
								 </td>
							</tr>
							<tr>
								<td valign="middle" align="right">验证码:</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="number_input"/>
								</td>
								<td>
								<%
								  String message_number = (String)request.getAttribute("number_error");
								 %>
								 <span style="color:red"><%=(message_number==null?"":message_number) %></span>
								 </td>
							</tr>
							<tr>
								<td valign="middle" align="right"></td>
								<td valign="middle" align="left">
									<img src="checkcode" border="1" onclick="this.src='checkcode?'+Math.random();" style="cursor:pointer">
								</td>
							</tr>
						</table>
						<p>
							<input type="submit" class="button" value="登录系统" />
							<input type="button" class="button" onclick="location='regist.jsp'" value="没有账号，去注册&raquo;" />
						</p>
					</form>
				</div>
			</div>
			<jsp:include page="foot.jsp"></jsp:include>
		</div>
	</body>
</html>

