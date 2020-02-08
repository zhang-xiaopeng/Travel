package edu.uestc.blog.service.impl;


import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import edu.uestc.blog.dao.BloggerDao;
import edu.uestc.blog.entity.Blogger;
import edu.uestc.blog.service.BloggerService;
import edu.uestc.blog.utils.Const;

@Service("bloggerService")
public class BloggerServiceImpl implements BloggerService{

	@Resource
	private BloggerDao bloggerDao ;
	
	@Override
	public Blogger login(String username) {
		
		return bloggerDao.getByUsername(username) ;
	}

	@Override
	public Integer update(Blogger blogger) {
		int result = bloggerDao.update(blogger) ;
		//更新后将数据放到session中
		SecurityUtils.getSubject().getSession().setAttribute(Const.CURRENT_USER, blogger);
		return result ;
	}

	@Override
	public Blogger find() {
		return bloggerDao.find() ;
	}

}
