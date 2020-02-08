package edu.uestc.Travel.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uestc.Travel.pojo.PageBean;
import edu.uestc.Travel.pojo.Route;
import edu.uestc.Travel.pojo.User;
import edu.uestc.Travel.service.FavoriteService;
import edu.uestc.Travel.service.RouteService;
import edu.uestc.Travel.service.impl.FavoriteServiceImpl;
import edu.uestc.Travel.service.impl.RouteServiceImpl;


@SuppressWarnings("serial")
@WebServlet("/route/*")
public class RouteServlet extends BaseServlet{
	private RouteService service = new RouteServiceImpl() ;
	private FavoriteService favoriteService = new FavoriteServiceImpl() ;
	/*
	 * 分页显示（模糊查询）
	 */
	public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.接收参数
		String pageSizeStr = request.getParameter("pageSize") ;
		String currentPageStr = request.getParameter("currentPage") ;
		String cidStr = request.getParameter("cid") ;
		
		//接收rname路线名称
		String rname = request.getParameter("rname") ;
		if(rname == null || rname.length() == 0) {
			rname = "" ;
		}
		
		//2.处理参数
		int pageSize = 0 ;
		if(pageSizeStr == null || pageSizeStr.length() == 0) {
			pageSize = 5 ;
		}else {
			pageSize = Integer.parseInt(pageSizeStr) ;
		}
		int currentPage = 0 ;
		if(currentPageStr == null || currentPageStr.length() == 0) {
			currentPage = 1 ;
		}else {
			currentPage = Integer.parseInt(currentPageStr) ;
		}
		int cid = 0 ;
		if(cidStr == null || cidStr.length() == 0 || "null".equals(cidStr)) {
		}else {
			cid = Integer.parseInt(cidStr) ;
		}
		
		//3.调用Service方法查询
		PageBean<Route> pageBean = service.pageQuery(cid, currentPage, pageSize,rname) ;
		
		//4.将PageBean对象序列化json返回
		writeValue(pageBean, response);
	}

	/*
	 * 根据一个id查询一个旅游线路的详细信息
	 */
	public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.接收id
		String rid = request.getParameter("rid") ;
		//2.调用service查询route对象
		Route route = service.findOne(rid) ;
		//3.转为写回客户端
		writeValue(route,response) ;
	}
	/*
	 * 判断当前登录用户是否收藏过该路线
	 */
	public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取线路rid
		String rid = request.getParameter("rid") ;
		
		//2.获取当前登录的用户
		User user = (User) request.getSession().getAttribute("user") ;
		int uid ;
		if(user == null) {
			//用户尚未登录
			uid = 0 ;
		}else {
			//用户已经登录
			uid = user.getUid() ;
		}
		
		//3.调用FavorateService查询是否收藏
		boolean flag = favoriteService.isFavorite(rid, uid) ;
		
		//4.将flag写回客户端
		writeValue(flag,response) ;
	}
	/*
	 * 添加收藏
	 */
	public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取线路rid
		String rid = request.getParameter("rid") ;
		//2.获取当前登录的用户
		User user = (User) request.getSession().getAttribute("user") ;
		int uid ;
		if(user == null) {
			//用户尚未登录
			return ;
		}else {
			//用户已经登录
			uid = user.getUid() ;
		}
		
		//3.调用FavorateService查询添加收藏
		favoriteService.addFavorite(rid, uid) ;
	
	}

}
