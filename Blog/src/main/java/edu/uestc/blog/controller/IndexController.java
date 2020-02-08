package edu.uestc.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.uestc.blog.entity.Blog;
import edu.uestc.blog.service.BlogService;
import edu.uestc.blog.utils.PageUtil;
import edu.uestc.blog.utils.StringUtil;

/**
 * 首页
 * @author 张霄鹏
 *
 */
@Controller
public class IndexController {

	@Resource
	private BlogService blogService ;
	
	@RequestMapping("/index")
	public ModelAndView index(
			@RequestParam(value="page",required=false,defaultValue="1")String page,
			String typeId,
			String releaseDateStr,
			String title ,
			String keyword ,
			HttpServletRequest request) {
		

/*		if(StringUtil.isEmpty(page)) {
			page = "1" ;
		}
		if(StringUtil.isEmpty(rows)) {
			rows = "10" ;
		}*/
		//1.处理接收到的参数
		int currentPage = Integer.parseInt(page) ;
		int pageSize = 10 ;
		int start = (currentPage - 1) * pageSize ;
		
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("start", start) ;
		map.put("pageSize", pageSize) ;
		map.put("typeId", typeId) ;
		map.put("releaseDateStr", releaseDateStr) ;
		map.put("title", title) ;
		map.put("keyword", keyword) ;
		
		//2.调用Service层的方法查询博客
		List<Blog> list = blogService.list(map) ;
		
		//2.2分页
		StringBuffer param = new StringBuffer() ;
		if(!StringUtil.isEmpty(typeId)) {
			param.append("typeId" + typeId + "&") ;
		}		
		if(!StringUtil.isEmpty(releaseDateStr)) {
			param.append("releaseDateStr" + releaseDateStr + "&") ;
		}
		
		String pageCode = PageUtil.getPagination(request.getContextPath()+"/index.html", 
				blogService.getTotal(map), Integer.parseInt(page), 10, param.toString()) ;
		
		//3.将数据写到ModelAndView中
		ModelAndView mav = new ModelAndView() ;
		mav.addObject("title","个人博客系统") ;
		mav.addObject("blogList",list) ; //博客列表
		mav.addObject("pageCode", pageCode) ; //分页
		mav.addObject("mainPage","foreground/blog/list.jsp") ; //列表页面
		
		return mav ;
	}
}
