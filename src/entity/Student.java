package entity;

import java.util.Date;
/**
 * 学生类/通讯录类
 * 
 * id		学号
 * classid	班级号
 * name		姓名
 * gender	性别
 * address	家庭地址
 * birthday	出生日期
 * tel		联系方式
 * 
 * @author Jasper
 *
 */
public class Student {
	private String id;
	private String classid;
	private String name;
	private String gender;
	private String address;
	private Date birthday;
	private String tel;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", classid=" + classid + ", name=" + name
				+ ", gender=" + gender + ", address=" + address + ", birthday="
				+ birthday + ", tel=" + tel + "]";
	}
	
	
}
