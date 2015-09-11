package entity;
/**
 * 用户类
 * 
 * username	用户名
 * password	密码
 * id		学号
 * classid	班级号
 * name		姓名
 * role		权限(admin,student)
 * 
 * @author Jasper
 *
 */
public class User {
	private String username;
	private String password;
	private String id;
	private String classid;
	private String name;
	private String role;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClassid() {
		return classid;
	}
	public void setClassid(String classid) {
		this.classid = classid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", id=" + id + ", classid=" + classid + ", name=" + name
				+ ", role=" + role + "]";
	}
}
