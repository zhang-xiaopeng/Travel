package edu.uestc.blog.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import edu.uestc.blog.dao.BlogDao;
import edu.uestc.blog.dao.CommentDao;
import edu.uestc.blog.entity.Blog;
import edu.uestc.blog.service.BlogService;

@Service("blogService")
public class BlogServiceImpl implements BlogService {

	@Resource
	private BlogDao blogDao ;
	@Resource
	private CommentDao commentDao ;
	
	@Override
	public List<Blog> countList() {
		return blogDao.countList() ;
	}

	@Override
	public List<Blog> list(Map<String, Object> paramMap) {
		return blogDao.list(paramMap) ;
	}

	@Override
	public Blog findById(Integer id) {
		return blogDao.findById(id) ;
	}

	@Override
	public Long getTotal(Map<String, Object> paramMap) {
		return blogDao.getTotal(paramMap) ;
	}

	@Override
	public Integer add(Blog blog) {
		return blogDao.add(blog) ;
	}

	@Override
	public Integer update(Blog blog) {
		return blogDao.update(blog) ;
	}

	@Override
	public Integer delete(Integer id) {
		//删除博客的时候，同时把其评论删掉
		commentDao.deleteByBlogId(id) ;
		return blogDao.delete(id) ;
	}

	@Override
	public Blog getLastBlog(Integer id) {
		return blogDao.getLastBlog(id) ;
	}

	@Override
	public Blog getNextBlog(Integer id) {
		return blogDao.getNextBlog(id) ;
	}


}
