package edu.uestc.blog.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import edu.uestc.blog.dao.CommentDao;
import edu.uestc.blog.entity.Comment;
import edu.uestc.blog.service.CommentService;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
	
	@Resource
	private CommentDao commentDao ;

	@Override
	public List<Comment> list(Map<String, Object> map) {
		return commentDao.list(map) ;
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return commentDao.getTotal(map) ;
	}

	@Override
	public Integer add(Comment comment) {
		return commentDao.add(comment) ;
	}

	@Override
	public Integer update(Comment comment) {
		return commentDao.update(comment) ;
	}

	@Override
	public Integer delete(Integer id) {
		return commentDao.delete(id) ;
	}

}
