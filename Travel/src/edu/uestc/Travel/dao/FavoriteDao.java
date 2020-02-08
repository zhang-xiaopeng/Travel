package edu.uestc.Travel.dao;

import edu.uestc.Travel.pojo.Favorite;

public interface FavoriteDao {
	//根据rid和uid查询收藏信息
	public Favorite findByRidAndUid(int rid,int uid) ;
	//根据rid查询收藏次数
	public int findCountByRid(int rid);
	//添加收藏
	public void add(int rid, int uid);
}
