package util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entity.Info;
/**
 * 通讯录信息类的数据库操作类
 * @author Jasper
 *
 */
public class InfoDAO{
	/**
	 * 添加一条通讯录信息
	 * @param info 一条信息对象
	 * @return 是否成功添加
	 */
	public static boolean add(Info info){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into info(id,classid,name,gender,province,address,birthday,tel) values(?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, info.getId());
			ps.setString(2, info.getClassid());
			ps.setString(3, info.getName());
			ps.setString(4, info.getGender());
			ps.setString(5, info.getProvince());
			ps.setString(6, info.getAddress());
			ps.setString(7,new SimpleDateFormat("yyyy-MM-dd").format(info.getBirthday()));
			ps.setString(8, info.getTel());
			
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
	 * 通过学号id删除一条通讯录信息
	 * @param id 学号
	 * @return 是否成功删除
	 */
	public static boolean deleteById(String id){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from info where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
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
	 * 通过学号更新一条通讯录信息
	 * @param id 学号
	 * @param info 通讯录信息
	 * @return 是否成功修改
	 */
	public static boolean updateById(String id,Info info){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "update info set classid=?,name=?,gender=?,province=?,address=?,birthday=?,tel=? where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, info.getClassid());
			ps.setString(2, info.getName());
			ps.setString(3, info.getGender());
			ps.setString(4, info.getProvince());
			ps.setString(5, info.getAddress());
			ps.setString(6,new SimpleDateFormat("yyyy-MM-dd").format(info.getBirthday()));
			ps.setString(7, info.getTel());
			ps.setString(8, id);
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
	 * 通过学号获得一个通讯录信息对象
	 * @param id 学号
	 * @return 通讯录信息对象
	 */
	public static Info findById(String id){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select id,classid,name,gender,province,address,birthday,tel from info where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				Info info = new Info();
				info.setId(rs.getString("id"));
				info.setClassid(rs.getString("classid"));
				info.setName(rs.getString("name"));
				info.setGender(rs.getString("gender"));
				info.setProvince(rs.getString("province"));
				info.setAddress(rs.getString("address"));
				info.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("birthday")));
				info.setTel(rs.getString("tel"));
				return info;
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
			String sql = "select classid from info where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				return rs.getString("classid");
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
	/**
	 * 通过姓名获得一个通讯录信息对象
	 * @param name 姓名
	 * @return 通讯录信息对象
	 */
	public static Info findByName(String name){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select id,classid,name,gender,province,address,birthday,tel from info where name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if(rs.next()){
				Info info = new Info();
				info.setId(rs.getString("id"));
				info.setClassid(rs.getString("classid"));
				info.setName(rs.getString("name"));
				info.setGender(rs.getString("gender"));
				info.setProvince(rs.getString("province"));
				info.setAddress(rs.getString("address"));
				info.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("birthday")));
				info.setTel(rs.getString("tel"));
				return info;
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
	/**
	 * 获得本班的，且符合输入参数要求的所有通讯录信息
	 * @param classid 班级号
	 * @param classify 按分类（学号/姓名/联系方式）查询
	 * @param keywords 关键字
	 * @param isFuzzy 是否模糊查询
	 * @return	通讯录信息列表
	 */
	public static List<Info> findByInput(String classid,String classify,String keywords,boolean isFuzzy){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Info> infoList = new ArrayList<Info>();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "";
			if("".equals(keywords)){
				sql = "select id,classid,name,gender,province,address,birthday,tel from info where classid=?";
			}else if(isFuzzy){
				sql = "select id,classid,name,gender,province,address,birthday,tel from info where classid=? and "+ classify +" like ? ";
				keywords = "%"+keywords+"%";
			}else{
				sql = "select id,classid,name,gender,province,address,birthday,tel from info where classid=? and "+ classify +" =?";				
			}
			ps = conn.prepareStatement(sql);
			ps.setString(1, classid);
			if(!"".equals(keywords)){
				ps.setString(2, keywords);				
			}
			rs = ps.executeQuery();
			while(rs.next()){
				Info info = new Info();
				infoList.add(info);
				info.setId(rs.getString("id"));
				info.setClassid(rs.getString("classid"));
				info.setName(rs.getString("name"));
				info.setGender(rs.getString("gender"));
				info.setProvince(rs.getString("province"));
				info.setAddress(rs.getString("address"));
				info.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("birthday")));
				info.setTel(rs.getString("tel"));
			}
			return infoList;
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
	}
	/**
	 * 获得符合输入参数要求的所有通讯录信息
	 * @param classify 按分类（班级号/学号/姓名/联系方式）查询
	 * @param keywords 关键字
	 * @param isFuzzy 是否模糊查询
	 * @return	通讯录信息列表
	 */
	public static List<Info> findByInput(String classify,String keywords,boolean isFuzzy){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Info> infoList = new ArrayList<Info>();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "";
			if("".equals(keywords)){
				sql = "select id,classid,name,gender,province,address,birthday,tel from info";
			}else if(isFuzzy){
				sql = "select id,classid,name,gender,province,address,birthday,tel from info where "+ classify +" like ? ";
				keywords = "%"+keywords+"%";
			}else{
				sql = "select id,classid,name,gender,province,address,birthday,tel from info where "+ classify +" =?";				
			}
			
			ps = conn.prepareStatement(sql);
			if(!"".equals(keywords)){
				ps.setString(1, keywords);				
			}
			rs = ps.executeQuery();
			while(rs.next()){
				Info info = new Info();
				infoList.add(info);
				info.setId(rs.getString("id"));
				info.setClassid(rs.getString("classid"));
				info.setName(rs.getString("name"));
				info.setGender(rs.getString("gender"));
				info.setProvince(rs.getString("province"));
				info.setAddress(rs.getString("address"));
				info.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("birthday")));
				info.setTel(rs.getString("tel"));
			}
			return infoList;
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
	}
	
	/**
	 * 获得所有本班的通讯录信息
	 * @param classid 班级号
	 * @return 通讯录信息列表
	 */
	public static List<Info> findAllByClassid(String classid){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Info> infoList = new ArrayList<Info>();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select id,classid,name,gender,province,address,birthday,tel from info where classid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, classid);
			rs = ps.executeQuery();
			while(rs.next()){
				Info info = new Info();
				infoList.add(info);
				info.setId(rs.getString("id"));
				info.setClassid(rs.getString("classid"));
				info.setName(rs.getString("name"));
				info.setGender(rs.getString("gender"));
				info.setProvince(rs.getString("province"));
				info.setAddress(rs.getString("address"));
				info.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("birthday")));
				info.setTel(rs.getString("tel"));
			}
			return infoList;
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
	}
	/**
	 * 获得所有的通讯录信息
	 * @return 通讯录信息列表
	 */
	public static List<Info> findAll(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Info> infoList = new ArrayList<Info>();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select id,classid,name,gender,province,address,birthday,tel from info";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Info info = new Info();
				infoList.add(info);
				info.setId(rs.getString("id"));
				info.setClassid(rs.getString("classid"));
				info.setName(rs.getString("name"));
				info.setGender(rs.getString("gender"));
				info.setProvince(rs.getString("province"));
				info.setAddress(rs.getString("address"));
				info.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("birthday")));
				info.setTel(rs.getString("tel"));
			}
			return infoList;
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
	}
	
	/**
	 * 获得所给列表中，学生的籍贯在各省分布的人数
	 * @param infoList 一个通讯录信息列表
	 * @return 一个长为34的int数组
	 */
	public static int[] getProvinceDistribution(List<Info> infoList){
		String[] provinces = {"北京","天津","上海","重庆","河北","河南","云南","辽宁","黑龙江","湖南","安徽","山东","新疆","江苏","浙江","江西","湖北","广西","甘肃","山西","内蒙古",
				"陕西","吉林","福建","贵州","广东","青海","西藏","四川","宁夏","海南","台湾","香港","澳门"};
		int []p = new int[provinces.length];
		for (int i = 0; i < infoList.size(); i++) {
			Info info = infoList.get(i);
			String province = info.getProvince();
			
			int index = -1;
			for (int j = 0; j < provinces.length; j++) {
				if(provinces[j].equals(province)){
					index = j;
					break;
				}
			}
			p[index]++;
		}
		return  p;
	}
}
