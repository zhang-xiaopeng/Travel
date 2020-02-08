package edu.uestc.Travel.dao;

import java.util.List;

import edu.uestc.Travel.pojo.RouteImg;

public interface RouteImgDao {
	//根据线路rid查询图片
	public List<RouteImg> findByRid(int rid) ;
}
