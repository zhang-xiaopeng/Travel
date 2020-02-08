package edu.uestc.blog.entity;

public class Blogger {
	/**主键*/
	private int id ;
	/**用户名*/
	private String username ;
	/**密码*/
	private String password ;
	/**个人简介*/
	private String proFile ;
	/**昵称*/
	private String nickname ;
	/**个性签名*/
	private String sign ;
	/**头像地址*/
	private String imageName ;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getProFile() {
		return proFile;
	}
	public void setProFile(String proFile) {
		this.proFile = proFile;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
}
