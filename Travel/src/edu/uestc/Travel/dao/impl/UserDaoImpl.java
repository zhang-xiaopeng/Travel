package edu.uestc.Travel.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import edu.uestc.Travel.dao.UserDao;
import edu.uestc.Travel.pojo.User;
import edu.uestc.Travel.util.JdbcUtils;

public class UserDaoImpl implements UserDao {

	private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource()) ;
	@Override
	public User findByUsername(String username) {
		User user = null ;
		try {
			//1.定义sql语句
			String sql = "select * from tab_user where username = ?" ;
			//2.执行sql
			user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),username) ;
		}catch(Exception e) {}
		
		return user;
	}

	@Override
	public void save(User user) {
		String sql = "insert into tab_user (username,password,name,birthday,sex,telephone,email,status,code) "
				+ "values (?,?,?,?,?,?,?,?,?)" ;
		template.update(sql,user.getUsername(),user.getPassword(),user.getName(),user.getBirthday(),
				user.getSex(),user.getTelephone(),user.getEmail(),user.getStatus(),user.getCode()) ;
	}

	@Override
	public User findByCode(String code) {
		User user = null ;
		try {
			String sql = "select * from tab_user where code = ?" ;
			user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),code) ;
		}catch(Exception e) {}
		
		return user ;
	}

	@Override
	public void updateStatus(User user) {
		String sql = "update tab_user set status = 'Y' where uid =? " ;
		template.update(sql,user.getUid()) ;
		
	}

	@Override
	public User finByUsernameAndPassword(String username,String password) {
		User user = null ;
		
		try {
			String sql = "select * from tab_user where username = ? and password = ? " ;
			user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),username,password) ;
		}catch(Exception e) {}
		
		return user ;
	}

}
