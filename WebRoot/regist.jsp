<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>班级通讯录系统-注册界面</title>
		<meta http-equiv="content-type" content="text/html;charset=utf-8"/>
		<link rel="stylesheet" type="text/css" href="css/style.css" />
	</head>
	<body>
		<div id="wrap">
			<div id="top_content">
				<div id="header">
					<div id="rightheader">
						<p><s:date name="time" format="yyyy-MM-dd"/><br/></p>
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
					<h1>注册信息</h1>
					<form action="regist.useraction" method="post">
						<table cellpadding="0" cellspacing="0" border="0"
							class="form_table">
							<tr>
								<td valign="middle" align="right">用户名:</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="username"/>
								</td>
								<td>
								<%
								  String message_user = (String)request.getAttribute("user_exist_error");
								 %>
								 <span style="color:red"><%=(message_user==null?"":message_user) %></span>
								 </td>
							</tr>
							<tr>
								<td valign="middle" align="right">密码:</td>
								<td valign="middle" align="left">
									<input type="password" class="inputgri" name="password"/>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">确认密码:</td>
								<td valign="middle" align="left">
									<input type="password" class="inputgri" name="repassword"/>
								</td>
								<td>
								<%
								  String message_pwd = (String)request.getAttribute("password_wrong_error");
								 %>
								 <span style="color:red"><%=(message_pwd==null?"":message_pwd) %></span>
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
								<td valign="middle" align="right">
									<a href="javascript:;" onclick="">换一张</a>
								</td>
								<td valign="middle" align="left">
									<img src="checkcode" border="1" onclick="this.src='checkcode?'+Math.random();" style="cursor:pointer">
								</td>
							</tr>
						</table>
						<p>
							<input type="submit" class="button" value="确认注册" />
							<input type="button" class="button" onclick="location='index.jsp'" value="去登陆" />
							<span style="color:red;"></span>
						</p>
					</form>
				</div>
			</div>
			<jsp:include page="foot.jsp"></jsp:include>
		</div>
	</body>
</html>
