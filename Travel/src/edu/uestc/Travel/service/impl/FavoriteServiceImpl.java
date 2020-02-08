package edu.uestc.Travel.service.impl;

import edu.uestc.Travel.dao.FavoriteDao;
import edu.uestc.Travel.dao.impl.FavoriteDaoImpl;
import edu.uestc.Travel.pojo.Favorite;
import edu.uestc.Travel.service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService {
	private FavoriteDao dao = new FavoriteDaoImpl() ;
	@Override
	public boolean isFavorite(String rid, int uid) {
		Favorite favorite = dao.findByRidAndUid(Integer.parseInt(rid), uid) ;
		
		return (favorite != null) ; //收藏了则为true
	}
	@Override
	public void addFavorite(String rid, int uid) {
		dao.add(Integer.parseInt(rid),uid) ;
	}

}
