package edu.uestc.Travel.dao;

import edu.uestc.Travel.pojo.User;

public interface UserDao {
	//根据用户名查询用户信息
	public User findByUsername(String username) ;
	//保存用户
	public void save(User user) ;
	//根据激活码查询用户信息
	public User findByCode(String code);
	//更新用户信息
	public void updateStatus(User user);
	//根据用户名和密码查询用户
	public User finByUsernameAndPassword(String username,String password);
}
