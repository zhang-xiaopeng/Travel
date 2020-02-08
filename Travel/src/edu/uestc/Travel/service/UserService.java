package edu.uestc.Travel.service;

import edu.uestc.Travel.pojo.User;

public interface UserService {
	//用户注册方法
	public boolean regist(User user);
	//邮件激活方法
	public boolean active(String code);
	//登录方法
	public User login(User user);

}
