package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.InfoDAO;
import entity.Info;
import entity.User;

public class InfoActionServlet extends HttpServlet {

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
			//获得当前会话中保存的用户信息
			User currentUser = (User)request.getSession().getAttribute("currentUser");
			List<Info> infoList = null;
			//如果是管理员，则获得所有通讯录信息，否则，获得本班的所有通讯录信息
			if(currentUser.getRole().equals("admin")){
				infoList =InfoDAO.findAll();
			}else{
				infoList =InfoDAO.findAllByClassid(currentUser.getClassid());
			}
			//将通讯录信息列表保存的请求中
			request.setAttribute("infoList", infoList);
			request.getSession().setAttribute("infoList", infoList);
			//将请求转发给显示通讯录的页面
			request.getRequestDispatcher("info_list.jsp").forward(request, response);
		}else if("/delete".equals(action)){
			/**
			 * 删除一条通讯录信息
			 */
			//获得请求中保存的学号
			String id = request.getParameter("id");
			//通过学号在数据库中删除一条通讯录信息
			InfoDAO.deleteById(id);
			//删除完毕，请求重定向到处理通讯录列表的Servlet
			response.sendRedirect("list.infoaction");
		}else if("/modify".equals(action)){
			/**
			 * 修改一条通讯录信息
			 */
			//获得请求中传递过来的信息，组装成一个通讯录信息对象（通过getInfo方法）
			Info info = getInfo(request);
			//依据学号，在数据库中更新一条通讯录记录
			InfoDAO.updateById(info.getId(), info);
			//修改完毕，请求重定向到处理通讯录列表的Servlet
			response.sendRedirect("list.infoaction");
		}else if("/add".equals(action)){
			/**
			 * 添加一条通讯录信息
			 */
			//如果该通讯录信息对象的学号已经存在数据库中，则添加失败，返回错误信息
			String id = request.getParameter("id");
			if(InfoDAO.findById(id)!=null){
				request.setAttribute("id_exist_wrong_error", "此学号信息已存在");
				request.getRequestDispatcher("info_add.jsp").forward(request, response);
				return;
			}
			//如果有任何一项没有填写，不能完成添加，返回错误信息
			if(request.getParameter("id").equals("") || 
					request.getParameter("classid").equals("") || 
					request.getParameter("name").equals("") || 
					request.getParameter("gender")==null || 
					request.getParameter("province")==null || 
					request.getParameter("address").equals("") || 
					request.getParameter("birthday").equals("") || 
					request.getParameter("tel").equals("") ){
				request.setAttribute("info_null_wrong_error", "信息不完整");
				request.getRequestDispatcher("info_add.jsp").forward(request, response);
				return;
			}
			//获得请求中传递过来的信息，组装成一个通讯录信息对象（通过getInfo方法）
			Info info = getInfo(request);
			//在数据库中添加一条通讯录记录
			InfoDAO.add(info);
			//添加完毕，请求重定向到处理通讯录列表的Servlet
			response.sendRedirect("list.infoaction");
		}else if("/search".equals(action)){
			/**
			 * 查询通讯录信息
			 */
			//获得分类，关键字，是否模糊查询
			String classify = request.getParameter("classify");
			String keywords = request.getParameter("keywords");
			String fuzzyquery = request.getParameter("fuzzyquery");
			
			//获得符合要求的所有本班的通讯录信息对象，添加到请求中
			User currentUser = (User)request.getSession().getAttribute("currentUser");
			boolean isAdmin = currentUser.getRole().equals("admin");
			boolean isFuzzy = "fuzzyquery".equals(fuzzyquery);
			List<Info> infoList = null;
			if(isAdmin){
				infoList = InfoDAO.findByInput(classify, keywords, isFuzzy);
			}else{
				infoList = InfoDAO.findByInput(currentUser.getClassid(),classify, keywords, isFuzzy);				
			}
			request.setAttribute("infoList", infoList);
			request.getSession().setAttribute("infoList", infoList);
			//将请求转发给显示通讯录的页面
			request.getRequestDispatcher("info_list.jsp").forward(request, response);
		}
	}
	/**
	 * 获取请求中的各项信息，包装成一个通讯录信息对象
	 * @param request 请求
	 * @return 通讯录信息对象
	 */
	public static Info getInfo(HttpServletRequest request){
		//定义一个新的通讯录信息对象
		Info info = new Info();
		//设置各项属性
		info.setId(request.getParameter("id"));
		info.setClassid(request.getParameter("classid"));
		info.setName(request.getParameter("name"));
		info.setGender(request.getParameter("gender").equals("m")?"男":"女");
		info.setProvince(request.getParameter("province"));
		info.setAddress(request.getParameter("address"));
		try {
			info.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		info.setTel(request.getParameter("tel"));
		
		return info;
	}
}
