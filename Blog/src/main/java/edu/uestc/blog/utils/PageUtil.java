package edu.uestc.blog.utils;
/**
 * 翻页工具类
 * @author 张霄鹏
 *
 */
public class PageUtil {
	/* <li><a href='/index.html?page=1&'>首页</a></li>
	 * <li class='disabled'><a href='#'>上一页</a></li>
	 * <li class='active'><a href='/index.html?page=1&'>1</a></li>
	 * <li class='disabled'><a href='#'>下一页</a></li>
	 * <li><a href='/index.html?page=1&'>尾页</a></li>
	 */
	/**
	 * 翻页方法
	 * @param targetUrl  提交地址
	 * @param totalCount 总数据条数
	 * @param currentPage 当前页
	 * @param pageSize   每页数据量
	 * @param param      查询参数
	 * @return
	 */
	public static String getPagination(String targetUrl,long totalCount,int currentPage,int pageSize,String param) {
		
		//1.计算总页数
		if(totalCount == 0) {
			return "未查询到数据" ;
		}
		long totalPage = (long)((totalCount % pageSize == 0)?(totalCount / pageSize):(totalCount / pageSize + 1)) ;
		
		//2.拼接字符串
		StringBuffer pageCode = new StringBuffer() ;
		//2.1拼上首页
		if(currentPage > 1) {
			//当前页不是第一页
			pageCode.append("<li><a href='" + targetUrl + "?page=1&" + param + "'>首页</a></li>") ;
		}else {
			//当前页是第一页
			pageCode.append("<li class='disabled'><a href='" + targetUrl + "?page=1&" + param + "'>首页</a></li>") ;
		}
		//2.2拼上上一页
		if(currentPage > 1) {
			//当前页不是第一页
			pageCode.append("<li><a href='"+ targetUrl +"?page="+ (currentPage-1) +"&"+ param +"'>上一页</a></li>") ;
		}else {
			//当前页是第一页
			pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>") ;
		}
		//2.3拼上当前页
		for(int i = 1 ; i <= totalPage ; i ++) {
			if(i == currentPage) {
				pageCode.append("<li class='active'><a href='"+ targetUrl +"?page="+ i +"&"+ param +"'>" + i + "</a></li>") ;
			}else {
				pageCode.append("<li><a href='"+ targetUrl +"?page="+ i +"&"+ param +"'>" + i + "</a></li>") ;

			}
		}
		//2.4拼上下一页
		if(currentPage < totalPage) {
			//当前页不是尾页
			pageCode.append("<li><a href='"+ targetUrl +"?page="+ (currentPage+1) +"&"+ param +"'>下一页</a></li>") ;
		}else {
			//当前页是尾页
			pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>") ;
		}
		//2.5拼上尾页
		if(currentPage < totalPage) {
			//当前页不是尾页
			pageCode.append("<li><a href='" + targetUrl + "?page="+ totalPage +"&" + param + "'>尾页</a></li>") ;
		}else {
			//当前页是尾页
			pageCode.append("<li class='disabled'><a href='" + targetUrl + "?page="+ totalPage +"&" + param + "'>尾页</a></li>") ;
		}
		return pageCode.toString() ;
	}
}
