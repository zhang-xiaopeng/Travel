package edu.uestc.blog.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.uestc.blog.entity.Blogger;
import edu.uestc.blog.service.BloggerService;
import edu.uestc.blog.utils.MD5Util;

/*
 * 博主登录控制器
 */
@Controller
@RequestMapping("/blogger")
public class BloggerController {
	
	@Resource
	private BloggerService bloggerService ;
	
	@RequestMapping("/login")
	public String login(Blogger blogger,HttpServletRequest request) {
		//0.获取用户名和密码并进行MD5加密
		String username = blogger.getUsername() ;
		String password = blogger.getPassword() ;
		String pw = MD5Util.MD5(password, "java") ;
		
		//1.调用Shrio框架校验用户名和密码
		Subject subject = SecurityUtils.getSubject() ;
		UsernamePasswordToken token = new UsernamePasswordToken(username,pw) ;
		try {//如果用户名和密码匹配成功，则登录成功,重定向到主页
			//传递token给Shiro的realm
			subject.login(token);
			return "redirect:/admin/main.jsp" ;
 		}catch(Exception e) {
			//用户名和密码匹配失败的话，则抛出异常，登录失败
			e.printStackTrace();
			//将blogger以及错误信息传回前台
			request.setAttribute("blogger", blogger);
			request.setAttribute("errorInfo", "用户名或密码错误");
		}
		return "login" ;
	}
	
	/**
	 * 关于博主
	 */
	@RequestMapping("/aboutMe")
	public ModelAndView aboutMe() {
		ModelAndView mav = new ModelAndView() ;
		mav.addObject("blogger",bloggerService.find()) ;
		mav.addObject("mainPage","foreground/blogger/info.jsp") ;
		mav.addObject("pageTitle","关于博主_个人博客系统") ;
		mav.setViewName("index") ;
		return mav ;
	}
}
