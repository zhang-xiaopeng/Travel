package edu.uestc.blog.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.uestc.blog.entity.Blog;
import edu.uestc.blog.entity.Comment;
import edu.uestc.blog.service.BlogService;
import edu.uestc.blog.service.CommentService;
import edu.uestc.blog.utils.ResponseUtil;
import net.sf.json.JSONObject;

/**
 * 前端用户提交评论
 * @author 张霄鹏
 *
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

	@Resource
	private CommentService commentService ;
	@Resource
	private BlogService blogService ;
	
	/**
	 * 提交评论
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/save")
	public String save(Comment comment,String imageCode,
			HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException {
		//1.判断验证码
		//从session中获取验证码
		String SRand = (String) session.getAttribute("sRand") ;
		
		JSONObject result = new JSONObject() ;
		int resultTotal = 0 ;
		if(!imageCode.equals(SRand)) {
			//将信息写到json中
			result.put("success",Boolean.FALSE) ;
			result.put("errorInfo", "验证码错误") ;
			
		}else {
			//2.评论保存
			String userIp = request.getRemoteAddr() ;
			comment.setUserIp(userIp) ;
			if(comment.getId()==null) {
				
				resultTotal = commentService.add(comment) ;
				//给对应博客的评论数+1
				Blog blog = blogService.findById(comment.getBlog().getId()) ;
				blog.setReplyHit(blog.getReplyHit() + 1);
				blogService.update(blog) ;
			}
		}
		
		if(resultTotal > 0) {
			result.put("success", Boolean.TRUE) ;
		}else {
			result.put("success", Boolean.FALSE) ;
		}
		ResponseUtil.write(response, result) ;
		
		return null ;
	}
	
}
