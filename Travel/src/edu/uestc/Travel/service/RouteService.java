package edu.uestc.Travel.service;

import edu.uestc.Travel.pojo.PageBean;
import edu.uestc.Travel.pojo.Route;

public interface RouteService {
	//模糊查询并分页展示
	public PageBean<Route> pageQuery(int cid,int currentPage,int pageSize, String rname) ;
	//根据id查询详情列表
	public Route findOne(String rid); 
}
