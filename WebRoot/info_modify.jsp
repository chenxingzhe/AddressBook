<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="entity.Info,util.InfoDAO,java.text.*" %>
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
						<%
							String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
						%>
						<p><%=date%><br/></p>
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
												Info info = InfoDAO.findById(id);
					%>
					<form action="modify.infoaction" method="post">
						<table cellpadding="0" cellspacing="0" border="0" class="form_table">
							<tr>
								<td valign="middle" align="right">学号:</td>
								<td valign="middle" align="left">
									<input class="inputgri" type="text" value="<%=info.getId()%>" disabled="disabled">
									<input name="id" type="hidden" value="<%=info.getId()%>">
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">班级:</td>
								<td valign="middle" align="left">
									<input class="inputgri" type="text" value="<%=info.getClassid()%>" disabled="disabled">
									<input name="classid" type="hidden" value="<%=info.getClassid()%>">
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">姓名:</td>
								<td valign="middle" align="left">
									<input class="inputgri" type="text" value="<%=info.getName()%>" disabled="disabled">
									<input name="name" type="hidden" value="<%=info.getName()%>">
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">性别:</td>
								<td valign="middle" align="left">
									<% String gender = info.getGender(); %>
									男<input type="radio" name="gender" value="m" <%=gender.equals("男")?"checked":"" %>>
									女<input type="radio" name="gender" value="f" <%=gender.equals("女")?"checked":"" %>>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">籍贯:</td>
								<td valign="middle" align="left">
									<% String province = info.getProvince();%>
									<select class="inputgri" name="province">
										<%
											String[] provinces = {"北京","天津","上海","重庆","河北","河南","云南","辽宁","黑龙江","湖南","安徽","山东","新疆","江苏","浙江","江西","湖北","广西","甘肃","山西","内蒙古",
											"陕西","吉林","福建","贵州","广东","青海","西藏","四川","宁夏","海南","台湾","香港","澳门"};
											for(int i = 0 ; i < provinces.length ; i++){
												if(provinces[i].equals(province)){
										 %>
										 	<option value="<%=provinces[i]%>" selected><%=provinces[i] %></option>
										 <%}else{ %>
										 	<option value="<%=provinces[i]%>"><%=provinces[i] %></option>
										 <%}} %>
									</select>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">地址:</td>
								<td valign="middle" align="left">
									<input class="inputgri" name="address" type="text" value="<%=info.getAddress()%>">
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">日期:</td>
								<td valign="middle" align="left">
									<input class="inputgri" name="birthday" type="text" onclick="SelectDate(this,'yyyy\-MM\-dd')" readonly="readonly" value="<%=new SimpleDateFormat("yyyy-MM-dd").format(info.getBirthday())%>"/>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">联系方式:</td>
								<td valign="middle" align="left">
									<input class="inputgri" name="tel" type="text" value="<%=info.getTel()%>">
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
