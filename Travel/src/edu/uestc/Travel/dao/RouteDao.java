package edu.uestc.Travel.dao;

import java.util.List;

import edu.uestc.Travel.pojo.Route;

public interface RouteDao {
	//根据cid查询总记录数
	public int findTotalCount(int cid, String rname) ;
	//根据cid,pageSize,start查询当前页的数据集合
	public List<Route> findByPage(int cid,int start,int pageSize, String rname) ;
	//根据rid查询线路详情信息
	public Route findById(int rid);
}
