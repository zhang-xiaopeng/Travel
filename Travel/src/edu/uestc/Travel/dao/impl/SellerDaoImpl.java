package edu.uestc.Travel.dao.impl;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import edu.uestc.Travel.dao.SellerDao;
import edu.uestc.Travel.pojo.Seller;
import edu.uestc.Travel.util.JdbcUtils;

public class SellerDaoImpl implements SellerDao {
	private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource()) ;
	
	@Override
	public Seller findById(int sid) {
		String sql = "select * from tab_seller where sid = ?" ;
		return template.queryForObject(sql, new BeanPropertyRowMapper<Seller>(Seller.class),sid) ;
	}

}
