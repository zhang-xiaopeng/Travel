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

import edu.uestc.blog.entity.Comment;
import edu.uestc.blog.service.CommentService;
import edu.uestc.blog.utils.DateJsonValueProcessor;
import edu.uestc.blog.utils.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 评论管理控制器
 * @author 张霄鹏
 *
 */
@Controller
@RequestMapping("/admin/comment")
public class CommentAdminController {

	@Resource
	private CommentService commentService ;
	
	/**
	 * 查询评论列表
	 * @param page
	 * @param rows
	 * @param state
	 * @param blogId
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/list")
	public String list(String page,String rows,String state,String blogId,HttpServletResponse response) throws IOException {
		//1.处理接收到的参数,并放到map中
		int currentPage = Integer.parseInt(page) ;
		int pageSize = Integer.parseInt(rows) ;

		int start = (currentPage - 1) * pageSize ;
		
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("blogId", blogId) ;
		map.put("state", state) ;
		map.put("start", start) ;
		map.put("pageSize", pageSize) ;
		
		//2.调用service层的方法查询
		List<Comment> list = commentService.list(map) ;
		Long total = commentService.getTotal(map) ;
		
		//3.将数据写到JSON中
		JSONObject result = new JSONObject() ;
		//3.1处理日期
		JsonConfig config = new JsonConfig() ;
		config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		//3.2处理List列表
		JSONArray jsonArray = JSONArray.fromObject(list,config) ;
		//3.3将数据放到JSON中
		result.put("rows", jsonArray) ;
		result.put("total", total) ;
		ResponseUtil.write(response, result) ;
		return null ;
	}
	
	@RequestMapping("/delete")
	public String delete(String ids,HttpServletResponse response) throws IOException {
		//1.处理接收到的参数
		String idsStr[] = ids.split(",") ;
		//2，调用service层的方法执行删除
		for(int i = 0 ; i < idsStr.length ; i ++) {
			commentService.delete(Integer.parseInt(idsStr[i])) ;
		}
		//3.将数据写到json中
		JSONObject result = new JSONObject() ;
		result.put("success", Boolean.valueOf(true)) ;
		ResponseUtil.write(response, result) ;
		
		return null ;
	}
	
	/**
	 * 审核评论
	 * @param ids
	 * @param state
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/review")
	public String review(String ids,String state,HttpServletResponse response) throws IOException {
		//1.处理接收到的参数
		String idsStr[] = ids.split(",") ;
		//2，调用service层的方法执行删除
		for(int i = 0 ; i < idsStr.length ; i ++) {
			Comment comment = new Comment() ;
			comment.setId(Integer.parseInt(idsStr[i]));
			comment.setState(Integer.parseInt(state));
			
			commentService.update(comment) ;
		}
		//3.将数据写到json中
		JSONObject result = new JSONObject() ;
		result.put("success", Boolean.valueOf(true)) ;
		ResponseUtil.write(response, result) ;
		
		return null ;
	}
}
