package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Student;
import entity.User;
/**
 * 学生类的数据库操作类
 * @author Jasper
 *
 */
public class StudentDAO {
	/**
	 * 通过学号id找到学生
	 * @param id 学号
	 * @return 学生对象
	 */
	public static Student findById(String id){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select id,classid,name from student where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				Student student = new Student();
				student.setId(rs.getString("id"));
				student.setClassid(rs.getString("classid"));
				student.setName(rs.getString("name"));
				return student;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
