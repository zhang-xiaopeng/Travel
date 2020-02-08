package edu.uestc.blog.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import edu.uestc.blog.dao.LinkDao;
import edu.uestc.blog.entity.Link;
import edu.uestc.blog.service.LinkService;

@Service("linkService")
public class LinkServiceImpl implements LinkService {

	@Resource
	private LinkDao linkDao ;
	
	@Override
	public Link findById(Integer id) {
		return linkDao.findById(id) ;
	}

	@Override
	public List<Link> findByMap(Map<String, Object> paramMap) {
		return linkDao.findByMap(paramMap) ;
	}

	@Override
	public Long getTotal(Map<String, Object> paramMap) {
		return linkDao.getTotal(paramMap) ;
	}

	@Override
	public Integer add(Link link) {
		return linkDao.add(link) ;
	}

	@Override
	public Integer update(Link link) {
		return linkDao.update(link) ;
	}

	@Override
	public Integer delete(Integer id) {
		return linkDao.delete(id) ;
	}

	@Override
	public List<Link> list() {
		return linkDao.list() ;
	}

}
