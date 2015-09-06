package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.StudentDAO;
import entity.Student;

public class StudentActionServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		if("/list".equals(action)){
			/**
			 * 显示通讯录列表
			 */
			//获得当前会话中保存的班级号，通过班级号获得本班的所有学生
			List<Student> studentList =StudentDAO.findAll((String)request.getSession().getAttribute("currentClass"));
			//将学生列表保存的请求中
			request.setAttribute("studentList", studentList);
			//将请求转发给显示通讯录的页面
			request.getRequestDispatcher("student_list.jsp").forward(request, response);
		}else if("/delete".equals(action)){
			/**
			 * 删除一条通讯录信息
			 */
			//获得请求中保存的学号
			String id = request.getParameter("id");
			//通过学号在数据库中删除一条学生信息
			StudentDAO.deleteById(id);
			//删除完毕，请求重定向到处理通讯录列表的Servlet
			response.sendRedirect("list.studentaction");
		}else if("/modify".equals(action)){
			/**
			 * 修改一条通讯录信息
			 */
			//获得请求中传递过来的信息，组装成一个学生对象（通过getStudent方法）
			Student student = getStudent(request);
			//依据学号，在数据库中更新一条通讯录记录
			StudentDAO.updateById(student.getId(), student);
			//修改完毕，请求重定向到处理通讯录列表的Servlet
			response.sendRedirect("list.studentaction");
		}else if("/add".equals(action)){
			/**
			 * 添加一条通讯录信息
			 */
			//如果有任何一项没有填写，不能完成添加，返回错误信息
			if(request.getParameter("id").equals("") || 
					request.getParameter("classid").equals("") || 
					request.getParameter("name").equals("") || 
					request.getParameter("gender")==null || 
					request.getParameter("address").equals("") || 
					request.getParameter("birthday").equals("") || 
					request.getParameter("tel").equals("") ){
				request.setAttribute("info_null_wrong_error", "信息不完整");
				request.getRequestDispatcher("student_add.jsp").forward(request, response);
				return;
			}
			//如果该学生对象的学号已经存在数据库中，则添加失败，返回错误信息
			String id = request.getParameter("id");
			if(StudentDAO.findById(id)!=null){
				request.setAttribute("id_exist_wrong_error", "此学号信息已存在");
				request.getRequestDispatcher("student_add.jsp").forward(request, response);
				return;
			}
			//获得请求中传递过来的信息，组装成一个学生对象（通过getStudent方法）
			Student student = getStudent(request);
			//在数据库中添加一条通讯录记录
			StudentDAO.add(student);
			//添加完毕，请求重定向到处理通讯录列表的Servlet
			response.sendRedirect("list.studentaction");
		}else if("/search".equals(action)){
			/**
			 * 查询通讯录信息
			 */
			//获得分类，关键字，是否模糊查询
			String classify = request.getParameter("classify");
			String keywords = request.getParameter("keywords");
			String fuzzyquery = request.getParameter("fuzzyquery");
			
			//添加到请求中
			request.setAttribute("classify", classify);
			request.setAttribute("keywords", keywords);
			request.setAttribute("fuzzyquery", fuzzyquery);
			
			//获得符合要求的所有本班的学生对象，添加到请求中
			List<Student> studentList = StudentDAO.findByInput((String)request.getSession().getAttribute("currentClass"),classify, keywords, "fuzzyquery".equals(fuzzyquery));
			request.setAttribute("studentList", studentList);
			//将请求转发给显示通讯录的页面
			request.getRequestDispatcher("student_list.jsp").forward(request, response);
		}
	}
	/**
	 * 获取请求中的各项信息，包装成一个学生对象
	 * @param request 请求
	 * @return 学生对象
	 */
	public static Student getStudent(HttpServletRequest request){
		//定义一个新的学生对象
		Student student = new Student();
		//设置各项属性
		student.setId(request.getParameter("id"));
		student.setClassid(request.getParameter("classid"));
		student.setName(request.getParameter("name"));
		student.setGender(request.getParameter("gender").equals("m")?"男":"女");
		student.setAddress(request.getParameter("address"));
		try {
			student.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		student.setTel(request.getParameter("tel"));
		
		return student;
	}
}
