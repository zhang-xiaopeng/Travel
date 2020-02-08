package edu.uestc.Travel.dao.impl;

import java.util.Date;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import edu.uestc.Travel.dao.FavoriteDao;
import edu.uestc.Travel.pojo.Favorite;
import edu.uestc.Travel.util.JdbcUtils;

public class FavoriteDaoImpl implements FavoriteDao {
	private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource()) ;
	
	@Override
	public Favorite findByRidAndUid(int rid, int uid) {
		Favorite favorite = null ;
		try {
			String sql = "select * from tab_favorite where rid = ? and uid = ?" ;
			favorite = template.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class),rid,uid) ;
		}catch(Exception e) {}
		return favorite ;
	}

	@Override
	public int findCountByRid(int rid) {
		String sql = "select count(*) from tab_favorite where rid = ?" ;
		return template.queryForObject(sql, Integer.class,rid) ;
	}

	@Override
	public void add(int rid, int uid) {
		String sql = "insert into tab_favorite values (?,?,?)" ;
		template.update(sql,rid,new Date(),uid) ;
	}

}
