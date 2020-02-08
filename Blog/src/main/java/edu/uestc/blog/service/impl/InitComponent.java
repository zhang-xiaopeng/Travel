package edu.uestc.blog.service.impl;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import edu.uestc.blog.entity.Blog;
import edu.uestc.blog.entity.BlogType;
import edu.uestc.blog.entity.Blogger;
import edu.uestc.blog.entity.Link;
import edu.uestc.blog.service.BlogService;
import edu.uestc.blog.service.BlogTypeService;
import edu.uestc.blog.service.BloggerService;
import edu.uestc.blog.service.LinkService;
import edu.uestc.blog.utils.Const;

@Component
public class InitComponent implements ServletContextListener,ApplicationContextAware{

	private static ApplicationContext applicationContext ;
	
	@SuppressWarnings("static-access")
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext ;
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext() ;
		//博客类别
		BlogTypeService blogTypeService = (BlogTypeService) applicationContext.getBean("blogTypeService") ;
		List<BlogType> blogTypeList = blogTypeService.findAllBlogType() ;
		application.setAttribute(Const.BLOG_TYPE_COUNT_LIST, blogTypeList) ;
		
		//博主信息
		BloggerService bloggerService = (BloggerService) applicationContext.getBean("bloggerService") ;
		Blogger blogger = bloggerService.find() ;
		blogger.setPassword(null) ;
		application.setAttribute(Const.BLOGGER, blogger) ;
		
		//按年月分类的博客
		BlogService blogService = (BlogService) applicationContext.getBean("blogService") ;
		List<Blog> blogCountList = blogService.countList() ;
		application.setAttribute(Const.BLOG_COUNT_LIST, blogCountList);
		
		//友情链接
		LinkService linkService =  (LinkService) applicationContext.getBean("linkService") ;
		List<Link> linkList = linkService.list() ;
		application.setAttribute(Const.LINK_LIST, linkList) ;
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
