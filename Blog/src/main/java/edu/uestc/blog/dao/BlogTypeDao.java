package edu.uestc.blog.dao;

import java.util.List;
import java.util.Map;

import edu.uestc.blog.entity.BlogType;

/**
 * 博客类型dao
 * @author 张霄鹏
 *
 */
public interface BlogTypeDao {
	//查询所有博客类型列表
	public List<BlogType> findAllBlogType() ;
	//根据id查询一条博客类型
	public BlogType findById(Integer id) ;
	//根据不固定参数查询博客类型列表
	public List<BlogType> findByMap(Map<String,Object> paramMap) ;
	//根据不固定参数查询博客类型数量
	public Long getTotal(Map<String,Object> paramMap) ;
	
	//增加一条博客类型
	public Integer add(BlogType blogType) ;
	
	//修改一条博客类型
	public Integer update(BlogType blogType) ;
	
	//删除一条博客类型
	public Integer delete(Integer id) ;
}
