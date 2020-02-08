package edu.uestc.blog.service;

import java.util.List;
import java.util.Map;

import edu.uestc.blog.entity.Blog;

/**
 * 博客service
 * @author 张霄鹏
 *
 */
public interface BlogService {
	//无参数查询所有博客列表，供首页使用
	public List<Blog> countList() ;
	//根据不固定参数查询博客类型列表
	public List<Blog> list(Map<String,Object> paramMap) ;
	//根据id查询一条博客类型
	public Blog findById(Integer id) ;
	//根据不固定参数查询博客类型数量
	public Long getTotal(Map<String,Object> paramMap) ;
	
	//增加一条博客类型
	public Integer add(Blog blog) ;
	
	//修改一条博客类型
	public Integer update(Blog blog) ;
	
	//删除一条博客类型
	public Integer delete(Integer id) ;
	
	//上一篇
	public Blog getLastBlog(Integer id) ;
	
	//下一篇
	public Blog getNextBlog(Integer id) ;
}
