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
					<h1>添加学生信息</h1>
					<form action="add.infoaction" method="post">
						<table cellpadding="0" cellspacing="0" border="0" class="form_table">
							<%
								User user = (User)request.getSession().getAttribute("currentUser");
								boolean isAdmin = user.getRole().equals("admin");
							 %>
							<tr>
								<td valign="middle" align="right">学号:</td>
								<td valign="middle" align="left">
									<%if(!isAdmin){ %>
										<input class="inputgri" type="text" value="<%=user.getId()%>" disabled="disabled">
										<input name="id" type="hidden" value="<%=user.getId()%>">
									<%}else{ %>
										<%String id = request.getParameter("id"); %>
										<input class="inputgri" type="text" name="id" placeholder="请输入学号" value="<%=id==null?"":id%>">
									<%} %>
								</td>
								<td>
								<%
								  String message = (String)request.getAttribute("id_exist_wrong_error");
								 %>
								 <span style="color:red"><%=(message==null?"":message) %></span>
								 </td>
							</tr>
							<tr>
								<td valign="middle" align="right">班级:</td>
								<td valign="middle" align="left">
									<%if(!isAdmin){ %>
										<input class="inputgri" type="text" value="<%=user.getClassid()%>" disabled="disabled">
										<input name="classid" type="hidden" value="<%=user.getClassid()%>">
									<%}else{ %>
										<%String classid = request.getParameter("classid"); %>
										<input class="inputgri" type="text" name="classid" placeholder="请输入班级号" value="<%=classid==null?"":classid%>">
									<%} %>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">姓名:</td>
								<td valign="middle" align="left">
									<%if(!isAdmin){ %>
										<input class="inputgri" type="text" value="<%=user.getName()%>" disabled="disabled">
										<input name="name" type="hidden" value="<%=user.getName()%>">
									<%}else{ %>
										<%String name = request.getParameter("name"); %>
										<input class="inputgri" type="text" name="name" placeholder="请输入姓名" value="<%=name==null?"":name%>">
									<%} %>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">性别:</td>
								<td valign="middle" align="left">
									<% String gender = request.getParameter("gender");%>
									男<input type="radio" name="gender" value='m' <%="m".equals(gender)?"checked":""%>>
									女<input type="radio" name="gender" value='f' <%="f".equals(gender)?"checked":""%>>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">籍贯:</td>
								<td valign="middle" align="left">
									<% String province = request.getParameter("province"); %>
									<select class="inputgri" name="province">
										<%
											String[] provinces = {"北京","天津","上海","重庆","河北","河南","云南","辽宁","黑龙江","湖南","安徽","山东","新疆","江苏","浙江","江西","湖北","广西","甘肃","山西","内蒙古",
											"陕西","吉林","福建","贵州","广东","青海","西藏","四川","宁夏","海南","台湾","香港","澳门"};
											for(int i = 0 ; i < provinces.length ; i++){
										 %>
										 <option value="<%=provinces[i]%>"><%=provinces[i] %></option>
										 <%} %>
									</select>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">家庭地址:</td>
								<td valign="middle" align="left">
									<% String address = request.getParameter("address"); %>
									<input class="inputgri" name="address" type="text" placeholder="请输入家庭地址" value="<%=address==null?"":address%>">
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">出生日期:</td>
								<td valign="middle" align="left">
									<% String birthday = request.getParameter("birthday"); %>
									<input class="inputgri" name="birthday" type="text" onclick="SelectDate(this,'yyyy\-MM\-dd')" readonly="readonly"  placeholder="请选择日期" value="<%=birthday==null?"":birthday%>"/>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">联系方式:</td>
								<td valign="middle" align="left">
									<% String tel = request.getParameter("tel"); %>
									<input class="inputgri" name="tel" type="text" placeholder="请输入联系方式" value="<%=tel==null?"":tel%>">
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									<%
									  String message2 = (String)request.getAttribute("info_null_wrong_error");
									 %>
									<span style="color:red;"><%=message2==null?"":message2 %></span>
								</td>
							</tr>
						</table>
						<p>
							<input type="submit" class="button" value="确认" />
							<a href="list.infoaction"><input type="button" class="button" value="返回"/></a>
						</p>
					</form>
				</div>
			</div>
			<jsp:include page="foot.jsp"></jsp:include>
		</div>
	</body>
</html>
