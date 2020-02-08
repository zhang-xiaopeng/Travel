package edu.uestc.blog.service;

import java.util.List;
import java.util.Map;

import edu.uestc.blog.entity.Link;
/**
 * 链接表service
 * @author 张霄鹏
 *
 */
public interface LinkService {
	//查询所有博客类型列表
	public List<Link> list() ;
	//根据id查询一条友情链接
	public Link findById(Integer id) ;
	//根据不固定参数查询友情链接列表
	public List<Link> findByMap(Map<String,Object> paramMap) ;
	//根据不固定参数查询友情链接数量
	public Long getTotal(Map<String,Object> paramMap) ;
	
	//增加一条友情链接
	public Integer add(Link link) ;
	
	//修改一条友情链接
	public Integer update(Link link) ;
	
	//删除一条友情链接
	public Integer delete(Integer id) ;
}
