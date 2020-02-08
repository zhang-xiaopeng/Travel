package edu.uestc.Travel.service.impl;

import java.util.List;

import edu.uestc.Travel.dao.FavoriteDao;
import edu.uestc.Travel.dao.RouteDao;
import edu.uestc.Travel.dao.RouteImgDao;
import edu.uestc.Travel.dao.SellerDao;
import edu.uestc.Travel.dao.impl.FavoriteDaoImpl;
import edu.uestc.Travel.dao.impl.RouteDaoImpl;
import edu.uestc.Travel.dao.impl.RouteImgDaoImpl;
import edu.uestc.Travel.dao.impl.SellerDaoImpl;
import edu.uestc.Travel.pojo.PageBean;
import edu.uestc.Travel.pojo.Route;
import edu.uestc.Travel.pojo.RouteImg;
import edu.uestc.Travel.pojo.Seller;
import edu.uestc.Travel.service.RouteService;

public class RouteServiceImpl implements RouteService {

	private RouteDao routeDao = new RouteDaoImpl() ;
	private RouteImgDao routeImgDao = new RouteImgDaoImpl() ;
	private SellerDao sellerDao = new SellerDaoImpl() ;
	private FavoriteDao favoriteDao = new FavoriteDaoImpl() ;
	
	@Override
	public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,String rname) {
		//1.创建PageBean对象
		PageBean<Route> pb = new PageBean<Route>() ;
		
		//2.设置pageSize和currentPage
		pb.setPageSize(pageSize) ;
		pb.setCurrentPage(currentPage) ;
		
		//3.设置totalCount
		int totalCount = routeDao.findTotalCount(cid,rname) ;
		pb.setTotalCount(totalCount) ;
		
		//4.设置totalPage
		int totalPage = (totalCount % pageSize == 0) ? (totalCount / pageSize) : (totalCount / pageSize + 1) ;
		pb.setTotalPage(totalPage) ;
		
		//5.设置list
		int start = (currentPage - 1) * pageSize ;
		List<Route> list = routeDao.findByPage(cid, start, pageSize,rname) ;
		pb.setList(list) ;
		
		return pb ;
	}
	@Override
	public Route findOne(String rid) {
		//1.根据rid去route表何种查询route对象
		Route route = routeDao.findById(Integer.parseInt(rid)) ;
		//2.根据route的rid查询图片集合
		List<RouteImg> routeImgList = routeImgDao.findByRid(route.getRid()) ;
		//2.2将集合设置到route中
		route.setRouteImgList(routeImgList);
		//3.根据route的sid查询卖家信息
		Seller seller = sellerDao.findById(route.getSid()) ;
		route.setSeller(seller);
		
		//4.查询收藏次数
		int count = favoriteDao.findCountByRid(route.getRid()) ;
		route.setCount(count);
		
		return route ;
	}

}
