package edu.uestc.blog.dao;

import java.util.List;
import java.util.Map;

import edu.uestc.blog.entity.Comment;
/**
 * 评论Dao层
 * @author 张霄鹏
 *
 */
public interface CommentDao {
	//查询根据条件评论
	public List<Comment> list(Map<String,Object> map) ;
	//根据条件查询评论数量
	public Long getTotal(Map<String,Object> map) ;
	
	//添加一条评论
	public Integer add(Comment comment) ;
	
	//后台管理员审核一条评论
	public Integer update(Comment comment) ;
	
	//删除评论
	public Integer delete(Integer id) ;
	//根据博客id删除评论
	public Integer deleteByBlogId(Integer blogId) ;
}
