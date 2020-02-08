package edu.uestc.blog.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import edu.uestc.blog.dao.BlogTypeDao;
import edu.uestc.blog.entity.BlogType;
import edu.uestc.blog.service.BlogTypeService;

/**
 * 博客类型实现类
 * @author 张霄鹏
 *
 */
@Service("blogTypeService")
public class BlogTypeServiceImpl implements BlogTypeService {
	
	@Resource
	private BlogTypeDao blogTypeDao ;
	
	@Override
	public List<BlogType> findAllBlogType() {
		return blogTypeDao.findAllBlogType() ;
	}

	@Override
	public BlogType findById(Integer id) {
		return blogTypeDao.findById(id) ;
	}

	@Override
	public List<BlogType> findByMap(Map<String, Object> paramMap) {
		return blogTypeDao.findByMap(paramMap) ;
	}

	@Override
	public Long getTotal(Map<String, Object> paramMap) {
		return blogTypeDao.getTotal(paramMap) ;
	}

	@Override
	public Integer add(BlogType blogType) {
		return blogTypeDao.add(blogType) ;
	}

	@Override
	public Integer update(BlogType blogType) {
		return blogTypeDao.update(blogType) ;
	}

	@Override
	public Integer delete(Integer id) {
		return blogTypeDao.delete(id) ;
	}

}
