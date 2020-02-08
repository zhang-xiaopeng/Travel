package edu.uestc.Travel.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import edu.uestc.Travel.dao.RouteImgDao;
import edu.uestc.Travel.pojo.RouteImg;
import edu.uestc.Travel.util.JdbcUtils;

public class RouteImgDaoImpl implements RouteImgDao{

	private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource()) ;
	
	@Override
	public List<RouteImg> findByRid(int rid) {
		String sql = "select * from tab_route_img where rid = ?" ;
		return template.query(sql, new BeanPropertyRowMapper<RouteImg>(RouteImg.class),rid) ;
	}

}
