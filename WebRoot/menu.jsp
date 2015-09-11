<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="entity.User,util.*" %>
<div id="navigation">
	<div style="padding:5px;color:#F7FFF1;">
		<% User currentUser = (User)request.getSession().getAttribute("currentUser"); %>
		欢迎&nbsp;
		<%=currentUser.getName()%>
		(<%=currentUser.getRole().equals("student")?"普通用户":"管理员"%>)&nbsp;
		登录&nbsp;
		<%if(currentUser.getRole().equals("student")){ %>
		学号：<%=currentUser.getId() %>&nbsp;
		班级：<%=currentUser.getClassid() %>&nbsp;
		<%=ClassDAO.findNameById(currentUser.getClassid()) %>&nbsp;|&nbsp;
		<%} %>
		<a href="pwd.jsp">修改密码</a>&nbsp;|&nbsp;
		<a href="logout.useraction">退出</a>&nbsp;|&nbsp;
		<a href="list.infoaction">通讯录列表</a>&nbsp;|&nbsp;
		<a href="info_add.jsp">添加通讯录信息</a>&nbsp;|&nbsp;
		<a href="statistics.jsp">数据统计</a>&nbsp;|&nbsp;
	</div>
</div>