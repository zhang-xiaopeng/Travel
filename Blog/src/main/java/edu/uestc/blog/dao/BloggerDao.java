package edu.uestc.blog.dao;


import org.apache.ibatis.annotations.Param;

import edu.uestc.blog.entity.Blogger;

/**
 * 博主dao
 * @author 张霄鹏
 *
 */
public interface BloggerDao {
	//根据登录名查询博主
	public Blogger getByUsername(@Param("username") String username) ;
	//查询所有的博主
	public Blogger find() ;
	//更新博主
	public Integer update(Blogger blogger) ;
}
