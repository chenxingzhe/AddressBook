package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;
/**
 * 用户类的数据库操作类
 * @author Jasper
 *
 */
public class UserDAO {
	/**
	 * 添加一条用户信息
	 * @param user 一个用户对象
	 * @return 是否成功添加
	 */
	public static boolean add(User user){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into user(username,password,id,classid,name,role) values(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getId());
			ps.setString(4, user.getClassid());
			ps.setString(5, user.getName());
			ps.setString(6, user.getRole());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 通过用户名删除一条用户信息
	 * @param username 用户名
	 * @return 是否删除成功
	 */
	public static boolean deleteByUsername(String username){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from user where username=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 修改一条用户信息的密码
	 * @param username 用户名
	 * @param newPassword 新密码
	 * @return 是否修改成功
	 */
	public static boolean updatePassword(String username,String newPassword){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "update user set password=? where username=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, newPassword);
			ps.setString(2, username);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 通过用户名获得用户对象
	 * @param username 用户名
	 * @return	用户对象
	 */
	public static User findByUsername(String username){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select username,password,id,classid,name,role from user where username=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if(rs.next()){
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setId(rs.getString("id"));
				user.setClassid(rs.getString("classid"));
				user.setName(rs.getString("name"));
				user.setRole(rs.getString("role"));
				return user;
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
