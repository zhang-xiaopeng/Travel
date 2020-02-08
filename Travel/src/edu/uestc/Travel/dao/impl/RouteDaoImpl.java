package edu.uestc.Travel.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import edu.uestc.Travel.dao.RouteDao;
import edu.uestc.Travel.pojo.Route;
import edu.uestc.Travel.util.JdbcUtils;

public class RouteDaoImpl implements RouteDao {

	private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource()) ;
	@Override
	public int findTotalCount(int cid,String rname) {
		//String sql = "select count(*) from tab_route where cid = ?" ;
		//1.定义SQL模板
		String sql = "select count(*) from tab_route where 1=1 " ;
		StringBuilder sb = new StringBuilder(sql) ;
		//定义参数集合
		List<Object> params = new ArrayList<Object>() ;
		//2.判断参数是否有值
		if(cid != 0) {
			sb.append(" and cid = ? ") ;
			params.add(cid) ;  //添加问号对应的值
		}
		if(rname != null && rname.length() > 0) {
			sb.append(" and rname like ? ") ;
			params.add("%" + rname + "%") ;
		}
		
		sql = sb.toString() ;
		
		return template.queryForObject(sql, Integer.class,params.toArray()) ;
	}

	@Override
	public List<Route> findByPage(int cid, int start, int pageSize,String rname) {
		//String sql = "select * from tab_route where cid = ? limit ?,?" ;
		//1.定义SQL模板
		String sql = "select * from tab_route where 1=1 " ;
		StringBuilder sb = new StringBuilder(sql) ;
		//定义参数集合
		List<Object> params = new ArrayList<Object>() ;
		//2.判断参数是否有值
		if(cid != 0) {
			sb.append(" and cid = ? ") ;
			params.add(cid) ;  //添加问号对应的值
		}
		if(rname != null && rname.length() > 0) {
			sb.append(" and rname like ? ") ;
			params.add("%" + rname + "%") ;
		}
		
		sb.append(" limit ?,? ") ;
		params.add(start) ;
		params.add(pageSize) ;
		
		sql = sb.toString() ;
		return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class),params.toArray()) ;
	}

	@Override
	public Route findById(int rid) {
		String sql = "select * from tab_route where rid = ?" ;
		return template.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class),rid) ;
	}

}
