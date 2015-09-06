package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import entity.Student;
/**
 * 学生类的数据库操作类
 * @author Jasper
 *
 */
public class StudentDAO {
	/**
	 * 添加一条学生信息
	 * @param student 一个学生对象
	 * @return 是否成功添加
	 */
	public static boolean add(Student student){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into student(id,classid,name,gender,address,birthday,tel) values(?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, student.getId());
			ps.setString(2, student.getClassid());
			ps.setString(3, student.getName());
			ps.setString(4, student.getGender());
			ps.setString(5, student.getAddress());
			ps.setString(6,new SimpleDateFormat("yyyy-MM-dd").format(student.getBirthday()));
			ps.setString(7, student.getTel());
			
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	/**
	 * 通过学号id删除一条学生信息
	 * @param id 学号
	 * @return 是否成功删除
	 */
	public static boolean deleteById(String id){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from student where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	/**
	 * 通过学号更新一条学生信息
	 * @param id 学号
	 * @param student 学生对象
	 * @return 是否成功修改
	 */
	public static boolean updateById(String id,Student student){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "update student set classid=?,name=?,gender=?,address=?,birthday=?,tel=? where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, student.getClassid());
			ps.setString(2, student.getName());
			ps.setString(3, student.getGender());
			ps.setString(4, student.getAddress());
			ps.setString(5,new SimpleDateFormat("yyyy-MM-dd").format(student.getBirthday()));
			ps.setString(6, student.getTel());
			ps.setString(7, id);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	/**
	 * 通过学号获得一个学生对象
	 * @param id 学号
	 * @return 学生对象
	 */
	public static Student findById(String id){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select id,classid,name,gender,address,birthday,tel from student where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				Student student = new Student();
				student.setId(rs.getString("id"));
				student.setClassid(rs.getString("classid"));
				student.setName(rs.getString("name"));
				student.setGender(rs.getString("gender"));
				student.setAddress(rs.getString("address"));
				student.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("birthday")));
				student.setTel(rs.getString("tel"));
				return student;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 通过学号获得所在班级号
	 * @param id 学号
	 * @return 班级号
	 */
	public static String findClassidById(String id){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select classid from student where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				return rs.getString("classid");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 通过姓名获得一个学生对象
	 * @param name 姓名
	 * @return 学生对象
	 */
	public static Student findByName(String name){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select id,classid,name,gender,address,birthday,tel from student where name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if(rs.next()){
				Student student = new Student();
				student.setId(rs.getString("id"));
				student.setClassid(rs.getString("classid"));
				student.setName(rs.getString("name"));
				student.setGender(rs.getString("gender"));
				student.setAddress(rs.getString("address"));
				student.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("birthday")));
				student.setTel(rs.getString("tel"));
				return student;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 获得本班的，且符合输入参数要求的所有学生
	 * @param classid 班级号
	 * @param classify 按分类（学号/姓名/联系方式）查询
	 * @param keywords 关键字
	 * @param isFuzzy 是否模糊查询
	 * @return	学生列表
	 */
	public static List<Student> findByInput(String classid,String classify,String keywords,boolean isFuzzy){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Student> studentList = new ArrayList<Student>();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "";
			if("".equals(keywords)){
				sql = "select id,classid,name,gender,address,birthday,tel from student where classid=?";
			}else if(isFuzzy){
				sql = "select id,classid,name,gender,address,birthday,tel from student where classid=? and "+ classify +" like ? ";
				keywords = "%"+keywords+"%";
			}else{
				sql = "select id,classid,name,gender,address,birthday,tel from student where classid=? and "+ classify +" =?";				
			}
			ps = conn.prepareStatement(sql);
			ps.setString(1, classid);
			if(!"".equals(keywords)){
				ps.setString(2, keywords);				
			}
			rs = ps.executeQuery();
			while(rs.next()){
				Student student = new Student();
				studentList.add(student);
				student.setId(rs.getString("id"));
				student.setClassid(rs.getString("classid"));
				student.setName(rs.getString("name"));
				student.setGender(rs.getString("gender"));
				student.setAddress(rs.getString("address"));
				student.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("birthday")));
				student.setTel(rs.getString("tel"));
			}
			return studentList;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 获得所有本班的学生
	 * @param classid 班级号
	 * @return 学生列表
	 */
	public static List<Student> findAll(String classid){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Student> studentList = new ArrayList<Student>();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select id,classid,name,gender,address,birthday,tel from student where classid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, classid);
			rs = ps.executeQuery();
			while(rs.next()){
				Student student = new Student();
				studentList.add(student);
				student.setId(rs.getString("id"));
				student.setClassid(rs.getString("classid"));
				student.setName(rs.getString("name"));
				student.setGender(rs.getString("gender"));
				student.setAddress(rs.getString("address"));
				student.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("birthday")));
				student.setTel(rs.getString("tel"));
			}
			return studentList;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
