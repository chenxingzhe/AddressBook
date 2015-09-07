<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="entity.User,util.*" %>
<div id="navigation">
	<div style="padding:5px;color:#F7FFF1;">
		欢迎&nbsp;&nbsp;
		<% User currentUser = (User)request.getSession().getAttribute("currentUser"); %>
		<%=StudentDAO.findById(currentUser.getUsername()).getName() %>
		(<%=currentUser.getUsername() %>)&nbsp;&nbsp;
		登录，&nbsp;&nbsp;
		班级：&nbsp;
		<%String classid = (String)request.getSession().getAttribute("currentClass"); %>
		<%=classid %>&nbsp;&nbsp;
		<%=ClassDAO.findNameById(classid) %>&nbsp;&nbsp;
		|&nbsp;<a href="pwd.jsp">修改密码</a>&nbsp;
		|&nbsp;<a href="logout.useraction">退出</a>&nbsp;
		|&nbsp;<a href="list.studentaction">通讯录列表</a>&nbsp;
		|&nbsp;<a href="student_add.jsp">添加</a>&nbsp;|
	</div>
</div>