package entity;
/**
 * 班级类
 * 
 * id	班级号
 * name	班级名称
 * 
 * @author Jasper
 *
 */
public class Class {
	private String id;
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Class [id=" + id + ", name=" + name + "]";
	}
	
}
