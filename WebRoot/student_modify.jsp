<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="entity.Student,util.StudentDAO,java.text.*" %>
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
		<script src="js/Calendar6.js" type="text/javascript"></script>
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
					<h1>修改学生信息</h1>
					<%
						String id = (String)request.getParameter("id");
						Student student = StudentDAO.findById(id);
					 %>
					<form action="modify.studentaction" method="post">
						<table cellpadding="0" cellspacing="0" border="0" class="form_table">
							<tr>
								<td valign="middle" align="right">学号:</td>
								<td valign="middle" align="left">
									<%=student.getId()%>
									<input type="hidden" name="id" value="<%=student.getId()%>">
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">班级:</td>
								<td valign="middle" align="left">
									<input class="inputgri" name="classid" type="text" value="<%=student.getClassid()%>">
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">姓名:</td>
								<td valign="middle" align="left">
									<input class="inputgri" name="name" type="text" value="<%=student.getName()%>">
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">性别:</td>
								<td valign="middle" align="left">
									<% String gender = student.getGender(); %>
									男<input type="radio" name="gender" value="m" <%=gender.equals("男")?"checked":"" %>>
									女<input type="radio" name="gender" value="f" <%=gender.equals("女")?"checked":"" %>>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">地址:</td>
								<td valign="middle" align="left">
									<input class="inputgri" name="address" type="text" value="<%=student.getAddress()%>">
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">日期:</td>
								<td valign="middle" align="left">
									<input class="inputgri" name="birthday" type="text" onclick="SelectDate(this,'yyyy\-MM\-dd')" readonly="readonly" value="<%=new SimpleDateFormat("yyyy-MM-dd").format(student.getBirthday())%>"/>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">联系方式:</td>
								<td valign="middle" align="left">
									<input class="inputgri" name="tel" type="text" value="<%=student.getTel()%>">
								</td>
							</tr>
						</table>
						<p>
							<input type="submit" class="button" value="确认" />
							<a href="list.studentaction"><input type="button" class="button" value="返回"/></a>
						</p>
					</form>
				</div>
			</div>
			<jsp:include page="foot.jsp"></jsp:include>
		</div>
	</body>
</html>
