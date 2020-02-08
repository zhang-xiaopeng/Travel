package edu.uestc.Travel.service.impl;

import edu.uestc.Travel.dao.UserDao;
import edu.uestc.Travel.dao.impl.UserDaoImpl;
import edu.uestc.Travel.pojo.User;
import edu.uestc.Travel.service.UserService;
import edu.uestc.Travel.util.MailUtils;
import edu.uestc.Travel.util.UuidUtil;

public class UserServiceImpl implements UserService{
	private UserDao dao = new UserDaoImpl() ;
	/*
	 * 用户注册的方法
	 */
	@Override
	public boolean regist(User user) {
		//1.根据用户名查询用户对象
		User u = dao.findByUsername(user.getUsername()) ;
		if(u != null) {
			//用户名存在，注册失败
			return false ;
		}

		//2.保存用户信息
		//2.1设置激活码
		user.setCode(UuidUtil.getUuid()) ;
		//2.2设置激活状态
		user.setStatus("N") ;
		
		dao.save(user) ;

		
		//3.邮件发送
		String content="<a href='http://localhost:8080/Travel/user/active?code="+ user.getCode()+"'>点击激活【旅游网】</a>" ;
		MailUtils.sendMail(user.getEmail(), content, "激活邮件") ;
		return true ;
	}
	@Override
	public boolean active(String code) {
		//1.根据激活码查询用户
		User user = dao.findByCode(code) ;
		if(code != null) {
			//2.调用dao的修改激活状态的方法
			dao.updateStatus(user) ;
			return true ;
		}else {
			return false;
		}
	}
	@Override
	public User login(User user) {
		return dao.finByUsernameAndPassword(user.getUsername(),user.getPassword()) ;
	}

}
