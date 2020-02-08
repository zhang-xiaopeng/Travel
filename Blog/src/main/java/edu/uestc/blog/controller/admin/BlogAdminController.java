package edu.uestc.blog.controller.admin;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.uestc.blog.entity.Blog;
import edu.uestc.blog.lucene.BlogIndex;
import edu.uestc.blog.service.BlogService;
import edu.uestc.blog.utils.DateJsonValueProcessor;
import edu.uestc.blog.utils.ResponseUtil;
import edu.uestc.blog.utils.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 博客控制器
 * @author 张霄鹏
 *
 */
@Controller
@RequestMapping("/admin/blog")
public class BlogAdminController {
	
	@Resource
	private BlogService blogService ;
	
	private BlogIndex blogIndex = new BlogIndex() ;
	
	/**
	 * 保存博客
	 * @param blog
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/save")
	public String save(Blog blog,HttpServletResponse response) throws IOException {
		//1.调用service层的方法进行保存或修改
		int resultTotal = 0 ;
		if(blog.getId() == null) {
			resultTotal = blogService.add(blog) ;
			blogIndex.addIndex(blog);  //保存博客时增加到博客索引中
		}else {
			resultTotal = blogService.update(blog) ;
			blogIndex.updateIndex(blog) ;//修改博客时增加到博客索引中
		}
		//2.将数据写到json中
		JSONObject result = new JSONObject() ;
		if(resultTotal > 0) {
			result.put("success", Boolean.valueOf(true)) ;
		}else {
			result.put("success", Boolean.valueOf(false)) ;
		}
		ResponseUtil.write(response, result) ;
		
		return null ;
	}
	/**
	 * 查询博客信息列表
	 * 可以分页查询
	 * 可以根据blog中的其它字段查询(标题，关键字)
	 * @throws IOException 
	 */
	@RequestMapping("/list")
	public String list(String page,String rows,Blog blog,String blogTypeId,HttpServletResponse response) throws IOException {
		//1.处理接收到的参数
		int currentPage = Integer.parseInt(page) ;
		int pageSize = Integer.parseInt(rows) ;
		int start = (currentPage - 1) * pageSize ;
		
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("start", start) ;
		map.put("pageSize", pageSize) ;
		map.put("title", StringUtil.formatLike(blog.getTitle())) ;
		map.put("keyword", StringUtil.formatLike(blog.getKeyword())) ;
		map.put("typeId", blogTypeId) ;
		
		//2.查询博客类型列表
		List<Blog> blogList = blogService.list(map) ;
		//3.查询总共有多少条数据
		Long total = blogService.getTotal(map) ;
		
		//4.将数据写到response中
		JSONObject result = new JSONObject() ;
		//将日期形式显示为字符串
		JsonConfig config = new JsonConfig() ;
		config.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor("yyyy-MM-dd")) ;
		//将Blog类型转换为JSONArray格式
		JSONArray jsonArray = JSONArray.fromObject(blogList,config) ;
		
		result.put("rows", jsonArray) ;
		result.put("total", total) ;//注意rows和total必须用这两个单词，否则显示不出来
		ResponseUtil.write(response, result) ;

		return null ;
	}
	
	/**
	 * 根据主键查询Blog
	 * @param id
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/findById")
	public String findById(String id,HttpServletResponse response) throws IOException {
		//1.调用Service层中的方法查询到Blog
		Blog blog = blogService.findById(Integer.parseInt(id)) ;
		//2.将数据写到Json中
/*		JSONObject result = new JSONObject() ;
		JSONArray jsonArray = JSONArray.fromObject(blog) ;
		result.put("blog", jsonArray) ;*/
		JSONObject result = JSONObject.fromObject(blog) ;
		ResponseUtil.write(response, result) ;
		
		return null ;
	}
	
	/**
	 * 删除博客
	 * @param id
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/delete")
	public String delete(String ids,HttpServletResponse response) throws IOException {
		//1.处理获取到的数据
		String idsStr[] = ids.split(",") ;
		//2.调用Service层的方法删除数据
		for(int i = 0 ; i < idsStr.length ; i ++) {
			blogService.delete(Integer.parseInt(idsStr[i])) ;
			blogIndex.deleteIndex(idsStr[i]) ;//删除博客时删除博客索引中的博客
		}
		//3.将数据写到json中
		JSONObject result = new JSONObject() ;
		result.put("success", Boolean.valueOf(true)) ;
		ResponseUtil.write(response, result);
		
		return null ;
	}
}
