package edu.uestc.blog.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import edu.uestc.blog.entity.Blog;
import edu.uestc.blog.entity.BlogType;
import edu.uestc.blog.entity.Blogger;
import edu.uestc.blog.entity.Link;
import edu.uestc.blog.service.BlogService;
import edu.uestc.blog.service.BlogTypeService;
import edu.uestc.blog.service.BloggerService;
import edu.uestc.blog.service.LinkService;
import edu.uestc.blog.utils.Const;
import edu.uestc.blog.utils.ResponseUtil;
import net.sf.json.JSONObject;

/**
 * 系统管理控制器
 * @author 张霄鹏
 *
 */
@Controller
@RequestMapping("/admin/system")
public class SystemAdminController {
	
	@Resource
	private BlogTypeService blogTypeService ;
	@Resource
	private BloggerService bloggerService ;
	@Resource
	private BlogService blogService ;
	@Resource
	private LinkService linkService ;
	
	/**
	 * 刷新系统缓存
	 * @throws IOException 
	 */
	@RequestMapping("/refreshSystem")
	public String refreshSystem(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//博客类别
		ServletContext application = RequestContextUtils.getWebApplicationContext(request).getServletContext() ;
		List<BlogType> list = blogTypeService.findAllBlogType() ;
		application.setAttribute(Const.BLOG_TYPE_COUNT_LIST, list);
		
		//博主信息
		Blogger blogger = bloggerService.find() ;
		blogger.setPassword(null) ;
		application.setAttribute(Const.BLOGGER, blogger) ;
		
		//按年月分类的博客
		List<Blog> blogCountList = blogService.countList() ;
		application.setAttribute(Const.BLOG_COUNT_LIST, blogCountList);
		
		//友情链接
		List<Link> linkList = linkService.list() ;
		application.setAttribute(Const.LINK_LIST, linkList) ;
		
		JSONObject result = new JSONObject() ;
		result.put("success", Boolean.valueOf(true)) ;
		ResponseUtil.write(response, result) ;
		
		return null ;
	}
	
}
