package edu.uestc.blog.service;


import edu.uestc.blog.entity.Blogger;

/**
 * 博主service
 * @author 张霄鹏
 *
 */
public interface BloggerService {
	//博主登录
	public Blogger login(String username) ;
	//查询所有的博主
	public Blogger find() ;
	//更新博主信息
	public Integer update(Blogger blogger) ;
}
