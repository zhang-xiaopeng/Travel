package edu.uestc.Travel.service;

public interface FavoriteService {
	//判断是否收藏
	public boolean isFavorite(String rid,int uid) ;
	//添加收藏
	public void addFavorite(String rid, int uid);
}
