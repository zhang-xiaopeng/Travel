package edu.uestc.blog.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.uestc.blog.entity.Blog;
import edu.uestc.blog.lucene.BlogIndex;
import edu.uestc.blog.service.BlogService;
import edu.uestc.blog.service.CommentService;
import edu.uestc.blog.utils.StringUtil;

/**
 * 博客控制器
 * @author 张霄鹏
 *
 */
@Controller
@RequestMapping("/blog")
public class BlogController {
	
	@Resource
	private BlogService blogService ;
	@Resource
	private CommentService commentService ;
	
	private BlogIndex blogIndex = new BlogIndex() ;

	/**
	 * 博客详细信息
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/articles/{id}")
	public ModelAndView details(@PathVariable("id")Integer id,HttpServletRequest request) {
		//1.根据主键查询博客信息并将点击量加1,然后更新博客
		Blog blog = blogService.findById(id) ;
		//阅读人数加1
		blog.setClickHit(blog.getClickHit() + 1);
		blogService.update(blog) ;
		
		
		//2.将博客信息放到mav中
		ModelAndView mav = new ModelAndView() ;
		mav.addObject("blog", blog) ;
		//3.添加主界面与界面题目
		mav.addObject("mainPage","foreground/blog/view.jsp") ;
		mav.addObject("pageTitle", blog.getTitle()+"_个人博客系统") ;
		mav.setViewName("index");
		
		//4.添加上一篇下一篇
		mav.addObject("pageCode",getUpAndDownPageCode(blogService.getLastBlog(id), blogService.getNextBlog(id), request.getServletContext().getContextPath())) ;
		
		//5.查询评论
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("blogId", blog.getId()) ;
		map.put("state", 1) ;
		
		mav.addObject("commentList", commentService.list(map)) ;
		
		//6.处理关键字
		String keyword = blog.getKeyword() ;
		if(StringUtil.isEmpty(keyword)) {
			mav.addObject("kewwords",null) ;
		}else {
			String arr[] = keyword.split(" ") ;
			mav.addObject("keywords",StringUtil.filterWhite(Arrays.asList(arr))) ;
		}
		
		return mav ;
	}
	
	/**
	 * 上一篇和下一篇
	 * @param lastBlog
	 * @param nextBlog
	 * @param projectContext 路径
	 * @return
	 */
	public String getUpAndDownPageCode(Blog lastBlog,Blog nextBlog,String projectContext) {
		StringBuffer pageCode = new StringBuffer() ;
		
		if(lastBlog == null || lastBlog.getId() == null) {
			pageCode.append("<p>上一篇，没有了！</p>") ;
		}else {
			pageCode.append("<p>上一篇：<a href='" + projectContext + "/blog/articles/"+ lastBlog.getId() +".html'>"+lastBlog.getTitle()+"</a></p>") ;
		}
		if(nextBlog == null || nextBlog.getId() == null) {
			pageCode.append("<p>下一篇，没有了！</p>") ;
		}else {
			pageCode.append("<p>下一篇：<a href='" + projectContext + "/blog/articles/"+ nextBlog.getId() +".html'>"+nextBlog.getTitle()+"</a></p>") ;
		}
		
		return pageCode.toString()  ;
	}
	
	/**
	 * 根据关键字查询
	 * @param q  查询的关键字
	 * @param page  当前页
	 * @param request
	 * @return
	 * @throws InvalidTokenOffsetsException 
	 * @throws ParseException 
	 * @throws IOException 
	 */
	@RequestMapping("/q")
	public ModelAndView q(@RequestParam(value="q",required=false,defaultValue="")String q,
						  @RequestParam(value="page",required=false,defaultValue="1")String page,
						  HttpServletRequest request) throws IOException, ParseException, InvalidTokenOffsetsException {
		
		ModelAndView mav = new ModelAndView() ;
		mav.addObject("mainPage", "foreground/blog/result.jsp") ;
		
		//在Lucene中查询
		List<Blog> blogList = blogIndex.searchBlog(q.trim()) ;
		
		int beginIndex = (Integer.parseInt(page)-1)*10 ;
		int toIndex = 0 ;
		if(blogList.size() >= Integer.parseInt(page) * 10) {
			toIndex = Integer.parseInt(page) * 10 ;
		}else {
			toIndex = blogList.size() ;
		}
		mav.addObject("blogList",blogList.subList(beginIndex, toIndex)) ;//将查询结果放到mav中
		
		mav.addObject("q",q) ;//将关键字放到mav中
		mav.addObject("resultTotal", blogList.size()) ; //搜索数据条数
		mav.addObject("pageTitle", "搜索关键字'"+ q +"'的结果页面_个人博客") ; //页面标题
		mav.addObject("pageCode",getUpAndDownPageCode(Integer.parseInt(page),10,blogList.size(),q,request.getServletContext().getContextPath())) ;//分页显示
		mav.setViewName("index");
		
		return mav ;
	}
	
	/**
	 * 查询结果翻页
	 * @return
	 */
	@RequestMapping("")
	public String getUpAndDownPageCode(int currentPage,int pageSize,int totalCount,String q,String projectContext) {
		//1.计算有多少页
		int totalPage = (totalCount % pageSize == 0)?(totalCount / pageSize):(totalCount / pageSize + 1) ;
		if(totalPage == 0) {
			return "未查询到数据" ;
		}
		//2.拼接字符串
		StringBuffer pageCode = new StringBuffer() ;
		pageCode.append("<nav>") ;
		pageCode.append("<ul class='pager' >") ;

		//2.1拼上上一页
		if(currentPage > 1) {  //当前页不是第一页
			pageCode.append("<li><a href='"+ projectContext +"/blog/q.html?page="+ (currentPage-1) +"&q="+ q +"'>上一页</a></li>") ;
		}else {  //当前页是第一页
			pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>") ;
		}
		
		//2.2拼上下一页
		if(currentPage < totalPage) {
			//当前页不是尾页
			pageCode.append("<li><a href='"+ projectContext +"/blog/q.html?page="+ (currentPage+1) +"&q="+ q +"'>下一页</a></li>") ;
		}else {
			//当前页是尾页
			pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>") ;
		}
		
		pageCode.append("</ul>") ;
		pageCode.append("</nav>") ;
		
		return pageCode.toString() ;
	}
}
