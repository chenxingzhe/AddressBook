package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.StudentDAO;
import util.UserDAO;
import entity.Student;
import entity.User;
/**
 * 用户类行为Servlet类
 * 
 * @author Jasper
 *
 */
public class UserActionServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		
		if("/login".equals(action)){
			/*
			 * 用户登录
			 */
			
			//获得用户输入的验证码
			String number_input = request.getParameter("number_input");
			//获得系统生成的，保存在当前会话中的验证码
			HttpSession session = request.getSession();
			String number_pic = (String)session.getAttribute("number_pic");
			
			//如果两个验证码不一致，则返回错误信息
			if(!number_input.toLowerCase().equals(number_pic.toLowerCase())){
				request.setAttribute("number_error", "验证码错误");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			
			//获得用户填写的用户名和密码
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			//获得符合条件的用户
			User user = UserDAO.findByUsername(username);
			
			//如果用户不存在或密码错误，返回错误信息
			if(user==null || !user.getPassword().equals(password)){
				request.setAttribute("user_or_password_wrong_error", "用户名或密码错误");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			
			//设置本次会话的当前用户
			request.getSession().setAttribute("currentUser", user);
			
			//至此登录成功，转到处理通讯录列表的Servlet
			response.sendRedirect("list.infoaction");

		}else if("/regist".equals(action)){
			/**
			 * 用户注册
			 */
			//获得用户输入的验证码
			String number_input = request.getParameter("number_input");
			//获得系统生成的，保存在当前会话中的验证码
			HttpSession session = request.getSession();
			String number_pic = (String)session.getAttribute("number_pic");
			
			//如果两个验证码不一致，则返回错误信息
			if(!number_input.toLowerCase().equals(number_pic.toLowerCase())){
				request.setAttribute("number_error", "验证码错误");
				request.getRequestDispatcher("regist.jsp").forward(request, response);
				return;
			}
			
			//获得用户输入的用户名
			String username = request.getParameter("username");
			//如果用户名已经存在，则返回错误信息
			if(UserDAO.findByUsername(username)!=null){
				request.setAttribute("user_exist_error", "用户名已存在");
				request.getRequestDispatcher("regist.jsp").forward(request, response);
				return;
			}
			//获得用户输入的密码和确认密码
			String password = request.getParameter("password");
			String repassword = request.getParameter("repassword");
			//如果两个密码不一致，则返回错误信息
			if(!password.equals(repassword)){
				request.setAttribute("password_wrong_error", "两次密码不一致");
				request.getRequestDispatcher("regist.jsp").forward(request, response);
				return;
			}
			
			//获得用户输入的学号，班级号，姓名，进行绑定
			String id = request.getParameter("id");
			String classid = request.getParameter("classid");
			String name = request.getParameter("name");
			Student student = StudentDAO.findById(id);
			//如果学生对象为空，即学号不存在，则返回错误信息
			if(student==null){
				request.setAttribute("id_wrong_error", "学号不存在");
				request.getRequestDispatcher("regist.jsp").forward(request, response);
				return;
			}
			//如果班级号不匹配，或姓名不匹配，则返回错误信息
			if(!student.getClassid().equals(classid) || !student.getName().equals(name)){
				request.setAttribute("classid_or_name_wrong_error", "班级号或姓名错误");
				request.getRequestDispatcher("regist.jsp").forward(request, response);
				return;
			}
			
			
			//新建一个用户对象
			User user = new User();
			//设置用户名和密码
			user.setUsername(username);
			user.setPassword(password);
			user.setId(id);
			user.setClassid(classid);
			user.setName(name);
			user.setRole("student");
			//添加到数据库
			UserDAO.add(user);
			//至此注册成功，转到登陆界面
			PrintWriter out = response.getWriter();
			out.print("<script>alert(\"注册成功\");location='index.jsp';</script>");
			out.close();
			
		}else if("/modifyPassword".equals(action)){
			/**
			 * 修改密码
			 */
			
			//获得输入的旧密码，新密码，确认密码
			String oldPassword = request.getParameter("old_password");
			String newPassword = request.getParameter("new_password");
			String affirmPassword = request.getParameter("affirm_password");
			
			//获得当前登陆的用户对象
			User user = (User)request.getSession().getAttribute("currentUser");
			//如果旧密码与当前用户的密码不一致，则返回错误信息
			if(!oldPassword.equals(user.getPassword())){
				request.setAttribute("old_password_wrong_error", "原密码错误");
				request.getRequestDispatcher("pwd.jsp").forward(request, response);
				return;
			}
			//如果新密码与确认密码不一致，则返回错误信息
			if(!newPassword.equals(affirmPassword)){
				request.setAttribute("password_wrong_error", "两次密码不一致");
				request.getRequestDispatcher("pwd.jsp").forward(request, response);
				return;
			}
			//更新数据库中的密码
			UserDAO.updatePassword(user.getUsername(), newPassword);
			//清除当前会话中保存的登陆用户信息，班级信息
			request.getSession().removeAttribute("currentUser");
			//至此修改密码完成，需重新登陆，转到登陆界面
			response.sendRedirect("index.jsp");
			
		}else if("/logout".equals(action)){
			/**
			 * 用户登出
			 */
			//退出系统的时候，清除当前会话保存的信息
			request.getSession().removeAttribute("currentUser");
			//转到登陆界面
			response.sendRedirect("index.jsp");
		}
	}

}
