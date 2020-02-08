package edu.uestc.Travel.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import edu.uestc.Travel.dao.CategoryDao;
import edu.uestc.Travel.pojo.Category;
import edu.uestc.Travel.util.JdbcUtils;

public class CategoryDaoImpl implements CategoryDao {
	private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource()) ;
	@Override
	public List<Category> findAll() {
		String sql = "select * from tab_category" ;
		return template.query(sql, new BeanPropertyRowMapper<Category>(Category.class)) ;
	}
	@Override
	public String findById(int cid) {
		String sql = "select cname from tab_category where cid = ?" ;
		return template.queryForObject(sql, String.class,cid);
	}

}
